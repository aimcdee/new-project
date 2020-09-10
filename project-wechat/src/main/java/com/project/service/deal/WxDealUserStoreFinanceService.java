package com.project.service.deal;

import com.project.constant.Constant;
import com.project.modules.deal.vo.save.DealUserStoreFinanceSaveVo;
import com.project.service.deal.impl.WxDealUserStoreFinanceServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 微信企业客户金融单调用层
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@FeignClient(name = "project-controller", fallback = WxDealUserStoreFinanceServiceImpl.class)
public interface WxDealUserStoreFinanceService {

    /**
     * 企业客户分页查询金融单列表
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_USER_STORE_FINANCE_PATH + "/list")
    R list(Map<String, Object> params);

    /**
     * 客户新增金融单
     * @param finance
     * @return
     */
    @PostMapping(Constant.DEAL_USER_STORE_FINANCE_PATH + "/save")
    R saveEntity(@RequestBody DealUserStoreFinanceSaveVo finance);
}
