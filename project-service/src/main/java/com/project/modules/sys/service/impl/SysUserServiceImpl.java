package com.project.modules.sys.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.exception.RRException;
import com.project.modules.sys.dao.SysUserDao;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.modules.sys.service.*;
import com.project.modules.sys.vo.info.SysUserInfoVo;
import com.project.modules.sys.vo.list.SysUserListInvokingVo;
import com.project.modules.sys.vo.list.SysUserListVo;
import com.project.modules.sys.vo.save.SysUserSaveVo;
import com.project.modules.sys.vo.update.SysUserUpdateVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 系统用户Service
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserDeptService sysUserDeptService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 校验手机号码是否已存在
     * @param phone
     * @param userId
     */
    @Override
    public void checkPhone(String phone, Long userId) {
        if (baseMapper.checkPhone(StringUtils.trim(phone), userId) > 0){
            throw new RRException("该手机号码已存在,请重新输入");
        }
    }

    /**
     * 根据手机号码查询用户信息
     * @param phone
     * @return
     */
    @Override
    public SysUserEntity queryByPhone(String phone) {
        return baseMapper.queryByPhone(StringUtils.trim(phone));
    }

    /**
     * 更新最近登录IP地址
     * @param userId
     * @param loginIp
     * @param loginTime
     */
    @Override
    public void updateLoginIp(Long userId, String loginIp, Date loginTime) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUserId(userId).setLoginIp(loginIp).setLoginTime(loginTime);
        updateById(sysUserEntity);
    }

    /**
     * 分页查询状态为正常系统用户列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        System.out.println("状态:" + MapUtils.getInteger(params, "status"));
        Page<SysUserListVo> page = new Query<SysUserListVo>(params).getPage();
        List<SysUserListVo> sysUserListVos  = baseMapper.queryByPage(
                page, StringUtils.trim(MapUtils.getString(params, "userName")),
                StringUtils.trim(MapUtils.getString(params, "phone")),
                MapUtils.getInteger(params, "status"),
                getRedisDeptIdList(MapUtils.getLongValue(params, "deptId")));
        sysUserListVos.forEach(sysUserListVo -> {
            sysUserListVo.setRoleList(sysRoleService.getRoleListByUserId(sysUserListVo.getUserId()));
        });
        return new PageUtils(page.setRecords(sysUserListVos));
    }


    /**
     * 新增系统用户
     * @param user
     * @param sysUserId
     */
    @Override
    public void saveSysUser(SysUserSaveVo user, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验非空
        checkUtils.checkSaveUserNotNull(user);
        SysUserEntity sysUserEntity = getSaveSysUserEntity(user, sysUserId);
        //新增系统用户
        save(sysUserEntity);
        //新增系统用户与系统部门关系
        sysUserDeptService.saveUserDept(sysUserEntity.getUserId(), user.getDeptId());
        //新增系统用户与角色关系
        sysUserRoleService.saveUserRole(sysUserEntity.getUserId(), user.getRoleIdList());
        updateRedis();
    }

    /**
     * 根据系统用户ID获取系统用户详情
     * @param userId
     * @return
     */
    @Override
    public SysUserInfoVo getSysUserInfoVo(Long userId) {
        SysUserInfoVo sysUserInfoVo = baseMapper.getSysUserInfoVo(userId);
        sysUserInfoVo.setConfirm(sysUserInfoVo.getPassword());
        sysUserInfoVo.setRoleIdList(sysUserRoleService.getRoleIdListByUserId(userId));
        return sysUserInfoVo;
    }

    /**
     * 更新系统用户
     * @param user
     * @param sysUserId
     */
    @Override
    public void updateSysUser(SysUserUpdateVo user, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SysUserEntity sysUserEntity = getUpdateSysUserEntity(user, sysUserId);
        updateById(sysUserEntity);
        //更新系统用户与系统部门关系
        sysUserDeptService.updateUserDept(user.getUserId(), user.getDeptId());
        //更新系统用户与角色关系
        sysUserRoleService.updateUserRole(user.getUserId(), user.getRoleIdList());
        updateRedis();
    }

    /**
     * 更新系统用户状态
     * @param userId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long userId, Integer status, Long sysUserId) {
        SysUserEntity sysUserEntity = getOne(new QueryWrapper<SysUserEntity>().eq(Objects.nonNull(userId), "user_id", userId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(sysUserEntity);
        sysUserEntity.setStatus(status).setUpdateUserId(sysUserId).setUpdateTime(new Date());
        updateById(sysUserEntity);
        updateRedis();
    }

    /**
     * 获取用户ID和用户名称
     * @return
     */
    @Override
    public List<SysUserListInvokingVo> getSysUser() {
        List<SysUserListInvokingVo> sysUserListInvokingVos = JSONArray.parseArray(redisUtils.get(RedisKeys.Sys.User(RedisKeyConstant.SYS_USER_LIST)), SysUserListInvokingVo.class);
        sysUserListInvokingVos = CollectionUtils.isNotEmpty(sysUserListInvokingVos) ? sysUserListInvokingVos : baseMapper.getSysUser(Constant.SUPER_ADMIN, Constant.SUPER_ADMIN_STRING, Constant.Status.NORMAL.getStatus());
        redisUtils.set(RedisKeys.Sys.User(RedisKeyConstant.SYS_USER_LIST), sysUserListInvokingVos);
        return sysUserListInvokingVos;
    }

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    //获取并设置更新的SysUserEntity对象
    private SysUserEntity getUpdateSysUserEntity(SysUserUpdateVo user, Long sysUserId) {
        //校验非空
        checkUtils.checkUpdateUserNotNull(user);
        SysUserEntity sysUserEntity = getOne(new QueryWrapper<SysUserEntity>().eq("user_id", user.getUserId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(sysUserEntity);
        if (Objects.nonNull(user.getPassword())){
            if (StringUtils.isBlank(user.getConfirm())){
                throw new RRException("请输入确认密码");
            }
            if (!user.getConfirm().equals(user.getPassword())){
                throw new RRException("两次密码输入不匹配,请重新输入");
            }
            String salt = RandomStringUtils.randomAlphanumeric(20);
            sysUserEntity
                    .setSalt(salt)
                    .setPassword(new Sha256Hash(StringUtils.trim(user.getPassword()), salt).toHex());
        }
        sysUserEntity
                .setUserName(user.getUserName())
                .setPhone(user.getPhone())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        return sysUserEntity;
    }

    //设置新增SysUserEntity对象
    private SysUserEntity getSaveSysUserEntity(SysUserSaveVo user, Long sysUserId) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUserEntity
                .setUserName(StringUtils.trim(user.getUserName()))
                .setPhone(StringUtils.trim(user.getPhone()))
                .setSalt(salt)
                .setPassword(new Sha256Hash(StringUtils.trim(user.getPassword()), salt).toHex())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return sysUserEntity;
    }

    //从redis中获取部门ID集合
    private List<Long> getRedisDeptIdList(Long deptId) {
        List <Long> deptIdList = JSONArray.parseArray(redisUtils.get(RedisKeys.Sys.Dept(RedisKeyConstant.SYS_DEPT_ID_LIST + deptId)), Long.class);
        if (CollectionUtils.isEmpty(deptIdList)){
            deptIdList = getDeptIdList(new ArrayList<>(), deptId);
            redisUtils.set(RedisKeys.Sys.Dept(RedisKeyConstant.SYS_DEPT_ID_LIST + deptId), deptIdList);
        }
        return deptIdList;
    }

    //获取该系统部门及所有的子部门
    private List<Long> getDeptIdList(List<Long> deptIdList, Long deptId) {
        if (Objects.isNull(deptId) || deptId == 0){
            deptId = Constant.HEADQUARTERS;
        }
        deptIdList.add(deptId);
        //获取该部门的子部门
        List<Long> childDeptIdList = sysDeptService.getChildDeptIdList(deptId);
        if (CollectionUtils.isNotEmpty(childDeptIdList)) {
            childDeptIdList.forEach(childDeptId -> {
                getDeptIdList(deptIdList, childDeptId);
            });
        }
        return deptIdList;
    }

    //更新redis上的用户列表信息
    private void updateRedis() {
        redisUtils.delete(RedisKeys.Sys.User(RedisKeyConstant.SYS_USER_LIST));
        List<SysUserListInvokingVo> sysUserListInvokingVos = baseMapper.getSysUser(Constant.SUPER_ADMIN, Constant.SUPER_ADMIN_STRING, Constant.Status.NORMAL.getStatus());
        redisUtils.set(RedisKeys.Sys.User(RedisKeyConstant.SYS_USER_LIST), sysUserListInvokingVos);
    }
}
