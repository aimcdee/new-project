package com.project.modules.sys.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.sys.service.SysDeptService;
import com.project.modules.sys.vo.save.SysDeptSaveVo;
import com.project.modules.sys.vo.update.SysDeptUpdateVo;
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

import java.util.HashMap;
import java.util.Map;

import static com.project.utils.ShiroUtils.getSysUserId;

/**
 * 系统部门Controller
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Slf4j
@RestController
@RequestMapping("/sys/dept")
@Api(tags = "部门管理", description = "SysDeptConreollre")
public class SysDeptConreollre {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 查看树形部门列表
     *
     * @return
     */
    @ApiOperation(value = "查看树形部门列表")
    @GetMapping("/treeList")
    @RequiresPermissions("sys:dept:list")
    public R treeList() {
        return R.ok(sysDeptService.getTreeList(new HashMap<>()));
    }

    /**
     * 校验系统部门名称是否已存在
     * @param deptName
     * @return
     */
    @ApiOperation(value = "校验部门名称是否已存在")
    @GetMapping("/check")
    public R check(@RequestParam String deptName){
        sysDeptService.checkNameRepeat(deptName, null);
        return R.ok("该部门名称可以使用");
    }

    /**
     * 分页查询系统部门列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询系统部门列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDeptService.queryPage(params);
        return R.ok(page);
    }

    /**
     * 新增系统部门
     * @param dept
     * @return
     */
    @ApiOperation(value = "新增部门")
    @ApiImplicitParam(paramType = "body", name = "dept", value = "部门信息对象", required = true, dataType = "SysDeptSaveVo")
    @SysLog("新增系统部门")
    @PostMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public R save(@RequestBody SysDeptSaveVo dept){
        ValidatorUtils.validateEntity(dept);
        sysDeptService.saveSysDept(dept, getSysUserId());
        return R.ok();
    }

    /**
     * 根据系统部门ID获取系统部门详情
     * @param deptId
     * @return
     */
    @ApiOperation(value = "根据部门ID获取部门详情")
    @GetMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    public R info(@PathVariable("deptId") Long deptId) {
        return R.ok(sysDeptService.getSysDeptInfoVo(deptId));
    }

    /**
     * 更新系统部门
     * @param dept
     * @return
     */
    @ApiOperation(value = "更新部门")
    @ApiImplicitParam(paramType = "body", name = "dept", value = "部门信息对象", required = true, dataType = "SysDeptUpdateVo")
    @SysLog("更新系统部门")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody SysDeptUpdateVo dept){
        ValidatorUtils.validateEntity(dept);
        sysDeptService.updateSysDept(dept, getSysUserId());
        return R.ok();
    }

    /**
     * 修改系统部门的状态为启用
     * @param deptId
     * @return
     */
    @ApiOperation(value = "修改部门的状态为启用")
    @SysLog("修改系统部门的状态为启用")
    @GetMapping("/normal/{deptId}")
    @RequiresPermissions("sys:dept:update")
    public R normal(@PathVariable("deptId") Long deptId){
        sysDeptService.changeStatus(deptId, Constant.StatusEnums.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改系统部门的状态为禁用
     * @param deptId
     * @return
     */
    @ApiOperation(value = "修改部门的状态为禁用")
    @SysLog("修改系统部门的状态为禁用")
    @GetMapping("/disable/{deptId}")
    @RequiresPermissions("sys:dept:update")
    public R disable(@PathVariable("deptId") Long deptId){
        sysDeptService.changeStatus(deptId, Constant.StatusEnums.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 删除系统部门
     * @param deptId
     * @return
     */
    @ApiOperation(value = "删除部门")
    @SysLog("删除系统部门")
    @GetMapping("/delete/{deptId}")
    @RequiresPermissions("sys:dept:delete")
    public R delete(@PathVariable("deptId") Long deptId){
        sysDeptService.delete(deptId);
        return R.ok();
    }
}
