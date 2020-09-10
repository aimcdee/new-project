package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.service.deal.WxDealUserStoreRefundService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.project.common.utils.ShiroUtils.getDealStoreId;
import static com.project.common.utils.ShiroUtils.isEnterprise;

/**
 * 微信端企业客户退费接口
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Slf4j
@RestController
@RequestMapping("/wechat/deal/user/store/refund")
@Api(tags = "微信端企业客户退费接口", description = "WxDealUserStoreRefundController")
public class WxDealUserStoreRefundController {

    @Autowired
    private WxDealUserStoreRefundService wxDealUserStoreRefundService;

    /**
     * 客户提现
     * @return
     */
    @ApiOperation(value = "客户提现")
    @GetMapping("/cashOut")
    public R cashOut() {
        if (isEnterprise()){
            return wxDealUserStoreRefundService.cashOut(getDealStoreId());
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 企业客户分页查看提现和扣费记录
     * @return
     */
    @ApiOperation(value = "企业客户分页查看提现和扣费记录")
    @SysLog("企业客户分页查看提现和扣费记录")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        if (isEnterprise()){
            params.put("dealStoreId", getDealStoreId());
            return wxDealUserStoreRefundService.list(params);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 企业客户获取提现或扣费记录详情
     * @return
     */
    @ApiOperation(value = "企业客户获取提现或扣费记录详情")
    @SysLog("企业客户分页查看提现和扣费记录")
    @GetMapping("/info/{refundId}")
    public R info(@PathVariable("refundId") String refundId){
        if (isEnterprise()){
            return wxDealUserStoreRefundService.info(refundId);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 企业客户修改单据状态为放弃
     * @param refundId
     * @return
     */
    @ApiOperation(value = "企业客户修改单据状态为放弃")
    @SysLog("企业客户修改单据状态为放弃")
    @GetMapping("/cancel/{refundId}")
    public R cancel(@PathVariable("refundId") String refundId){
        if (isEnterprise()){
            return wxDealUserStoreRefundService.changeStatus(refundId);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

}
