package com.project.modules.wechat.cou.controller;

import com.project.constant.Constant;
import com.project.modules.cou.service.CouWaresService;
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
 * 微信系统商品Controller
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Slf4j
@RestController
@RequestMapping(Constant.COU_WARES_PATH)
@Api(tags = "微信系统品牌端管理", description = "WxCouWaresController")
public class WxCouWaresController {

    @Autowired
    private CouWaresService couWaresService;

    /**
     * 获取所有状态为正常商品对象
     * @param couSeriesId
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常商品对象")
    @GetMapping("/getCouWaresList/{couSeriesId}")
    public R getCouWaresList(@PathVariable("couSeriesId") Long couSeriesId){
        return R.ok(couWaresService.getCouWaresList(couSeriesId));
    }
}
