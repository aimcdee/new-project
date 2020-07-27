package com.project.common.oauth;

import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.modules.deal.vo.login.DealUserLoginVo;
import com.project.utils.RedisKeys;
import com.project.utils.RedisUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 认证
 *
 * @author liangyuding
 * @date 2020-04-07
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        //根据accessToken，查询用户信息
        Long dealUserId = redisUtils.get(RedisKeys.Wx.WxLoginToken(accessToken), Long.class, Constant.LOGIN_EXPIRES);
        //token失效
        if (dealUserId == null) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        String wxDealIdKey = RedisKeys.Wx.WxLogin(new StringBuilder().append(RedisKeyConstant.WX_LOGIN).append("_").append(dealUserId).toString());
        DealUserLoginVo dealUserLoginVo = redisUtils.get(wxDealIdKey, DealUserLoginVo.class, Constant.LOGIN_EXPIRES);
        if (dealUserLoginVo == null || dealUserLoginVo.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(dealUserLoginVo, accessToken, getName());
        return info;
    }
}
