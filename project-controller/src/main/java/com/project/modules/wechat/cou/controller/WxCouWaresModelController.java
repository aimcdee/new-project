package com.project.modules.wechat.cou.controller;

import com.project.constant.Constant;
import com.project.modules.cou.service.CouWaresModelService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品型号Controller
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@RestController
@RequestMapping(Constant.COU_MODEL_PATH)
@Api(tags = "微信系统品牌端管理", description = "WxCouWaresModelController")
public class WxCouWaresModelController {

    @Autowired
    private CouWaresModelService couWaresModelService;

    /**
     * 获取所有状态为正常商品型号的ID和名称
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常商品型号的ID和名称")
    @GetMapping("/getCouModelList")
    public R getCouModelList(){
        return R.ok(couWaresModelService.getCouModelList());
    }
}
