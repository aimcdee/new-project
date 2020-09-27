package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.deal.dao.DealWaresInstallmentDao;
import com.project.modules.deal.entity.DealWaresInstallmentEntity;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.service.DealWaresInstallmentService;
import com.project.modules.deal.vo.info.DealWaresInstallmentInfoVo;
import com.project.modules.deal.vo.list.DealWaresInstallmentListVo;
import com.project.modules.deal.vo.save.DealWaresInstallmentSaveVo;
import com.project.modules.deal.vo.wx.list.DealWaresInstallmentWxListVo;
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
 * 咨询分期客户管理表Service
 *
 * @author liangyuding
 * @date 2020-06-06
 */
@Slf4j
@Service("dealWaresInstallmentService")
public class DealWaresInstallmentServiceImpl extends ServiceImpl<DealWaresInstallmentDao, DealWaresInstallmentEntity> implements DealWaresInstallmentService {

    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private CheckUtils checkUtils;

    /**
     * 分页查询咨询分期客户列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealWaresInstallmentListVo> page = new Query<DealWaresInstallmentListVo>(params).getPage();
        List<DealWaresInstallmentListVo> installmentList = baseMapper.queryPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealUserName")),
                MapUtils.getLong(params, "dealUserId"),
                StringUtils.trim(MapUtils.getString(params, "contactName")),
                StringUtils.trim(MapUtils.getString(params, "dealWaresTitle")),
                StringUtils.trim(MapUtils.getString(params, "contactPhone")),
                MapUtils.getInteger(params, "followStatus"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        if (CollectionUtils.isNotEmpty(installmentList)){
            installmentList.forEach(installment ->{
                installment.setSysUserName(dealInvokingService.getSysUserNameBySysUserId(installment.getSysUserId()));
            });
        }
        return new PageUtils(page.setRecords(installmentList));
    }

    /**
     * 客户分页查询个人咨询分期记录列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryWxPage(Map<String, Object> params) {
        Page<DealWaresInstallmentWxListVo> page = new Query<DealWaresInstallmentWxListVo>(params).getPage();
        List<DealWaresInstallmentWxListVo> installmentWxList = baseMapper.queryWxPage(
                page,
                MapUtils.getLong(params, "dealUserId"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        if (CollectionUtils.isNotEmpty(installmentWxList)){
            installmentWxList.forEach(installmentWx ->{
                installmentWx.setSysUserName(dealInvokingService.getSysUserNameBySysUserId(installmentWx.getSysUserId()));
            });
        }
        return new PageUtils(page.setRecords(installmentWxList));
    }

    /**
     * 新增咨询分期客户
     * @param installment
     */
    @Override
    @Transactional
    public void saveEntity(DealWaresInstallmentSaveVo installment) {
        try {
            trimUtils.beanValueTrim(installment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("操作失败,去空失败");
        }
        if (Objects.isNull(installment.getDealUserId())){
            throw new RRException("操作失败,请选择正在执行的所属客户");
        }
        save(getDealWaresInstallmentSaveEntity(installment));
    }

    //获取DealWaresInstallmentEntity新增对象
    private DealWaresInstallmentEntity getDealWaresInstallmentSaveEntity(DealWaresInstallmentSaveVo installment) {
        DealWaresInstallmentEntity dealWaresInstallmentEntity = new DealWaresInstallmentEntity();
        try {
            dealWaresInstallmentEntity = (DealWaresInstallmentEntity) JavaBeanUtils.mapToJavaBean(DealWaresInstallmentEntity.class, JavaBeanUtils.javaBeanToMap(installment));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("操作失败,类转换失败");
        }
        dealWaresInstallmentEntity
                .setDealWaresTitle(dealInvokingService.getDealWaresTitleById(installment.getDealWaresId()))
                .setFollowStatus(Constant.InstallmentStatus.CHECKPENDING.getStatus());
        return dealWaresInstallmentEntity;
    }

    /**
     * 获取咨询分期客户详情
     * @param installmentId
     * @return
     */
    @Override
    public DealWaresInstallmentInfoVo info(Long installmentId) {
        return baseMapper.info(installmentId);
    }

    /**
     * 修改咨询分期单据跟进状态
     * @param installmentId
     * @param followRemark
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long installmentId, String followRemark, Integer status, Long sysUserId) {
        DealWaresInstallmentEntity dealWaresInstallmentEntity = getOne(new QueryWrapper<DealWaresInstallmentEntity>().eq("installment_id", installmentId).last("LIMIT 1"));
        checkUtils.checkInstallmentStatus(dealWaresInstallmentEntity.getFollowStatus(), status);
        dealWaresInstallmentEntity
                .setFollowStatus(status)
                .setSysUserId(sysUserId)
                .setFollowRemark(followRemark)
                .setFollowTime(new Date());
        updateById(dealWaresInstallmentEntity);
    }
}
