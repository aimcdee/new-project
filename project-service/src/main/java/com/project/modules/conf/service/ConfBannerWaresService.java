package com.project.modules.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.conf.entity.ConfBannerWaresEntity;
import com.project.modules.conf.vo.Invoking.ConfBannerWaresInvokingVo;

import java.util.List;

/**
 * 轮播企业商品管理Service
 *
 * @author liangyuding
 * @date 2020-06-08
 */
public interface ConfBannerWaresService extends IService<ConfBannerWaresEntity> {

    /**
     * 根据轮播图ID获取轮播商品对象集合
     * @param bannerId
     * @return
     */
    List<ConfBannerWaresInvokingVo> getBannerWaresListByBannerId(Long bannerId);

    /**
     * 新增轮播企业商品关联及轮播图片路径
     * @param bannerId
     * @param dealWaresId
     * @param image
     */
    void saveEntity(Long bannerId, String dealWaresId, String image);

    /**
     * 更新轮播企业商品关联及轮播图片路径
     * @param bannerId
     * @param dealWaresId
     * @param image
     */
    void updateEntity(Long bannerId, String dealWaresId, String image);
}
