package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.modules.deal.dao.DealAssessImageDao;
import com.project.modules.deal.entity.DealAssessImageEntity;
import com.project.modules.deal.service.DealAssessImageService;
import com.project.modules.deal.vo.invoking.DealImageInvokingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品评估价格图片管理Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@Service("dealAssessImageService")
public class DealAssessImageServiceImpl extends ServiceImpl<DealAssessImageDao, DealAssessImageEntity> implements DealAssessImageService {

    /**
     * 根据评估ID查询行驶证图对象
     * @param dealAssessId
     * @param type
     * @return
     */
    @Override
    public DealAssessImageEntity getDriveImage(Long dealAssessId, Integer type) {
        return baseMapper.getDriveImage(dealAssessId, type);
    }

    /**
     * 根据评估ID查询评估商品图集合对象
     * @param dealAssessId
     * @param type
     * @return
     */
    @Override
    public List<DealAssessImageEntity> getWaresImages(Long dealAssessId, Integer type) {
        return baseMapper.getWaresImages(dealAssessId, type);
    }

    /**
     * 新增评估图片
     * @param dealAssessId
     * @param imageSaveVo
     * @param type
     */
    @Override
    @Transactional
    public void saveEntity(Long dealAssessId, DealImageInvokingVo imageSaveVo, Integer type) {
        DealAssessImageEntity dealAssessImageEntity = new DealAssessImageEntity();
        dealAssessImageEntity.setImage(imageSaveVo.getImage()).setImageType(type).setDealAssessId(dealAssessId);
        save(dealAssessImageEntity);
    }
}
