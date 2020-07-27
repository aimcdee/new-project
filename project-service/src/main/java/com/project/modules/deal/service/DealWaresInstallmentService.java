package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealWaresInstallmentEntity;
import com.project.modules.deal.vo.info.DealWaresInstallmentInfoVo;
import com.project.modules.deal.vo.save.DealWaresInstallmentSaveVo;
import com.project.utils.PageUtils;

import java.util.Map;

/**
 * 咨询分期客户管理表Service
 *
 * @author liangyuding
 * @date 2020-06-06
 */
public interface DealWaresInstallmentService extends IService<DealWaresInstallmentEntity> {

    /**
     * 分页查询咨询分期客户列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 客户分页查询个人咨询分期记录列表
     * @param params
     * @return
     */
    PageUtils queryWxPage(Map<String, Object> params);

    /**
     * 新增咨询分期客户
     * @param installment
     */
    void saveEntity(DealWaresInstallmentSaveVo installment);

    /**
     * 获取咨询分期客户详情
     * @param installmentId
     * @return
     */
    DealWaresInstallmentInfoVo info(Long installmentId);

    /**
     * 修改咨询分期单据跟进状态
     * @param installmentId
     * @param followRemark
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long installmentId, String followRemark, Integer status, Long sysUserId);
}
