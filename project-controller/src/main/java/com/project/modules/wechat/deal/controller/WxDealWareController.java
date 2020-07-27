package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealWaresService;
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
     * 企业客户分页查询自己上传的企业商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "企业客户分页查询自己上传的企业商品列表")
    @GetMapping("/personalList")
    public R personalList(@RequestParam Map<String, Object> params){
        return R.ok(dealWaresService.queryPersonalPage(params));
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
     * 企业客户新增商品信息
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresSaveVo")
    @SysLog("企业客户新增商品信息")
    @PostMapping("/save")
    public R save (@RequestBody DealWaresSaveVo wares){
        ValidatorUtils.validateEntity(wares);
        dealWaresService.saveEntity(wares);
        return R.ok();
    }

    /**
     * 企业客户获取企业客户个人商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "企业客户获取企业客户个人商品的详情")
    @GetMapping("/personal/{dealWaresId}")
    public R personal(@PathVariable("dealWaresId") String dealWaresId){
        return R.ok(dealWaresService.info(dealWaresId));
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
     * 客户获取零售端商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "客户获取零售端商品的详情")
    @GetMapping("/retail/{dealWaresId}")
    public R retail(@PathVariable("dealWaresId") String dealWaresId){
        return R.ok(dealWaresService.retail(dealWaresId));
    }

    /**
     * 企业客户修改个人商品信息
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresUpdateVo")
    @SysLog("企业客户修改个人商品信息")
    @PostMapping("/update")
    public R update (@RequestBody DealWaresUpdateVo wares){
        ValidatorUtils.validateEntity(wares);
        dealWaresService.updateEntity(wares);
        return R.ok();
    }
}
