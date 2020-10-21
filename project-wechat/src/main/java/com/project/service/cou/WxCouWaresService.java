package com.project.service.cou;

import com.project.constant.Constant;
import com.project.service.cou.impl.WxCouWaresServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 微信系统商品调用层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@FeignClient(name = "project-controller", fallback = WxCouWaresServiceImpl.class)
public interface WxCouWaresService {

    /**
     * 根据系列获取所有状态为正常商品对象
     * @param couSeriesId
     * @return
     */
    @GetMapping(Constant.COU_WARES_PATH + "/getCouWaresList/{couSeriesId}")
    R getCouWaresList(@PathVariable("couSeriesId") Long couSeriesId);
}
