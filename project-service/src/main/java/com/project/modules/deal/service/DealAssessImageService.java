package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealAssessImageEntity;
import com.project.modules.deal.vo.invoking.DealImageInvokingVo;

import java.util.List;

/**
 * 商品评估价格图片管理Service
 *
 * @author liangyuding
 * @date 2020-04-22
 */
public interface DealAssessImageService extends IService<DealAssessImageEntity> {

    /**
     * 根据评估ID查询行驶证图对象
     * @param dealAssessId
     * @param type
     * @return
     */
    DealAssessImageEntity getDriveImage(Long dealAssessId, Integer type);

    /**
     * 根据评估ID查询评估商品图集合对象
     * @param dealAssessId
     * @param type
     * @return
     */
    List<DealAssessImageEntity> getWaresImages(Long dealAssessId, Integer type);

    /**
     * 新增评估图片
     * @param dealAssessId
     * @param imageSaveVo
     * @param type
     */
    void saveEntity(Long dealAssessId, DealImageInvokingVo imageSaveVo, Integer type);
}
