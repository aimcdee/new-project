package com.project.service.cou.impl;

import com.project.service.cou.WxCouWaresSeriesService;
import com.project.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信调用异常处理层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@Service("wxCouWaresSeriesService")
public class WxCouWaresSeriesServiceImpl implements WxCouWaresSeriesService {

    /**
     * 根据品牌ID获取所有状态为正常品牌系列对象
     * @param couBrandId
     * @return
     */
    @Override
    public R getCouSeriesList(Long couBrandId) {
        log.error("调用{}异常:{}, 品牌ID{}", "根据品牌ID获取所有状态为正常品牌系列对象", couBrandId);
        return null;
    }
}
