package com.project.modules.cou.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.cou.service.CouWaresService;
import com.project.modules.cou.vo.save.CouWaresSaveVo;
import com.project.modules.cou.vo.update.CouWaresUpdateVo;
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
 * 商品Controller
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Slf4j
@RestController
@RequestMapping("/cou/wares")
@Api(tags = "商品管理", description = "CouWaresController")
public class CouWaresController {

    @Autowired
    private CouWaresService couWaresService;

    /**
     * 分页查询商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询商品列表")
    @GetMapping("/list")
    @RequiresPermissions("cou:wares:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(couWaresService.queryPage(params));
    }

    /**
     * 新增商品
     * @param wares
     * @return
     */
    @ApiOperation(value = "新增商品")
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "CouWaresSaveVo")
    @SysLog("新增商品")
    @PostMapping("/save")
    @RequiresPermissions("cou:wares:save")
    public R save(@RequestBody CouWaresSaveVo wares){
        ValidatorUtils.validateEntity(wares);
        couWaresService.saveEntity(wares, getSysUserId());
        return R.ok();
    }

    /**
     * 根据商品ID获取商品详情
     * @param waresId
     * @return
     */
    @ApiOperation(value = "根据商品ID获取商品详情")
    @GetMapping("/info/{waresId}")
    @RequiresPermissions("cou:wares:info")
    public R info(@PathVariable("waresId") Long waresId) {
        return R.ok(couWaresService.info(waresId));
    }

    /**
     * 更新商品
     * @param wares
     * @return
     */
    @ApiOperation(value = "更新商品")
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "CouWaresUpdateVo")
    @SysLog("更新商品")
    @PostMapping("/update")
    @RequiresPermissions("cou:wares:update")
    public R update(@RequestBody CouWaresUpdateVo wares){
        ValidatorUtils.validateEntity(wares);
        couWaresService.updateEntity(wares, getSysUserId());
        return R.ok();
    }

    /**
     * 修改商品的状态为启用
     * @param waresId
     * @return
     */
    @ApiOperation(value = "修改商品的状态为启用")
    @SysLog("修改商品的状态为启用")
    @GetMapping("/normal/{waresId}")
    @RequiresPermissions("cou:wares:update")
    public R normal(@PathVariable("waresId") Long waresId){
        couWaresService.changeStatus(waresId, Constant.StatusEnums.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改商品的状态为禁用
     * @param waresId
     * @return
     */
    @ApiOperation(value = "修改商品的状态为禁用")
    @SysLog("修改商品的状态为禁用")
    @GetMapping("/disable/{waresId}")
    @RequiresPermissions("cou:wares:update")
    public R disable(@PathVariable("waresId") Long waresId){
        couWaresService.changeStatus(waresId, Constant.StatusEnums.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 根据系列ID获取所有状态为正常商品对象
     * @return
     */
    @ApiOperation(value = "根据系列ID获取所有状态为正常商品对象")
    @GetMapping("/getCouWaresList/{couSeriesId}")
    public R getCouWaresList(@PathVariable("couSeriesId") Long couSeriesId){
        return R.ok(couWaresService.getCouWaresList(couSeriesId));
    }
}
