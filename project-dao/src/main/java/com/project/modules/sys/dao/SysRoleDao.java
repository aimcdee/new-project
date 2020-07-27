package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.sys.entity.SysRoleEntity;
import com.project.modules.sys.vo.info.SysRoleInfoVo;
import com.project.modules.sys.vo.invoking.SysRoleInvokingVo;
import com.project.modules.sys.vo.list.SysRoleListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色Dao
 *
 * @author liangyuding
 * @data 2020-03-10
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
     * 校验角色名称是否已存在
     * @param roleName
     * @param roleId
     * @return
     */
    Integer checkNameRepeat(@Param("roleName") String roleName, @Param("roleId") Long roleId);

    /**
     * 分页查询系统角色列表
     * @param page
     * @param roleName
     * @param status
     * @return
     */
    List<SysRoleListVo> queryByPage(
            Page<SysRoleListVo> page,
            @Param("roleName") String roleName,
            @Param("status") Integer status);

    /**
     * 根据系统用户ID查询该用户的系统角色
     * @param userId
     * @return
     */
    List<SysRoleInvokingVo> getRoleListByUserId(@Param("userId") Long userId);

    /**
     * 根据系统角色ID查询角色详情
     * @param roleId
     * @return
     */
    SysRoleInfoVo getSysRoleInfoVo(@Param("roleId") Long roleId);
}
