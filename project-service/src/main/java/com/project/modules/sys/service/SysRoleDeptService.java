package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * 系统部门与系统角色关系Service
 *
 * @author liangyuding
 * @date 2020-03-17
 */
public interface SysRoleDeptService extends IService<SysRoleDeptEntity> {

    /**
     * 保存系统角色与系统部门关系
     * @param roleId
     * @param deptIdList
     */
    void saveSysDeptRole(Long roleId, List<Long> deptIdList);

    /**
     * 删除系统角色与系统部门关系
     * @param roleId
     */
    void deleteSysRoleDeptByRoleId(Long roleId);

    /**
     * 更新系统角色与系统部门关系
     * @param roleId
     * @param deptIdList
     */
    void updateSysDeptRole(Long roleId, List<Long> deptIdList);

    /**
     * 根据系统角色ID获取系统部门
     * @param roleId
     * @return
     */
    List<Long> getDeptIdByRoleId(Long roleId);

    /**
     * 删除系统部门与系统角色关系
     * @param deptId
     */
    void deleteSysRoleDeptByDeptId(Long deptId);
}
