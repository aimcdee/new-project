package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.deal.dao.DealAssessDao;
import com.project.modules.deal.entity.DealAssessEntity;
import com.project.modules.deal.service.DealAssessImageService;
import com.project.modules.deal.service.DealAssessService;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.vo.info.DealAssessInfoVo;
import com.project.modules.deal.vo.list.DealAssessListVo;
import com.project.modules.deal.vo.save.DealAssessSaveVo;
import com.project.modules.deal.vo.update.DealAssessUpdateVo;
import com.project.modules.deal.vo.wx.info.DealAssessWxInfoVo;
import com.project.modules.deal.vo.wx.list.DealAssessWxListVo;
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
import java.util.Objects;

/**
 * 商品评估Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@Service("dealAssessService")
public class DealAssessServiceImpl extends ServiceImpl<DealAssessDao, DealAssessEntity> implements DealAssessService {

    @Autowired
    private DealAssessImageService dealAssessImageService;
    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private CheckUtils checkUtils;

    /**
     * 分页查询商品评估列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealAssessListVo> page = new Query<DealAssessListVo>(params).getPage();
        List<DealAssessListVo> assessList = baseMapper.queryPage(
                page,
                MapUtils.getLong(params, "dealUserId"),
                MapUtils.getInteger(params, "status"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        if (CollectionUtils.isNotEmpty(assessList)){
            assessList.forEach(assess -> {
                assess
                        .setCouBrandName(dealInvokingService.getCouBrandNameById(assess.getCouBrandId()))
                        .setCouSeriesName(dealInvokingService.getCouSeriesNameById(assess.getCouSeriesId()))
                        //设置行驶证图对象
                        .setDriveImage(dealAssessImageService.getDriveImage(assess.getDealAssessId(), Constant.ImageType.DRIVE.getType()))
                        //设置评估商品图片路径
                        .setWaresImages(dealAssessImageService.getWaresImages(assess.getDealAssessId(), Constant.ImageType.WARES.getType()));
            });
        }
        return new PageUtils(page.setRecords(assessList));
    }

    /**
     * 分页查询微信端个人商品评估列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryWxPage(Map<String, Object> params) {
        Page<DealAssessWxListVo> page = new Query<DealAssessWxListVo>(params).getPage();
        List<DealAssessWxListVo> assessWxList = baseMapper.queryWxPage(page, MapUtils.getLong(params, "dealUserId"));
        if (CollectionUtils.isNotEmpty(assessWxList)){
            assessWxList.forEach(assessWx -> {
                assessWx
                        //设置评估商品图片路径
                        .setWaresImages(dealAssessImageService.getWaresImages(assessWx.getDealAssessId(), Constant.ImageType.WARES.getType()));
            });
        }
        return new PageUtils(page.setRecords(assessWxList));
    }

    /**
     * 新增商品评估
     * @param assess
     */
    @Override
    @Transactional
    public void saveEntity(DealAssessSaveVo assess) {
        try {
            trimUtils.beanValueTrim(assess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.isNull(assess.getDealUserId())){
            throw new RRException("请选择所属客户");
        }
        DealAssessEntity dealAssessEntity = getDealWaresAssessSaveEntity(assess);
        save(dealAssessEntity);
        //保存行驶证图
        dealAssessImageService.saveEntity(dealAssessEntity.getDealAssessId(), assess.getDriveImage(), Constant.ImageType.DRIVE.getType());
        //保存商品评估图
        assess.getWaresImages().forEach(waresImage -> {
            dealAssessImageService.saveEntity(dealAssessEntity.getDealAssessId(), waresImage, Constant.ImageType.WARES.getType());
        });
    }

    /**
     * 根据商品评估ID获取商品评估详情
     * @param dealAssessId
     * @return
     */
    @Override
    public DealAssessInfoVo info(Long dealAssessId) {
        DealAssessInfoVo assessInfo = baseMapper.info(dealAssessId);
        if (Objects.nonNull(assessInfo)){
            assessInfo
                    .setCouBrandName(dealInvokingService.getCouBrandNameById(assessInfo.getCouBrandId()))
                    .setCouSeriesName(dealInvokingService.getCouSeriesNameById(assessInfo.getCouSeriesId()))
                    //设置行驶证图对象
                    .setDriveImage(dealAssessImageService.getDriveImage(dealAssessId, Constant.ImageType.DRIVE.getType()))
                    //设置评估商品图集合对象
                    .setWaresImages(dealAssessImageService.getWaresImages(dealAssessId, Constant.ImageType.WARES.getType()));
        }
        return assessInfo;
    }

    /**
     * 根据商品评估ID获取微信端商品评估详情
     * @param dealAssessId
     * @return
     */
    @Override
    public DealAssessWxInfoVo infoWx(Long dealAssessId) {
        DealAssessWxInfoVo assessWxInfo = baseMapper.infoWx(dealAssessId);
        if (Objects.nonNull(assessWxInfo)){
            assessWxInfo
                    //设置评估商品图集合对象
                    .setWaresImages(dealAssessImageService.getWaresImages(dealAssessId, Constant.ImageType.WARES.getType()));
        }
        return assessWxInfo;
    }

    /**
     * 评估价钱
     * @param assess
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateEntity(DealAssessUpdateVo assess, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(assess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验更新对象属性非空
        checkUtils.checkNotNull(assess);
        DealAssessEntity dealAssessEntity = getOne(new QueryWrapper<DealAssessEntity>().eq("deal_assess_id", assess.getDealAssessId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(dealAssessEntity);
        dealAssessEntity
                .setDealAssessPrice(assess.getDealAssessPrice())
                .setStatus(Constant.AssessStatus.PENDING.getStatus())
                .setExamineUserId(sysUserId).setExamineTime(new Date());
        updateById(dealAssessEntity);
    }

    /**
     * 审核客户提交的评估作废
     * @param dealAssessId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long dealAssessId, Integer status, Long sysUserId) {
        DealAssessEntity dealAssessEntity = getOne(new QueryWrapper<DealAssessEntity>().eq("deal_assess_id", dealAssessId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(dealAssessEntity);
        dealAssessEntity.setStatus(status).setExamineUserId(sysUserId).setExamineTime(new Date());
        updateById(dealAssessEntity);
    }

    //设置DealWaresAssessEntity新增对象
    private DealAssessEntity getDealWaresAssessSaveEntity(Object data) {
        DealAssessEntity dealAssessEntity = getTransformationJavaBean(data);
        dealAssessEntity
                .setProAreaName(dealInvokingService.getAreaNameById(dealAssessEntity.getProAreaId()))
                .setCityAreaName(dealInvokingService.getAreaNameById(dealAssessEntity.getCityAreaId()))
                .setCountyAreaName(dealInvokingService.getAreaNameById(dealAssessEntity.getCountyAreaId()))
                .setDealAssessPrice(new BigDecimal(0))
                .setStatus(Constant.AssessStatus.CHECKPENDING.getStatus())
                .setSellStatus(Constant.AssessSellStatus.INREVIEW.getStatus());
        return dealAssessEntity;
    }

    //装换对象
    private DealAssessEntity getTransformationJavaBean(Object data){
        DealAssessEntity dealAssessEntity = new DealAssessEntity();
        try {
            dealAssessEntity = (DealAssessEntity) JavaBeanUtils.mapToJavaBean(DealAssessEntity.class, JavaBeanUtils.javaBeanToMap(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dealAssessEntity;
    }

}
