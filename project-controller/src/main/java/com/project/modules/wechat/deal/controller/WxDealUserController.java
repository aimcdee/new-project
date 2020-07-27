package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealUserService;
import com.project.modules.deal.vo.update.DealUserUpdateVo;
import com.project.utils.R;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信客户Controller
 *
 * @author liangyuding
 * @date 2020-03-27
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_USER_PATH)
@Api(tags = "微信客户端管理", description = "WxDealUserController")
public class WxDealUserController {

    @Autowired
    private DealUserService dealUserService;


    /**
     * 获取个人详细信息
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取个人详细信息")
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId) {
        return R.ok(dealUserService.info(userId));
    }

    /**
     * 更新个人信息
     * @param user
     * @return
     */
    @ApiOperation(value = "更新个人信息")
    @ApiImplicitParam(paramType = "body", name = "user", value = "客户信息对象", required = true, dataType = "DealUserUpdateVo")
    @SysLog("更新个人信息")
    @PostMapping("/update")
    public R update(@RequestBody DealUserUpdateVo user){
        ValidatorUtils.validateEntity(user);
        dealUserService.updateEntity(user);
        return R.ok();
    }

    /**
     * 客户提现
     * @param dealStoreId
     * @return
     */
    @ApiOperation(value = "客户提现")
    @SysLog("客户提现")
    @GetMapping("/cashOut/{dealStoreId}")
    @RequiresPermissions("deal:user:update")
    public R cashOut(@PathVariable("dealStoreId") Long dealStoreId){
        dealUserService.cashOut(dealStoreId);
        return R.ok();
    }
}
