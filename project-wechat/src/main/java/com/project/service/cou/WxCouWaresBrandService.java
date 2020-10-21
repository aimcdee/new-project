package com.project.service.cou;

import com.project.constant.Constant;
import com.project.service.cou.impl.WxCouWaresBrandServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微信系统品牌调用层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@FeignClient(name = "project-controller", fallback = WxCouWaresBrandServiceImpl.class)
public interface WxCouWaresBrandService {

    /**
     * 获取热门品牌对象列表
     * @return
     */
    @GetMapping(Constant.COU_BRAND_PATH + "/getHotCouBrandList")
    R getHotCouBrandList();

    /**
     * 获取所有状态为正常品牌对象
     * @return
     */
    @GetMapping(Constant.COU_BRAND_PATH + "/getCouBrandList")
    R getCouBrandList();
}
