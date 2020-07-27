package com.project.modules.deal.service;

/**
 * 移动端登录Service
 *
 * @author liangyuding
 * @date 2020-05-14
 */
public interface DealUserLoginService {

    /**
     * 客户微信登录
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
