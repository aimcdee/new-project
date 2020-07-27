package com.project.controller.conf;

import com.project.service.conf.WxConfbannerService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信端轮播图端口
 *
 * @author liangyuding
 * @date 2020-04-07
 */
@Slf4j
@RestController
@RequestMapping("/wechat/conf/banner")
@Api(tags = "微信端轮播图端口", description = "WxConfBannerController")
public class WxConfBannerController {

    @Autowired
    private WxConfbannerService wxConfbannerService;

    /**
     * 查询可显示的轮播图集合
     * @return
     */
    @ApiOperation(value = "查询可显示的轮播图集合")
    @GetMapping("/list")
    public R list() {
        return wxConfbannerService.list();
    }
}
