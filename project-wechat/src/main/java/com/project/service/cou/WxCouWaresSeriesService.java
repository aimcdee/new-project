package com.project.service.cou;

import com.project.constant.Constant;
import com.project.service.cou.impl.WxCouWaresSeriesServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 微信系统品牌系列调用层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@FeignClient(name = "project-controller", fallback = WxCouWaresSeriesServiceImpl.class)
public interface WxCouWaresSeriesService {

    /**
     * 根据品牌ID获取所有状态为正常品牌系列对象
     * @param couBrandId
     * @return
     */
    @GetMapping(Constant.COU_SERIES_PATH + "/getCouSeriesList/{couBrandId}")
    R getCouSeriesList(@PathVariable("couBrandId") Long couBrandId);
}
