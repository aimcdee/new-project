package com.project.controller.cou;

import com.project.service.cou.WxCouWaresBrandService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信端系统商品品牌接口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/cou/wares/brand")
@Api(tags = "微信端系统商品品牌接口", description = "WxCouWaresBrandController")
public class WxCouWaresBrandController {

    @Autowired
    private WxCouWaresBrandService wxCouWaresBrandService;

    /**
     * 获取所有状态为正常商品品牌对象
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常商品品牌对象")
    @GetMapping("/getCouBrandList")
    public R getCouBrandList(){
        return wxCouWaresBrandService.getCouBrandList();
    }
}
