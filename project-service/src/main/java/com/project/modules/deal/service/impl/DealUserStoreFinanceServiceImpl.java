package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.modules.deal.dao.DealUserStoreFinanceDao;
import com.project.modules.deal.entity.DealUserStoreFinanceEntity;
import com.project.modules.deal.service.DealBillExamineService;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.service.DealUserStoreDepositService;
import com.project.modules.deal.service.DealUserStoreFinanceService;
import com.project.modules.deal.vo.info.DealUserStoreFinanceInfoVo;
import com.project.modules.deal.vo.list.DealUserStoreFinanceListVo;
import com.project.modules.deal.vo.save.DealUserStoreDepositSaveVo;
import com.project.modules.deal.vo.save.DealUserStoreFinanceSaveVo;
import com.project.modules.deal.vo.wx.DealUserStoreFinanceWxListVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 企业用户金融单Service
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Slf4j
@Service("dealUserStoreFinanceService")
public class DealUserStoreFinanceServiceImpl extends ServiceImpl<DealUserStoreFinanceDao, DealUserStoreFinanceEntity> implements DealUserStoreFinanceService {

    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private DDateUtils dDateUtils;
    @Autowired
    private DealBillExamineService dealBillExamineService;
    @Autowired
    private DealUserStoreDepositService dealUserStoreDepositService;
    @Autowired
    private CreateNoAndIDUtils createNoAndIDUtils;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private CheckUtils checkUtils;

    /**
     * 分页查询金融单列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealUserStoreFinanceListVo> page = new Query<DealUserStoreFinanceListVo>(params).getPage();
        List<DealUserStoreFinanceListVo> financeList = baseMapper.queryPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealPhone")),
                StringUtils.trim(MapUtils.getString(params, "contactPhone")),
                StringUtils.trim(MapUtils.getString(params, "contactName")),
                MapUtils.getInteger(params, "status"),
                dDateUtils.getDate(params, "startTime"),
                dDateUtils.getDate(params, "endTime"));
        if (CollectionUtils.isNotEmpty(financeList)){
            financeList.forEach(finance ->{
                finance.setSysUserName(dealInvokingService.getSysUserNameBySysUserId(finance.getSysUserId()));
            });
        }
        return new PageUtils(page.setRecords(financeList));
    }

    /**
     * 企业客户分页查询金融单列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryWxPage(Map<String, Object> params) {
        Page<DealUserStoreFinanceWxListVo> page = new Query<DealUserStoreFinanceWxListVo>(params).getPage();
        List<DealUserStoreFinanceWxListVo> financeWxList = baseMapper.queryWxPage(page, MapUtils.getLong(params, "dealPhone"));
        if (CollectionUtils.isNotEmpty(financeWxList)){
            financeWxList.forEach(financeWx ->{
                financeWx.setSysUserName(dealInvokingService.getSysUserNameBySysUserId(financeWx.getSysUserId()));
            });
        }
        return new PageUtils(page.setRecords(financeWxList));
    }

    /**
     * 新增金融单
     * @param finance
     */
    @Override
    @Transactional
    public void saveEntity(DealUserStoreFinanceSaveVo finance) {
        try {
            trimUtils.beanValueTrim(finance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkUtils.checkNotNull(finance);
        save(getDealUserStoreFinanceSaveEntity(finance));
    }

    /**
     * 获取金融单详情
     * @param financeId
     * @return
     */
    @Override
    public DealUserStoreFinanceInfoVo info(String financeId) {
        DealUserStoreFinanceInfoVo dealUserStoreFinanceInfoVo = baseMapper.info(financeId);
        dealUserStoreFinanceInfoVo
                .setSysUserName(dealInvokingService.getSysUserNameBySysUserId(dealUserStoreFinanceInfoVo.getSysUserId()))
                .setDealBillExamineInvokingVo(dealBillExamineService.getExamineUser(financeId, Constant.BillType.FINANCE.getType()));
        return dealUserStoreFinanceInfoVo;
    }

    /**
     * 修改金融单状态
     * @param financeId
     * @param status
     * @param followUserId
     * @param financePrice
     * @param sysUserId
     * @param sysUserName
     */
    @Override
    @Transactional
    public void changeStatus(String financeId, Integer status, Long followUserId, BigDecimal financePrice, Long sysUserId, String sysUserName) {
        DealUserStoreFinanceEntity dealUserStoreFinanceEntity = getOne(new QueryWrapper<DealUserStoreFinanceEntity>().eq("finance_id", financeId).last("LIMIT 1"));
        checkUtils.checkFinanceStatus(dealUserStoreFinanceEntity.getStatus(), status);
        if (status.equals(Constant.FinanceStatus.CHECKPENDING.getStatus())){
            dealUserStoreFinanceEntity.setSysUserId(followUserId).setSysUserName(dealInvokingService.getSysUserNameBySysUserId(followUserId));
        }
        dealUserStoreFinanceEntity
                .setStatus(status)
                .setFinancePrice(Optional.ofNullable(financePrice).orElse(new BigDecimal(0)))
                .setUpdateTime(new Date());
        updateById(dealUserStoreFinanceEntity);
        //新增审核单据
        dealBillExamineService.saveEntity(financeId, Constant.BillType.FINANCE.getType(), Constant.DEFAUL_REMARK, sysUserId);
        if (status.equals(Constant.FinanceStatus.SUCCESS.getStatus())){
            DealUserStoreDepositSaveVo dealUserStoreDepositSaveVo = new DealUserStoreDepositSaveVo();
            dealUserStoreDepositSaveVo.setDealStoreId(dealUserStoreFinanceEntity.getDealStoreId()).setDepositPrice(Constant.DEFAUL_PRICE).setRemark(Constant.DEFAUL_REMARK);
            dealUserStoreDepositService.saveEntity(dealUserStoreDepositSaveVo, dealUserStoreFinanceEntity.getSysUserId());
        }
    }

    //获取DealUserStoreFinanceEntity新增对象
    private DealUserStoreFinanceEntity getDealUserStoreFinanceSaveEntity(DealUserStoreFinanceSaveVo finance) {
        DealUserStoreFinanceEntity dealUserStoreFinanceEntity = new DealUserStoreFinanceEntity();
        dealUserStoreFinanceEntity
                .setFinanceId(createNoAndIDUtils.getDealFinanceId())
                .setFinanceNo(createNoAndIDUtils.getDealFinanceCode())
                .setDealStoreId(finance.getDealStoreId())
                .setContactName(finance.getContactName())
                .setContactPhone(finance.getContactPhone())
                .setSex(finance.getSex())
                .setFinancePrice(new BigDecimal(0))
                .setStatus(Constant.FinanceStatus.INREVIEW.getStatus());
        return dealUserStoreFinanceEntity;
    }
}
