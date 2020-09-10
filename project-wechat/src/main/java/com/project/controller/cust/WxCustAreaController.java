package com.project.controller.cust;

import com.project.constant.Constant;
import com.project.modules.cust.service.CustAreaService;
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
 * 微信端区域接口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/cust/area")
@Api(tags = "微信端区域接口", description = "WxCustAreaController")
public class WxCustAreaController {

//    @Autowired
//    private WxCustAresService wxCustAresService;
    @Autowired
    private CustAreaService custAreaService;

    /**
     * 查看省份
     * @return
     */
    @ApiOperation(value = "查看省份")
    @GetMapping("/province")
    public R province() {
        return R.ok(custAreaService.getArea(null, Constant.AreaType.PROVINCE.getType()));
//        return wxCustAresService.province();
    }

    /**
     * 查看市
     * @return
     */
    @ApiOperation(value = "查看市")
    @GetMapping("/city/{areaId}")
    public R city(@PathVariable("areaId") Long areaId){
        return R.ok(custAreaService.getArea(areaId, Constant.AreaType.CITY.getType()));
//        return wxCustAresService.city(areaId);
    }

    /**
     * 查看县/区
     * @return
     */
    @ApiOperation(value = "查看县/区")
    @GetMapping("/county/{areaId}")
    public R county(@PathVariable("areaId") Long areaId){
        return R.ok(custAreaService.getArea(areaId, Constant.AreaType.COUNTY.getType()));
//        return wxCustAresService.county(areaId);
    }
}
