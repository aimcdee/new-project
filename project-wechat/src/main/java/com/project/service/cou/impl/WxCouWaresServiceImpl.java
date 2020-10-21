package com.project.service.cou.impl;

import com.project.service.cou.WxCouWaresService;
import com.project.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信调用异常处理层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@Service("wxCouWaresService")
public class WxCouWaresServiceImpl implements WxCouWaresService {

    /**
     * 根据系列ID获取所有状态为正常商品对象
     * @param seriesId
     * @return
     */
    @Override
    public R getCouWaresList(Long seriesId) {
        log.error("调用{}异常:{}, 系列ID{}", "根据系列ID获取所有状态为正常商品对象", seriesId);
        return null;
    }
}
