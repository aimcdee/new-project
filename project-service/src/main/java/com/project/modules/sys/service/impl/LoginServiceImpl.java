package com.project.modules.sys.service.impl;

import com.project.constant.Constant;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.modules.sys.service.LoginService;
import com.project.modules.sys.service.SysUserService;
import com.project.modules.sys.vo.login.LoginUserVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * 登录控制器Service
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Slf4j
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CheckUtils checkUtils;

    /**
     * 登录
     * @param phone
     * @param password
     * @return
     */
    @Override
    public LoginUserVo login(String phone, String password, HttpServletRequest request) {
        //根据手机号码查询用户信息
        SysUserEntity user = sysUserService.queryByPhone(phone);
        //校验登录账号和密码
        checkUtils.checkLoginUser(user, password);
        // 获取token
        String token = JjwtUtils.createToken(user.getUserId(), user.getUserName(), user.getPhone());
        // 缓存token
        LoginUserVo loginUserVo = getLoginUserVo(token, user, request);
        redisUtils.set(RedisKeys.SysLogin.Login(user.getUserId()), JsonUtil.toJson(loginUserVo), Constant.LOGIN_EXPIRES);
        // 更新最近登录IP地址
        sysUserService.updateLoginIp(loginUserVo.getUserId(), loginUserVo.getLoginIp(), loginUserVo.getLoginTime());
        return loginUserVo;
    }

    /**
     * 退出登录
     * @param userId
     */
    @Override
    public void logout(Long userId) {
        redisUtils.delete(RedisKeys.SysLogin.Login(userId));
    }

    //设置LoginUserVo登录对象
    private LoginUserVo getLoginUserVo(String token, SysUserEntity sysUserEntity, HttpServletRequest request) {
        LoginUserVo userVo = new LoginUserVo();
        userVo.setToken(token)
                // 用户ID
                .setUserId(sysUserEntity.getUserId())
                // 用户姓名
                .setUserName(sysUserEntity.getUserName())
                //手机号码
                .setPhone(sysUserEntity.getPhone())
                //用户状态
                .setStatus(sysUserEntity.getStatus())
                // token到期时间
                .setExpireTime(JjwtUtils.getExpiration(token))
                //登录IP
                .setLoginIp(IPUtils.getIpAddr(request))
                // 登录时间
                .setLoginTime(JjwtUtils.getIssuedAt(token));
        return userVo;
    }
}
