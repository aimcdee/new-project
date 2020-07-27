package com.project.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 中间调用Dao
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Mapper
public interface SysCheckInvokingDao {

    /**
     * 根据菜单ID查询菜单是否存在
     * @param menuId
     * @return
     */
    Integer checkMenuIdNotNull(@Param("menuId") Long menuId);

    /**
     * 根据部门ID查询部门是否存在
     * @param deptId
     * @return
     */
    Integer checkDeptIdNotNull(@Param("deptId") Long deptId);

    /**
     * 校验该角色是否被用户使用
     * @param roleId
     * @return
     */
    Integer checkRoleIdUseForUser(@Param("roleId") Long roleId);

    /**
     * 校验该部门是否被用户使用
     * @param deptId
     * @return
     */
    Integer checkDeptIdUseForUser(@Param("deptId") Long deptId);

    /**
     * 根据系统配置Id和系统配置编码查询系统设置是否存在
     * @param infoId
     * @param code
     * @return
     */
    Integer getSysConfigEntityByInfoIdAndCode(@Param("infoId") Long infoId, @Param("code") String code);
}
