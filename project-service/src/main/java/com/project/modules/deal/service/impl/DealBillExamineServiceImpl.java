package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.modules.deal.dao.DealBillExamineDao;
import com.project.modules.deal.entity.DealBillExamineEntity;
import com.project.modules.deal.service.DealBillExamineService;
import com.project.modules.deal.vo.invoking.DealBillExamineInvokingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 审核单Service
 *
 * @author liangyuding
 * @date 2020-05-56
 */
@Slf4j
@Service("dealBillExamineService")
public class DealBillExamineServiceImpl extends ServiceImpl<DealBillExamineDao, DealBillExamineEntity> implements DealBillExamineService {

    /**
     * 查询审核对象
     * @param billId
     * @param type
     * @return
     */
    @Override
    public DealBillExamineInvokingVo getExamineUser(String billId, Integer type) {
        return baseMapper.getExamineUser(billId, type);
    }

    /**
     * 新增审核单据
     * @param billId
     * @param type
     * @param remark
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(String billId, Integer type, String remark, Long sysUserId) {
        DealBillExamineEntity dealBillExamineEntity = new DealBillExamineEntity();
        dealBillExamineEntity
                .setBillId(billId)
                .setBillType(type)
                .setExamineUserId(sysUserId)
                .setExamineRemark(remark);
        save(dealBillExamineEntity);
    }

    /**
     * 获取单据的审核记录
     * @param billId
     * @param type
     * @return
     */
    @Override
    public List<DealBillExamineInvokingVo> getExamineList(String billId, Integer type) {
        return baseMapper.getExamineList(billId, type);
    }

}
