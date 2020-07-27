package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单Dao
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 分页查询系统菜单
     * @param page
     * @return
     */
    List<SysMenuEntity> queryPage(Page<SysMenuEntity> page);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(@Param("parentId") Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     * @return
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取父类名称
     * @param parentId
     * @return
     */
    String getParentName(@Param("parentId") Long parentId);
}
