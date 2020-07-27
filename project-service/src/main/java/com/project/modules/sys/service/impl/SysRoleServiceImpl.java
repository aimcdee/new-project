package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.sys.dao.SysRoleDao;
import com.project.modules.sys.entity.SysRoleEntity;
import com.project.modules.sys.service.SysCheckInvokingService;
import com.project.modules.sys.service.SysRoleDeptService;
import com.project.modules.sys.service.SysRoleMenuService;
import com.project.modules.sys.service.SysRoleService;
import com.project.modules.sys.vo.info.SysRoleInfoVo;
import com.project.modules.sys.vo.invoking.SysRoleInvokingVo;
import com.project.modules.sys.vo.list.SysRoleListVo;
import com.project.modules.sys.vo.save.SysRoleSaveVo;
import com.project.modules.sys.vo.update.SysRoleUpdateVo;
import com.project.utils.CheckUtils;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.TrimUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统角色Service
 *
 * @author liangyuding
 * @data 2020-03-17
 */
@Slf4j
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;
    @Autowired
    private SysCheckInvokingService sysCheckInvokingService;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;

    /**
     * 校验角色名称是否已存在
     * @param roleName
     * @param roleId
     */
    @Override
    public void checkNameRepeat(String roleName, Long roleId) {
        if (baseMapper.checkNameRepeat(StringUtils.trim(roleName), roleId) > 0){
            throw new RRException("该角色名称已存在,请重新输入");
        }
    }

    /**
     * 分页查询系统角色列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysRoleListVo> page = new Query<SysRoleListVo>(params).getPage();
        List<SysRoleListVo> sysUserListVos = baseMapper.queryByPage(page, StringUtils.trim(MapUtils.getString(params, "roleName")), MapUtils.getInteger(params, "status"));
        return new PageUtils(page.setRecords(sysUserListVos));
    }

    /**
     * 新增系统角色
     * @param role
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveSysRole(SysRoleSaveVo role, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkUtils.checkRoleNotNull(role.getRoleName(), role.getDeptIdList(), role.getMenuIdList());
        checkNameRepeat(role.getRoleName(), null);
        //获取系统角色对象
        SysRoleEntity sysRoleEntity = getSaveSysRoleEntity(role, sysUserId);
        if (super.save(sysRoleEntity)){
            //保存系统角色与系统菜单关系
            sysRoleMenuService.saveSysRoleMenu(sysRoleEntity.getRoleId(), role.getMenuIdList());
            //保存系统角色与系统部门关系
            sysRoleDeptService.saveSysDeptRole(sysRoleEntity.getRoleId(), role.getDeptIdList());
        }

    }

    /**
     * 根据系统角色ID获取系统角色详情
     * @param roleId
     * @return
     */
    @Override
    public SysRoleInfoVo getSysUserInfoVo(Long roleId) {
        SysRoleInfoVo sysRoleInfoVo = baseMapper.getSysRoleInfoVo(roleId);
        checkUtils.checkEntityNotNull(sysRoleInfoVo);
        sysRoleInfoVo
                //根据系统角色ID获取系统菜单
                .setMenuIdList(sysRoleMenuService.getMenuIdByRoleId(roleId))
                //根据系统角色ID获取系统部门
                .setDeptIdList(sysRoleDeptService.getDeptIdByRoleId(roleId));
        return sysRoleInfoVo;
    }

    /**
     * 更新系统角色
     * @param role
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateSysRole(SysRoleUpdateVo role, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SysRoleEntity sysRoleEntity = getUpdateSysRoleEntity(role, sysUserId);
        updateById(sysRoleEntity);
        //更新系统角色与系统菜单关系
        sysRoleMenuService.updateSysRoleMenu(sysRoleEntity.getRoleId(), role.getMenuIdList());
        //更新系统角色与系统部门关系
        sysRoleDeptService.updateSysDeptRole(sysRoleEntity.getRoleId(), role.getDeptIdList());
    }

    /**
     * 修改系统角色状态
     * @param roleId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long roleId, Integer status, Long sysUserId) {
        SysRoleEntity sysRoleEntity = getOne(new QueryWrapper<SysRoleEntity>().eq("role_id", roleId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(sysRoleEntity);
        sysRoleEntity.setStatus(status).setUpdateUserId(sysUserId).setUpdateTime(new Date());
        updateById(sysRoleEntity);
    }

    /**
     * 删除系统角色
     * @param roleId
     */
    @Override
    @Transactional
    public void delete(Long roleId) {
        //校验该角色是否被使用
        sysCheckInvokingService.checkRoleIdIsUse(roleId);
        //删除系统角色与系统菜单关系
        sysRoleMenuService.deleteSysRoleMenuByRoleId(roleId);
        //删除系统角色与系统部门关系
        sysRoleDeptService.deleteSysRoleDeptByRoleId(roleId);
        //删除系统角色
        removeById(roleId);
    }

    /**
     * 根据系统用户ID查询该用户的系统角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRoleInvokingVo> getRoleListByUserId(Long userId) {
        return baseMapper.getRoleListByUserId(userId);
    }

    //获取并设置SysRoleEntity更新对象
    private SysRoleEntity getUpdateSysRoleEntity(SysRoleUpdateVo role, Long sysUserId) {
        checkUtils.checkRoleNotNull(role.getRoleName(), role.getDeptIdList(), role.getMenuIdList());
        SysRoleEntity sysRoleEntity = getOne(new QueryWrapper<SysRoleEntity>().eq("role_id", role.getRoleId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(sysRoleEntity);
        sysRoleEntity
                .setRoleName(role.getRoleName())
                .setRemake(role.getRemake())
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        checkNameRepeat(sysRoleEntity.getRoleName(), sysRoleEntity.getRoleId());
        return sysRoleEntity;
    }

    //设置SysRoleEntity保存对象
    private SysRoleEntity getSaveSysRoleEntity(SysRoleSaveVo role, Long sysUserId) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        sysRoleEntity
                .setRoleName(role.getRoleName())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setRemake(role.getRemake())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return sysRoleEntity;
    }

}
