package com.project.service.deal.impl;

import com.project.modules.deal.vo.save.DealUserStoreFinanceSaveVo;
import com.project.service.deal.WxDealUserStoreFinanceService;
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
@Service("wxDealUserStoreFinanceService")
public class WxDealUserStoreFinanceServiceImpl implements WxDealUserStoreFinanceService {

    /**
     * 企业客户分页查询金融单列表
     * @param params
     * @return
     */
    @Override
    public R list(Map<String, Object> params) {
        log.error("调用{}异常:{}", "企业客户分页查询金融单列表");
        return null;
    }

    /**
     * 客户新增金融单
     * @param finance
     * @return
     */
    @Override
    public R saveEntity(DealUserStoreFinanceSaveVo finance) {
        log.error("调用{}异常:{}, 金融单对象{}", "客户新增金融单", finance);
        return null;
    }
}
