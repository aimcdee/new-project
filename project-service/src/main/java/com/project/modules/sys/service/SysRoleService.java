package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysRoleEntity;
import com.project.modules.sys.vo.info.SysRoleInfoVo;
import com.project.modules.sys.vo.invoking.SysRoleInvokingVo;
import com.project.modules.sys.vo.save.SysRoleSaveVo;
import com.project.modules.sys.vo.update.SysRoleUpdateVo;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统角色Service
 *
 * @author liangyuding
 * @data 2020-03-10
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 校验角色名称是否已存在
     * @param roleName
     * @param roleId
     */
    void checkNameRepeat(String roleName, Long roleId);

    /**
     * 分页查询系统角色列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增系统角色
     * @param role
     * @param sysUserId
     */
    void saveSysRole(SysRoleSaveVo role, Long sysUserId);

    /**
     * 根据系统角色ID获取系统角色详情
     * @param roleId
     * @return
     */
    SysRoleInfoVo getSysUserInfoVo(Long roleId);

    /**
     * 更新系统角色
     * @param role
     * @param sysUserId
     */
    void updateSysRole(SysRoleUpdateVo role, Long sysUserId);

    /**
     * 修改系统角色状态
     * @param roleId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long roleId, Integer status, Long sysUserId);

    /**
     * 删除系统角色
     * @param roleId
     */
    void delete(Long roleId);

    /**
     * 根据系统用户ID查询该用户的系统角色
     * @param userId
     * @return
     */
    List<SysRoleInvokingVo> getRoleListByUserId(Long userId);
}
