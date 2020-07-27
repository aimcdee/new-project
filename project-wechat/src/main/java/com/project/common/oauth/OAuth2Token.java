package com.project.common.oauth;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author liangyuding
 * @date 2020-04-07
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
