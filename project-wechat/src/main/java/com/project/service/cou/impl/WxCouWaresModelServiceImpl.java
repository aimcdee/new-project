package com.project.service.cou.impl;

import com.project.service.cou.WxCouWaresModelService;
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
@Service("wxCouWaresModelService")
public class WxCouWaresModelServiceImpl implements WxCouWaresModelService {

    /**
     * 获取所有状态为正常商品型号对象
     * @return
     */
    @Override
    public R getCouModelList() {
        log.error("调用{}异常:{}", "获取所有状态为正常商品型号对象");
        return null;
    }
}
