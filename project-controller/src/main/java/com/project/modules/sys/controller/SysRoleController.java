package com.project.modules.sys.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.sys.service.SysRoleService;
import com.project.modules.sys.vo.save.SysRoleSaveVo;
import com.project.modules.sys.vo.update.SysRoleUpdateVo;
import com.project.utils.PageUtils;
import com.project.utils.R;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.project.utils.ShiroUtils.getSysUserId;

/**
 * 系统角色Controller
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Slf4j
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色管理", description = "SysRoleController")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 校验角色名称是否已存在
     * @param roleName
     * @return
     */
    @ApiOperation(value = "校验角色名称是否已存在")
    @GetMapping("/check")
    public R check(@RequestParam String roleName) {
        sysRoleService.checkNameRepeat(roleName, null);
        return R.ok("该角色名称可以使用");
    }

    /**
     * 分页查询状态为正常系统角色列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询状态为正常角色列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysRoleService.queryPage(params);
        return R.ok(page);
    }

    /**
     * 新增系统角色
     * @param role
     * @return
     */
    @ApiOperation(value = "新增角色")
    @ApiImplicitParam(paramType = "body", name = "role", value = "角色信息对象", required = true, dataType = "SysRoleSaveVo")
    @SysLog("新增系统角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public R save(@RequestBody SysRoleSaveVo role){
        ValidatorUtils.validateEntity(role);
        sysRoleService.saveSysRole(role, getSysUserId());
        return R.ok();
    }

    /**
     * 根据系统角色ID获取系统角色详情
     * @param roleId
     * @return
     */
    @ApiOperation(value = "根据角色ID获取角色详情")
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("roleId") Long roleId) {
        return R.ok(sysRoleService.getSysUserInfoVo(roleId));
    }

    /**
     * 更新系统角色
     * @param role
     * @return
     */
    @ApiOperation(value = "更新角色")
    @ApiImplicitParam(paramType = "body", name = "role", value = "角色信息对象", required = true, dataType = "SysRoleUpdate")
    @SysLog("更新系统角色")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody SysRoleUpdateVo role){
        ValidatorUtils.validateEntity(role);
        sysRoleService.updateSysRole(role, getSysUserId());
        return R.ok();
    }

    /**
     * 修改系统角色的状态为启用
     * @param roleId
     * @return
     */
    @ApiOperation(value = "修改角色的状态为启用")
    @SysLog("修改系统角色的状态为启用")
    @GetMapping("/normal/{roleId}")
    @RequiresPermissions("sys:role:update")
    public R normal(@PathVariable("roleId") Long roleId){
        sysRoleService.changeStatus(roleId, Constant.Status.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改系统角色的状态为禁用
     * @param roleId
     * @return
     */
    @ApiOperation(value = "修改角色的状态为禁用")
    @SysLog("修改系统角色的状态为禁用")
    @GetMapping("/disable/{roleId}")
    @RequiresPermissions("sys:role:update")
    public R disable(@PathVariable("roleId") Long roleId){
        sysRoleService.changeStatus(roleId, Constant.Status.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 删除系统角色
     * @param roleId
     * @return
     */
    @ApiOperation(value = "删除角色")
    @SysLog("删除系统角色")
    @GetMapping("/delete/{roleId}")
    @RequiresPermissions("sys:role:delete")
    public R delete(@PathVariable("roleId") Long roleId){
        sysRoleService.delete(roleId);
        return R.ok();
    }
}
