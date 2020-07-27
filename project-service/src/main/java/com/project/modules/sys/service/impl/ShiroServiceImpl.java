package com.project.modules.sys.service.impl;

import com.project.constant.Constant;
import com.project.modules.sys.dao.ShiroDao;
import com.project.modules.sys.dao.SysMenuDao;
import com.project.modules.sys.dao.SysUserDao;
import com.project.modules.sys.entity.SysMenuEntity;
import com.project.modules.sys.service.ShiroService;
import com.project.modules.sys.vo.login.LoginUserVo;
import com.project.utils.JjwtUtils;
import com.project.utils.RedisKeys;
import com.project.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private ShiroDao shiroDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN || userId == Constant.SUPER_ADMIN_STRING) {
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    /**
     * 根据accessToken，查询用户信息
     * @param token
     * @return
     */
    @Override
    public LoginUserVo queryByToken(String token) {
        return redisUtils.get(RedisKeys.SysLogin.Login(JjwtUtils.getUserId(token)), LoginUserVo.class);
    }

    /**
     * 获取用户角色列表
     */
    @Override
    public Set<String> getUserRole(long userId) {
        List<String> roleList = shiroDao.findAllRoleByUserId(userId);
        // Shiro要求角色列字符串不能为null或空
        return roleList.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
    }
}
