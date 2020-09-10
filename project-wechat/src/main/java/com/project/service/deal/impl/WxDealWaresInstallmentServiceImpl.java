package com.project.service.deal.impl;

import com.project.modules.deal.vo.save.DealWaresInstallmentSaveVo;
import com.project.service.deal.WxDealWaresInstallmentService;
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
@Service("wxDealWaresInstallmentService")
public class WxDealWaresInstallmentServiceImpl implements WxDealWaresInstallmentService {

    /**
     * 客户分页查询个人咨询分期记录列表
     * @param params
     * @return
     */
    @Override
    public R queryPage(Map<String, Object> params) {
        log.error("调用{}异常:{}", "客户分页查询个人咨询分期记录列表");
        return null;
    }

    /**
     * 新增咨询分期
     * @param installment
     * @return
     */
    @Override
    public R saveEntity(DealWaresInstallmentSaveVo installment) {
        log.error("调用{}异常:{}, 咨询分期对象:{}", "新增咨询分期", installment);
        return null;
    }
}
