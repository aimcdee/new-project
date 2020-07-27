package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.cou.vo.Invoking.CouWaresNameAndYearInvokingVo;
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
import com.project.modules.deal.vo.wx.*;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private DDateUtils dDateUtils;
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
                MapUtils.getLong(params, "releaseAreaId"),
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couWaresId"),
                MapUtils.getLong(params, "couModelId"),
                MapUtils.getLong(params, "dealStoreId"),
                MapUtils.getLong(params, "proAreaId"),
                MapUtils.getLong(params, "cityAreaId"),
                MapUtils.getLong(params, "countyAreaId"),
                MapUtils.getInteger(params, "onlineStatus"),
                MapUtils.getInteger(params, "sellStatus"),
                dDateUtils.getDate(params, "startTime"),
                dDateUtils.getDate(params, "endTime"));
        waresLists.forEach(wares -> {
            DealWaresExamineInvokingVo dealWaresExamineInvokingVo = dealWaresExamineService.getExamineUser(wares.getDealWaresId());
            DealStoreUserInvokingVo storeUser = dealInvokingService.getDealStoreUserInvokingVoByStoreId(wares.getDealStoreId());
            wares
                    .setCouBrandName(dealInvokingService.getCouBrandNameById(wares.getCouBrandId()))
                    .setCouSeriesName(dealInvokingService.getCouSeriesNameById(wares.getCouSeriesId()))
                    .setCouWaresName(dealInvokingService.getCouWaresNameById(wares.getCouWaresId()))
                    .setCouModelName(dealInvokingService.getCouModelNameById(wares.getCouModelId()))
                    .setExamineId(Optional.ofNullable(dealWaresExamineInvokingVo.getExamineId()).orElse(null))
                    .setExamineUserId(Optional.ofNullable(dealWaresExamineInvokingVo.getExamineUserId()).orElse(null))
                    .setExamineUserName(Optional.ofNullable(dealWaresExamineInvokingVo.getExamineUserName()).orElse(null))
                    .setExamineTime(Optional.ofNullable(dealWaresExamineInvokingVo.getExamineTime()).orElse(null))
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
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couWaresId"),
                MapUtils.getLong(params, "couModelId"),
                MapUtils.getLong(params, "dealStoreId"),
                MapUtils.getLong(params, "proAreaId"),
                MapUtils.getLong(params, "cityAreaId"),
                MapUtils.getLong(params, "countyAreaId"),
                MapUtils.getInteger(params, "onlineStatus"),
                MapUtils.getInteger(params, "sellStatus"),
                dDateUtils.getDate(params, "startTime"),
                dDateUtils.getDate(params, "endTime"));
        personaList.forEach(personal -> {
            personal
                    .setDealWaresTitle(new StringBuilder().append(personal.getCouBrandName()).append(" ").append(personal.getDealWaresTitle()).toString())
                    .setCouBrandName(dealInvokingService.getCouBrandNameById(personal.getCouBrandId()))
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
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couWaresId"),
                MapUtils.getLong(params, "couModelId"),
                MapUtils.getLong(params, "proAreaId"),
                MapUtils.getLong(params, "cityAreaId"),
                MapUtils.getLong(params, "countyAreaId"),
                Constant.WaresOnLineStatus.ONLINE.getStatus(),
                Constant.WaresSellStatus.UNSALE.getStatus(),
                dDateUtils.getDate(params, "startTime"),
                dDateUtils.getDate(params, "endTime"));
        storeList.forEach(store -> {
            store
                    .setDealWaresTitle(new StringBuilder().append(store.getCouBrandName()).append(" ").append(store.getDealWaresTitle()).toString())
                    .setCouBrandName(dealInvokingService.getCouBrandNameById(store.getCouBrandId()))
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
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couWaresId"),
                MapUtils.getLong(params, "couModelId"),
                MapUtils.getLong(params, "proAreaId"),
                MapUtils.getLong(params, "cityAreaId"),
                MapUtils.getLong(params, "countyAreaId"),
                Constant.WaresOnLineStatus.ONLINE.getStatus(),
                Constant.WaresSellStatus.UNSALE.getStatus(),
                dDateUtils.getDate(params, "startTime"),
                dDateUtils.getDate(params, "endTime"));
        retailList.forEach(retail -> {
            retail
                    .setDealWaresTitle(new StringBuilder().append(retail.getCouBrandName()).append(" ").append(retail.getDealWaresTitle()).toString())
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
        checkStoreStatus(wares.getDealStoreId());
        try {
            trimUtils.beanValueTrim(wares);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkUtils.checkNotNull(wares);
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
                .setCouWaresName(dealInvokingService.getCouWaresNameById(waresInfo.getCouWaresId()))
                .setCouModelName(dealInvokingService.getCouModelNameById(waresInfo.getCouModelId()))
                .setCoverImage(dealWaresImageService.getImage(waresInfo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.YES.getType()))
                .setWaresImages(dealWaresImageService.getImageList(waresInfo.getDealWaresId(), Constant.ImageType.WARES.getType(), Constant.IsWaresCover.NO.getType()))
                .setDriveImage(dealWaresImageService.getImage(waresInfo.getDealWaresId(), Constant.ImageType.DRIVE.getType(), Constant.IsWaresCover.NO.getType()))
                .setDealUserId(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserId).orElse(null))
                .setDealUserName(Optional.ofNullable(storeUser).map(DealStoreUserInvokingVo::getDealUserName).orElse(null));
        return waresInfo;
    }

    /**
     * 获取企业端商品的详情
     * @param dealWaresId
     * @return
     */
    @Override
    public DealWaresWxStoreInfoVo store(String dealWaresId) {
        DealWaresWxStoreInfoVo storeInfo = baseMapper.store(dealWaresId);
        storeInfo
                .setCouBrandName(dealInvokingService.getCouBrandNameById(storeInfo.getCouBrandId()))
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
        retailInfo
                .setCouBrandName(dealInvokingService.getCouBrandNameById(retailInfo.getCouBrandId()))
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
        checkStoreStatus(wares.getDealStoreId());
        try {
            trimUtils.beanValueTrim(wares);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkUtils.checkNotNull(wares);
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
     * 审核企业商品上线状态
     * @param dealWaresId
     * @param remark
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeOnLineStatus(String dealWaresId, String remark, Integer status, Long sysUserId) {
        DealWaresEntity dealWaresEntity = getOne(new QueryWrapper<DealWaresEntity>().eq("deal_wares_id", dealWaresId).last("LIMIT 1"));
        //更新状态前校验操作
        checkUpdateStatusBefore(dealWaresEntity, status);
        //修改单据状态不为下架时
        if (!status.equals(Constant.WaresOnLineStatus.UNLINE.getStatus())){
            //新增审核单据
            dealWaresExamineService.saveEntity(dealWaresId, remark, sysUserId);
        }
        //更新企业商品在线状态
        updateOnLineStatus(dealWaresEntity, status);
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

    //更新企业商品在线状态
    private void updateOnLineStatus(DealWaresEntity dealWaresEntity, Integer status) {
        dealWaresEntity.setOnlineStatus(status).setUpdateTime(new Date());
        updateById(dealWaresEntity);
    }

    //更新状态前操作
    private void checkUpdateStatusBefore(DealWaresEntity dealWaresEntity, Integer status) {
        checkUtils.checkEntityNotNull(dealWaresEntity);
        checkUtils.checkWareRole(status, dealWaresEntity.getOnlineStatus(), dealWaresEntity.getSellStatus());
    }

    //获取DealWaresEntity更新对象
    private DealWaresEntity getDealWaresUpdateEntity(DealWaresUpdateVo wares) {
        DealWaresEntity dealWaresEntity = getOne(new QueryWrapper<DealWaresEntity>().eq("deal_wares_id", wares.getDealWaresId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(dealWaresEntity);
        String couSeriesName = dealInvokingService.getCouSeriesNameById(wares.getCouSeriesId());
        CouWaresNameAndYearInvokingVo couWares = dealInvokingService.getCouWaresNameAndYearById(wares.getCouWaresId());
        dealWaresEntity
                .setDealWaresTitle(new StringBuilder().append(couSeriesName).append(" ").append(couWares.getCouWaresName()).toString())
                .setReleaseAreaId(wares.getReleaseAreaId())
                .setReleaseAreaName(dealInvokingService.getAreaNameById(wares.getReleaseAreaId()))
                .setContactPhone(wares.getContactPhone())
                .setContactName(wares.getContactName())
                .setSex(wares.getSex())
                .setWaresFrameCode(wares.getWaresFrameCode())
                .setCouBrandId(wares.getCouBrandId())
                .setCouSeriesId(wares.getCouSeriesId())
                .setCouWaresId(wares.getCouWaresId())
                .setCouModelId(wares.getCouModelId())
                .setTradePrice(wares.getTradePrice())
                .setRetailPrice(wares.getRetailPrice())
                .setRegisterTime(wares.getRegisterTime())
                .setDistance(wares.getDistance())
                .setLicenseId(wares.getLicenseId())
                .setLicenseCode(dealInvokingService.getLicenseCodeById(wares.getLicenseId()))
                .setProAreaId(wares.getProAreaId())
                .setProAreaName(dealInvokingService.getAreaNameById(wares.getProAreaId()))
                .setCityAreaId(wares.getCityAreaId())
                .setCityAreaName(dealInvokingService.getAreaNameById(wares.getCityAreaId()))
                .setCountyAreaId(wares.getCountyAreaId())
                .setCountyAreaName(dealInvokingService.getAreaNameById(wares.getCountyAreaId()))
                .setAddr(wares.getAddr())
                .setWaresRemark(wares.getWaresRemark())
                .setTransferNum(wares.getTransferNum())
                .setIsMaintain(wares.getIsMaintain())
                .setIsMortgage(wares.getIsMortgage())
                .setIsTransfer(wares.getIsTransfer())
                .setSellStatus(Constant.WaresSellStatus.UNSALE.getStatus())
                .setOnlineStatus(Constant.WaresOnLineStatus.SALE.getStatus())
                .setSubmitTime(new Date())
                .setUpdateTime(new Date())
                .setDealStoreId(wares.getDealStoreId());
        return dealWaresEntity;
    }

    //获取DealWaresEntity新增对象
    private DealWaresEntity getDealWaresSaveEntity(DealWaresSaveVo wares) {
        DealWaresEntity dealWaresEntity = new DealWaresEntity();
        String couSeriesName = dealInvokingService.getCouSeriesNameById(wares.getCouSeriesId());
        CouWaresNameAndYearInvokingVo couWares = dealInvokingService.getCouWaresNameAndYearById(wares.getCouWaresId());
        dealWaresEntity
                .setDealWaresId(createNoAndIDUtils.getDealWaresId())
                .setDealWaresTitle(new StringBuilder().append(couSeriesName).append(" ").append(couWares.getCouWaresName()).toString())
                .setDealWaresNo(createNoAndIDUtils.getDealWaresCode())
                .setReleaseAreaId(wares.getReleaseAreaId())
                .setReleaseAreaName(dealInvokingService.getAreaNameById(wares.getReleaseAreaId()))
                .setContactPhone(wares.getContactPhone())
                .setContactName(wares.getContactName())
                .setSex(wares.getSex())
                .setWaresFrameCode(wares.getWaresFrameCode())
                .setCouBrandId(wares.getCouBrandId())
                .setCouSeriesId(wares.getCouSeriesId())
                .setCouWaresId(wares.getCouWaresId())
                .setCouModelId(wares.getCouModelId())
                .setTradePrice(wares.getTradePrice())
                .setRetailPrice(wares.getRetailPrice())
                .setRegisterTime(wares.getRegisterTime())
                .setDistance(wares.getDistance())
                .setLicenseId(wares.getLicenseId())
                .setLicenseCode(dealInvokingService.getLicenseCodeById(wares.getLicenseId()))
                .setProAreaId(wares.getProAreaId())
                .setProAreaName(dealInvokingService.getAreaNameById(wares.getProAreaId()))
                .setCityAreaId(wares.getCityAreaId())
                .setCityAreaName(dealInvokingService.getAreaNameById(wares.getCityAreaId()))
                .setCountyAreaId(wares.getCountyAreaId())
                .setCountyAreaName(dealInvokingService.getAreaNameById(wares.getCountyAreaId()))
                .setAddr(wares.getAddr())
                .setWaresRemark(wares.getWaresRemark())
                .setTransferNum(wares.getTransferNum())
                .setIsMaintain(wares.getIsMaintain())
                .setIsMortgage(wares.getIsMortgage())
                .setIsTransfer(wares.getIsTransfer())
                .setSellStatus(Constant.WaresSellStatus.UNSALE.getStatus())
                .setOnlineStatus(Constant.WaresOnLineStatus.SALE.getStatus())
                .setDealStoreId(wares.getDealStoreId());
        return dealWaresEntity;
    }

    //校验客户是否是企业客户或该企业认证单是否已通过审核
    private void checkStoreStatus(Long storeId) {
        if (dealInvokingService.checkUserStore(storeId, Constant.StoreType.ENTERPRISE.getType()) <= 0){
            throw new RRException("新增商品失败,该名客户不是企业用户");
        }
        if (dealInvokingService.checkStoreStatus(storeId, Constant.Examine.SUCCESS.getExamine()) <= 0){
            throw new RRException("新增商品失败,该名客户此条企业认证信息还未通过");
        }
    }
}
