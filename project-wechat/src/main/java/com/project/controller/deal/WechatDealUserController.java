package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.modules.deal.service.DealUserService;
import com.project.modules.deal.vo.update.DealUserUpdateVo;
import com.project.service.deal.WxDealUserService;
import com.project.utils.R;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.project.common.utils.ShiroUtils.getDealUserId;

/**
 * 微信端客户端口
 *
 * @author liangyuding
 * @date 2020-04-07
 */
@Slf4j
@RestController
@RequestMapping("/wechat/deal/user")
@Api(tags = "微信端客户端口", description = "WechatDealUserController")
public class WechatDealUserController {

    @Autowired
    private WxDealUserService wxDealUserService;
    @Autowired
    private DealUserService dealUserService;

    /**
     * 客户获取自己的详细信息
     * @return
     */
    @ApiOperation(value = "客户获取自己的详细信息")
    @GetMapping("/info")
    public R info() {
        return wxDealUserService.getDealUserInfoVo(getDealUserId());
    }

    /**
     * 更新个人信息
     * @param user
     * @return
     */
    @ApiOperation(value = "更新个人信息")
    @ApiImplicitParam(paramType = "body", name = "user", value = "客户信息对象", required = true, dataType = "DealUserUpdateVo")
    @SysLog("更新客户")
    @PostMapping("/update")
    public R update(@RequestBody DealUserUpdateVo user){
        ValidatorUtils.validateEntity(user);
        user.setDealUserId(getDealUserId());
        return wxDealUserService.updateDealUser(user);
    }
}
