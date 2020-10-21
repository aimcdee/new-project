package com.project.modules.cou.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.cou.service.CouWaresSeriesService;
import com.project.modules.cou.vo.save.CouWaresSeriesSaveVo;
import com.project.modules.cou.vo.update.CouWaresSeriesUpdateVo;
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
 * 系列管理Controller
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@RestController
@RequestMapping("/cou/wares/series")
@Api(tags = "商品管理", description = "CouWaresSeriesController")
public class CouWaresSeriesController {

    @Autowired
    private CouWaresSeriesService couWaresSeriesService;

    /**
     * 分页查询系列列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询系列列表")
    @GetMapping("/list")
    @RequiresPermissions("cou:wares:series:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(couWaresSeriesService.queryPage(params));
    }

    /**
     * 新增系列
     * @param series
     * @return
     */
    @ApiOperation(value = "新增系列")
    @ApiImplicitParam(paramType = "body", name = "series", value = "系列信息对象", required = true, dataType = "CouWaresSeriesSaveVo")
    @SysLog("新增系列")
    @PostMapping("/save")
    @RequiresPermissions("cou:wares:series:save")
    public R save(@RequestBody CouWaresSeriesSaveVo series){
        ValidatorUtils.validateEntity(series);
        couWaresSeriesService.saveEntity(series, getSysUserId());
        return R.ok();
    }

    /**
     * 根据系列ID获取系列详情
     * @param couSeriesId
     * @return
     */
    @ApiOperation(value = "根据品牌ID获取系列详情")
    @GetMapping("/info/{couSeriesId}")
    @RequiresPermissions("cou:wares:series:info")
    public R info(@PathVariable("couSeriesId") Long couSeriesId) {
        return R.ok(couWaresSeriesService.info(couSeriesId));
    }

    /**
     * 更新系列
     * @param series
     * @return
     */
    @ApiOperation(value = "更新系列")
    @ApiImplicitParam(paramType = "body", name = "series", value = "系列信息对象", required = true, dataType = "CouWaresUpdateVo")
    @SysLog("更新系列")
    @PostMapping("/update")
    @RequiresPermissions("cou:wares:series:update")
    public R update(@RequestBody CouWaresSeriesUpdateVo series){
        ValidatorUtils.validateEntity(series);
        couWaresSeriesService.updateEntity(series, getSysUserId());
        return R.ok();
    }

    /**
     * 修改系列的状态为启用
     * @param couSeriesId
     * @return
     */
    @ApiOperation(value = "修改系列的状态为启用")
    @SysLog("修改系列的状态为启用")
    @GetMapping("/normal/{couSeriesId}")
    @RequiresPermissions("cou:wares:series:update")
    public R normal(@PathVariable("couSeriesId") Long couSeriesId){
        couWaresSeriesService.changeStatus(couSeriesId, Constant.StatusEnums.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改系列的状态为禁用
     * @param couSeriesId
     * @return
     */
    @ApiOperation(value = "修改系列的状态为禁用")
    @SysLog("修改系列的状态为禁用")
    @GetMapping("/disable/{couSeriesId}")
    @RequiresPermissions("cou:wares:series:update")
    public R disable(@PathVariable("couSeriesId") Long couSeriesId){
        couWaresSeriesService.changeStatus(couSeriesId, Constant.StatusEnums.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 根据品牌ID获取所有状态为正常系列对象
     * @param couBrandId
     * @return
     */
    @ApiOperation(value = "根据品牌ID获取所有状态为正常系列对象")
    @GetMapping("/getCouSeriesList/{couBrandId}")
    public R getCouSeriesList(@PathVariable("couBrandId") Long couBrandId){
        return R.ok(couWaresSeriesService.getCouSeriesList(couBrandId));
    }
}
