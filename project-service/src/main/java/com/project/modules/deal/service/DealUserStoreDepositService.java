package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealUserStoreDepositEntity;
import com.project.modules.deal.vo.info.DealUserStoreDepositInfoVo;
import com.project.modules.deal.vo.save.DealUserStoreDepositSaveVo;
import com.project.modules.deal.vo.update.DealUserStoreDepositUpdateVo;
import com.project.utils.PageUtils;

import java.util.Map;

/**
 * 企业用户保证金单Service
 *
 * @author liangyuding
 * @date 2020-05-16
 */
public interface DealUserStoreDepositService extends IService<DealUserStoreDepositEntity> {

    /**
     * 分页查询保证金单列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增保证金单
     * @param deposit
     * @param sysUserId
     */
    void saveEntity(DealUserStoreDepositSaveVo deposit, Long sysUserId);

    /**
     * 获取保证金单详情
     * @param depositId
     * @return
     */
    DealUserStoreDepositInfoVo info(String depositId);

    /**
     * 更新保证金单
     * @param deposit
     * @param sysUserId
     */
    void updateEntity(DealUserStoreDepositUpdateVo deposit, Long sysUserId);

    /**
     * 修改单据审核状态
     * @param depositId
     * @param remark
     * @param status
     * @param sysUserId
     */
    void changeStatus(String depositId, String remark, Integer status, Long sysUserId);
}
