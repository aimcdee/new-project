package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealBillExamineService;
import com.project.modules.deal.service.DealUserStoreRefundService;
import com.project.modules.deal.vo.invoking.DealUserStoreRefundInvokingVo;
import com.project.modules.deal.vo.save.DealUserStoreRefundSaveVo;
import com.project.modules.deal.vo.update.DealUserStoreRefundUpdateVo;
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
 * 企业用户退费Controller
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Slf4j
@RestController
@RequestMapping("/deal/user/store/refund")
@Api(tags = "客户管理", description = "DealUserStoreRefundController")
public class DealUserStoreRefundController {

    @Autowired
    private DealUserStoreRefundService dealUserStoreRefundService;
    @Autowired
    private DealBillExamineService dealBillExamineService;

    /**
     * 分页查询退费单列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询退费单列表")
    @GetMapping("/list")
    @RequiresPermissions("deal:user:store:refund:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealUserStoreRefundService.queryPage(params));
    }

    /**
     * 新增退费单
     * @param refund
     * @return
     */
    @ApiOperation(value = "新增退费单")
    @ApiImplicitParam(paramType = "body", name = "refund", value = "退费单信息对象", required = true, dataType = "DealUserStoreRefundSaveVo")
    @SysLog("新增退费单")
    @PostMapping("/save")
    @RequiresPermissions("deal:user:store:refund:save")
    public R save(@RequestBody DealUserStoreRefundSaveVo refund){
        ValidatorUtils.validateEntity(refund);
        dealUserStoreRefundService.saveEntity(refund, Constant.RefundType.REFUND.getType(), getSysUserId());
        return R.ok();
    }

    /**
     * 获取退费单详情
     * @param refundId
     * @return
     */
    @ApiOperation(value = "获取退费单详情")
    @GetMapping("/info/{refundId}")
    @RequiresPermissions("deal:user:store:refund:info")
    public R info(@PathVariable("refundId") String refundId){
        return R.ok(dealUserStoreRefundService.info(refundId));
    }

    /**
     * 更新退费单
     * @param refund
     * @return
     */
    @ApiOperation(value = "更新退费单")
    @ApiImplicitParam(paramType = "body", name = "refund", value = "退费单信息对象", required = true, dataType = "DealUserStoreRefundUpdateVo")
    @SysLog("更新退费单")
    @PostMapping("/update")
    @RequiresPermissions("deal:user:store:refund:update")
    public R update(@RequestBody DealUserStoreRefundUpdateVo refund){
        ValidatorUtils.validateEntity(refund);
        dealUserStoreRefundService.updateEntity(refund, getSysUserId());
        return R.ok();
    }

    /**
     * 修改单据状态为放弃
     * @param refundId
     * @return
     */
    @ApiOperation(value = "修改单据状态为放弃")
    @SysLog("修改单据状态为放弃")
    @GetMapping("/cancel/{refundId}")
    @RequiresPermissions("deal:user:store:refund:update")
    public R cancel(@PathVariable("refundId") String refundId){
        dealUserStoreRefundService.changeStatus(refundId, null, Constant.BillStatus.CANCEL.getStatus(), getSysUserId(), getSysUserName());
        return R.ok();
    }

    /**
     * 修改单据状态为驳回
     * @param refund
     * @return
     */
    @ApiOperation(value = "修改单据状态为驳回")
    @SysLog("修改单据状态为驳回")
    @PostMapping("/reject")
    @RequiresPermissions("deal:user:store:refund:update")
    public R reject(@RequestBody DealUserStoreRefundInvokingVo refund){
        ValidatorUtils.validateEntity(refund);
        dealUserStoreRefundService.changeStatus(refund.getRefundId(), refund.getRemark(), Constant.BillStatus.REJECT.getStatus(), getSysUserId(), getSysUserName());
        return R.ok();
    }

    /**
     * 修改单据状态为经理审核
     * @param refund
     * @return
     */
    @ApiOperation(value = "修改单据状态为经理审核")
    @SysLog("修改单据状态为经理审核")
    @PostMapping("/manager")
    @RequiresPermissions("deal:user:store:refund:update")
    public R manager(@RequestBody DealUserStoreRefundInvokingVo refund){
        ValidatorUtils.validateEntity(refund);
        dealUserStoreRefundService.changeStatus(refund.getRefundId(), refund.getRemark(), Constant.BillStatus.MANAGER.getStatus(), getSysUserId(), getSysUserName());
        return R.ok();
    }

    /**
     * 修改单据状态为已通过
     * @param refund
     * @return
     */
    @ApiOperation(value = "修改单据状态为已通过")
    @SysLog("修改单据状态为已通过")
    @PostMapping("/success")
    @RequiresPermissions("deal:user:store:refund:update")
    public R success(@RequestBody DealUserStoreRefundInvokingVo refund){
        ValidatorUtils.validateEntity(refund);
        dealUserStoreRefundService.changeStatus(refund.getRefundId(), refund.getRemark(), Constant.BillStatus.SUCCESS.getStatus(), getSysUserId(), getSysUserName());
        return R.ok();
    }

    /**
     * 获取单据的审核记录
     * @param refundId
     * @return
     */
    @ApiOperation(value = "获取单据的审核记录")
    @GetMapping("/getExamineList/{refundId}")
    public R getExamineList(@PathVariable("refundId") String refundId){
        return R.ok(dealBillExamineService.getExamineList(refundId, Constant.BillType.REFUND.getType()));
    }
}
