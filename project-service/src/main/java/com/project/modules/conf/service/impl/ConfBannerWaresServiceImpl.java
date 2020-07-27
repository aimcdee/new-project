package com.project.modules.conf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.modules.conf.dao.ConfBannerWaresDao;
import com.project.modules.conf.entity.ConfBannerWaresEntity;
import com.project.modules.conf.service.ConfBannerWaresService;
import com.project.modules.conf.vo.Invoking.ConfBannerWaresInvokingVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 轮播企业商品管理Service
 *
 * @author liangyuding
 * @date 2020-06-08
 */
@Slf4j
@Service("confBannerImageService")
public class ConfBannerWaresServiceImpl extends ServiceImpl<ConfBannerWaresDao, ConfBannerWaresEntity> implements ConfBannerWaresService {

    /**
     * 根据轮播图ID获取轮播商品对象集合
     * @param bannerId
     * @return
     */
    @Override
    public List<ConfBannerWaresInvokingVo> getBannerWaresListByBannerId(Long bannerId) {
        List<ConfBannerWaresInvokingVo> confBannerWaresInvokingVos = baseMapper.getBannerWaresListByBannerId(bannerId);
        if (CollectionUtils.isNotEmpty(confBannerWaresInvokingVos)){
            confBannerWaresInvokingVos.forEach(confBannerWaresInvokingVo -> {
                confBannerWaresInvokingVo
                        .setDealWaresTitle(Optional.ofNullable(baseMapper.getDealWaresInvokingVoByDealWaresId(confBannerWaresInvokingVo.getDealWaresId())).orElse(null));
            });
        }
        return confBannerWaresInvokingVos;

    }

    /**
     * 新增轮播企业商品关联及轮播图片路径
     * @param bannerId
     * @param dealWaresId
     * @param image
     */
    @Override
    @Transactional
    public void saveEntity(Long bannerId, String dealWaresId, String image) {
        save(getConfBannerWaresSaveEntity(bannerId, dealWaresId, image));
    }

    /**
     * 更新轮播企业商品
     * @param bannerId
     * @param dealWaresId
     */
    @Override
    @Transactional
    public void updateEntity(Long bannerId, String dealWaresId, String image) {
        remove(new QueryWrapper<ConfBannerWaresEntity>().eq("banner_id", bannerId));
        saveEntity(bannerId, dealWaresId, image);

    }

    //获取ConfBannerWaresEntity新增对象
    private ConfBannerWaresEntity getConfBannerWaresSaveEntity(Long bannerId, String dealWaresId, String image) {
        ConfBannerWaresEntity confBannerWaresEntity = new ConfBannerWaresEntity();
        confBannerWaresEntity.setBannerId(bannerId).setDealWaresId(dealWaresId).setImage(image);
        return confBannerWaresEntity;
    }
}
