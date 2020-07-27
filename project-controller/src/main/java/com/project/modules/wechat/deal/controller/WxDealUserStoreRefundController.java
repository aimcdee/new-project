package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealUserStoreRefundService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信端企业客户退费单Controller
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_USER_STORE_REFUND_PATH)
@Api(tags = "客户管理", description = "WxDealUserStoreRefundController")
public class WxDealUserStoreRefundController {

    @Autowired
    private DealUserStoreRefundService dealUserStoreRefundService;

    /**
     * 企业客户查看提现或扣费记录
     * @param params
     * @return
     */
    @ApiOperation(value = "企业客户查看提现记录")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealUserStoreRefundService.queryWxPage(params));
    }

    /**
     * 企业客户获取提现或扣费详情
     * @param refundId
     * @return
     */
    @ApiOperation(value = "企业客户获取提现或扣费详情")
    @GetMapping("/info/{refundId}")
    public R info(@PathVariable("refundId") String refundId){
        return R.ok(dealUserStoreRefundService.infoWx(refundId));
    }

    /**
     * 企业客服放弃提现
     * @param refundId
     * @return
     */
    @ApiOperation(value = "企业客服放弃提现")
    @SysLog("企业客服放弃提现")
    @GetMapping("/cancel/{refundId}")
    public R cancel(@PathVariable("refundId") String refundId){
        dealUserStoreRefundService.changeStatus(refundId, null, Constant.BillStatus.CANCEL.getStatus(), null, null);
        return R.ok();
    }
}
