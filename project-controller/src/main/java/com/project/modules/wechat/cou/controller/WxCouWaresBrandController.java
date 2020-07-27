package com.project.modules.wechat.cou.controller;

import com.project.constant.Constant;
import com.project.modules.cou.service.CouWaresBrandService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信系统商品品牌Controller
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping(Constant.COU_BRAND_PATH)
@Api(tags = "微信系统品牌端管理", description = "WxCouWaresBrandController")
public class WxCouWaresBrandController {

    @Autowired
    private CouWaresBrandService couWaresBrandService;

    /**
     * 获取所有状态为正常商品品牌对象
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常商品品牌对象")
    @GetMapping("/getCouBrandList")
    public R getCouBrandList(){
        return R.ok(couWaresBrandService.getCouBrandList());
    }
}
