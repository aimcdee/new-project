package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.modules.sys.vo.info.SysUserInfoVo;
import com.project.modules.sys.vo.list.SysUserListInvokingVo;
import com.project.modules.sys.vo.save.SysUserSaveVo;
import com.project.modules.sys.vo.update.SysUserUpdatePasswordVo;
import com.project.modules.sys.vo.update.SysUserUpdateVo;
import com.project.utils.PageUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户Service
 *
 * @author liangyuding
 * @date 2020-03-10
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 分页查询系统用户列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增系统用户
     * @param user
     * @param sysUserId
     */
    void saveSysUser(SysUserSaveVo user, Long sysUserId);

    /**
     * 根据系统用户ID获取系统用户详情
     * @param userId
     * @return
     */
    SysUserInfoVo getSysUserInfoVo(Long userId);

    /**
     * 更新系统用户
     * @param user
     * @param sysUserId
     */
    void updateSysUser(SysUserUpdateVo user, Long sysUserId);

    /**
     * 修改用户密码
     * @param user
     * @param sysUserId
     */
    void updatePassword(SysUserUpdatePasswordVo user, Long sysUserId);

    /**
     * 重置用户密码
     * @param userId
     * @param sysUserId
     */
    void resetPassword(Long userId, Long sysUserId);

    /**
     * 更新系统用户状态
     * @param userId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long userId, Integer status, Long sysUserId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 校验手机号码是否已存在
     * @param phone
     * @param userId
     */
    void checkPhone(String phone, Long userId);

    /**
     * 根据手机号码查询用户信息
     * @param phone
     * @return
     */
    SysUserEntity queryByPhone(String phone);

    /**
     * 更新最近登录IP地址
     * @param userId
     * @param loginIp
     * @param loginTime
     */
    void updateLoginIp(Long userId, String loginIp, Date loginTime);

    /**
     * 获取用户ID和用户名称
     * @return
     */
    List<SysUserListInvokingVo> getSysUser();
}
