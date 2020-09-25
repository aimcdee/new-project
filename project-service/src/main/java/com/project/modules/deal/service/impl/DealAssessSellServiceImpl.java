package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.modules.deal.dao.DealAssessSellDao;
import com.project.modules.deal.entity.DealAssessSellEntity;
import com.project.modules.deal.service.DealAssessSellService;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.vo.info.DealAssessSellInfoVo;
import com.project.modules.deal.vo.list.DealAssessSellListVo;
import com.project.modules.deal.vo.save.DealAssessSellSaveVo;
import com.project.modules.deal.vo.update.DealAssessSellUpdateVo;
import com.project.modules.deal.vo.wx.DealAssessSellWxInfoVo;
import com.project.modules.deal.vo.wx.DealAssessSellWxListVo;
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

/**
 * 评估商品出售Service
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Slf4j
@Service("dealAssessSellService")
public class DealAssessSellServiceImpl extends ServiceImpl<DealAssessSellDao, DealAssessSellEntity> implements DealAssessSellService {

    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private CheckUtils checkUtils;

    /**
     * 分页查询评估商品出售列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealAssessSellListVo> page = new Query<DealAssessSellListVo>(params).getPage();
        List<DealAssessSellListVo> dealAssessSellListVos = baseMapper.queryPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "contactName")),
                StringUtils.trim(MapUtils.getString(params, "contactPhone")),
                MapUtils.getInteger(params, "status"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        if (CollectionUtils.isNotEmpty(dealAssessSellListVos)){
            dealAssessSellListVos.forEach(dealAssessSellListVo -> {
                dealAssessSellListVo
                        .setSysUserName(dealInvokingService.getSysUserNameBySysUserId(dealAssessSellListVo.getSysUserId()));
            });
        }
        return new PageUtils(page.setRecords(dealAssessSellListVos));
    }

    /**
     * 分页查询个人评估商品出售列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryWxPage(Map<String, Object> params) {
        Page<DealAssessSellWxListVo> page = new Query<DealAssessSellWxListVo>(params).getPage();
        List<DealAssessSellWxListVo> dealAssessSellListVos = baseMapper.queryWxPage(page, MapUtils.getLong(params, "dealUserId"));
        if (CollectionUtils.isNotEmpty(dealAssessSellListVos)){
            dealAssessSellListVos.forEach(dealAssessSellListVo -> {
                dealAssessSellListVo
                        .setSysUserName(dealInvokingService.getSysUserNameBySysUserId(dealAssessSellListVo.getSysUserId()));
            });
        }
        return new PageUtils(page.setRecords(dealAssessSellListVos));
    }

    /**
     * 新增评估商品出售
     * @param sell
     */
    @Override
    @Transactional
    public void saveEntity(DealAssessSellSaveVo sell) {
        try {
            trimUtils.beanValueTrim(sell);
        } catch (Exception e) {
            e.printStackTrace();
        }
        save(getDealAssessSellSaveEntity(sell));
        dealInvokingService.updateAssessSellStstus(sell.getDealAssessId(), Constant.AssessSellStatus.PROCESSING.getStatus());
    }

    /**
     * 根据评估商品出售ID获取评估商品出售详情
     * @param dealSellId
     * @return
     */
    @Override
    public DealAssessSellInfoVo info(Long dealSellId) {
        DealAssessSellInfoVo dealAssessSellInfoVo = baseMapper.info(dealSellId);
        dealAssessSellInfoVo
                .setSysUserName(dealInvokingService.getSysUserNameBySysUserId(dealAssessSellInfoVo.getSysUserId()));
        return dealAssessSellInfoVo;
    }

    /**
     * 根据评估商品出售ID获取个人评估商品出售详情
     * @param dealSellId
     * @return
     */
    @Override
    public DealAssessSellWxInfoVo infoWx(Long dealSellId) {
        DealAssessSellWxInfoVo dealAssessSellWxInfoVo = baseMapper.infoWx(dealSellId);
        dealAssessSellWxInfoVo
                .setSysUserName(dealInvokingService.getSysUserNameBySysUserId(dealAssessSellWxInfoVo.getSysUserId()));
        return dealAssessSellWxInfoVo;
    }

    /**
     * 修改评估商品出售信息
     * @param sell
     */
    @Override
    @Transactional
    public void updateEntity(DealAssessSellUpdateVo sell) {
        try {
            trimUtils.beanValueTrim(sell);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateById(getDealAssessSellUpdateEntity(sell));
    }

    /**
     * 修改评估商品出售状态
     * @param dealSellId
     * @param status
     * @param followUserId
     * @param sellPrice
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long dealSellId, Integer status, Long followUserId, BigDecimal sellPrice, Long sysUserId) {
        DealAssessSellEntity dealAssessSellEntity = getOne(new QueryWrapper<DealAssessSellEntity>().eq("deal_sell_id", dealSellId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(dealAssessSellEntity);
        //校验当前商品出售单据与修改的状态
        checkUtils.checkAssessStatus(status, dealAssessSellEntity.getStatus());
        if (status.equals(Constant.DropInStatus.PROCESSING.getStatus())){
            dealAssessSellEntity.setSysUserId(followUserId);
        } else {
            if (status.equals(Constant.DropInStatus.SUCCESS.getStatus())){
                dealAssessSellEntity.setSellPrice(sellPrice);
                dealInvokingService.updateAssessSellStstus(dealAssessSellEntity.getDealAssessId(), Constant.AssessSellStatus.SUCCESS.getStatus());
            }if (status.equals(Constant.DropInStatus.CANCEL.getStatus())){
                dealInvokingService.updateAssessSellStstus(dealAssessSellEntity.getDealAssessId(), Constant.AssessSellStatus.INREVIEW.getStatus());
            }
        }
        dealAssessSellEntity.setStatus(status).setExamineUserId(sysUserId).setExamineTime(new Date());
        updateById(dealAssessSellEntity);
    }

    //获取DealAssessSellEntity更新对象
    private DealAssessSellEntity getDealAssessSellUpdateEntity(DealAssessSellUpdateVo sell) {
        DealAssessSellEntity dealAssessSellEntity = getOne(new QueryWrapper<DealAssessSellEntity>().eq("deal_sell_id", sell.getDealSellId()).eq("status", Constant.DropInStatus.INREVIEW.getStatus()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(dealAssessSellEntity);
        try {
            dealAssessSellEntity = (DealAssessSellEntity) JavaBeanUtils.mapToJavaBean(DealAssessSellEntity.class, JavaBeanUtils.javaBeanToMap(sell));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dealAssessSellEntity
                .setProAreaName(dealInvokingService.getAreaNameById(sell.getProAreaId()))
                .setCityAreaName(dealInvokingService.getAreaNameById(sell.getCityAreaId()))
                .setCountyAreaName(dealInvokingService.getAreaNameById(sell.getCountyAreaId()));
        return dealAssessSellEntity;
    }

    //获取DealAssessSellEntity新增对象
    private DealAssessSellEntity getDealAssessSellSaveEntity(DealAssessSellSaveVo sell) {
        String assessWaresTitle = dealInvokingService.getAssessWaresTitle(sell.getDealAssessId(), Constant.WaresSellStatus.UNSALE.getStatus());
        DealAssessSellEntity dealAssessSellEntity = new DealAssessSellEntity();
        try {
            dealAssessSellEntity = (DealAssessSellEntity) JavaBeanUtils.mapToJavaBean(DealAssessSellEntity.class, JavaBeanUtils.javaBeanToMap(sell));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dealAssessSellEntity
                .setAssessWaresTitle(assessWaresTitle)
                .setProAreaName(dealInvokingService.getAreaNameById(sell.getProAreaId()))
                .setCityAreaName(dealInvokingService.getAreaNameById(sell.getCityAreaId()))
                .setCountyAreaName(dealInvokingService.getAreaNameById(sell.getCountyAreaId()))
                .setSellPrice(new BigDecimal(0))
                .setDealUserId(dealInvokingService.getDealUserIdByDealAssessId(sell.getDealAssessId(), Constant.AssessSellStatus.INREVIEW.getStatus()))
                .setStatus(Constant.DropInStatus.INREVIEW.getStatus());
        return dealAssessSellEntity;
    }
}
