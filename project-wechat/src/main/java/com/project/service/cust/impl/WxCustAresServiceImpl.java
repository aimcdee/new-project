package com.project.service.cust.impl;

import com.project.service.cust.WxCustAresService;
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
@Service("wxCustAresService")
public class WxCustAresServiceImpl implements WxCustAresService {

    /**
     * 查看省份
     * @return
     */
    @Override
    public R province() {
        log.error("调用{}异常:{}", "查看省份");
        return null;
    }

    /**
     * 查看市
     * @param areaId
     * @return
     */
    @Override
    public R city(Long areaId) {
        log.error("调用{}异常:{}, 上级区域ID:{}", "查看市", areaId);
        return null;
    }

    /**
     * 查看县/区
     * @param areaId
     * @return
     */
    @Override
    public R county(Long areaId) {
        log.error("调用{}异常:{}, 上级区域ID:{}", "查看县/区", areaId);
        return null;
    }
}
