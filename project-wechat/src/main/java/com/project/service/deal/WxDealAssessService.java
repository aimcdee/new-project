package com.project.service.deal;

import com.project.constant.Constant;
import com.project.modules.deal.vo.save.DealAssessSaveVo;
import com.project.service.deal.impl.WxDealAssessServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信商品评估调用层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@FeignClient(name = "project-controller", fallback = WxDealAssessServiceImpl.class)
public interface WxDealAssessService {

    /**
     * 分页查询个人商品评估列表
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_ASSESS_PATH + "/list")
    R list(@RequestParam Map<String, Object> params);

    /**
     * 新增商品评估
     * @param assess
     * @return
     */
    @PostMapping(Constant.DEAL_ASSESS_PATH + "/save")
    R saveEntity(@RequestBody DealAssessSaveVo assess);

    /**
     * 获取个人商品评估详情
     * @param dealAssessId
     * @return
     */
    @GetMapping(Constant.DEAL_ASSESS_PATH + "/info/{dealAssessId}")
    R info(@PathVariable("dealAssessId") Long dealAssessId);
}
