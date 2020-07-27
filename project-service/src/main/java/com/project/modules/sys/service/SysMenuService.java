package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysMenuEntity;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单Service
 *
 * @author liangyuding
 * @date 2020-03-18
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 分页查询菜单列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取用户菜单列表
     * @param sysUserId
     * @return
     */
    List<SysMenuEntity> getUserMenuList(Long sysUserId);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 保存系统菜单
     * @param menu
     */
    void saveSysMenuEntity(SysMenuEntity menu);

    /**
     * 修改系统菜单
     * @param menu
     */
    void updateSysMenuEntity(SysMenuEntity menu);

    /**
     * 删除系统菜单
     * @param menuId
     */
    void delete(long menuId);

    /**
     * 系统菜单信息
     * @param menuId
     * @return
     */
    SysMenuEntity getSysMenuEntity(Long menuId);
}
