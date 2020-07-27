package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.modules.deal.dao.DealWaresImageDao;
import com.project.modules.deal.entity.DealWaresImageEntity;
import com.project.modules.deal.service.DealWaresImageService;
import com.project.modules.deal.vo.invoking.DealImageInvokingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品图片管理Service
 *
 * @author liangyuding
 * @date 2020-06-02
 */
@Slf4j
@Service("dealWaresImageService")
public class DealWaresImageServiceImpl extends ServiceImpl<DealWaresImageDao, DealWaresImageEntity> implements DealWaresImageService {

    /**
     * 新增商品图片路径管理对象
     * @param dealWaresId
     * @param image
     * @param imageType
     * @param isCover
     */
    @Override
    @Transactional
    public void saveEntity(String dealWaresId, DealImageInvokingVo image, Integer imageType, Integer isCover) {
        DealWaresImageEntity dealWaresImageEntity = new DealWaresImageEntity();
        dealWaresImageEntity.setImage(image.getImage()).setImageType(imageType).setIsCover(isCover).setDealWaresId(dealWaresId);
        save(dealWaresImageEntity);
    }

    /**
     * 删除该商品下的图片路径
     * @param dealWaresId
     */
    @Override
    @Transactional
    public void deleteEntity(String dealWaresId) {
        remove(new QueryWrapper<DealWaresImageEntity>().eq("deal_wares_id", dealWaresId));
    }

    /**
     * 根据商品ID,图片类型,是否为封面图查询商品图片对象
     * @param dealWaresId
     * @param imageType
     * @param cover
     * @return
     */
    @Override
    public DealWaresImageEntity getImage(String dealWaresId, Integer imageType, Integer cover) {
        return getOne(new QueryWrapper<DealWaresImageEntity>().eq("deal_wares_id", dealWaresId).eq("image_type", imageType).eq("is_cover", cover).last("LIMIT 1"));
    }

    /**
     * 获取商品图片集合对象
     * @param dealWaresId
     * @param imageType
     * @param cover
     * @return
     */
    @Override
    public List<DealWaresImageEntity> getImageList(String dealWaresId, Integer imageType, Integer cover) {
        return list(new QueryWrapper<DealWaresImageEntity>().eq("deal_wares_id", dealWaresId).eq("image_type", imageType).eq("is_cover", cover));
    }
}
