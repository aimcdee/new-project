package com.project.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.annotation.SysLog;
import com.project.modules.sys.entity.SysMenuEntity;
import com.project.modules.sys.service.ShiroService;
import com.project.modules.sys.service.SysMenuService;
import com.project.utils.R;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.project.utils.ShiroUtils.getSysUserId;

/**
 * 系统菜单Controller
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Slf4j
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "菜单管理", description = "SysMenuConreollre")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;

    /**
     * 导航菜单
     */
    @ApiOperation(value = "导航菜单")
    @GetMapping("/nav")
    public R nav() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getSysUserId());
        Set<String> permissions = shiroService.getUserPermissions(getSysUserId());
        return R.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 所有菜单列表
     * @return
     */
    @ApiOperation(value = "所有菜单列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public R list() {
        List<SysMenuEntity> menuList = sysMenuService.list(null);
        for (SysMenuEntity sysMenuEntity : menuList) {
            SysMenuEntity parentMenuEntity = sysMenuService.getOne(new QueryWrapper<SysMenuEntity>().eq("parent_id", sysMenuEntity.getParentId()).last("LIMIT 1"));
            if (parentMenuEntity != null) {
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }
        return R.ok(menuList);
    }

    /**
     * 分页查询菜单列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询菜单列表")
    @GetMapping("/pageList")
    @RequiresPermissions("sys:menu:list")
    public R pageList(@RequestParam Map<String, Object> params) {
        return R.ok(sysMenuService.queryPage(params));
    }



    /**
     * 选择菜单(添加、修改菜单)
     */
    @ApiOperation(value = "选择菜单(添加、修改菜单)")
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:list")
    public R select() {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
        //添加顶级菜单
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        sysMenuEntity
                .setMenuId(0L)
                .setName("一级菜单")
                .setParentId(-1L)
                .setOpen(true);
        menuList.add(sysMenuEntity);
        return R.ok(menuList);
    }

    /**
     * 系统菜单信息
     * @param menuId
     * @return
     */
    @ApiOperation(value = "系统信息")
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") Long menuId) {
        return R.ok(sysMenuService.getSysMenuEntity(menuId));
    }

    /**
     * 保存系统菜单
     * @param menu
     * @return
     */
    @ApiOperation(value = "保存菜单")
    @ApiImplicitParam(paramType = "body", name = "menu", value = "菜单信息对象", required = true, dataType = "SysMenuEntity")
    @SysLog("保存系统菜单")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenuEntity menu) {
        ValidatorUtils.validateEntity(menu);
        sysMenuService.saveSysMenuEntity(menu);
        return R.ok();
    }

    /**
     * 修改系统菜单
     * @param menu
     * @return
     */
    @ApiOperation(value = "修改菜单")
    @ApiImplicitParam(paramType = "body", name = "menu", value = "菜单信息对象", required = true, dataType = "SysMenuEntity")
    @SysLog("修改系统菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenuEntity menu) {
        ValidatorUtils.validateEntity(menu);
        sysMenuService.updateSysMenuEntity(menu);
        return R.ok();
    }

    /**
     * 删除系统菜单
     * @param menuId
     * @return
     */
    @ApiOperation(value = "删除菜单")
    @SysLog("删除系统菜单")
    @GetMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 42) {
            return R.error("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return R.error("请先删除子菜单或按钮");
        }
        sysMenuService.delete(menuId);
        return R.ok();
    }
}
