package com.project.controller.cou;

import com.project.modules.cou.service.CouWaresSeriesService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信端系统品牌系列接口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/cou/wares/series")
@Api(tags = "微信端系统品牌系列接口", description = "WxCouWaresSeriesController")
public class WxCouWaresSeriesController {

//    @Autowired
//    private WxCouWaresSeriesService wxCouWaresSeriesService;

    @Autowired
    private CouWaresSeriesService couWaresSeriesService;

    /**
     * 根据品牌ID获取所有状态为正常品牌系列对象
     * @param couBrandId
     * @return
     */
    @ApiOperation(value = "根据品牌ID获取所有状态为正常品牌系列对象")
    @GetMapping("/getCouSeriesList/{couBrandId}")
    public R getCouSeriesList(@PathVariable("couBrandId") Long couBrandId){
        return R.ok(couWaresSeriesService.getCouSeriesList(couBrandId));
//        return wxCouWaresSeriesService.getCouSeriesList(couBrandId);
    }
}
