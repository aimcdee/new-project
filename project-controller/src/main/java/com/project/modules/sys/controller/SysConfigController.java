package com.project.modules.sys.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.sys.service.SysConfigService;
import com.project.modules.sys.vo.save.SysConfigSaveVo;
import com.project.modules.sys.vo.update.SysConfigUpdateVo;
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
 * 系统配置信息
 *
 * @author liangyuding
 * @date 2020-03-19
 */
@Slf4j
@RestController
@RequestMapping("/sys/config")
@Api(tags = "配置管理", description = "SysConfigController")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 校验配置名称是否已存在
     * @param name
     * @return
     */
    @ApiOperation(value = "校验配置名称是否已存在")
    @GetMapping("/checkName")
    public R checkName(@RequestParam String name) {
        sysConfigService.checkNameRepeat(name, null);
        return R.ok("该配置名称可以使用");
    }

    /**
     * 校验配置编码是否已存在
     * @param code
     * @return
     */
    @ApiOperation(value = "校验配置编码是否已存在")
    @GetMapping("/checkCode")
    public R checkCode(@RequestParam String code) {
        sysConfigService.checkCodeRepeat(code, null);
        return R.ok("该配置编码可以使用");
    }

    /**
     * 分页查询系统配置信息
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询系统配置信息")
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public R list(@RequestParam Map<String, Object> params) {
        return R.ok(sysConfigService.queryPage(params));
    }

    /**
     * 新增系统配置
     * @param config
     * @return
     */
    @ApiOperation(value = "新增系统配置")
    @ApiImplicitParam(paramType = "body", name = "config", value = "配置信息对象", required = true, dataType = "SysConfigSaveVo")
    @SysLog("新增系统配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public R save(@RequestBody SysConfigSaveVo config) {
        ValidatorUtils.validateEntity(config);
        sysConfigService.saveSysConfigSaveVo(config, getSysUserId());
        return R.ok();
    }

    /**
     * 根据系统配置ID获取系统配置信息
     * @param configId
     * @return
     */
    @ApiOperation(value = "获取配置信息")
    @GetMapping("/info/{configId}")
    @RequiresPermissions("sys:config:info")
    public R info(@PathVariable("configId") Long configId) {
        return R.ok(sysConfigService.info(configId));
    }

    /**
     * 修改系统配置
     * @param config
     * @return
     */
    @ApiOperation(value = "修改系统配置")
    @ApiImplicitParam(paramType = "body", name = "config", value = "配置信息对象", required = true, dataType = "SysConfigUpdateVo")
    @SysLog("修改系统配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public R update(@RequestBody SysConfigUpdateVo config) {
        ValidatorUtils.validateEntity(config);
        sysConfigService.updateSysConfigUpdateVo(config, getSysUserId());
        return R.ok();
    }

    /**
     * 启用配置信息
     * @param configId
     * @return
     */
    @ApiOperation(value = "启用配置信息")
    @SysLog("启用配置信息")
    @GetMapping("/normal/{configId}")
    @RequiresPermissions("sys:config:update")
    public R normal(@PathVariable("configId") Long configId){
        sysConfigService.changeStatus(configId, Constant.Status.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 停用配置信息
     * @param configId
     * @return
     */
    @ApiOperation(value = "停用配置信息")
    @SysLog("停用配置信息")
    @GetMapping("/disable/{configId}")
    @RequiresPermissions("sys:config:update")
    public R disable(@PathVariable("configId") Long configId){
        sysConfigService.changeStatus(configId, Constant.Status.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }
}