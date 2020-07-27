package com.project.service.deal;

import com.project.constant.Constant;
import com.project.service.deal.impl.WxDealUserStoreRefundServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 微信企业客户退费调用层
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@FeignClient(name = "project-controller", fallback = WxDealUserStoreRefundServiceImpl.class)
public interface WxDealUserStoreRefundService {

    /**
     * 企业客户查看提现记录
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_USER_STORE_REFUND_PATH + "/list")
    R list(@RequestParam Map<String, Object> params);

    /**
     * 企业客户获取提现或扣费记录详情
     * @param refundId
     * @return
     */
    @GetMapping(Constant.DEAL_USER_STORE_REFUND_PATH + "/info/{refundId}")
    R info(@PathVariable("refundId") String refundId);

    /**
     * 企业客户修改单据状态为放弃
     * @param refundId
     * @return
     */
    @GetMapping(Constant.DEAL_USER_STORE_REFUND_PATH + "/cancel/{refundId}")
    R changeStatus(@PathVariable("refundId") String refundId);
}
