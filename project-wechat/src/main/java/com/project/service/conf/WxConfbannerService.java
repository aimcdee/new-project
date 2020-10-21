package com.project.service.conf;

import com.project.constant.Constant;
import com.project.service.conf.impl.WxConfbannerServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微信轮播图调用层
 *
 * @author liangyuding
 * @date 2020-04-15
 */
@FeignClient(name = "project-controller", fallback = WxConfbannerServiceImpl.class)
public interface WxConfbannerService {

    /**
     * 查询状态为正常的零售端轮播图集合
     * @return
     */
    @GetMapping(Constant.CONF_BANNER_PATH + "/retailList")
    R retailList();

    /**
     * 查询状态为正常的企业端轮播图集合
     * @return
     */
    @GetMapping(Constant.CONF_BANNER_PATH + "/storeList")
    R storeList();
}
