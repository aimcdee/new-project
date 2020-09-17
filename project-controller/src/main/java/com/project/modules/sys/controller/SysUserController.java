package com.project.modules.sys.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.sys.service.SysUserService;
import com.project.modules.sys.vo.save.SysUserSaveVo;
import com.project.modules.sys.vo.update.SysUserUpdatePasswordVo;
import com.project.modules.sys.vo.update.SysUserUpdateVo;
import com.project.utils.R;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.project.utils.ShiroUtils.getSysUserId;

/**
 * 系统用户Controller
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Slf4j
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户管理", description = "SysUserController")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 校验手机号码是否已存在
     * @param phone
     * @return
     */
    @ApiOperation(value = "校验手机号码是否已存在")
    @GetMapping("/check")
    public R check(@RequestParam String phone){
        sysUserService.checkPhone(phone, null);
        return R.ok("手机号码可以使用");
    }

    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询用户列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        return R.ok(sysUserService.queryPage(params));
    }

    /**
     * 新增系统用户
     * @param user
     * @return
     */
    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(paramType = "body", name = "user", value = "用户信息对象", required = true, dataType = "SysUserSaveVo")
    @SysLog("新增系统用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserSaveVo user){
        ValidatorUtils.validateEntity(user);
        sysUserService.saveSysUser(user, getSysUserId());
        return R.ok();
    }

    /**
     * 根据系统用户ID获取系统用户详情
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据用户ID获取用户详情")
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId) {
        return R.ok(sysUserService.getSysUserInfoVo(userId));
    }

    /**
     * 更新系统用户
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户")
    @ApiImplicitParam(paramType = "body", name = "user", value = "用户信息对象", required = true, dataType = "SysUserUpdate")
    @SysLog("更新系统用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserUpdateVo user){
        ValidatorUtils.validateEntity(user);
        sysUserService.updateSysUser(user, getSysUserId());
        return R.ok();
    }

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    @ApiOperation(value = "修改用户密码")
    @ApiImplicitParam(paramType = "body", name = "user", value = "用户密码信息对象", required = true, dataType = "SysUserUpdatePasswordVo")
    @SysLog("修改用户密码")
    @PostMapping("/updatePassword")
    @RequiresPermissions("sys:user:update")
    public R updatePassword(SysUserUpdatePasswordVo user){
        ValidatorUtils.validateEntity(user);
        sysUserService.updatePassword(user, getSysUserId());
        return R.ok();
    }

    /**
     * 重置用户密码
     * @param userId
     * @return
     */
    @ApiOperation(value = "重置用户密码")
    @SysLog("重置用户密码")
    @GetMapping("/resetPassword/{userId}")
    @RequiresPermissions("sys:user:update")
    public R resetPassword(@PathVariable("userId") Long userId){
        sysUserService.resetPassword(userId, getSysUserId());
        return R.ok();
    }

    /**
     * 修改系统用户的状态为启用
     * @param userId
     * @return
     */
    @ApiOperation(value = "修改用户的状态为启用")
    @SysLog("修改系统用户的状态为启用")
    @GetMapping("/normal/{userId}")
    @RequiresPermissions("sys:user:update")
    public R normal(@PathVariable("userId") Long userId){
        sysUserService.changeStatus(userId, Constant.Status.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改系统用户的状态为禁用
     * @param userId
     * @return
     */
    @ApiOperation(value = "修改用户的状态为禁用")
    @SysLog("修改系统用户的状态为禁用")
    @GetMapping("/disable/{userId}")
    @RequiresPermissions("sys:user:update")
    public R disable(@PathVariable("userId") Long userId){
        sysUserService.changeStatus(userId, Constant.Status.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 获取用户ID和用户名称
     * @return
     */
    @ApiOperation(value = "获取用户ID和用户名称")
    @GetMapping("/getSysUser")
    public R getSysUser(){
        return R.ok(sysUserService.getSysUser());
    }

    /**
     * 获取所有业务员
     * @return
     */
    @ApiOperation(value = "获取所有业务员")
    @GetMapping("/getSaleUser")
    public R getSaleUser(){
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(Constant.RoleId.SALE_MANAGER.getRoleId());
        roleIdList.add(Constant.RoleId.SALE.getRoleId());
        return R.ok(sysUserService.getSaleUser(roleIdList));
    }
}
