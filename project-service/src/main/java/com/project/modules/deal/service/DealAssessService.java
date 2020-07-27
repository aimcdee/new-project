package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealAssessEntity;
import com.project.modules.deal.vo.info.DealAssessInfoVo;
import com.project.modules.deal.vo.save.DealAssessSaveVo;
import com.project.modules.deal.vo.update.DealAssessUpdateVo;
import com.project.modules.deal.vo.wx.DealAssessWxInfoVo;
import com.project.utils.PageUtils;

import java.util.Map;

/**
 * 商品评估Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
public interface DealAssessService extends IService<DealAssessEntity> {

    /**
     * 分页查询商品评估列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 分页查询商品评估列表
     * @param params
     * @return
     */
    PageUtils queryWxPage(Map<String, Object> params);

    /**
     * 新增商品评估
     * @param assess
     */
    void saveEntity(DealAssessSaveVo assess);

    /**
     * 根据商品评估ID获取商品评估详情
     * @param dealAssessId
     * @return
     */
    DealAssessInfoVo info(Long dealAssessId);

    /**
     * 根据商品评估ID获取商品评估详情
     * @param dealAssessId
     * @return
     */
    DealAssessWxInfoVo infoWx(Long dealAssessId);

    /**
     * 评估价钱
     * @param assess
     * @param sysUserId
     */
    void updateEntity(DealAssessUpdateVo assess, Long sysUserId);

    /**
     * 审核客户提交的评估作废
     * @param dealAssessId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long dealAssessId, Integer status, Long sysUserId);
}
