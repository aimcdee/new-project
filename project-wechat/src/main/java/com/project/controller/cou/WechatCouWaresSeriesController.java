package com.project.controller.cou;

import com.project.service.cou.WxCouWaresSeriesService;
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
 * 微信端系统系列接口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/cou/wares/series")
@Api(tags = "微信端系统系列接口", description = "WechatCouWaresSeriesController")
public class WechatCouWaresSeriesController {

    @Autowired
    private WxCouWaresSeriesService wxCouWaresSeriesService;

    /**
     * 根据品牌ID获取所有状态为正常系列对象
     * @param couBrandId
     * @return
     */
    @ApiOperation(value = "根据品牌ID获取所有状态为正常系列对象")
    @GetMapping("/getCouSeriesList/{couBrandId}")
    public R getCouSeriesList(@PathVariable("couBrandId") Long couBrandId){
        return wxCouWaresSeriesService.getCouSeriesList(couBrandId);
    }
}
