package com.project.modules.sys.oauth2;

import com.google.gson.reflect.TypeToken;
import com.project.modules.sys.service.ShiroService;
import com.project.modules.sys.vo.login.LoginUserVo;
import com.project.utils.JsonUtil;
import com.project.utils.RedisKeys;
import com.project.utils.RedisUtils;
import com.project.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 认证
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Slf4j
@Component
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof SysAuthcToken;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LoginUserVo user = (LoginUserVo) principals.getPrimaryPrincipal();
        log.info("登陆的对象:{}" + user);
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet;
        String permJson = redisUtils.get(RedisKeys.SysLogin.Perm(userId));
        if (StringUtils.isNotEmpty(permJson)) {
            permsSet = JsonUtil.fromJson(permJson, new TypeToken<Set<String>>() {
            }.getType());
        } else {
            permsSet = shiroService.getUserPermissions(userId);
            //权限缓存600秒
            redisUtils.set(RedisKeys.SysLogin.Perm(userId), permsSet, 600);
        }

        // 用户角色列表
        Set<String> roleSet;
        String roleJson = redisUtils.get(RedisKeys.SysLogin.Part(userId));
        if (StringUtils.isNotEmpty(roleJson)) {
            roleSet = JsonUtil.fromJson(roleJson, new TypeToken<Set<String>>() {
            }.getType());
        } else {
            roleSet = shiroService.getUserRole(userId);
            //权限缓存600秒
            redisUtils.set(RedisKeys.SysLogin.Part(userId), roleSet, 600);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        info.setRoles(roleSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("登陆的token:{}" + token);
        SysAuthcToken statelessToken = (SysAuthcToken) token;
        // 签证信息
        LoginUserVo userVo = statelessToken.getLoginUser();
        // 使用缓存中的userToken和当前验证token进行对比
        return new SimpleAuthenticationInfo(userVo, userVo.getToken(), getName());
    }
}
