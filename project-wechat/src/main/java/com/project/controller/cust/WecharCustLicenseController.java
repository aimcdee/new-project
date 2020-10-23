package com.project.controller.cust;

import com.project.service.cust.WxCustAreaLicenseService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信端牌照接口
 *
 * @author liangyuding
 * @date 2020-09-25
 */
@Slf4j
@RestController
@RequestMapping("/wechat/cust/license")
@Api(tags = "微信端牌照接口", description = "WecharCustLicenseController")
public class WecharCustLicenseController {

    @Autowired
    private WxCustAreaLicenseService wxCustAreaLicenseService;

    /**
     * 查看省份牌照
     * @return
     */
    @ApiOperation(value = "查看省份牌照")
    @GetMapping("/list")
    public R list(@RequestParam("provinceId") Long provinceId, @RequestParam("cityId") Long cityId) {
        return wxCustAreaLicenseService.getList(provinceId, cityId);
    }
}
