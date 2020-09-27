package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealWaresEntity;
import com.project.modules.deal.vo.info.DealWaresInfoVo;
import com.project.modules.deal.vo.list.DealWaresListVo;
import com.project.modules.deal.vo.wx.info.DealWaresWxRetailInfoVo;
import com.project.modules.deal.vo.wx.info.DealWaresWxStoreInfoVo;
import com.project.modules.deal.vo.wx.list.DealWaresWxPersonalListVo;
import com.project.modules.deal.vo.wx.list.DealWaresWxRetailListVo;
import com.project.modules.deal.vo.wx.list.DealWaresWxStoreListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 企业客户商品Dao
 *
 * @author liangyuding
 * @date 2020-06-02
 */
@Mapper
public interface DealWaresDao extends BaseMapper<DealWaresEntity> {

    /**
     * 分页企业商品列表
     * @param page
     * @param dealWaresTitle
     * @param releaseAreaId
     * @param couBrandId
     * @param couSeriesId
     * @param couModelId
     * @param marketYear
     * @param dealStoreId
     * @param proAreaId
     * @param cityAreaId
     * @param countyAreaId
     * @param onlineStatus
     * @param sellStatus
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealWaresListVo> queryPage(
            Page<DealWaresListVo> page,
            @Param("dealWaresTitle") String dealWaresTitle,
            @Param("releaseAreaId") Long releaseAreaId,
            @Param("couBrandId") Long couBrandId,
            @Param("couSeriesId") Long couSeriesId,
            @Param("couModelId") Long couModelId,
            @Param("marketYear") Integer marketYear,
            @Param("dealStoreId") Long dealStoreId,
            @Param("proAreaId") Long proAreaId,
            @Param("cityAreaId") Long cityAreaId,
            @Param("countyAreaId") Long countyAreaId,
            @Param("onlineStatus") Integer onlineStatus,
            @Param("sellStatus") Integer sellStatus,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 企业客户分页查询自己上传的企业商品列表
     * @param page
     * @param dealWaresTitle
     * @param couBrandId
     * @param couSeriesId
     * @param couModelId
     * @param dealStoreId
     * @param proAreaId
     * @param cityAreaId
     * @param countyAreaId
     * @param onlineStatus
     * @param sellStatus
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealWaresWxPersonalListVo> queryPersonalPage(
            Page<DealWaresWxPersonalListVo> page,
            @Param("dealWaresTitle") String dealWaresTitle,
            @Param("couBrandId") Long couBrandId,
            @Param("couSeriesId") Long couSeriesId,
            @Param("couModelId") Long couModelId,
            @Param("marketYear") Integer marketYear,
            @Param("dealStoreId") Long dealStoreId,
            @Param("proAreaId") Long proAreaId,
            @Param("cityAreaId") Long cityAreaId,
            @Param("countyAreaId") Long countyAreaId,
            @Param("onlineStatus") Integer onlineStatus,
            @Param("sellStatus") Integer sellStatus,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 企业端分页查询商品列表
     * @param page
     * @param dealWaresTitle
     * @param couBrandId
     * @param couSeriesId
     * @param couModelId
     * @param proAreaId
     * @param cityAreaId
     * @param countyAreaId
     * @param onlineStatus
     * @param sellStatus
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealWaresWxStoreListVo> queryStorePage(
            Page<DealWaresWxStoreListVo> page,
            @Param("dealWaresTitle") String dealWaresTitle,
            @Param("couBrandId") Long couBrandId,
            @Param("couSeriesId") Long couSeriesId,
            @Param("couModelId") Long couModelId,
            @Param("marketYear") Integer marketYear,
            @Param("proAreaId") Long proAreaId,
            @Param("cityAreaId") Long cityAreaId,
            @Param("countyAreaId") Long countyAreaId,
            @Param("onlineStatus") Integer onlineStatus,
            @Param("sellStatus") Integer sellStatus,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 零售端分页查询商品列表
     * @param page
     * @param dealWaresTitle
     * @param couBrandId
     * @param couSeriesId
     * @param couModelId
     * @param proAreaId
     * @param cityAreaId
     * @param countyAreaId
     * @param onlineStatus
     * @param sellStatus
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealWaresWxRetailListVo> queryRetailPage(
            Page<DealWaresWxRetailListVo> page,
            @Param("dealWaresTitle") String dealWaresTitle,
            @Param("couBrandId") Long couBrandId,
            @Param("couSeriesId") Long couSeriesId,
            @Param("couModelId") Long couModelId,
            @Param("marketYear") Integer marketYear,
            @Param("proAreaId") Long proAreaId,
            @Param("cityAreaId") Long cityAreaId,
            @Param("countyAreaId") Long countyAreaId,
            @Param("onlineStatus") Integer onlineStatus,
            @Param("sellStatus") Integer sellStatus,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 获取企业商品的详情
     * @param dealWaresId
     * @return
     */
    DealWaresInfoVo info(@Param("dealWaresId") String dealWaresId);

    /**
     * 获取企业端商品的详情
     * @param dealWaresId
     * @return
     */
    DealWaresWxStoreInfoVo store(@Param("dealWaresId") String dealWaresId);

    /**
     * 获取零售端商品的详情
     * @param dealWaresId
     * @return
     */
    DealWaresWxRetailInfoVo retail(@Param("dealWaresId") String dealWaresId);
}
