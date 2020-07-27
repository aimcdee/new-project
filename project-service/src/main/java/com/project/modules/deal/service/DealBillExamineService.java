package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealBillExamineEntity;
import com.project.modules.deal.vo.invoking.DealBillExamineInvokingVo;

import java.util.List;

/**
 * 审核单Service
 *
 * @author liangyuding
 * @date 2020-05-56
 */
public interface DealBillExamineService extends IService<DealBillExamineEntity> {

    /**
     * 查询审核对象
     * @param depositId
     * @param type
     * @return
     */
    DealBillExamineInvokingVo getExamineUser(String depositId, Integer type);

    /**
     * 新增审核单据
     * @param billId
     * @param type
     * @param remark
     * @param sysUserId
     */
    void saveEntity(String billId, Integer type, String remark, Long sysUserId);

    /**
     * 获取单据的审核记录
     * @param billId
     * @param type
     * @return
     */
    List<DealBillExamineInvokingVo> getExamineList(String billId, Integer type);
}
