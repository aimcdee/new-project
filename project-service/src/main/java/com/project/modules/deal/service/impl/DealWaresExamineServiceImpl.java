package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.modules.deal.dao.DealWaresExamineDao;
import com.project.modules.deal.entity.DealWaresExamineEntity;
import com.project.modules.deal.service.DealWaresExamineService;
import com.project.modules.deal.vo.invoking.DealWaresExamineInvokingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 企业商品审核表Service
 *
 * @author liangyuding
 * @date 2020-06-02
 */
@Slf4j
@Service("dealWareExamineService")
public class DealWaresExamineServiceImpl extends ServiceImpl<DealWaresExamineDao, DealWaresExamineEntity> implements DealWaresExamineService {

    /**
     * 获取商品的审核对象
     * @param dealWaresId
     * @return
     */
    @Override
    public DealWaresExamineInvokingVo getExamineUser(String dealWaresId) {
        return baseMapper.getExamineUser(dealWaresId);
    }

    /**
     * 新增企业商品审核单
     * @param dealWaresId
     * @param remark
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(String dealWaresId, String remark, Long sysUserId) {
        DealWaresExamineEntity dealWaresExamineEntity = new DealWaresExamineEntity();
        dealWaresExamineEntity
                .setDealWaresId(dealWaresId)
                .setExamineUserId(sysUserId)
                .setExamineRemark(remark);
        save(dealWaresExamineEntity);
    }

    /**
     * 获取商品的审核记录
     * @param dealWaresId
     * @return
     */
    @Override
    public List<DealWaresExamineInvokingVo> getExamineList(String dealWaresId) {
        return baseMapper.getExamineList(dealWaresId);
    }
}
