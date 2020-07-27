package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealAssessSellService;
import com.project.modules.deal.vo.save.DealAssessSellSaveVo;
import com.project.modules.deal.vo.update.DealAssessSellUpdateVo;
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
 * 微信个人商品评估Controller
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_ASSESS_SELL_PATH)
@Api(tags = "微信个人商品评估端管理", description = "WxDealAssessSellController")
public class WxDealAssessSellController {

    @Autowired
    private DealAssessSellService dealAssessSellService;

    /**
     * 分页查询评估商品出售列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询评估商品出售列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealAssessSellService.queryWxPage(params));
    }

    /**
     * 新增评估商品出售
     * @param sell
     * @return
     */
    @ApiOperation(value = "新增评估商品出售")
    @ApiImplicitParam(paramType = "body", name = "sell", value = "评估商品出售对象", required = true, dataType = "DealAssessSellSaveVo")
    @SysLog("新增评估商品出售")
    @PostMapping("/save")
    public R save(@RequestBody DealAssessSellSaveVo sell){
        ValidatorUtils.validateEntity(sell);
        dealAssessSellService.saveEntity(sell);
        return R.ok();
    }

    /**
     * 根据评估商品出售ID获取个人评估商品出售详情
     * @param dealSellId
     * @return
     */
    @ApiOperation(value = "根据评估商品出售ID获取个人评估商品出售详情")
    @GetMapping("/info/{dealSellId}")
    public R info(@PathVariable("dealSellId") Long dealSellId) {
        return R.ok(dealAssessSellService.infoWx(dealSellId));
    }

    /**
     * 修改评估商品出售信息
     * @param sell
     * @return
     */
    @ApiOperation(value = "修改评估商品出售信息")
    @ApiImplicitParam(paramType = "body", name = "sell", value = "评估商品出售对象", required = true, dataType = "DealAssessSellUpdateVo")
    @SysLog("修改评估商品出售信息")
    @PostMapping("/update")
    public R update(@RequestBody DealAssessSellUpdateVo sell){
        ValidatorUtils.validateEntity(sell);
        dealAssessSellService.updateEntity(sell);
        return R.ok();
    }
}
