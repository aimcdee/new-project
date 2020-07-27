package com.project.utils;

import com.alibaba.fastjson.JSONObject;
import com.project.exception.RRException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
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
     * 获取微信手机号码
     *
     * @param encryptedData
     * @param iv
     * @param jsCode
     * @return 手机号码
     */
    public String getProjectPhone(final String encryptedData, final String iv, final String jsCode) {
        return getPhone(encryptedData, iv, jsCode, WeChatKeyEnum.project);
    }

    /**
     * 获取微信手机号码
     *
     * @param encryptedData
     * @param iv
     * @param jsCode
     * @return 手机号码
     */
    private String getPhone(final String encryptedData, final String iv, final String jsCode, WeChatKeyEnum weChatKeyEnum) {
        String session_key = getSessionKey(jsCode, weChatKeyEnum);
        String purePhoneNumber = getPurePhoneNumber(encryptedData, session_key, iv);
        return purePhoneNumber;
    }

    /**
     * 解析手机号码
     *
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
     *
     * @param jsCode
     * @param weChatKeyEnum
     * @return
     */
    private String getSessionKey(final String jsCode, final WeChatKeyEnum weChatKeyEnum) {
        String url = getUrl(jsCode, weChatKeyEnum);
        log.info("weixin getSessionKey url:{}", url);
        String response = HttpClientUtil.doGet(url);
        log.info("weixin getSessionKey response:{}", response);
        JSONObject jsonObject = JSONObject.parseObject(response);
        //请求成功
        if (!jsonObject.containsKey("errcode")) {
//            String openid = jsonObject.getString("openid");
            String session_key = jsonObject.getString("session_key");
            return session_key;
        } else {
            log.error("weixin getSessionKey error!url:{},respone:{}", url, response);
            throw new RRException("微信登录异常!");
        }
    }

    /**
     * 微信登录地址
     *
     * @param jsCode
     * @param weChatKeyEnum
     * @return
     */
    private String getUrl(String jsCode, WeChatKeyEnum weChatKeyEnum) {
        return "https://api.weixin.qq.com/sns/jscode2session?appid=" + weChatKeyEnum.getAppId() +
            "&secret=" + weChatKeyEnum.getAppSecret() +
            "&js_code=" + jsCode + "&grant_type=authorization_code";
    }

    enum WeChatKeyEnum {
        project("wxa9b239bd2967585f", "6dc8c23b61171c38f4b661b59ad56fbf"),
        ;

        private String appId;
        private String appSecret;

        public String getAppId() {
            return appId;
        }

        public String getAppSecret() {
            return appSecret;
        }

        WeChatKeyEnum(String appId, String appSecret) {
            this.appId = appId;
            this.appSecret = appSecret;
        }
    }

}
