package com.project.service.cust;

import com.project.constant.Constant;
import com.project.service.cust.impl.WxCustAreaLicenseServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信区域调用层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@FeignClient(name = "project-controller", fallback = WxCustAreaLicenseServiceImpl.class)
public interface WxCustAreaLicenseService {

    /**
     * 查看省份牌照
     * @param provinceId
     * @param cityId
     * @return
     */
    @GetMapping(Constant.CUST_AREA_LICENSE_PATH + "/list")
    R getList(@RequestParam("provinceId") Long provinceId, @RequestParam("cityId") Long cityId);
}
