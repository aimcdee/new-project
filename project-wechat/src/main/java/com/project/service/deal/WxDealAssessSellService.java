package com.project.service.deal;

import com.project.constant.Constant;
import com.project.modules.deal.vo.save.DealAssessSellSaveVo;
import com.project.modules.deal.vo.update.DealAssessSellUpdateVo;
import com.project.service.deal.impl.WxDealAssessSellServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信商品评估出售调用层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@FeignClient(name = "project-controller", fallback = WxDealAssessSellServiceImpl.class)
public interface WxDealAssessSellService {

    /**
     * 分页查询商品评估列表
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_ASSESS_SELL_PATH + "/list")
    R queryPage(@RequestParam Map<String, Object> params);

    /**
     * 新增个人评估商品出售
     * @param sell
     */
    @PostMapping(Constant.DEAL_ASSESS_SELL_PATH + "/save")
    R saveEntity(@RequestBody DealAssessSellSaveVo sell);

    /**
     * 根据个人评估商品出售ID获取个人评估商品出售详情
     * @param dealSellId
     * @return
     */
    @GetMapping(Constant.DEAL_ASSESS_SELL_PATH + "/info/{dealSellId}")
    R info(@PathVariable("dealSellId") Long dealSellId);

    /**
     * 修改个人评估商品出售信息
     * @param sell
     */
    @PostMapping(Constant.DEAL_ASSESS_SELL_PATH + "/update")
    R updateEntity(@RequestBody DealAssessSellUpdateVo sell);
}
