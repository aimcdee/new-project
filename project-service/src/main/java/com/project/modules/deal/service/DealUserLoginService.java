package com.project.modules.deal.service;

/**
 * 移动端登录Service
 *
 * @author liangyuding
 * @date 2020-05-14
 */
public interface DealUserLoginService {

    /**
     * 微信端手机号码登录
     * @param phone
     * @return
     */
    String wxSmsDealUserlogin(String phone);

    /**
     * 微信端授权登录
     * @param phone
     */
    String wxDealUserlogin(String phone);

    /**
     * 退出登录
     * @param userId
     * @return
     */
    void logout(Long userId);
}
