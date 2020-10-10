package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealUserStoreRefundEntity;
import com.project.modules.deal.vo.info.DealUserStoreRefundInfoVo;
import com.project.modules.deal.vo.save.DealUserStoreRefundSaveVo;
import com.project.modules.deal.vo.update.DealUserStoreRefundUpdateVo;
import com.project.modules.deal.vo.wx.info.DealUserStoreRefundWxInfoVo;
import com.project.utils.PageUtils;

import java.util.Map;

/**
 * 企业用户退费Service
 *
 * @author liangyuding
 * @date 2020-05-16
 */
public interface DealUserStoreRefundService extends IService<DealUserStoreRefundEntity> {

    /**
     * 分页查询退费单列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 企业客户查看提现记录
     * @param params
     * @return
     */
    PageUtils queryWxPage(Map<String, Object> params);

    /**
     * 新增退费单
     * @param refund
     * @param type
     * @param sysUserId
     */
    void saveEntity(DealUserStoreRefundSaveVo refund, Integer type, Long sysUserId);

    /**
     * 获取退费单详情
     * @param refundId
     * @return
     */
    DealUserStoreRefundInfoVo info(String refundId);

    /**
     * 企业客户获取提现或扣费记录详情
     * @param refundId
     * @return
     */
    DealUserStoreRefundWxInfoVo infoWx(String refundId);

    /**
     * 更新退费单
     * @param refund
     * @param sysUserId
     */
    void updateEntity(DealUserStoreRefundUpdateVo refund, Long sysUserId);

    /**
     * 修改单据状态
     * @param refundId
     * @param remark
     * @param status
     * @param sysUserId
     * @param sysUserName
     */
    void changeStatus(String refundId, String remark, Integer status, Long sysUserId, String sysUserName);
}
