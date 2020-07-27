package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.modules.sys.dao.SysRoleDeptDao;
import com.project.modules.sys.entity.SysRoleDeptEntity;
import com.project.modules.sys.service.SysCheckInvokingService;
import com.project.modules.sys.service.SysRoleDeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统部门与系统角色关系Service
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Slf4j
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDeptEntity> implements SysRoleDeptService {

    @Autowired
    private SysCheckInvokingService sysCheckInvokingService;

    /**
     * 保存系统角色与系统部门关系
     * @param roleId
     * @param deptIdList
     */
    @Override
    @Transactional
    public void saveSysDeptRole(Long roleId, List<Long> deptIdList) {
        SysRoleDeptEntity sysRoleDeptEntity = new SysRoleDeptEntity();
        deptIdList.forEach(deptId -> {
            sysCheckInvokingService.checkDeptIdNotNull(deptId);
            sysRoleDeptEntity.setId(null).setDeptId(deptId).setRoleId(roleId);
            save(sysRoleDeptEntity);
        });
    }

    /**
     * 删除系统角色与系统部门关系
     * @param roleId
     */
    @Override
    @Transactional
    public void deleteSysRoleDeptByRoleId(Long roleId) {
        remove(new QueryWrapper<SysRoleDeptEntity>().eq("role_id", roleId));
    }

    /**
     * 更新系统角色与系统部门关系
     * @param roleId
     * @param deptIdList
     */
    @Override
    @Transactional
    public void updateSysDeptRole(Long roleId, List<Long> deptIdList) {
        deleteSysRoleDeptByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(deptIdList)){
            saveSysDeptRole(roleId, deptIdList);
        }
    }

    /**
     * 根据系统角色ID获取系统部门
     * @param roleId
     * @return
     */
    @Override
    public List<Long> getDeptIdByRoleId(Long roleId) {
        return baseMapper.getDeptIdByRoleId(roleId);
    }

    /**
     * 删除系统部门与系统角色关系
     * @param deptId
     */
    @Override
    @Transactional
    public void deleteSysRoleDeptByDeptId(Long deptId) {
        remove(new QueryWrapper<SysRoleDeptEntity>().eq("dept_id", deptId));
    }
}
