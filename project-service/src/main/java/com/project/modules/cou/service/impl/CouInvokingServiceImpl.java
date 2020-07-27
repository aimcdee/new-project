package com.project.modules.cou.service.impl;

import com.project.modules.cou.dao.CouInvoKingDao;
import com.project.modules.cou.service.CouInvokingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品中间调用Service
 *
 * @author liangyuding
 * @date 2020-05-15
 */
@Slf4j
@Service("couInvokingService")
public class CouInvokingServiceImpl implements CouInvokingService {

    @Autowired
    private CouInvoKingDao couInvoKingDao;

    /**
     * 根据品牌ID获取品牌名称
     * @param couBrandId
     * @return
     */
    @Override
    public String getCouBrandNameById(Long couBrandId) {
        return couInvoKingDao.getCouBrandNameById(couBrandId);
    }

    /**
     * 根据品牌系列ID获取品牌系列名称
     * @param couSeriesId
     * @return
     */
    @Override
    public String getCouSeriesNameById(Long couSeriesId) {
        return couInvoKingDao.getCouSeriesNameById(couSeriesId);
    }

    /**
     * 根据商品型号ID获取商品型号名称
     * @param couModelId
     * @return
     */
    @Override
    public String getCouModelNameById(Long couModelId) {
        return couInvoKingDao.getCouModelNameById(couModelId);
    }
}
