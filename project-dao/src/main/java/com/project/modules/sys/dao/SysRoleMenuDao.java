package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色与系统菜单关系Dao
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 根据系统角色ID获取系统菜单
     * @param roleId
     * @return
     */
    List<Long> getMenuIdByRoleId(@Param("roleId") Long roleId);
}
