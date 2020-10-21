package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.deal.dao.DealUserStoreDepositDao;
import com.project.modules.deal.entity.DealUserStoreDepositEntity;
import com.project.modules.deal.service.DealBillExamineService;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.service.DealUserStoreDepositService;
import com.project.modules.deal.vo.info.DealUserStoreDepositInfoVo;
import com.project.modules.deal.vo.invoking.DealBillExamineInvokingVo;
import com.project.modules.deal.vo.list.DealUserStoreDepositListVo;
import com.project.modules.deal.vo.save.DealUserStoreDepositSaveVo;
import com.project.modules.deal.vo.update.DealUserStoreDepositUpdateVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 企业用户保证金单Service
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Slf4j
@Service("dealUserStoreDepositService")
public class DealUserStoreDepositServiceImpl extends ServiceImpl<DealUserStoreDepositDao, DealUserStoreDepositEntity> implements DealUserStoreDepositService {

    @Autowired
    private DealBillExamineService dealBillExamineService;
    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private CreateNoAndIDUtils createNoAndIDUtils;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;

    /**
     * 分页查询保证金单列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealUserStoreDepositListVo> page = new Query<DealUserStoreDepositListVo>(params).getPage();
        List<DealUserStoreDepositListVo> dealUserStoreDepositListVos = baseMapper.queryPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealPhone")),
                MapUtils.getInteger(params, "status"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        if (CollectionUtils.isNotEmpty(dealUserStoreDepositListVos)){
            dealUserStoreDepositListVos.forEach(dealUserStoreDepositListVo -> {
                DealBillExamineInvokingVo dealBillExamineInvokingVo = dealBillExamineService.getExamineUser(dealUserStoreDepositListVo.getDepositId(), Constant.BillType.DEPOSIT.getType());
                if (Objects.nonNull(dealBillExamineInvokingVo)){
                    dealUserStoreDepositListVo
                            .setExamineId(dealBillExamineInvokingVo.getExamineId())
                            .setExamineUserId(dealBillExamineInvokingVo.getExamineUserId())
                            .setExamineUserName(dealBillExamineInvokingVo.getExamineUserName())
                            .setExamineTime(dealBillExamineInvokingVo.getExamineTime());
                }
            });
        }
        return new PageUtils(page.setRecords(dealUserStoreDepositListVos));
    }

    /**
     * 新增保证金单
     * @param deposit
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(DealUserStoreDepositSaveVo deposit, Long sysUserId) {
        //新增前操作
        saveBefore(deposit);
        save(getDealUserStoreDepositSaveEntity(deposit, sysUserId));
    }

    /**
     * 获取保证金单详情
     * @param depositId
     * @return
     */
    @Override
    @Transactional
    public DealUserStoreDepositInfoVo info(String depositId) {
        DealUserStoreDepositInfoVo dealUserStoreDepositInfoVo = baseMapper.info(depositId);
        dealUserStoreDepositInfoVo.setExamine(dealBillExamineService.getExamineUser(depositId, Constant.BillType.DEPOSIT.getType()));
        return dealUserStoreDepositInfoVo;
    }

    /**
     * 更新保证金单
     * @param deposit
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateEntity(DealUserStoreDepositUpdateVo deposit, Long sysUserId) {
        //更新前操作
        updateBefore(deposit);
        updateById(getDealUserStoreDepositUpdateEntity(deposit, sysUserId));
    }

    /**
     * 修改单据审核状态
     * @param depositId
     * @param remark
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(String depositId, String remark, Integer status, Long sysUserId) {
        //更新状态前操作
        DealUserStoreDepositEntity dealUserStoreDepositEntity = getUpdateStatusBefore(depositId, status, sysUserId);
        //修改单据状态不为放弃时
        if (!status.equals(Constant.BillStatus.CANCEL.getStatus())){
            //新增审核单据
            dealBillExamineService.saveEntity(dealUserStoreDepositEntity.getDepositId(), Constant.BillType.DEPOSIT.getType(), remark, sysUserId);
        }
        dealUserStoreDepositEntity.setStatus(status);
        updateById(dealUserStoreDepositEntity);
        //如果单据修改后的状态为通过
        if (dealUserStoreDepositEntity.getStatus().equals(Constant.BillStatus.SUCCESS.getStatus())){
            //修改客户表的保证金总金额和信用等级
            dealInvokingService.changeUserPrice(dealUserStoreDepositEntity.getDealStoreId(), dealUserStoreDepositEntity.getDepositPrice());
        }
    }

    //更新状态前操作
    private DealUserStoreDepositEntity getUpdateStatusBefore(String depositId, Integer status, Long sysUserId) {
        DealUserStoreDepositEntity dealUserStoreDepositEntity = getOne(new QueryWrapper<DealUserStoreDepositEntity>().eq("deposit_id", depositId).last("LIMIT 1"));
        checkUtils.checkDepositRole(status, sysUserId, dealUserStoreDepositEntity.getStatus(), dealUserStoreDepositEntity.getCreateUserId());
        dealUserStoreDepositEntity.setUpdateUserId(sysUserId).setUpdateTime(new Date());
        checkUtils.checkEntityNotNull(dealUserStoreDepositEntity);
        return dealUserStoreDepositEntity;
    }

    //获取DealUserStoreDepositEntity更新对象
    private DealUserStoreDepositEntity getDealUserStoreDepositUpdateEntity(DealUserStoreDepositUpdateVo deposit, Long sysUserId) {
        DealUserStoreDepositEntity dealUserStoreDepositEntity = getOne(new QueryWrapper<DealUserStoreDepositEntity>().eq("deposit_id", deposit.getDepositId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(dealUserStoreDepositEntity);
        checkStoreStatus(dealUserStoreDepositEntity.getDealStoreId());
        dealUserStoreDepositEntity
                .setDepositPrice(deposit.getDepositPrice())
                .setRemark(deposit.getRemark())
                .setStatus(Constant.BillStatus.FINANCE.getStatus())
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        return dealUserStoreDepositEntity;
    }

    //更新前操作
    private void updateBefore(DealUserStoreDepositUpdateVo deposit) {
        try {
            trimUtils.beanValueTrim(deposit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验金额
        checkUtils.checkPrice(deposit.getDepositPrice());
    }

    //获取DealUserStoreDepositEntity新增对象
    private DealUserStoreDepositEntity getDealUserStoreDepositSaveEntity(DealUserStoreDepositSaveVo deposit, Long sysUserId) {
        DealUserStoreDepositEntity dealUserStoreDepositEntity = new DealUserStoreDepositEntity();
        dealUserStoreDepositEntity
                .setDepositId(createNoAndIDUtils.getDealDepositId())
                .setDepositNo(createNoAndIDUtils.getDealDepositCode())
                .setDealStoreId(deposit.getDealStoreId())
                .setDepositPrice(deposit.getDepositPrice())
                .setRemark(deposit.getRemark())
                .setStatus(Constant.BillStatus.FINANCE.getStatus())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return dealUserStoreDepositEntity;
    }

    //新增前操作
    private void saveBefore(DealUserStoreDepositSaveVo deposit) {
        try {
            trimUtils.beanValueTrim(deposit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验更新对象属性非空
        checkUtils.checkNotNull(deposit);
        //校验企业用户
        checkStoreStatus(deposit.getDealStoreId());
        //校验金额
        checkUtils.checkPrice(deposit.getDepositPrice());
    }

    //校验客户是否是企业客户或该企业认证单是否已通过审核
    private void checkStoreStatus(Long storeId) {
        if (dealInvokingService.checkUserStore(storeId, Constant.StoreType.ENTERPRISE.getType()) <= 0){
            throw new RRException("新增保证金失败,该名客户不是企业用户");
        }
        if (dealInvokingService.checkStoreStatus(storeId, Constant.Examine.SUCCESS.getExamine()) <= 0){
            throw new RRException("新增保证金失败,该名客户此条企业认证信息还未通过");
        }
    }
}
