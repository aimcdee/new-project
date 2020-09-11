package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.modules.deal.service.DealAssessSellService;
import com.project.modules.deal.vo.save.DealAssessSellSaveVo;
import com.project.modules.deal.vo.update.DealAssessSellUpdateVo;
import com.project.service.deal.WxDealAssessSellService;
import com.project.utils.R;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.project.common.utils.ShiroUtils.getDealUserId;

/**
 * 微信端商品评估端口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/deal/assess/sell")
@Api(tags = "微信端商品评估端口", description = "WechatDealAssessSellController")
public class WechatDealAssessSellController {

    @Autowired
    private WxDealAssessSellService wxDealAssessSellService;
    @Autowired
    private DealAssessSellService dealAssessSellService;

    /**
     * 分页查询商品评估列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询商品评估列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("dealUserId", getDealUserId());
        return R.ok(dealAssessSellService.queryWxPage(params));
//        return wxDealAssessSellService.queryPage(params);
    }

    /**
     * 新增个人评估商品出售
     * @param sell
     * @return
     */
    @ApiOperation(value = "新增个人评估商品出售")
    @ApiImplicitParam(paramType = "body", name = "sell", value = "个人评估商品出售对象", required = true, dataType = "DealAssessSellSaveVo")
    @SysLog("新增个人评估商品出售")
    @PostMapping("/save")
    public R save(@RequestBody DealAssessSellSaveVo sell){
        ValidatorUtils.validateEntity(sell);
        dealAssessSellService.saveEntity(sell);
        return R.ok();
//        return wxDealAssessSellService.saveEntity(sell);
    }

    /**
     * 根据个人评估商品出售ID获取个人评估商品出售详情
     * @param dealSellId
     * @return
     */
    @ApiOperation(value = "根据个人评估商品出售ID获取个人评估商品出售详情")
    @GetMapping("/info/{dealSellId}")
    public R info(@PathVariable("dealSellId") Long dealSellId) {
        return R.ok(dealAssessSellService.infoWx(dealSellId));
//        return wxDealAssessSellService.info(dealSellId);
    }

    /**
     * 修改个人评估商品出售信息
     * @param sell
     * @return
     */
    @ApiOperation(value = "修改个人评估商品出售信息")
    @ApiImplicitParam(paramType = "body", name = "sell", value = "个人评估商品出售对象", required = true, dataType = "DealAssessSellUpdateVo")
    @SysLog("修改个人评估商品出售信息")
    @PostMapping("/update")
    public R update(@RequestBody DealAssessSellUpdateVo sell){
        ValidatorUtils.validateEntity(sell);
        dealAssessSellService.updateEntity(sell);
        return R.ok();
//        return wxDealAssessSellService.updateEntity(sell);
    }
}
