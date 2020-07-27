package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealWaresInstallmentService;
import com.project.modules.deal.vo.save.DealWaresInstallmentSaveVo;
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
 * 微信端咨询分期客户管理表 Controller
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_WARES_INSTALLMENT_PATH)
@Api(tags = "商品管理", description = "WxDealWaresIntallmentController")
public class WxDealWaresIntallmentController {

    @Autowired
    private DealWaresInstallmentService dealWaresInstallmentService;

    /**
     * 客户分页查询个人咨询分期记录列表
     * @param params
     * @return
     */
    @ApiOperation(value = "客户分页查询个人咨询分期记录列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealWaresInstallmentService.queryWxPage(params));
    }

    /**
     * 新增咨询分期
     * @param installment
     * @return
     */
    @ApiOperation(value = "新增咨询分期")
    @ApiImplicitParam(paramType = "body", name = "installment", value = "咨询分期信息对象", required = true, dataType = "DealWaresInstallmentSaveVo")
    @SysLog("新增咨询分期")
    @PostMapping("/save")
    public R save(@RequestBody DealWaresInstallmentSaveVo installment){
        ValidatorUtils.validateEntity(installment);
        dealWaresInstallmentService.saveEntity(installment);
        return R.ok();
    }
}
