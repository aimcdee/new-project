package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealAssessSellEntity;
import com.project.modules.deal.vo.info.DealAssessSellInfoVo;
import com.project.modules.deal.vo.save.DealAssessSellSaveVo;
import com.project.modules.deal.vo.update.DealAssessSellUpdateVo;
import com.project.modules.deal.vo.wx.DealAssessSellWxInfoVo;
import com.project.utils.PageUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 评估商品出售Service
 *
 * @author liangyuding
 * @date 2020-05-16
 */
public interface DealAssessSellService extends IService<DealAssessSellEntity> {

    /**
     * 分页查询评估商品出售列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 分页查询个人评估商品出售列表
     * @param params
     * @return
     */
    PageUtils queryWxPage(Map<String, Object> params);

    /**
     * 新增评估商品出售
     * @param sell
     */
    void saveEntity(DealAssessSellSaveVo sell);

    /**
     * 根据评估商品出售ID获取评估商品出售详情
     * @param dealSellId
     * @return
     */
    DealAssessSellInfoVo info(Long dealSellId);

    /**
     * 根据评估商品出售ID获取个人评估商品出售详情
     * @param dealSellId
     * @return
     */
    DealAssessSellWxInfoVo infoWx(Long dealSellId);

    /**
     * 修改评估商品出售信息
     * @param sell
     */
    void updateEntity(DealAssessSellUpdateVo sell);

    /**
     * 修改评估商品出售状态
     * @param dealSellId
     * @param status
     * @param followUserId
     * @param sellPrice
     * @param userId
     */
    void changeStatus(Long dealSellId, Integer status, Long followUserId, BigDecimal sellPrice, Long userId);
}
