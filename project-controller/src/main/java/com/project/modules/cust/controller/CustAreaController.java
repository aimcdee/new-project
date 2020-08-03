package com.project.modules.cust.controller;

import com.project.constant.Constant;
import com.project.modules.cust.service.CustAreaService;
import com.project.modules.sys.service.SysConfigService;
import com.project.utils.R;
import com.project.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 区域Controller
 *
 * @author liangyuding
 * @date 2020-03-26
 */
@Slf4j
@RestController
@RequestMapping("/cust/area")
@Api(tags = "区域管理", description = "CustAreaController")
public class CustAreaController {

    @Autowired
    private CustAreaService custAreaService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查看树形菜单区域列表
     *
     * @return
     */
    @ApiOperation(value = "查看树形菜单区域列表")
    @GetMapping("/treeList")
    @RequiresPermissions("cust:area:list")
    public R treeList() {
        return R.ok(custAreaService.getTreeList());
    }

    /**
     * 分页区域列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页区域列表")
    @GetMapping("/list")
    @RequiresPermissions("cust:area:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(custAreaService.queryPage(params));
    }

    /**
     * 查看省
     * @return
     */
    @ApiOperation(value = "查看省份")
    @GetMapping("/province")
    @RequiresPermissions("cust:area:list")
    public R province(){
        return R.ok(custAreaService.getArea(null, Constant.AreaType.PROVINCE.getType()));
    }

    /**
     * 查看市
     * @param areaId
     * @return
     */
    @ApiOperation(value = "查看市")
    @GetMapping("/city/{areaId}")
    @RequiresPermissions("cust:area:list")
    public R city(@PathVariable("areaId") Long areaId){
        return R.ok(custAreaService.getArea(areaId, Constant.AreaType.CITY.getType()));
    }

    /**
     * 查看县/区
     * @param areaId
     * @return
     */
    @ApiOperation(value = "查看县/区")
    @GetMapping("/county/{areaId}")
    @RequiresPermissions("cust:area:list")
    public R county(@PathVariable("areaId") Long areaId){
        return R.ok(custAreaService.getArea(areaId, Constant.AreaType.COUNTY.getType()));
    }
}
