package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealAssessSellService;
import com.project.modules.deal.vo.invoking.DealSellFollowInvokingVo;
import com.project.modules.deal.vo.invoking.DealSellPriceInvokingVo;
import com.project.modules.deal.vo.save.DealAssessSellSaveVo;
import com.project.modules.deal.vo.update.DealAssessSellUpdateVo;
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
 * 评估商品出售Controller
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Slf4j
@RestController
@RequestMapping("/deal/assess/sell")
@Api(tags = "商品管理", description = "DealAssessSellController")
public class DealAssessSellController {

    @Autowired
    private DealAssessSellService dealAssessSellService;

    /**
     * 分页查询评估商品出售列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询评估商品出售列表")
    @GetMapping("/list")
    @RequiresPermissions("deal:assess:sell:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealAssessSellService.queryPage(params));
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
    @RequiresPermissions("deal:assess:sell:save")
    public R save(@RequestBody DealAssessSellSaveVo sell){
        ValidatorUtils.validateEntity(sell);
        dealAssessSellService.saveEntity(sell);
        return R.ok();
    }

    /**
     * 根据评估商品出售ID获取评估商品出售详情
     * @param dealSellId
     * @return
     */
    @ApiOperation(value = "根据评估商品出售ID获取评估商品出售详情")
    @GetMapping("/info/{dealSellId}")
    @RequiresPermissions("deal:assess:sell:info")
    public R info(@PathVariable("dealSellId") Long dealSellId) {
        return R.ok(dealAssessSellService.info(dealSellId));
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
    @RequiresPermissions("deal:assess:sell:update")
    public R update(@RequestBody DealAssessSellUpdateVo sell){
        ValidatorUtils.validateEntity(sell);
        dealAssessSellService.updateEntity(sell);
        return R.ok();
    }

    /**
     * 修改评估商品出售状态为处理中
     * @param sell
     * @return
     */
    @ApiOperation(value = "修改评估商品出售状态为处理中")
    @ApiImplicitParam(paramType = "body", name = "sell", value = "评估商品出售跟进人对象", required = true, dataType = "DealAssessSellInvokingVo")
    @PostMapping("/processing")
    @SysLog("修改评估商品出售状态为处理中")
    @RequiresPermissions("deal:assess:sell:update")
    public R processing(@RequestBody DealSellFollowInvokingVo sell){
        dealAssessSellService.changeStatus(sell.getDealSellId(), Constant.DropInStatus.PROCESSING.getStatus(), sell.getFollowUserId(), null, getSysUserId());
        return R.ok();
    }

    /**
     * 修改评估商品出售状态为已取消
     * @param dealSellId
     * @return
     */
    @ApiOperation(value = "修改评估商品出售状态为已取消")
    @GetMapping("/cancel/{dealSellId}")
    @SysLog("修改评估商品出售状态为已取消")
    @RequiresPermissions("deal:assess:sell:update")
    public R cancel(@PathVariable("dealSellId") Long dealSellId){
        dealAssessSellService.changeStatus(dealSellId, Constant.DropInStatus.CANCEL.getStatus(),  null, null, getSysUserId());
        return R.ok();
    }

    /**
     * 修改评估商品出售状态为已完成
     * @param sell
     * @return
     */
    @ApiOperation(value = "修改评估商品出售状态为已完成")
    @ApiImplicitParam(paramType = "body", name = "sell", value = "评估商品出售回收金额对象", required = true, dataType = "DealSellPriceInvokingVo")
    @PostMapping("/success")
    @SysLog("修改评估商品出售状态为已完成")
    @RequiresPermissions("deal:assess:sell:update")
    public R success(@RequestBody DealSellPriceInvokingVo sell){
        dealAssessSellService.changeStatus(sell.getDealSellId(), Constant.DropInStatus.SUCCESS.getStatus(),  null, sell.getSellPrice(), getSysUserId());
        return R.ok();
    }
}
