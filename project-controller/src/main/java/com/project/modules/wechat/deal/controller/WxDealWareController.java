package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealWaresService;
import com.project.modules.deal.vo.invoking.DealWaresChangeOnlineStatusInvokingVo;
import com.project.modules.deal.vo.save.DealWaresSaveVo;
import com.project.modules.deal.vo.update.DealWaresUpdateVo;
import com.project.utils.R;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信商品Controller
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_WARES_PATH)
@Api(tags = "微信商品端管理", description = "WxDealWareController")
public class WxDealWareController {

    @Autowired
    private DealWaresService dealWaresService;

    /**
     * 个人中心-企业-商品模块-分页查询自己企业商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "个人中心-企业-商品模块-分页查询自己企业商品列表")
    @GetMapping("/personalList")
    public R personalList(@RequestParam Map<String, Object> params){
        return R.ok(dealWaresService.queryPersonalPage(params));
    }

    /**
     * 个人中心-企业-商品模块-新增商品-新增商品信息
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresSaveVo")
    @SysLog("个人中心-企业-商品模块-新增商品-新增商品信息")
    @PostMapping("/save")
    public R save (@RequestBody DealWaresSaveVo wares){
        ValidatorUtils.validateEntity(wares);
        dealWaresService.saveEntity(wares);
        return R.ok();
    }

    /**
     * 个人中心-企业-商品模块-获取自己商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "个人中心-企业-商品模块-获取自己商品的详情")
    @GetMapping("/personal/{dealWaresId}")
    public R personal(@PathVariable("dealWaresId") String dealWaresId){
        return R.ok(dealWaresService.infoWx(dealWaresId));
    }

    /**
     * 个人中心-企业-商品模块-修改商品-修改商品信息
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresUpdateVo")
    @SysLog("个人中心-企业-商品模块-修改商品-修改商品信息")
    @PostMapping("/update")
    public R update (@RequestBody DealWaresUpdateVo wares){
        ValidatorUtils.validateEntity(wares);
        dealWaresService.updateEntity(wares);
        return R.ok();
    }

    /**
     * 个人中心-企业-商品模块-修改企业商品上线状态为上架
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品审核信息", required = true, dataType = "DealWaresChangeOnlineStatusInvokingVo")
    @ApiOperation(value = "修改企业商品上线状态为上架")
    @SysLog("修改企业商品上线状态为上架")
    @PostMapping("/onLine")
    public R onLine(@RequestBody DealWaresChangeOnlineStatusInvokingVo wares){
        dealWaresService.changeOnLineStatus(wares.getDealWaresId(), wares.getDealStoreId(), Constant.WaresOnLineStatus.ONLINE.getStatus(), null);
        return R.ok();
    }

    /**
     * 个人中心-企业-商品模块-修改企业商品上线状态为下架
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品审核信息", required = true, dataType = "DealWaresChangeOnlineStatusInvokingVo")
    @ApiOperation(value = "修改企业商品上线状态为下架")
    @SysLog("修改企业商品上线状态为下架")
    @PostMapping("/unLine")
    public R unLine(@RequestBody DealWaresChangeOnlineStatusInvokingVo wares){
        dealWaresService.changeOnLineStatus(wares.getDealWaresId(), wares.getDealStoreId(), Constant.WaresOnLineStatus.UNLINE.getStatus(), null);
        return R.ok();
    }

    /**
     * 个人中心-企业-商品模块-修改企业商品出售情况为已出售
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "个人中心-企业-商品模块-修改企业商品出售情况为已出售")
    @SysLog("个人中心-企业-商品模块-修改企业商品出售情况为已出售")
    @GetMapping("/sale/{dealWaresId}")
    public R sale(@PathVariable("dealWaresId") String dealWaresId){
        dealWaresService.changeSellStatus(dealWaresId, Constant.WaresSellStatus.SALE.getStatus());
        return R.ok();
    }

    /**
     * 企业端分页查询商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "企业端分页查询商品列表")
    @GetMapping("/storeList")
    public R storeList(@RequestParam Map<String, Object> params){
        return R.ok(dealWaresService.queryStorePage(params));
    }

    /**
     * 企业客户获取企业端商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "企业客户获取企业端商品的详情")
    @GetMapping("/store/{dealWaresId}")
    public R store(@PathVariable("dealWaresId") String dealWaresId){
        return R.ok(dealWaresService.store(dealWaresId));
    }

    /**
     * 零售端分页查询商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "零售端分页查询商品列表")
    @GetMapping("/retailList")
    public R retailList(@RequestParam Map<String, Object> params){
        return R.ok(dealWaresService.queryRetailPage(params));
    }

    /**
     * 零售端-获取商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "客户获取零售端商品的详情")
    @GetMapping("/retail/{dealWaresId}")
    public R retail(@PathVariable("dealWaresId") String dealWaresId){
        return R.ok(dealWaresService.retail(dealWaresId));
    }


}
