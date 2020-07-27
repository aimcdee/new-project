package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealAssessService;
import com.project.modules.deal.vo.save.DealAssessSaveVo;
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
 * 微信商品评估Controller
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_ASSESS_PATH)
@Api(tags = "微信商品端管理", description = "WxDealAssessController")
public class WxDealAssessController {

    @Autowired
    private DealAssessService dealAssessService;

    /**
     * 分页查询商品评估列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询商品评估列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealAssessService.queryWxPage(params));
    }

    /**
     * 新增商品评估
     * @param assess
     * @return
     */
    @ApiOperation(value = "新增商品评估")
    @ApiImplicitParam(paramType = "body", name = "assess", value = "商品评估信息对象", required = true, dataType = "DealAssessSaveVo")
    @SysLog("新增商品评估")
    @PostMapping("/save")
    public R save(@RequestBody DealAssessSaveVo assess){
        ValidatorUtils.validateEntity(assess);
        dealAssessService.saveEntity(assess);
        return R.ok();
    }

    /**
     * 根据商品评估ID获取商品评估详情
     * @param dealAssessId
     * @return
     */
    @ApiOperation(value = "根据商品评估ID获取商品评估详情")
    @GetMapping("/info/{dealAssessId}")
    public R info(@PathVariable("dealAssessId") Long dealAssessId) {
        return R.ok(dealAssessService.infoWx(dealAssessId));
    }
}
