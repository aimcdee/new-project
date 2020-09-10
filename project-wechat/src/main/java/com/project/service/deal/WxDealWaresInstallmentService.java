package com.project.service.deal;

import com.project.constant.Constant;
import com.project.modules.deal.vo.save.DealWaresInstallmentSaveVo;
import com.project.service.deal.impl.WxDealWaresInstallmentServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 微信咨询商品分期调用层
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@FeignClient(name = "project-controller", fallback = WxDealWaresInstallmentServiceImpl.class)
public interface WxDealWaresInstallmentService {

    /**
     * 客户分页查询个人咨询分期记录列表
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_WARES_INSTALLMENT_PATH + "/list")
    R queryPage(@RequestParam Map<String, Object> params);

    /**
     * 新增咨询分期
     * @param installment
     * @return
     */
    @PostMapping(Constant.DEAL_WARES_INSTALLMENT_PATH + "/save")
    R saveEntity(@RequestBody DealWaresInstallmentSaveVo installment);
}
