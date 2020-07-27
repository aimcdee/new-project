package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealUserStoreFinanceService;
import com.project.modules.deal.vo.save.DealUserStoreFinanceSaveVo;
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
 * 微信端企业客户金融单Controller
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_USER_STORE_FINANCE_PATH)
@Api(tags = "微信客户端管理", description = "WxDealUserStoreFinanceController")
public class WxDealUserStoreFinanceController {

    @Autowired
    private DealUserStoreFinanceService dealUserStoreFinanceService;

    /**
     * 企业客户分页查询金融单列表
     * @param params
     * @return
     */
    @ApiOperation(value = "企业客户分页查询金融单列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealUserStoreFinanceService.queryPage(params));
    }

    /**
     * 新增金融单
     * @param finance
     * @return
     */
    @ApiOperation(value = "新增金融单")
    @ApiImplicitParam(paramType = "body", name = "finance", value = "金融单信息对象", required = true, dataType = "DealUserStoreFinanceSaveVo")
    @SysLog("新增金融单")
    @PostMapping("/save")
    public R save(@RequestBody DealUserStoreFinanceSaveVo finance){
        ValidatorUtils.validateEntity(finance);
        dealUserStoreFinanceService.saveEntity(finance);
        return R.ok();
    }
}
