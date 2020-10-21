package com.project.service.conf.impl;

import com.project.service.conf.WxConfbannerService;
import com.project.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liangyuding
 * @date 2020-04-15
 */
@Slf4j
@Service
public class WxConfbannerServiceImpl implements WxConfbannerService {

    /**
     * 查询状态为正常的零售端轮播图集合
     * @return
     */
    @Override
    public R retailList() {
        log.error("调用{}异常:{}", "查询状态为正常的零售端轮播图集合");
        return null;
    }

    /**
     * 查询状态为正常的企业端轮播图集合
     * @return
     */
    @Override
    public R storeList() {
        log.error("调用{}异常:{}", "查询状态为正常的企业端轮播图集合");
        return null;
    }
}
