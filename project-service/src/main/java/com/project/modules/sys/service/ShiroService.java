package com.project.modules.sys.service;

import com.project.modules.sys.vo.login.LoginUserVo;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author liangyuding
 * @date 2020-03-18
 */
public interface ShiroService {

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 根据accessToken，查询用户信息
     * @param accessToken
     * @return
     */
    LoginUserVo queryByToken(String accessToken);

    /**
     * 获取用户角色列表
     */
    Set<String> getUserRole(long userId);
}
