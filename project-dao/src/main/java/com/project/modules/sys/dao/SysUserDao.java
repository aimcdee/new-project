package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.modules.sys.vo.info.SysUserInfoVo;
import com.project.modules.sys.vo.list.SysUserListInvokingVo;
import com.project.modules.sys.vo.list.SysUserListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户Dao
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 校验手机号码是否已存在
     * @param phone
     * @param userId
     * @return
     */
    Integer checkPhone(@Param("phone") String phone, @Param("userId") Long userId);

    /**
     * 分页查询状态为正常系统用户列表
     * @param page
     * @param userName
     * @param phone
     * @param status
     * @param deptIdList
     * @return
     */
    List<SysUserListVo> queryByPage(
            Page<SysUserListVo> page,
            @Param("userName") String userName,
            @Param("phone") String phone,
            @Param("status") Integer status,
            @Param("deptIdList") List<Long> deptIdList);

    /**
     * 根据系统用户ID获取系统用户详情
     * @param userId
     * @return
     */
    SysUserInfoVo getSysUserInfoVo(@Param("userId") Long userId);

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(@Param("userId") Long userId);

    /**
     * 查询用户的所有权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(@Param("userId") Long userId);

    /**
     * 根据手机号码查询用户信息
     * @param phone
     * @return
     */
    SysUserEntity queryByPhone(@Param("phone") String phone);

    /**
     * 获取用户ID和用户名称
     * @param superAdmin
     * @param admin
     * @param status
     * @return
     */
    List<SysUserListInvokingVo> getSysUser(@Param("superAdmin") Long superAdmin, @Param("admin") Long admin, @Param("status") Integer status);
}
