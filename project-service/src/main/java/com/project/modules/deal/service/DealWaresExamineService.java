package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealWaresExamineEntity;
import com.project.modules.deal.vo.invoking.DealWaresExamineInvokingVo;

import java.util.List;

/**
 * 企业商品审核表Service
 *
 * @author liangyuding
 * @date 2020-06-02
 */
public interface DealWaresExamineService extends IService<DealWaresExamineEntity> {

    /**
     * 获取商品的审核对象
     * @param dealWaresId
     * @return
     */
    DealWaresExamineInvokingVo getExamineUser(String dealWaresId);

    /**
     * 新增企业商品审核单
     * @param dealWaresId
     * @param remark
     * @param sysUserId
     */
    void saveEntity(String dealWaresId, String remark, Long sysUserId);

    /**
     * 获取商品的审核记录
     * @param dealWaresId
     * @return
     */
    List<DealWaresExamineInvokingVo> getExamineList(String dealWaresId);


}
