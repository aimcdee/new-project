package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealBillExamineService;
import com.project.modules.deal.service.DealUserStoreDepositService;
import com.project.modules.deal.vo.invoking.DealUserStoreDepositInvokingVo;
import com.project.modules.deal.vo.save.DealUserStoreDepositSaveVo;
import com.project.modules.deal.vo.update.DealUserStoreDepositUpdateVo;
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
 * 企业用户保证金单Controller
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Slf4j
@RestController
@RequestMapping("/deal/user/store/deposit")
@Api(tags = "客户管理", description = "DealUserStoreDepositController")
public class DealUserStoreDepositController {

    @Autowired
    private DealUserStoreDepositService dealUserStoreDepositService;
    @Autowired
    private DealBillExamineService dealBillExamineService;

    /**
     * 分页查询保证金单列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询保证金单列表")
    @GetMapping("/list")
    @RequiresPermissions("deal:user:store:deposit:list")
    public R reviewList(@RequestParam Map<String, Object> params){
        return R.ok(dealUserStoreDepositService.queryPage(params));
    }

    /**
     * 新增保证金单
     * @param deposit
     * @return
     */
    @ApiOperation(value = "新增保证金单")
    @ApiImplicitParam(paramType = "body", name = "deposit", value = "保证金信息对象", required = true, dataType = "DealUserStoreDepositSaveVo")
    @SysLog("新增保证金单")
    @PostMapping("/save")
    @RequiresPermissions("deal:user:store:deposit:save")
    public R save(@RequestBody DealUserStoreDepositSaveVo deposit){
        ValidatorUtils.validateEntity(deposit);
        dealUserStoreDepositService.saveEntity(deposit, getSysUserId());
        return R.ok();
    }

    /**
     * 获取保证金单详情
     * @param depositId
     * @return
     */
    @ApiOperation(value = "获取保证金单详情")
    @GetMapping("/info/{depositId}")
    @RequiresPermissions("deal:user:store:deposit:info")
    public R info(@PathVariable("depositId") String depositId){
        return R.ok(dealUserStoreDepositService.info(depositId));
    }

    /**
     * 更新保证金单
     * @param deposit
     * @return
     */
    @ApiOperation(value = "更新保证金单")
    @ApiImplicitParam(paramType = "body", name = "deposit", value = "保证金信息对象", required = true, dataType = "DealUserStoreDepositUpdateVo")
    @SysLog("更新保证金单")
    @PostMapping("/update")
    @RequiresPermissions("deal:user:store:deposit:update")
    public R update(@RequestBody DealUserStoreDepositUpdateVo deposit){
        ValidatorUtils.validateEntity(deposit);
        dealUserStoreDepositService.updateEntity(deposit, getSysUserId());
        return R.ok();
    }

    /**
     * 修改单据状态为放弃
     * @param depositId
     * @return
     */
    @ApiOperation(value = "修改单据状态为放弃")
    @SysLog("修改单据状态为放弃")
    @GetMapping("/cancel/{depositId}")
    public R cancel(@PathVariable("depositId") String depositId){
        dealUserStoreDepositService.changeStatus(depositId, null, Constant.BillStatus.CANCEL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改单据状态为驳回
     * @param deposit
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "deposit", value = "单据审核信息", required = true, dataType = "DealUserStoreDepositInvokingVo")
    @ApiOperation(value = "修改单据状态为驳回")
    @SysLog("修改单据状态为驳回")
    @PostMapping("/reject")
    public R reject(@RequestBody DealUserStoreDepositInvokingVo deposit){
        ValidatorUtils.validateEntity(deposit);
        dealUserStoreDepositService.changeStatus(deposit.getDepositId(), deposit.getRemark(), Constant.BillStatus.REJECT.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改单据状态为经理审核
     * @param deposit
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "deposit", value = "单据审核信息", required = true, dataType = "DealUserStoreDepositInvokingVo")
    @ApiOperation(value = "修改单据状态为经理审核")
    @SysLog("修改单据状态为经理审核")
    @PostMapping("/manager")
    public R manager(@RequestBody DealUserStoreDepositInvokingVo deposit){
        dealUserStoreDepositService.changeStatus(deposit.getDepositId(), deposit.getRemark(), Constant.BillStatus.MANAGER.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改单据状态为已通过
     * @param deposit
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "deposit", value = "单据审核信息", required = true, dataType = "DealUserStoreDepositInvokingVo")
    @ApiOperation(value = "修改单据状态为已通过")
    @SysLog("修改单据状态为已通过")
    @PostMapping("/success")
    public R success(@RequestBody DealUserStoreDepositInvokingVo deposit){
        dealUserStoreDepositService.changeStatus(deposit.getDepositId(), deposit.getRemark(), Constant.BillStatus.SUCCESS.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 获取单据的审核记录
     * @param depositId
     * @return
     */
    @ApiOperation(value = "获取单据的审核记录")
    @GetMapping("/getExamineList/{depositId}")
    public R getExamineList(@PathVariable("depositId") String depositId){
        return R.ok(dealBillExamineService.getExamineList(depositId, Constant.BillType.DEPOSIT.getType()));
    }
}
