package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.deal.dao.DealWaresDao;
import com.project.modules.deal.entity.DealWaresEntity;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.service.DealWaresExamineService;
import com.project.modules.deal.service.DealWaresImageService;
import com.project.modules.deal.service.DealWaresService;
import com.project.modules.deal.vo.info.DealWaresInfoVo;
import com.project.modules.deal.vo.invoking.DealStoreUserInvokingVo;
import com.project.modules.deal.vo.invoking.DealWaresExamineInvokingVo;
import com.project.modules.deal.vo.list.DealWaresListVo;
import com.project.modules.deal.vo.save.DealWaresSaveVo;
import com.project.modules.deal.vo.update.DealWaresUpdateVo;
import com.project.modules.deal.vo.wx.info.DealWaresWxInfoVo;
import com.project.modules.deal.vo.wx.info.DealWaresWxRetailInfoVo;
import com.project.modules.deal.vo.wx.info.DealWaresWxStoreInfoVo;
import com.project.modules.deal.vo.wx.list.DealWaresWxPersonalListVo;
import com.project.modules.deal.vo.wx.list.DealWaresWxRetailListVo;
import com.project.modules.deal.vo.wx.list.DealWaresWxStoreListVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 企业客户商品Service
 *
 * @author liangyuding
 * @date 2020-06-02
 */
@Slf4j
@Service("dealWaresService")
public class DealWaresServiceImpl extends ServiceImpl<DealWaresDao, DealWaresEntity> implements DealWaresService {

    @Autowired
    private DealWaresExamineService dealWaresExamineService;
    @Autowired
    private DealWaresImageService dealWaresImageService;
    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private CreateNoAndIDUtils createNoAndIDUtils;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;

    /**
     * 分页企业商品列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealWaresListVo> page = new Query<DealWaresListVo>(params).getPage();
        List<DealWaresListVo> waresLists = baseMapper.queryPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealWaresTitle")),
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couModelId"),
                MapUtils.getInteger(params, "marketYear"),
                MapUtils.getLong(params, "dealStoreId"),
                MapUtils.getLong(params, "proAreaId"),
                MapUtils.getLong(params, "cityAreaId"),
                MapUtils.getLong(params, "countyAreaId"),
                MapUtils.getInteger(params, "status"),
                MapUtils.getInteger(params, "onlineStatus"),
                MapUtils.getInteger(params, "sellStatus"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        waresLists.forEach(wares -> {
            String couBrandName = dealInvokingService.getCouBrandNameById(wares.getCouBrandId());
            String couSeriesName = dealInvokingService.getCouSeriesNameById(wares.getCouSeriesId());
            DealWaresExamineInvokingVo dealWaresExamineInvokingVo = dealWaresExamineService.getExamineUser(wares.getDealWaresId());
            DealStoreUserInvokingVo storeUser = dealInvokingService.getDealStoreUserInvokingVoByStoreId(wares.getDealStoreId());
            wares
                    .setDealWaresTitle(new StringBuilder(couBrandName).append(" ").append(couSeriesName).append(" ").append(wares.getDealWaresTitle()).toString())
                    .setCouBrandName(couBrandName).setCouSeriesName(couSeriesName)
                    .setCouModelName(dealInvokingService.getCouModelNameById(wares.getCouModelId()))
                    .setExamineId(Optional.ofNullable(dealWaresExamineInvokingVo).map(DealWaresExamineInvokingVo :: getExamineId).orElse(null))
                    .setExamineUserId(Optional.ofNullable(dealWaresExamineInvokingVo).map(DealWaresExamineInvokingVo :: getExamineUserId).orElse(null))
                    .setExamineUserName(Optional.ofNullable(dealWaresExamineInvokingVo).map(DealWaresExamineInvokingVo :: getExamineUserName).orElse(null))
                    .setExamineTime(Optional.ofNullable(dealWaresExamineInvokingVo).map(DealWaresExamineInvokingVo :: getExamineTime).orElse(null))
                    .setCoverImage(dealWaresImageService.getImage(wares.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()))
                    .setWaresImages(dealWaresImageService.getImageList(wares.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.NO.getType()))
                    .setDriveImage(dealWaresImageService.getImage(wares.getDealWaresId(), Constant.ImageType.DRIVE.getType(), Constant.IsWaresCover.NO.getType()))
                    .setDealUserId(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserId).orElse(null))
                    .setDealUserName(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserName).orElse(null));
        });
        return new PageUtils(page.setRecords(waresLists));
    }

    /**
     * 企业客户分页查询自己上传的企业商品列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPersonalPage(Map<String, Object> params) {
        Page<DealWaresWxPersonalListVo> page = new Query<DealWaresWxPersonalListVo>(params).getPage();
        List<DealWaresWxPersonalListVo> personaList = baseMapper.queryPersonalPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealWaresTitle")),
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couModelId"),
                MapUtils.getInteger(params, "marketYear"),
                MapUtils.getLong(params, "dealStoreId"),
                MapUtils.getLong(params, "proAreaId"),
                MapUtils.getLong(params, "cityAreaId"),
                MapUtils.getLong(params, "countyAreaId"),
                MapUtils.getInteger(params, "status"),
                MapUtils.getInteger(params, "onlineStatus"),
                MapUtils.getInteger(params, "sellStatus"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        personaList.forEach(personal -> {
            String couBrandName = dealInvokingService.getCouBrandNameById(personal.getCouBrandId());
            String couSeriesName = dealInvokingService.getCouSeriesNameById(personal.getCouSeriesId());
            personal
                    .setDealWaresTitle(new StringBuilder(couBrandName).append(" ").append(couSeriesName).append(" ").append(personal.getDealWaresTitle()).toString())
                    .setCouBrandName(couBrandName).setCouSeriesName(couSeriesName)
                    .setCouModelName(dealInvokingService.getCouModelNameById(personal.getCouModelId()))
                    .setCoverImage(dealWaresImageService.getImage(personal.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()));
        });
        return new PageUtils(page.setRecords(personaList));
    }

    /**
     * 企业端分页查询商品列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryStorePage(Map<String, Object> params) {
        Page<DealWaresWxStoreListVo> page = new Query<DealWaresWxStoreListVo>(params).getPage();
        List<DealWaresWxStoreListVo> storeList = baseMapper.queryStorePage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealWaresTitle")),
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couModelId"),
                MapUtils.getInteger(params, "marketYear"),
                MapUtils.getLong(params, "proAreaId"),
                MapUtils.getLong(params, "cityAreaId"),
                MapUtils.getLong(params, "countyAreaId"),
                Constant.WaresStatus.SUSSESS.getStatus(),
                Constant.WaresOnLineStatus.ONLINE.getStatus(),
                Constant.WaresSellStatus.UNSALE.getStatus(),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        storeList.forEach(store -> {
            String couBrandName = dealInvokingService.getCouBrandNameById(store.getCouBrandId());
            String couSeriesName = dealInvokingService.getCouSeriesNameById(store.getCouSeriesId());
            store
                    .setDealWaresTitle(new StringBuilder(couBrandName).append(" ").append(couSeriesName).append(" ").append(store.getDealWaresTitle()).toString())
                    .setCouBrandName(couBrandName).setCouSeriesName(couSeriesName)
                    .setCouModelName(dealInvokingService.getCouModelNameById(store.getCouModelId()))
                    .setCoverImage(dealWaresImageService.getImage(store.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()));
        });
        return new PageUtils(page.setRecords(storeList));
    }

    /**
     * 零售端分页查询商品列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryRetailPage(Map<String, Object> params) {
        Page<DealWaresWxRetailListVo> page = new Query<DealWaresWxRetailListVo>(params).getPage();
        List<DealWaresWxRetailListVo> retailList = baseMapper.queryRetailPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealWaresTitle")),
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couModelId"),
                MapUtils.getInteger(params, "marketYear"),
                MapUtils.getLong(params, "proAreaId"),
                MapUtils.getLong(params, "cityAreaId"),
                MapUtils.getLong(params, "countyAreaId"),
                Constant.WaresStatus.SUSSESS.getStatus(),
                Constant.WaresOnLineStatus.ONLINE.getStatus(),
                Constant.WaresSellStatus.UNSALE.getStatus(),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        retailList.forEach(retail -> {
            String couBrandName = dealInvokingService.getCouBrandNameById(retail.getCouBrandId());
            String couSeriesName = dealInvokingService.getCouSeriesNameById(retail.getCouSeriesId());
            retail
                    .setDealWaresTitle(new StringBuilder(couBrandName).append(" ").append(couSeriesName).append(" ").append(retail.getDealWaresTitle()).toString())
                    .setCouBrandName(dealInvokingService.getCouBrandNameById(retail.getCouBrandId()))
                    .setCoverImage(dealWaresImageService.getImage(retail.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()));
        });
        return new PageUtils(page.setRecords(retailList));
    }

    /**
     * 企业客户新增商品
     * @param wares
     */
    @Override
    @Transactional
    public void saveEntity(DealWaresSaveVo wares) {
        checkPermissions(wares.getDealStoreId(), null);
        try {
            trimUtils.beanValueTrim(wares);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("操作失败,类元素去空失败");
        }
        checkUtils.checkStoreIdAndWaresImage(wares.getDealStoreId(), wares.getCoverImage(), wares.getWaresImages(), wares.getDriveImage());
        DealWaresEntity dealWaresEntity = getDealWaresSaveEntity(wares);
        save(dealWaresEntity);
        //保存行驶证图
        dealWaresImageService.saveEntity(dealWaresEntity.getDealWaresId(), wares.getDriveImage(), Constant.ImageType.DRIVE.getType(), Constant.IsWaresCover.NO.getType());
        //保存封面图
        dealWaresImageService.saveEntity(dealWaresEntity.getDealWaresId(), wares.getCoverImage(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType());
        //保存商品图
        wares.getWaresImages().forEach(waresImage -> {
            dealWaresImageService.saveEntity(dealWaresEntity.getDealWaresId(), waresImage, Constant.ImageType.WARES.getType(), Constant.IsWaresCover.NO.getType());
        });
    }

    /**
     * 获取企业商品的详情
     * @param dealWaresId
     * @return
     */
    @Override
    public DealWaresInfoVo info(String dealWaresId) {
        DealWaresInfoVo waresInfo = baseMapper.info(dealWaresId);
        DealStoreUserInvokingVo storeUser = dealInvokingService.getDealStoreUserInvokingVoByStoreId(waresInfo.getDealStoreId());
        waresInfo
                .setCouBrandName(dealInvokingService.getCouBrandNameById(waresInfo.getCouBrandId()))
                .setCouSeriesName(dealInvokingService.getCouSeriesNameById(waresInfo.getCouSeriesId()))
                .setCouModelName(dealInvokingService.getCouModelNameById(waresInfo.getCouModelId()))
                .setCoverImage(dealWaresImageService.getImage(waresInfo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()))
                .setWaresImages(dealWaresImageService.getImageList(waresInfo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.NO.getType()))
                .setDriveImage(dealWaresImageService.getImage(waresInfo.getDealWaresId(), Constant.ImageType.DRIVE.getType(), Constant.IsWaresCover.NO.getType()))
                .setDealUserId(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserId).orElse(null))
                .setDealUserName(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserName).orElse(null))
                .setDealUserPhone(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserPhone).orElse(null));
        return waresInfo;
    }

    /**
     * 客户获取自己企业商品的详情
     * @param dealWaresId
     * @return
     */
    @Override
    public DealWaresWxInfoVo infoWx(String dealWaresId) {
        DealWaresWxInfoVo waresWxInfoVo = baseMapper.infoWx(dealWaresId);
        DealStoreUserInvokingVo storeUser = dealInvokingService.getDealStoreUserInvokingVoByStoreId(waresWxInfoVo.getDealStoreId());
        waresWxInfoVo
                .setSexLable(Constant.SexValue.getSexLabeValue(waresWxInfoVo.getSex()))
                .setCouBrandName(dealInvokingService.getCouBrandNameById(waresWxInfoVo.getCouBrandId()))
                .setCouSeriesName(dealInvokingService.getCouSeriesNameById(waresWxInfoVo.getCouSeriesId()))
                .setCouModelName(dealInvokingService.getCouModelNameById(waresWxInfoVo.getCouModelId()))
                .setIsMaintainLable(Constant.DefaultField.DefaultFieldValue(waresWxInfoVo.getIsMaintain()))
                .setIsMortgageLable(Constant.DefaultField.DefaultFieldValue(waresWxInfoVo.getIsMortgage()))
                .setIsTransferLable(Constant.DefaultField.DefaultFieldValue(waresWxInfoVo.getIsTransfer()))
                .setCoverImage(dealWaresImageService.getImage(waresWxInfoVo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()))
                .setWaresImages(dealWaresImageService.getImageList(waresWxInfoVo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.NO.getType()))
                .setDriveImage(dealWaresImageService.getImage(waresWxInfoVo.getDealWaresId(), Constant.ImageType.DRIVE.getType(), Constant.IsWaresCover.NO.getType()))
                .setDealUserId(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserId).orElse(null))
                .setDealUserName(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserName).orElse(null));
        return waresWxInfoVo;
    }

    /**
     * 获取企业端商品的详情
     * @param dealWaresId
     * @return
     */
    @Override
    public DealWaresWxStoreInfoVo store(String dealWaresId) {
        DealWaresWxStoreInfoVo storeInfo = baseMapper.store(dealWaresId);
        String couBrandName = dealInvokingService.getCouBrandNameById(storeInfo.getCouBrandId());
        String couSeriesName = dealInvokingService.getCouSeriesNameById(storeInfo.getCouSeriesId());
        storeInfo
                .setDealWaresTitle(new StringBuilder(couBrandName).append(" ").append(couSeriesName).append(" ").append(storeInfo.getDealWaresTitle()).toString())
                .setCouBrandName(couBrandName).setCouSeriesName(couSeriesName)
                .setCouModelName(dealInvokingService.getCouModelNameById(storeInfo.getCouModelId()))
                .setCoverImage(dealWaresImageService.getImage(storeInfo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()))
                .setWaresImages(dealWaresImageService.getImageList(storeInfo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.NO.getType()));
        return storeInfo;
    }

    /**
     * 获取零售端商品的详情
     * @param dealWaresId
     * @return
     */
    @Override
    public DealWaresWxRetailInfoVo retail(String dealWaresId) {
        DealWaresWxRetailInfoVo retailInfo = baseMapper.retail(dealWaresId);
        String couBrandName = dealInvokingService.getCouBrandNameById(retailInfo.getCouBrandId());
        String couSeriesName = dealInvokingService.getCouSeriesNameById(retailInfo.getCouSeriesId());
        retailInfo
                .setDealWaresTitle(new StringBuilder(couBrandName).append(" ").append(couSeriesName).append(" ").append(retailInfo.getDealWaresTitle()).toString())
                .setCouBrandName(couBrandName).setCouSeriesName(couSeriesName)
                .setCouModelName(dealInvokingService.getCouModelNameById(retailInfo.getCouModelId()))
                .setCoverImage(dealWaresImageService.getImage(retailInfo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()))
                .setWaresImages(dealWaresImageService.getImageList(retailInfo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.NO.getType()));
        return retailInfo;
    }

    /**
     * 企业客户更新商品信息
     * @param wares
     */
    @Override
    @Transactional
    public void updateEntity(DealWaresUpdateVo wares) {
        checkPermissions(wares.getDealStoreId(), wares.getDealWaresId());
        try {
            trimUtils.beanValueTrim(wares);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("操作失败,类元素去空失败");
        }
        checkUtils.checkStoreIdAndWaresImage(wares.getDealStoreId(), wares.getCoverImage(), wares.getWaresImages(), wares.getDriveImage());
        DealWaresEntity dealWaresEntity = getDealWaresUpdateEntity(wares);
        updateById(dealWaresEntity);
        dealWaresImageService.deleteEntity(dealWaresEntity.getDealWaresId());
        //保存行驶证图
        dealWaresImageService.saveEntity(dealWaresEntity.getDealWaresId(), wares.getDriveImage(), Constant.ImageType.DRIVE.getType(), Constant.IsWaresCover.NO.getType());
        //保存封面图
        dealWaresImageService.saveEntity(dealWaresEntity.getDealWaresId(), wares.getCoverImage(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType());
        //保存商品图
        wares.getWaresImages().forEach(waresImage -> {
            dealWaresImageService.saveEntity(dealWaresEntity.getDealWaresId(), waresImage, Constant.ImageType.WARES.getType(), Constant.IsWaresCover.NO.getType());
        });
    }

    /**
     * 修改企业商品审核状态
     * @param dealWaresId
     * @param remark
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(String dealWaresId, String remark, Integer status, Long sysUserId) {
        DealWaresEntity dealWaresEntity = getOne(new QueryWrapper<DealWaresEntity>().eq("deal_wares_id", dealWaresId).last("LIMIT 1"));
        //更新状态前校验操作
        checkUpdateStatusBefore(dealWaresEntity, status);
        //更新商品审核状态
        dealWaresEntity.setStatus(status).setUpdateTime(new Date());
        updateById(dealWaresEntity);
        //新增审核单据
        dealWaresExamineService.saveEntity(dealWaresId, remark, sysUserId);
    }

    /**
     * 修改企业商品上线状态
     * @param dealWaresId
     * @param dealStoreId
     * @param onLineStatus
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeOnLineStatus(String dealWaresId, Long dealStoreId, Integer onLineStatus, Long sysUserId) {
        DealWaresEntity dealWaresEntity = getOne(new QueryWrapper<DealWaresEntity>().eq("deal_wares_id", dealWaresId).last("LIMIT 1"));
        //更新状态前校验操作
        checkUpdateOnLineStatusBefore(dealWaresEntity, dealStoreId);
        //更新企业商品在线状态
        updateOnLineStatus(dealWaresEntity, onLineStatus);
    }

    /**
     * 修改企业商品出售情况为已出售
     * @param dealWaresId
     * @param status
     */
    @Override
    @Transactional
    public void changeSellStatus(String dealWaresId, Integer status) {
        DealWaresEntity dealWaresEntity = getOne(new QueryWrapper<DealWaresEntity>().eq("deal_wares_id", dealWaresId).last("LIMIT 1"));
        if (dealWaresEntity.getOnlineStatus().equals(Constant.WaresOnLineStatus.UNLINE.getStatus())){
            throw new RRException("操作失败,该商品已下架");
        }
        dealWaresEntity.setSellStatus(status);
        updateOnLineStatus(dealWaresEntity, Constant.WaresOnLineStatus.UNLINE.getStatus());
    }

    //更新企业商品上线状态
    private void updateOnLineStatus(DealWaresEntity dealWaresEntity, Integer onLineStatus) {
        dealWaresEntity.setOnlineStatus(onLineStatus).setUpdateTime(new Date());
        updateById(dealWaresEntity);
    }

    //更新审核状态前操作
    private void checkUpdateStatusBefore(DealWaresEntity dealWaresEntity, Integer status) {
        checkUtils.checkEntityNotNull(dealWaresEntity);
        checkUtils.checkSysRole(dealWaresEntity.getOnlineStatus(), dealWaresEntity.getSellStatus(), status, dealWaresEntity.getStatus());
    }

    //更新上线状态前操作
    private void checkUpdateOnLineStatusBefore(DealWaresEntity dealWaresEntity, Long dealStoreId) {
        checkUtils.checkEntityNotNull(dealWaresEntity);
        checkUtils.checkRole(dealWaresEntity.getSellStatus(), dealWaresEntity.getStatus(), dealStoreId, dealWaresEntity.getDealStoreId());
    }

    //获取DealWaresEntity更新对象
    private DealWaresEntity getDealWaresUpdateEntity(DealWaresUpdateVo wares) {
        DealWaresEntity dealWaresEntity = getOne(new QueryWrapper<DealWaresEntity>().eq("deal_wares_id", wares.getDealWaresId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(dealWaresEntity);
        if (dealWaresEntity.getOnlineStatus().equals(Constant.WaresOnLineStatus.ONLINE.getStatus()) || dealWaresEntity.getSellStatus().equals(Constant.WaresSellStatus.SALE.getStatus())){
            throw new RRException("操作失败,该商品已上线或已出售");
        }
        try {
            dealWaresEntity = (DealWaresEntity) JavaBeanUtils.mapToJavaBean(DealWaresEntity.class, JavaBeanUtils.javaBeanToMap(wares));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("操作失败,类转换失败");
        }
        dealWaresEntity
                .setProAreaName(dealInvokingService.getAreaNameById(wares.getProAreaId()))
                .setCityAreaName(dealInvokingService.getAreaNameById(wares.getCityAreaId()))
                .setCountyAreaName(dealInvokingService.getAreaNameById(wares.getCountyAreaId()))
                .setStatus(Constant.WaresStatus.SALE.getStatus())
                .setSubmitTime(new Date())
                .setUpdateTime(new Date());
        return dealWaresEntity;
    }

    //获取DealWaresEntity新增对象
    private DealWaresEntity getDealWaresSaveEntity(DealWaresSaveVo wares) {
        DealWaresEntity dealWaresEntity = new DealWaresEntity();
        try {
            dealWaresEntity = (DealWaresEntity) JavaBeanUtils.mapToJavaBean(DealWaresEntity.class, JavaBeanUtils.javaBeanToMap(wares));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("操作失败,类转换失败");
        }
        dealWaresEntity
                .setDealWaresId(createNoAndIDUtils.getDealWaresId())
                .setDealWaresNo(createNoAndIDUtils.getDealWaresCode())
                .setProAreaName(dealInvokingService.getAreaNameById(wares.getProAreaId()))
                .setCityAreaName(dealInvokingService.getAreaNameById(wares.getCityAreaId()))
                .setCountyAreaName(dealInvokingService.getAreaNameById(wares.getCountyAreaId()))
                .setStatus(Constant.WaresStatus.SALE.getStatus())
                .setSellStatus(Constant.WaresSellStatus.UNSALE.getStatus())
                .setOnlineStatus(Constant.WaresOnLineStatus.UNLINE.getStatus());
        return dealWaresEntity;
    }

    //新增或更新校验客户权限
    private void checkPermissions(Long storeId, String dealWareId){
        if (null == dealWareId){
            if (dealInvokingService.checkUserStore(storeId, Constant.StoreType.ENTERPRISE.getType()) <= 0){
                throw new RRException("新增商品失败,该名客户不是企业用户");
            }
        } else {
            if (dealInvokingService.checkUserStore(storeId, Constant.StoreType.ENTERPRISE.getType()) <= 0){
                throw new RRException("更新商品失败,该名客户不是企业用户");
            }

            if (dealInvokingService.checkWaresStore(storeId, dealWareId) <= 0){
                throw new RRException("更新商品失败,此条商品不属于该客户");
            }
        }
    }
}
