package com.project.controller.cou;

import com.project.modules.cou.service.CouWaresModelService;
import com.project.service.cou.WxCouWaresModelService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信端系统商品型号接口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/cou/wares/model")
@Api(tags = "微信端系统商品型号接口", description = "WechatCouWaresModelController")
public class WechatCouWaresModelController {

    @Autowired
    private WxCouWaresModelService wxCouWaresModelService;
    @Autowired
    private CouWaresModelService couWaresModelService;

    /**
     * 获取所有状态为正常商品型号对象
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常商品型号对象")
    @GetMapping("/getCouModelList")
    public R getCouModelList(){
        return R.ok(couWaresModelService.getCouModelList());
//        return wxCouWaresModelService.getCouModelList();
    }
}
