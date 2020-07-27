package com.project.modules.sys.oauth2;

import com.project.modules.sys.vo.login.LoginUserVo;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author liangyuding
 * @date 2020-03-18
 */
public class SysAuthcToken implements AuthenticationToken {

    private Long userId;
    private String userToken;
    private LoginUserVo currentUser;

    public SysAuthcToken(Long userId, String userToken, LoginUserVo currentUser) {
        this.userId = userId;
        this.userToken = userToken;
        this.currentUser = currentUser;
    }

    public LoginUserVo getLoginUser() {
        return currentUser;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return userToken;
    }
}
