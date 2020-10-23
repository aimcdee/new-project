package com.project.controller.cou;

import com.project.service.cou.WxCouWaresService;
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
 * 微信端系统商品接口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/cou/wares")
@Api(tags = "微信端系统商品接口", description = "WechatCouWaresController")
public class WechatCouWaresController {

    @Autowired
    private WxCouWaresService wxCouWaresService;


    /**
     * 获取所有状态为正常商品对象
     * @param couSeriesId
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常商品对象")
    @GetMapping("/getCouWaresList/{couSeriesId}")
    public R getCouWaresList(@PathVariable("couSeriesId") Long couSeriesId){
        return wxCouWaresService.getCouWaresList(couSeriesId);
    }
}
