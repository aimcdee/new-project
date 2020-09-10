package com.project.service.cust;

import com.project.constant.Constant;
import com.project.service.cust.impl.WxCustAresServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 微信区域调用层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@FeignClient(name = "project-controller", fallback = WxCustAresServiceImpl.class)
public interface WxCustAresService {

    /**
     * 查看省份
     * @return
     */
    @GetMapping(Constant.CUST_AREA_PATH + "/province")
    R province();

    /**
     * 查看市
     * @return
     */
    @GetMapping(Constant.CUST_AREA_PATH + "/city/{areaId}")
    R city(@PathVariable("areaId") Long areaId);

    /**
     * 查看县/区
     * @param areaId
     * @return
     */
    @GetMapping(Constant.CUST_AREA_PATH + "/county/{areaId}")
    R county(@PathVariable("areaId") Long areaId);
}
