package com.project.service.cust.impl;

import com.project.service.cust.WxCustAreaLicenseService;
import com.project.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信调用异常处理层
 *
 * @author liangyuding
 * @date 2020-10-10
 */
@Slf4j
@Service("wxCustAreaLicenseService")
public class WxCustAreaLicenseServiceImpl implements WxCustAreaLicenseService {

    /**
     * 查看省份牌照
     * @return
     */
    @Override
    public R getList(Long provinceId, Long cityId) {
        log.error("调用省份ID:{},市区ID:{}, 查看省份牌照异常", provinceId, cityId);
        return null;
    }
}
