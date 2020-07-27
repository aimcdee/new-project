package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.modules.sys.dao.SysUserRoleDao;
import com.project.modules.sys.entity.SysUserRoleEntity;
import com.project.modules.sys.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统用户与系统角色关系Service
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Slf4j
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    /**
     * 新增系统用户与系统角色关系
     * @param userId
     * @param roleIdList
     */
    @Override
    public void saveUserRole(Long userId, List<Long> roleIdList) {
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        roleIdList.forEach(roleId ->{
            sysUserRoleEntity
                    .setId(null)
                    .setUserId(userId)
                    .setRoleId(roleId);
            save(sysUserRoleEntity);
        });
    }

    /**
     * 根据系统用户ID查询该用户的系统角色关系
     * @param userId
     * @return
     */
    @Override
    public List<Long> getRoleIdListByUserId(Long userId) {
        return baseMapper.getRoleIdListByUserId(userId);
    }

    /**
     * 更新系统用户与系统角色关系
     * @param userId
     * @param roleIdList
     */
    @Override
    public void updateUserRole(Long userId, List<Long> roleIdList) {
        if (!Constant.SUPER_ADMIN.equals(userId) && !Constant.SUPER_ADMIN_STRING.equals(userId)){
            remove(new QueryWrapper<SysUserRoleEntity>().eq("user_id", userId));
            saveUserRole(userId, roleIdList);
        }
    }

}
