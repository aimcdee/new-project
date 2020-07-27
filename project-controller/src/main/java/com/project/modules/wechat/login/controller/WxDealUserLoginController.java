package com.project.modules.wechat.login.controller;

import com.project.constant.Constant;
import com.project.modules.deal.service.DealUserLoginService;
import com.project.utils.R;
import com.project.utils.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信客户登录Controller
 *
 * @author liangyuding
 * @date 2020-03-27
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_USER_PATH)
@Api(tags = "微信客户登录端管理", description = "WxDealUserLoginController")
public class WxDealUserLoginController {

    @Autowired
    private DealUserLoginService dealUserLoginService;

    /**
     * 客户微信登录
     * @param phone
     * @return
     */
    @ApiOperation(value = "客户微信登录")
    @PostMapping("/wxLogin")
    public R wxLogin(@RequestBody String phone){
        return R.ok(dealUserLoginService.wxDealUserlogin(phone));
    }

    /**
     * 退出登录
     * @param dealUserId
     * @return
     */
    @ApiOperation(value = "退出登录")
    @GetMapping("/logout/{dealUserId}")
    public R logout(@PathVariable("dealUserId") Long dealUserId){
        dealUserLoginService.logout(dealUserId);
        return R.ok(StatusCode.LOGIN_OUT);
    }
}
