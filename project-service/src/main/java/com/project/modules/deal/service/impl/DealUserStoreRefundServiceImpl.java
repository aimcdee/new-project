package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.deal.dao.DealUserStoreRefundDao;
import com.project.modules.deal.entity.DealUserStoreRefundEntity;
import com.project.modules.deal.service.DealBillExamineService;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.service.DealUserStoreRefundService;
import com.project.modules.deal.vo.info.DealUserStoreRefundInfoVo;
import com.project.modules.deal.vo.invoking.DealBillExamineInvokingVo;
import com.project.modules.deal.vo.list.DealUserStoreRefundListVo;
import com.project.modules.deal.vo.save.DealUserStoreRefundSaveVo;
import com.project.modules.deal.vo.update.DealUserStoreRefundUpdateVo;
import com.project.modules.deal.vo.wx.info.DealUserStoreRefundWxInfoVo;
import com.project.modules.deal.vo.wx.list.DealUserStoreRefundWxListVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 企业用户退费Service
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Slf4j
@Service("dealUserStoreRefundService")
public class DealUserStoreRefundServiceImpl extends ServiceImpl<DealUserStoreRefundDao, DealUserStoreRefundEntity> implements DealUserStoreRefundService {


    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private DealBillExamineService dealBillExamineService;
    @Autowired
    private CreateNoAndIDUtils createNoAndIDUtils;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;

    /**
     * 分页查询退费单列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealUserStoreRefundListVo> page = new Query<DealUserStoreRefundListVo>(params).getPage();
        List<DealUserStoreRefundListVo> dealUserStoreRefundListVos = baseMapper.queryPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealPhone")),
                MapUtils.getInteger(params, "status"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        if (CollectionUtils.isNotEmpty(dealUserStoreRefundListVos)){
            dealUserStoreRefundListVos.forEach(dealUserStoreRefundListVo -> {
                DealBillExamineInvokingVo dealBillExamineInvokingVo = dealBillExamineService.getExamineUser(dealUserStoreRefundListVo.getRefundId(), Constant.BillType.REFUND.getType());
                if (Objects.nonNull(dealBillExamineInvokingVo)){
                    dealUserStoreRefundListVo
                            .setDealUserName(Optional.ofNullable(dealInvokingService.getDealUserNameById(dealUserStoreRefundListVo.getDealUserId())).orElse(Constant.DEFAUL_NAME))
                            .setExamineId(dealBillExamineInvokingVo.getExamineId())
                            .setExamineUserId(dealBillExamineInvokingVo.getExamineUserId())
                            .setExamineUserName(dealBillExamineInvokingVo.getExamineUserName())
                            .setExamineTime(dealBillExamineInvokingVo.getExamineTime());
                }
            });
        }
        return new PageUtils(page.setRecords(dealUserStoreRefundListVos));
    }

    /**
     * 企业客户查看提现记录
     * @param params
     * @return
     */
    @Override
    public PageUtils queryWxPage(Map<String, Object> params) {
        Page<DealUserStoreRefundWxListVo> page = new Query<DealUserStoreRefundWxListVo>(params).getPage();
        return new PageUtils(page.setRecords(baseMapper.queryWxPage(page, MapUtils.getLong(params, "dealStoreId"))));
    }

    /**
     * 新增退费单
     * @param refund
     * @param type
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(DealUserStoreRefundSaveVo refund, Integer type, Long sysUserId) {
        //新增前操作
        saveBefore(refund);
        save(getDealUserStoreRefundSaveEntity(refund, type, sysUserId));
    }

    /**
     * 获取退费单详情
     * @param refundId
     * @return
     */
    @Override
    public DealUserStoreRefundInfoVo info(String refundId) {
        DealUserStoreRefundInfoVo dealUserStoreRefundInfoVo = baseMapper.info(refundId);
        dealUserStoreRefundInfoVo
                .setDealBillExamineInvokingVo(dealBillExamineService.getExamineUser(refundId, Constant.BillType.REFUND.getType()))
                .setDealUserName(Optional.ofNullable(dealInvokingService.getDealUserNameById(dealUserStoreRefundInfoVo.getDealUserId())).orElse(Constant.DEFAUL_NAME));
        return dealUserStoreRefundInfoVo;
    }

    /**
     * 获取退费单详情
     * @param refundId
     * @return
     */
    @Override
    public DealUserStoreRefundWxInfoVo infoWx(String refundId) {
        DealUserStoreRefundWxInfoVo dealUserStoreRefundInfoVo = baseMapper.infoWx(refundId);
        dealUserStoreRefundInfoVo
                .setDealBillExamineInvokingVo(dealBillExamineService.getExamineUser(refundId, Constant.BillType.REFUND.getType()));
        return dealUserStoreRefundInfoVo;
    }

    /**
     * 更新退费单
     * @param refund
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateEntity(DealUserStoreRefundUpdateVo refund, Long sysUserId) {
        //更新前操作
        updateBefore(refund);
        updateById(getDealUserStoreRefundUpdateEntity(refund, sysUserId));
    }

    /**
     * 修改单据状态
     * @param refundId
     * @param remark
     * @param status
     * @param sysUserId
     * @param sysUserName
     */
    @Override
    @Transactional
    public void changeStatus(String refundId, String remark, Integer status, Long sysUserId, String sysUserName) {
        DealUserStoreRefundEntity dealUserStoreRefundEntity = getOne(new QueryWrapper<DealUserStoreRefundEntity>().eq("refund_id", refundId).last("LIMIT 1"));
        //更新状态前操作
        checkUpdateStatusBefore(dealUserStoreRefundEntity, status, sysUserId);
        //修改单据状态为不放弃是
        if (!status.equals(Constant.BillStatus.CANCEL.getStatus())){
            //新增审核单据
            dealBillExamineService.saveEntity(dealUserStoreRefundEntity.getRefundId(), Constant.BillType.REFUND.getType(), remark, sysUserId);
            dealUserStoreRefundEntity.setUpdateUserId(sysUserId);
        }
        //更新状态
        updateStatus(dealUserStoreRefundEntity, status);
        //如果单据修改后的状态为通过
        if (dealUserStoreRefundEntity.getStatus().equals(Constant.BillStatus.SUCCESS.getStatus())){
            //如果退费单是客户提现
            if (dealUserStoreRefundEntity.getRefundType().equals(Constant.RefundType.CASHOUT.getType())){
                //修改客户类型成个人和企业认证为作废
                dealInvokingService.changeUserStore(dealUserStoreRefundEntity.getDealStoreId());
            }else{
                //修改企业客户表的保证金总金额和信用等级
                dealInvokingService.changeUserPrice(dealUserStoreRefundEntity.getDealStoreId(), new BigDecimal(0).subtract(dealUserStoreRefundEntity.getRefundPrice()));
            }
        }
    }

    //更新状态
    private void updateStatus(DealUserStoreRefundEntity dealUserStoreRefundEntity, Integer status) {
        dealUserStoreRefundEntity.setStatus(status).setUpdateTime(new Date());
        updateById(dealUserStoreRefundEntity);
    }

    //更新状态前校验操作
    private void checkUpdateStatusBefore(DealUserStoreRefundEntity dealUserStoreRefundEntity, Integer status, Long sysUserId) {
        checkUtils.checkEntityNotNull(dealUserStoreRefundEntity);
        checkUtils.checkRefundRole(status, sysUserId, dealUserStoreRefundEntity.getStatus(), dealUserStoreRefundEntity.getCreateUserId());
    }

    //获取DealUserStoreRefundEntity更新对象
    private DealUserStoreRefundEntity getDealUserStoreRefundUpdateEntity(DealUserStoreRefundUpdateVo refund, Long sysUserId) {
        DealUserStoreRefundEntity dealUserStoreRefundEntity = getOne(new QueryWrapper<DealUserStoreRefundEntity>().eq("refund_id", refund.getRefundId()).last("LIMIT 1"));
        //校验更新对象属性非空
        checkUtils.checkNotNull(refund);
        //校验企业用户
        checkStoreStatus(dealUserStoreRefundEntity.getDealStoreId());
        dealUserStoreRefundEntity
                .setRefundPrice(refund.getRefundPrice())
                .setRemark(refund.getRemark())
                .setStatus(Constant.BillStatus.FINANCE.getStatus())
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        return dealUserStoreRefundEntity;
    }

    //更新前操作
    private void updateBefore(DealUserStoreRefundUpdateVo refund) {
        try {
            trimUtils.beanValueTrim(refund);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验金额
        checkUtils.checkPrice(refund.getRefundPrice());
    }

    //获取DealUserStoreRefundEntity新增对象
    private DealUserStoreRefundEntity getDealUserStoreRefundSaveEntity(DealUserStoreRefundSaveVo refund, Integer type, Long sysUserId) {
        DealUserStoreRefundEntity dealUserStoreRefundEntity = new DealUserStoreRefundEntity();
        dealUserStoreRefundEntity
                .setRefundId(createNoAndIDUtils.getDealRefundId())
                .setRefundNo(createNoAndIDUtils.getDealRefundCode())
                .setDealStoreId(refund.getDealStoreId())
                .setRefundPrice(refund.getRefundPrice())
                .setRemark(refund.getRemark())
                .setStatus(Constant.BillStatus.FINANCE.getStatus())
                .setRefundType(type)
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return dealUserStoreRefundEntity;
    }

    //新增前操作
    private void saveBefore(DealUserStoreRefundSaveVo refund) {
        try {
            trimUtils.beanValueTrim(refund);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Objects.isNull(refund.getDealStoreId())){
            throw new RRException("请选择需要退费的客户企业");
        }
        //校验企业用户
        checkStoreStatus(refund.getDealStoreId());
        //校验金额
        checkUtils.checkPrice(refund.getRefundPrice());
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
