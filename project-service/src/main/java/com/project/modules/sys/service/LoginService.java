package com.project.modules.sys.service;

import com.project.modules.sys.vo.login.LoginUserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器Service
 *
 * @author liangyuding
 * @date 2020-03-18
 */
public interface LoginService {

    /**
     * 登录
     * @param phone
     * @param password
     * @param request
     * @return
     */
    LoginUserVo login(String phone, String password, HttpServletRequest request);

    /**
     * 退出登录
     * @param userId
     */
    void logout(Long userId);
}
