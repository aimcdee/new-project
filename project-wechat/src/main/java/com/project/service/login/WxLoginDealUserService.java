package com.project.service.login;

import com.project.constant.Constant;
import com.project.service.login.impl.WxLoginDealUserServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 微信登录调用层
 *
 * @author liangyuding
 * @date 2020-03-30
 */
@FeignClient(name = "project-controller", fallback = WxLoginDealUserServiceImpl.class)
public interface WxLoginDealUserService {

    /**
     * 客户微信端手机号码登录
     * @param phone
     * @return
     */
    @PostMapping(value = Constant.DEAL_USER_PATH + "/wxSmsLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
    R wxSmsLogin(@RequestBody String phone);

    /**
     * 客户微信端授权登录
     * @param phone
     * @return
     */
    @PostMapping(value = Constant.DEAL_USER_PATH + "/wxLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
    R wxLogin(@RequestBody String phone);

    /**
     * 退出登录
     * @param dealUserId
     * @return
     */
    @GetMapping(value = Constant.DEAL_USER_PATH + "/logout/{dealUserId}")
    R logout(@PathVariable("dealUserId") Long dealUserId);
}
