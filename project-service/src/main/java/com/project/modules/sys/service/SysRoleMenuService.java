package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 系统角色与系统菜单关系Service
 *
 * @author liangyuding
 * @date 2020-03-17
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * 保存系统角色与系统菜单关系
     * @param roleId
     * @param menuIdList
     */
    void saveSysRoleMenu(Long roleId, List<Long> menuIdList);

    /**
     * 删除系统角色与系统菜单关系
     * @param roleId
     */
    void deleteSysRoleMenuByRoleId(Long roleId);

    /**
     * 更新系统角色与系统菜单关系
     * @param roleId
     * @param menuIdList
     */
    void updateSysRoleMenu(Long roleId, List<Long> menuIdList);

    /**
     * 根据系统角色ID获取系统菜单
     * @param roleId
     * @return
     */
    List<Long> getMenuIdByRoleId(Long roleId);
}
