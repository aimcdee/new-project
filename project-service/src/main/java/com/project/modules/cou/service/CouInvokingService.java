package com.project.modules.cou.service;

/**
 * 商品中间调用Service
 *
 * @author liangyuding
 * @date 2020-05-15
 */
public interface CouInvokingService {

    /**
     * 根据品牌ID获取品牌名称
     * @param couBrandId
     * @return
     */
    String getCouBrandNameById(Long couBrandId);

    /**
     * 根据品牌系列ID获取品牌系列名称
     * @param seriesId
     * @return
     */
    String getCouSeriesNameById(Long seriesId);

    /**
     * 根据商品型号ID获取商品型号名称
     * @param modelId
     * @return
     */
    String getCouModelNameById(Long modelId);
}
