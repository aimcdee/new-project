package com.project.service.deal;

import com.project.constant.Constant;
import com.project.modules.deal.vo.update.DealUserUpdateVo;
import com.project.service.deal.impl.WxDealUserServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 微信客户调用层
 *
 * @author liangyuding
 * @date 2020-04-07
 */
@FeignClient(name = "project-controller", fallback = WxDealUserServiceImpl.class)
public interface WxDealUserService {

    /**
     * 获取自己的详细信息
     * @param dealUserId
     * @return
     */
    @GetMapping(Constant.DEAL_USER_PATH + "/info/{dealUserId}")
    R getDealUserInfoVo(@PathVariable("dealUserId") Long dealUserId);


    /**
     * 更新个人信息
     * @param user
     * @return
     */
    @PostMapping(Constant.DEAL_USER_PATH + "/update")
    R updateDealUser(@RequestBody DealUserUpdateVo user);

    /**
     * 客户提现
     * @param dealStoreId
     * @return
     */
    @GetMapping(Constant.DEAL_USER_PATH + "/cashOut/{dealStoreId}")
    R cashOut(@PathVariable("dealStoreId") Long dealStoreId);
}
