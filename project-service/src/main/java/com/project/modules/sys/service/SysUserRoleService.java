package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 系统用户与系统角色关系Service
 *
 * @author liangyuding
 * @date 2020-03-10
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    /**
     * 新增系统用户与系统角色关系
     * @param userId
     * @param roleIdList
     */
    void saveUserRole(Long userId, List<Long> roleIdList);

    /**
     * 根据系统用户ID查询该用户的系统角色关系
     * @param userId
     * @return
     */
    List<Long> getRoleIdListByUserId(Long userId);

    /**
     * 更新系统用户与系统角色关系
     * @param userId
     * @param roleIdList
     */
    void updateUserRole(Long userId, List<Long> roleIdList);

}
