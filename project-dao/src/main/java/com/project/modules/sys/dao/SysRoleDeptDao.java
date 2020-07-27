package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.sys.entity.SysRoleDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统部门与系统角色关系Dao
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Mapper
public interface SysRoleDeptDao extends BaseMapper<SysRoleDeptEntity> {

    /**
     * 根据系统角色ID获取系统部门
     * @param roleId
     * @return
     */
    List<Long> getDeptIdByRoleId(@Param("roleId") Long roleId);
}
