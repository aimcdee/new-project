package com.project.service.cou.impl;

import com.project.service.cou.WxCouWaresBrandService;
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
@Service("wxCouWaresBrandService")
public class WxCouWaresBrandServiceImpl implements WxCouWaresBrandService {

    /**
     * 获取所有状态为正常商品品牌对象
     * @return
     */
    @Override
    public R getCouBrandList() {
        log.error("调用{}异常:{}", "获取所有状态为正常商品品牌对象");
        return null;
    }
}
