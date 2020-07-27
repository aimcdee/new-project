package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.modules.sys.dao.SysRoleMenuDao;
import com.project.modules.sys.entity.SysRoleMenuEntity;
import com.project.modules.sys.service.SysCheckInvokingService;
import com.project.modules.sys.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统角色与系统菜单关系Service
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Slf4j
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    @Autowired
    private SysCheckInvokingService sysCheckInvokingService;

    /**
     * 保存系统角色与系统菜单关系
     * @param roleId
     * @param menuIdList
     */
    @Override
    @Transactional
    public void saveSysRoleMenu(Long roleId, List<Long> menuIdList) {
        SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
        menuIdList.forEach(menuId -> {
            sysCheckInvokingService.checkMenuIdNotNull(menuId);
            sysRoleMenuEntity.setId(null).setRoleId(roleId).setMenuId(menuId);
            save(sysRoleMenuEntity);
        });
    }

    /**
     * 删除系统角色与系统菜单关系
     * @param roleId
     */
    @Override
    @Transactional
    public void deleteSysRoleMenuByRoleId(Long roleId) {
        remove(new QueryWrapper<SysRoleMenuEntity>().eq("role_id", roleId));
    }

    /**
     * 更新系统角色与系统菜单关系
     * @param roleId
     * @param menuIdList
     */
    @Override
    @Transactional
    public void updateSysRoleMenu(Long roleId, List<Long> menuIdList) {
        //删除系统角色与系统菜单关系
        deleteSysRoleMenuByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(menuIdList)){
            //保存系统角色与系统菜单关系
            saveSysRoleMenu(roleId, menuIdList);
        }
    }

    /**
     * 根据系统角色ID获取系统菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Long> getMenuIdByRoleId(Long roleId) {
        return baseMapper.getMenuIdByRoleId(roleId);
    }
}
