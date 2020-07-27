package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysUserDeptEntity;

/**
 * 系统用户与系统部门关系Service
 *
 * @author liangyuding
 * @date 2020-03-10
 */
public interface SysUserDeptService extends IService<SysUserDeptEntity> {

    /**
     * 新增系统用户与系统部门关系
     * @param userId
     * @param deptId
     */
    void saveUserDept(Long userId, Long deptId);

    /**
     * 更新用户与系统部门的关系
     * @param userId
     * @param deptId
     */
    void updateUserDept(Long userId, Long deptId);
}
