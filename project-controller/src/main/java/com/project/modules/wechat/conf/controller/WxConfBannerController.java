package com.project.modules.wechat.conf.controller;

import com.project.constant.Constant;
import com.project.modules.conf.service.ConfBannerService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 微信轮播图Controller
 *
 * @author liangyuding
 * @date 2020-04-15
 */
@Slf4j
@RestController
@RequestMapping(Constant.CONF_BANNER_PATH)
@Api(tags = "微信轮播图管理", description = "WxConfBannerController")
public class WxConfBannerController {

    @Autowired
    private ConfBannerService confBannerService;

    /**
     * 查询状态为正常的零售端轮播图集合
     * @return
     */
    @ApiOperation(value = "查询状态为正常的零售端轮播图集合")
    @GetMapping("/retailList")
    public R retailList(){
        return R.ok(confBannerService.typeList(Constant.DisplayType.RETAIL.getType(), Constant.StatusEnums.NORMAL.getStatus()));
    }

    /**
     * 查询状态为正常的企业端轮播图集合
     * @return
     */
    @ApiOperation(value = "查询状态为正常的企业端轮播图集合")
    @GetMapping("/storeList")
    public R storeList(){
        return R.ok(confBannerService.typeList(Constant.DisplayType.STORE.getType(), Constant.StatusEnums.NORMAL.getStatus()));
    }

}
