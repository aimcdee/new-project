package com.project.modules.cou.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品中间调用Dao
 *
 * @author liangyuding
 * @date 2020-05-15
 */
@Mapper
public interface CouInvoKingDao {

    /**
     * 根据品牌ID获取品牌名称
     * @param couBrandId
     * @return
     */
    String getCouBrandNameById(@Param("couBrandId") Long couBrandId);


    /**
     * 根据品牌系列ID获取品牌系列名称
     * @param couSeriesId
     * @return
     */
    String getCouSeriesNameById(@Param("couSeriesId") Long couSeriesId);

    /**
     * 根据商品型号ID获取商品型号名称
     * @param couModelId
     * @return
     */
    String getCouModelNameById(@Param("couModelId") Long couModelId);
}
