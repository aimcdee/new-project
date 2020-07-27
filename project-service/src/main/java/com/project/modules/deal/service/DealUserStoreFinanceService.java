package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealUserStoreFinanceEntity;
import com.project.modules.deal.vo.info.DealUserStoreFinanceInfoVo;
import com.project.modules.deal.vo.save.DealUserStoreFinanceSaveVo;
import com.project.utils.PageUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 企业用户金融单Service
 *
 * @author liangyuding
 * @date 2020-05-16
 */
public interface DealUserStoreFinanceService extends IService<DealUserStoreFinanceEntity> {

    /**
     * 分页查询金融单列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 企业客户分页查询金融单列表
     * @param params
     * @return
     */
    PageUtils queryWxPage(Map<String, Object> params);

    /**
     * 新增金融单
     * @param finance
     */
    void saveEntity(DealUserStoreFinanceSaveVo finance);

    /**
     * 获取金融单详情
     * @param financeId
     * @return
     */
    DealUserStoreFinanceInfoVo info(String financeId);

    /**
     * 修改金融单状态
     * @param financeId
     * @param status
     * @param followUserId
     * @param financePrice
     * @param sysUserId
     * @param sysUserName
     */
    void changeStatus(String financeId, Integer status, Long followUserId, BigDecimal financePrice, Long sysUserId, String sysUserName);
}
