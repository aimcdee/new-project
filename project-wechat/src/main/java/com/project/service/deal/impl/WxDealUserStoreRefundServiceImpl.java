package com.project.service.deal.impl;

import com.project.service.deal.WxDealUserStoreRefundService;
import com.project.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信调用异常处理层
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Slf4j
@Service("wxDealUserStoreRefundService")
public class WxDealUserStoreRefundServiceImpl implements WxDealUserStoreRefundService {

    @Override
    public R cashOut(Long dealStoreId) {
        log.error("调用{}异常:{}", "企业客户提现");
        return null;
    }

    /**
     * 企业客户查看提现记录
     * @param params
     * @return
     */
    @Override
    public R list(Map<String, Object> params) {
        log.error("调用{}异常:{}", "企业客户查看提现记录");
        return null;
    }

    /**
     * 企业客户获取提现或扣费记录详情
     * @param refundId
     * @return
     */
    @Override
    public R info(String refundId) {
        log.error("调用{}异常:{}, 退费单ID{}", "企业客户获取提现或扣费记录详情", refundId);
        return null;
    }

    /**
     * 企业客户修改单据状态为放弃
     * @param refundId
     * @return
     */
    @Override
    public R changeStatus(String refundId) {
        log.error("调用{}异常:{}, 退费单ID{}", "企业客户修改单据状态为放弃", refundId);
        return null;
    }
}
