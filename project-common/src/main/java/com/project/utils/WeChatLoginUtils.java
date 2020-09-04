package com.project.utils;

import com.alibaba.fastjson.JSONObject;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.Map;

/**
 * 描述:
 * 微信登录方法
 *
 * @author liangyuding
 * @date 2020-03-30
 */
@Component
public class WeChatLoginUtils {
    private static Logger log = LoggerFactory.getLogger(WeChatLoginUtils.class);

    /**
     * 获取微信授权登录手机号码
     * @param params
     * @return
     */
    public String getLoginPhone(Map<String, Object> params) {
        //包括敏感数据在内的完整用户信息的加密数据
        String encryptedData = MapUtils.getString(params, "encryptedData");
        //加密算法的初始向量
        String iv = MapUtils.getString(params, "iv");
        //调用接口获取登录凭证（code）
        String code = MapUtils.getString(params, "code");
        log.info("登录加密敏感数据:{}", encryptedData);
        log.info("iv码:{}", iv);
        log.info("code码:{}", code);
        // 非空校验
        if (StringUtils.isBlank(encryptedData) || StringUtils.isBlank(iv) || StringUtils.isBlank(code)) {
            throw new RRException("参数encryptedData、iv、code不能为空！");
        }

        //解密获取微信授权登录的手机号码
        String phone = getPhone(encryptedData, iv, code, Constant.WeChatKeyEnum.WECHATKEY);

        log.info("手机号码:{}", phone);
        if (StringUtils.isBlank(phone)){
            throw new RRException("获取手机号码失败");
        }
        return phone;
    }

    /**
     * 获取微信手机号码
     * @param encryptedData
     * @param iv
     * @param code
     * @return 手机号码
     */
    private String getPhone(final String encryptedData, final String iv, final String code, Constant.WeChatKeyEnum weChatKeyEnum) {
        return getPurePhoneNumber(encryptedData, getSessionKey(code, weChatKeyEnum), iv);
    }

    /**
     * 解析手机号码
     * @param encryptedData
     * @param sessionkey
     * @param iv
     * @return
     */
    private String getPurePhoneNumber(final String encryptedData, final String sessionkey, final String iv) {
        log.info("getPurePhoneNumber params encryptedData:{},sessionkey:{},iv:{}", encryptedData, sessionkey, iv);
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                log.info("getPurePhoneNumber result:{}", result);
                JSONObject jsonObject = JSONObject.parseObject(result);
                if (jsonObject != null && jsonObject.containsKey("purePhoneNumber")) {
                    return jsonObject.getString("purePhoneNumber");
                }
            }
        } catch (Exception e) {
            log.error("get weChat getPurePhoneNumber error:{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取微信session_key
     * @param code
     * @param weChatKeyEnum
     * @return
     */
    private String getSessionKey(final String code, final Constant.WeChatKeyEnum weChatKeyEnum) {
        String url = getUrl(code, weChatKeyEnum);
        log.info("URL路径:{}", url);
        String response = HttpClientUtil.doGet(url);
        log.info("SessionKey值:{}", response);
        JSONObject jsonObject = JSONObject.parseObject(response);
        //请求成功
        if (!jsonObject.containsKey("errcode")) {
            String session_key = jsonObject.getString("session_key");
            return session_key;
        } else {
            log.error("微信错误URL路径:{}", url);
            log.error("异常response:{}", response);
            throw new RRException("微信登录异常!");
        }
    }

    /**
     * 微信登录地址
     * @param jsCode
     * @param weChatKeyEnum
     * @return
     */
    private String getUrl(String jsCode, Constant.WeChatKeyEnum weChatKeyEnum) {
        String url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?appid=")
                .append(weChatKeyEnum.getAppId()).append("&secret=").append(weChatKeyEnum.getAppSecret())
                .append("&js_code=").append(jsCode).append("&grant_type=authorization_code").toString();
        return url;
    }
}
