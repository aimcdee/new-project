package com.project.service.login.impl;

import com.project.service.login.WxLoginDealUserService;
import com.project.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信调用异常处理层
 *
 * @author liangyuding
 * @date 2020-04-07
 */
@Slf4j
@Service
public class WxLoginDealUserServiceImpl implements WxLoginDealUserService {

    /**
     * 客户微信端手机号码登录
     * @param phone
     * @return
     */
    @Override
    public R wxSmsLogin(String phone) {
        log.error("调用{}异常:{}, phone:{}", "客户微信端手机号码登录", phone);
        return null;
    }

    /**
     * 客户微信端授权登录
     * @param phone
     * @return
     */
    @Override
    public R wxLogin(String phone) {
        log.error("调用{}异常:{}, phone:{}", "客户微信端授权登录", phone);
        return null;
    }

    /**
     * 退出登录
     * @param userId
     * @return
     */
    @Override
    public R logout(Long userId) {
        log.error("调用{}异常:{}", "退出登录");
        return null;
    }

}
