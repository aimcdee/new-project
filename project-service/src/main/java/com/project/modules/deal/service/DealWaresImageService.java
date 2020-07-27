package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealWaresImageEntity;
import com.project.modules.deal.vo.invoking.DealImageInvokingVo;

import java.util.List;

public interface DealWaresImageService extends IService<DealWaresImageEntity> {

    /**
     * 新增商品图片路径管理对象
     * @param dealWaresId
     * @param image
     * @param imageType
     * @param isCover
     */
    void saveEntity(String dealWaresId, DealImageInvokingVo image, Integer imageType, Integer isCover);

    /**
     * 删除该商品下的图片路径
     * @param dealWaresId
     */
    void deleteEntity(String dealWaresId);

    /**
     * 根据商品ID,图片类型,是否为封面图查询商品图片对象
     * @param dealWaresId
     * @param imageType
     * @param cover
     * @return
     */
    DealWaresImageEntity getImage(String dealWaresId, Integer imageType, Integer cover);

    /**
     * 获取商品图片集合对象
     * @param dealWaresId
     * @param imageType
     * @param cover
     * @return
     */
    List<DealWaresImageEntity> getImageList(String dealWaresId, Integer imageType, Integer cover);
}
