package com.project.modules.sys.service;

/**
 * 中间调用校验Service
 *
 * @author liangyuding
 * @date 2020-03-17
 */
public interface SysCheckInvokingService {

    /**
     * 根据菜单ID查询菜单是否存在
     * @param menuId
     */
    void checkMenuIdNotNull(Long menuId);

    /**
     * 根据部门ID查询部门是否存在
     * @param deptId
     */
    void checkDeptIdNotNull(Long deptId);

    /**
     * 校验该角色是否被用户使用
     * @param roleId
     */
    void checkRoleIdIsUse(Long roleId);

    /**
     * 校验该部门是否被使用
     * @param deptId
     */
    void checkDeptIdIsUse(Long deptId);

    /**
     * 根据系统配置Id和系统配置编码查询系统设置是否存在
     *
     * @param infoId
     * @param code
     * @return
     */
    Integer getSysConfigEntityByInfoIdAndCode(Long infoId, String code);
}
