package com.project.service.login;

/**
 * 微信登录调用层
 *
 * @author liangyuding
 * @date 2020-03-30
 */
//@FeignClient(name = "project-controller", fallback = WxLoginDealUserServiceImpl.class)
public interface WxLoginDealUserService {

//    /**
//     * 客户验证码登录
//     * @param phone
//     * @return
//     */
//    @PostMapping(value = Constant.DEAL_USER_PATH + "/wxLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
//    R wxLogin(@RequestBody String phone);
//
//    /**
//     * 退出登录
//     * @param dealUserId
//     * @return
//     */
//    @GetMapping(value = Constant.DEAL_USER_PATH + "/logout/{dealUserId}")
//    R logout(@PathVariable("dealUserId") Long dealUserId);
}
