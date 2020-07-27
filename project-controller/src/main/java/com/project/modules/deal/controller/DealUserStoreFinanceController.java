package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealBillExamineService;
import com.project.modules.deal.service.DealUserStoreFinanceService;
import com.project.modules.deal.vo.invoking.DealFinanceFollowInvokingVo;
import com.project.modules.deal.vo.invoking.DealFinancePriceInvokingVo;
import com.project.modules.deal.vo.save.DealUserStoreFinanceSaveVo;
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
import static com.project.utils.ShiroUtils.getSysUserName;

/**
 * 企业用户金融单Controller
 *
 * @author liangyuding
 * @date 2020-06-04
 */
@Slf4j
@RestController
@RequestMapping("/deal/user/store/finance")
@Api(tags = "客户管理", description = "DealUserStoreFinanceController")
public class DealUserStoreFinanceController {

    @Autowired
    private DealUserStoreFinanceService dealUserStoreFinanceService;
    @Autowired
    private DealBillExamineService dealBillExamineService;

    /**
     * 分页查询金融单列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询保证金单列表")
    @GetMapping("/list")
    @RequiresPermissions("deal:user:store:finance:list")
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
    @RequiresPermissions("deal:user:store:finance:save")
    public R save(@RequestBody DealUserStoreFinanceSaveVo finance){
        ValidatorUtils.validateEntity(finance);
        dealUserStoreFinanceService.saveEntity(finance);
        return R.ok();
    }

    /**
     * 获取金融单详情
     * @param financeId
     * @return
     */
    @ApiOperation(value = "获取金融单详情")
    @GetMapping("/info/{financeId}")
    @RequiresPermissions("deal:user:store:finance:info")
    public R info(@PathVariable("financeId") String financeId){
        return R.ok(dealUserStoreFinanceService.info(financeId));
    }

    /**
     * 修改金融单状态为已作废
     * @param financeId
     * @return
     */
    @ApiOperation(value = "修改金融单状态为已作废")
    @GetMapping("/waste/{financeId}")
    @SysLog("修改金融单状态为已作废")
    @RequiresPermissions("deal:user:store:finance:update")
    public R waste(@PathVariable("financeId") String financeId){
        dealUserStoreFinanceService.changeStatus(financeId, Constant.FinanceStatus.WASTE.getStatus(),  null, null, getSysUserId(), getSysUserName());
        return R.ok();
    }

    /**
     * 修改金融单状态为处理中
     * @param finance
     * @return
     */
    @ApiOperation(value = "修改金融单状态为处理中")
    @ApiImplicitParam(paramType = "body", name = "finance", value = "金融单跟进对象", required = true, dataType = "DealFinanceFollowInvokingVo")
    @PostMapping("/checkpending")
    @SysLog("修改金融单状态为处理中")
    @RequiresPermissions("deal:user:store:finance:update")
    public R checkpending(@RequestBody DealFinanceFollowInvokingVo finance){
        dealUserStoreFinanceService.changeStatus(finance.getFinanceId(), Constant.FinanceStatus.CHECKPENDING.getStatus(), finance.getFollowUserId(), null, getSysUserId(), getSysUserName());
        return R.ok();
    }

    /**
     * 修改金融单状态为已完成
     * @param finance
     * @return
     */
    @ApiOperation(value = "修改金融单状态为已完成")
    @ApiImplicitParam(paramType = "body", name = "finance", value = "金融单金额对象", required = true, dataType = "DealFinancePriceInvokingVo")
    @PostMapping("/success")
    @SysLog("修改金融单状态为已完成")
    @RequiresPermissions("deal:user:store:finance:update")
    public R success(@RequestBody DealFinancePriceInvokingVo finance){
        dealUserStoreFinanceService.changeStatus(finance.getFinanceId(), Constant.FinanceStatus.SUCCESS.getStatus(),  null, finance.getFinancePrice(), getSysUserId(), getSysUserName());
        return R.ok();
    }

    /**
     * 获取单据的审核记录
     * @param financeId
     * @return
     */
    @ApiOperation(value = "获取单据的审核记录")
    @GetMapping("/getExamineList/{financeId}")
    public R getExamineList(@PathVariable("financeId") String financeId){
        return R.ok(dealBillExamineService.getExamineList(financeId, Constant.BillType.FINANCE.getType()));
    }

}
