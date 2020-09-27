package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealWaresInstallmentService;
import com.project.modules.deal.vo.invoking.DealInstallmentFollowInvokingVo;
import com.project.modules.deal.vo.save.DealWaresInstallmentSaveVo;
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
 * 咨询分期客户管理表 Controller
 *
 * @author liangyuding
 * @date 2020-06-06
 */
@Slf4j
@RestController
@RequestMapping("/deal/wares/installment")
@Api(tags = "商品管理", description = "DealWaresIntallmentController")
public class DealWaresIntallmentController {

    @Autowired
    private DealWaresInstallmentService dealWaresInstallmentService;

    /**
     * 分页查询咨询分期客户列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询咨询分期客户列表")
    @GetMapping("/list")
    @RequiresPermissions("deal:wares:installment:list")
    public R reviewList(@RequestParam Map<String, Object> params){
        return R.ok(dealWaresInstallmentService.queryPage(params));
    }

    /**
     * 新增咨询分期客户
     * @param installment
     * @return
     */
    @ApiOperation(value = "新增咨询分期客户")
    @ApiImplicitParam(paramType = "body", name = "installment", value = "咨询分期客户信息对象", required = true, dataType = "DealWaresInstallmentSaveVo")
    @SysLog("新增咨询分期客户")
    @PostMapping("/save")
    @RequiresPermissions("deal:wares:installment:save")
    public R save(@RequestBody DealWaresInstallmentSaveVo installment){
        ValidatorUtils.validateEntity(installment);
        dealWaresInstallmentService.saveEntity(installment);
        return R.ok();
    }

    /**
     * 获取咨询分期客户详情
     * @param installmentId
     * @return
     */
    @ApiOperation(value = "获取咨询分期客户详情")
    @GetMapping("/info/{installmentId}")
    @RequiresPermissions("deal:wares:installment:info")
    public R info(@PathVariable("installmentId") Long installmentId){
        return R.ok(dealWaresInstallmentService.info(installmentId));
    }

    /**
     * 修改单据跟进状态为已跟进
     * @param follow
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "follow", value = "单据跟进信息", required = true, dataType = "DealInstallmentFollowInvokingVo")
    @ApiOperation(value = "修改单据跟进状态为已跟进")
    @SysLog("修改单据跟进状态为已跟进")
    @PostMapping("/success")
    public R success(@RequestBody DealInstallmentFollowInvokingVo follow){
        dealWaresInstallmentService.changeStatus(follow.getInstallmentId(), follow.getFollowRemark(), Constant.InstallmentStatus.SUCCESS.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改单据跟进状态为已作废
     * @param follow
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "follow", value = "单据跟进信息", required = true, dataType = "DealInstallmentFollowInvokingVo")
    @ApiOperation(value = "修改单据跟进状态为已作废")
    @SysLog("修改单据跟进状态为已作废")
    @PostMapping("/waste")
    public R waste(@RequestBody DealInstallmentFollowInvokingVo follow){
        dealWaresInstallmentService.changeStatus(follow.getInstallmentId(), follow.getFollowRemark(), Constant.InstallmentStatus.WASTE.getStatus(), getSysUserId());
        return R.ok();
    }
}
