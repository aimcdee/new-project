package com.project.controller.login;

import com.project.exception.RRException;
import com.project.service.login.WxLoginDealUserService;
import com.project.utils.R;
import com.project.utils.WeChatLoginUtils;
import com.project.vo.login.SmsUserLoginVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.project.common.utils.ShiroUtils.getDealUserId;

/**
 * 微信端登录登录
 *
 * @author liangyuding
 * @date 2020-03-30
 */

@Slf4j
@RestController
@RequestMapping("/wechat")
@Api(tags = "微信端登录接口", description = "WxLoginController")
public class WxDealUserLoginController {

    @Autowired
    private WxLoginDealUserService wxLoginDealUserService;

    @Autowired
    private WeChatLoginUtils weChatLoginUtils;

    /**
     * 短信验证码登录
     */
    @PostMapping("/smsLogin")
    public R smsLogin(@RequestBody SmsUserLoginVo form) {
        //表单校验
//        ValidatorUtils.validateEntity(form);
//        if (!"000000".equals(form.getSmsCode()) && !sendSmsService.checkSmsCode(form.getPhone(), form.getSmsCode())) {
//            throw new RRException("短信验证码错误!");
//        }
        if (StringUtils.trim(form.getPhone()) == null) {
            throw new RRException("手机号码不能为空!");
        }
        if (StringUtils.trim(form.getSmsCode()) == null) {
            throw new RRException("请输入验证码!");
        }
//        return wxLoginDealUserService.wxLogin(form.getPhone());
        return wxLoginDealUserService.wxLogin("13422356022");
    }

    /**
     * 微信登录
     *
     * @param params
     * @return
     */
    @PostMapping("/wxLogin")
    public R wxLogin(@RequestBody Map<String, Object> params) {
        //包括敏感数据在内的完整用户信息的加密数据
        String encryptedData = MapUtils.getString(params, "encryptedData");
        //加密算法的初始向量
        String iv = MapUtils.getString(params, "iv");
        //调用接口获取登录凭证（code）
        String jscode = MapUtils.getString(params, "jscode");
        log.info("weChatlogin params encryptedData:{},iv:{},jscode:{}", encryptedData, iv, jscode);
        // 非空校验
        if (StringUtils.isBlank(encryptedData) || StringUtils.isBlank(iv) || StringUtils.isBlank(jscode)) {
            throw new RRException("参数encryptedData、iv、jscode不能为空！");
        }
        //解密获取微信授权登录的手机号码
        String phone = weChatLoginUtils.getProjectPhone(encryptedData, iv, jscode);
        return wxLoginDealUserService.wxLogin(phone);
    }

    /**
     * 退出登陆
     */
    @PostMapping("/logout")
    public R logout() {
        return wxLoginDealUserService.logout(getDealUserId());
    }

}
