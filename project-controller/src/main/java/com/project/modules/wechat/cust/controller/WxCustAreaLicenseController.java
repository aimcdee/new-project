package com.project.modules.wechat.cust.controller;

import com.project.constant.Constant;
import com.project.modules.cust.service.CustAreaLicenseService;
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
 * 微信区域牌照Controller
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@RestController
@RequestMapping(Constant.CUST_AREA_LICENSE_PATH)
@Api(tags = "微信端区域牌照管理", description = "WxCoufAreaController")
public class WxCustAreaLicenseController {
    @Autowired

    private CustAreaLicenseService custAreaLicenseService;
    /**
     * 查看省份牌照
     * @return
     */
    @ApiOperation(value = "查看省份牌照")
    @GetMapping("/list")
    public R list(@RequestParam("provinceId") Long provinceId, @RequestParam("cityId") Long cityId) {
        return R.ok(custAreaLicenseService.getList(provinceId, cityId));
    }
}
