package com.project.modules.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.constant.RedisListKeyConstant;
import com.project.exception.RRException;
import com.project.modules.sys.dao.SysUserDao;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.modules.sys.service.*;
import com.project.modules.sys.vo.info.SysUserInfoVo;
import com.project.modules.sys.vo.list.SysUserListInvokingVo;
import com.project.modules.sys.vo.list.SysUserListVo;
import com.project.modules.sys.vo.save.SysUserSaveVo;
import com.project.modules.sys.vo.update.SysUserUpdatePasswordVo;
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
    private SysConfigService sysConfigService;
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
        Long deptId = getDeptId(params);
        Page<SysUserListVo> page = new Query<SysUserListVo>(params).getPage();
        List<SysUserListVo> sysUserListVos  = baseMapper.queryByPage(
                page, StringUtils.trim(MapUtils.getString(params, "userName")),
                StringUtils.trim(MapUtils.getString(params, "phone")),
                MapUtils.getInteger(params, "status"),
                getRedisDeptIdList(deptId));
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
    @Transactional
    public void saveSysUser(SysUserSaveVo user, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验非空
        checkUtils.checkPasswordTheSame(user.getPassword(), user.getConfirm());
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
    @Transactional
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
     * 修改用户密码
     * @param user
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updatePassword(SysUserUpdatePasswordVo user, Long sysUserId) {
        //校验非空
        checkUtils.checkUpdatePasswordNotNull(user);
        SysUserEntity sysUserEntity = getOne(new QueryWrapper<SysUserEntity>().eq("user_id", user.getUserId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(sysUserEntity);
        String oldPassword = new Sha256Hash(StringUtils.trim(user.getOldPassword()), sysUserEntity.getSalt()).toHex();
        if (!oldPassword.equals(sysUserEntity.getPassword())){
            throw new RRException("原密码输入有误,请重新输入");
        }
        String salt = getSalt();
        sysUserEntity
                .setSalt(salt)
                .setPassword(new Sha256Hash(StringUtils.trim(user.getNewPassword()), salt).toHex());
        updateById(sysUserEntity);
    }

    /**
     * 重置用户密码
     * @param userId
     * @param sysUserId
     */
    @Override
    @Transactional
    public void resetPassword(Long userId, Long sysUserId) {
        SysUserEntity sysUserEntity = getOne(new QueryWrapper<SysUserEntity>().eq("user_id", userId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(sysUserEntity);
        String salt = getSalt();
        sysUserEntity
                .setSalt(salt)
                .setPassword(new Sha256Hash(StringUtils.trim(Constant.DEAL_PASSWORD), salt).toHex());
        updateById(sysUserEntity);
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
        SysUserEntity sysUserEntity = getOne(new QueryWrapper<SysUserEntity>().eq(ObjectUtils.isNotBlank(userId), "user_id", userId).last("LIMIT 1"));
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
        List<SysUserListInvokingVo> sysUserListInvokingVos = JSONArray.parseArray(redisUtils.get(RedisKeys.Sys.User(RedisListKeyConstant.SYS_USER_LIST)), SysUserListInvokingVo.class);
        sysUserListInvokingVos = CollectionUtils.isNotEmpty(sysUserListInvokingVos) ? sysUserListInvokingVos : baseMapper.getSysUser(Constant.SUPER_ADMIN, Constant.SUPER_ADMIN_STRING, Constant.StatusEnums.NORMAL.getStatus());
        redisUtils.set(RedisKeys.Sys.User(RedisListKeyConstant.SYS_USER_LIST), sysUserListInvokingVos);
        return sysUserListInvokingVos;
    }

    /**
     * 获取所有销售业务员
     * @param roleIdList
     * @return
     */
    @Override
    public List<SysUserListInvokingVo> getSaleUser(List<Long> roleIdList) {
        List<SysUserListInvokingVo> sysUserListInvokingVos = baseMapper.getSaleUser(roleIdList, Constant.StatusEnums.NORMAL.getStatus());
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
        if (StringUtils.isNotBlank(user.getPassword())){
            if (StringUtils.isBlank(user.getConfirm())){
                throw new RRException("请输入确认密码");
            }
            if (!user.getConfirm().equals(user.getPassword())){
                throw new RRException("两次密码输入不匹配,请重新输入");
            }
            String salt = getSalt();
            sysUserEntity
                    .setSalt(salt)
                    .setPassword(new Sha256Hash(StringUtils.trim(user.getPassword()), salt).toHex());
        }
        sysUserEntity
                .setUserName(user.getUserName())
                .setPhone(user.getPhone())
                .setStatus(Constant.StatusEnums.NORMAL.getStatus())
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        return sysUserEntity;
    }

    //更新redis上的用户列表信息
    private void updateRedis() {
        redisUtils.delete(RedisKeys.Sys.User(RedisListKeyConstant.SYS_USER_LIST));
        List<SysUserListInvokingVo> sysUserListInvokingVos = baseMapper.getSysUser(Constant.SUPER_ADMIN, Constant.SUPER_ADMIN_STRING, Constant.StatusEnums.NORMAL.getStatus());
        redisUtils.set(RedisKeys.Sys.User(RedisListKeyConstant.SYS_USER_LIST), sysUserListInvokingVos);
    }

    //设置新增SysUserEntity对象
    private SysUserEntity getSaveSysUserEntity(SysUserSaveVo user, Long sysUserId) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        //sha256加密
        String salt = getSalt();
        sysUserEntity
                .setUserName(StringUtils.trim(user.getUserName()))
                .setPhone(StringUtils.trim(user.getPhone()))
                .setSalt(salt)
                .setPassword(new Sha256Hash(StringUtils.trim(user.getPassword()), salt).toHex())
                .setStatus(Constant.StatusEnums.NORMAL.getStatus())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return sysUserEntity;
    }

    //获取salt
    private String getSalt(){
        return RandomStringUtils.randomAlphanumeric(20);
    }

    //从redis中获取部门ID集合
    private List<Long> getRedisDeptIdList(Long deptId) {
        List<Long> redisValues = JSONArray.parseArray(redisUtils.get(RedisKeys.Sys.Dept(new StringBuilder().append(RedisListKeyConstant.SYS_DEPT_ID_LIST).append("_").append(deptId).toString())), Long.class);
        List<Long> deptIdList = CollectionUtils.isNotEmpty(redisValues) ? redisValues : new ArrayList<>();
        if (CollectionUtils.isEmpty(deptIdList)){
            deptIdList = getDeptIdList(deptIdList, deptId);
            redisUtils.set(RedisKeys.Sys.Dept(new StringBuilder().append(RedisListKeyConstant.SYS_DEPT_ID_LIST).append("_").append(deptId).toString()), deptIdList);
        }
        return deptIdList;
    }

    //递归获取该部门及其子部门
    private List<Long> getDeptIdList(List<Long> deptIdList, Long deptId) {
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

    //获取查询条件的部门ID
    private Long getDeptId(Map<String, Object> params) {
        Long deptId = MapUtils.getLong(params, "areaId");
        if (Objects.isNull(deptId)){
            //从redis中配置缓存获取默认部门ID
            deptId = redisUtils.get(RedisKeys.Sys.Config(RedisKeyConstant.DEFAUL_DEPT), Long.class);
            if (Objects.isNull(deptId)){
                Map map = JSON.parseObject(sysConfigService.getDefaultValue(RedisKeyConstant.DEFAUL_DEPT));
                deptId = Long.parseLong(String.valueOf(map.get(RedisKeyConstant.DEFAUL_DEPT)));
                redisUtils.set(RedisKeys.Sys.Config(RedisKeyConstant.DEFAUL_DEPT), deptId);
            }
        }
        return deptId;
    }
}
