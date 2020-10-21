package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealUserService;
import com.project.modules.deal.vo.save.DealUserSaveVo;
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

import java.util.Map;

/**
 * 客户Controller
 *
 * @author liangyuding
 * @date 2020-03-27
 */
@Slf4j
@RestController
@RequestMapping("/deal/user")
@Api(tags = "客户管理", description = "DealUserController")
public class DealUserController {

    @Autowired
    private DealUserService dealUserService;

    /**
     * 分页查询客户列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询客户列表")
    @GetMapping("/list")
    @RequiresPermissions("deal:user:list")
    public R normalList(@RequestParam Map<String, Object> params){
        return R.ok(dealUserService.queryPage(params));
    }

    /**
     * 新增客户
     * @param user
     * @return
     */
    @ApiOperation(value = "新增客户")
    @ApiImplicitParam(paramType = "body", name = "user", value = "客户信息对象", required = true, dataType = "DealUserSaveVo")
    @SysLog("新增客户")
    @PostMapping("/save")
    @RequiresPermissions("deal:user:save")
    public R save(@RequestBody DealUserSaveVo user){
        ValidatorUtils.validateEntity(user);
        return R.ok(dealUserService.saveEntity(user));
    }

    /**
     * 根据客户ID获取客户详情
     * @param dealUserId
     * @return
     */
    @ApiOperation(value = "根据客户ID获取客户详情")
    @GetMapping("/info/{dealUserId}")
    @RequiresPermissions("deal:user:info")
    public R info(@PathVariable("dealUserId") Long dealUserId) {
        return R.ok(dealUserService.info(dealUserId));
    }

    /**
     * 更新客户
     * @param user
     * @return
     */
    @ApiOperation(value = "更新客户")
    @ApiImplicitParam(paramType = "body", name = "user", value = "客户信息对象", required = true, dataType = "DealUserUpdateVo")
    @SysLog("更新客户")
    @PostMapping("/update")
    @RequiresPermissions("deal:user:update")
    public R update(@RequestBody DealUserUpdateVo user){
        ValidatorUtils.validateEntity(user);
        dealUserService.updateEntity(user);
        return R.ok();
    }

    /**
     * 修改客户的状态为启用
     * @param dealUserId
     * @return
     */
    @ApiOperation(value = "修改客户的状态为启用")
    @SysLog("修改客户的状态为启用")
    @GetMapping("/normal/{dealUserId}")
    @RequiresPermissions("deal:user:update")
    public R normal(@PathVariable("dealUserId") Long dealUserId){
        dealUserService.changeStatus(dealUserId, Constant.StatusEnums.NORMAL.getStatus());
        return R.ok();
    }

    /**
     * 修改客户的状态为禁用
     * @param dealUserId
     * @return
     */
    @ApiOperation(value = "修改客户的状态为禁用")
    @SysLog("修改客户的状态为禁用")
    @GetMapping("/disable/{dealUserId}")
    @RequiresPermissions("deal:user:update")
    public R disable(@PathVariable("dealUserId") Long dealUserId){
        dealUserService.changeStatus(dealUserId, Constant.StatusEnums.DISABLE.getStatus());
        return R.ok();
    }

    /**
     * 获取所有状态为正常的客户集合
     * @param dealUserName
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常的客户集合")
    @GetMapping("/getDealUserList")
    public R getDealUserList(@RequestParam(value="dealUserName", required=false) String dealUserName) {
        return R.ok(dealUserService.getDealUserList(Constant.StatusEnums.NORMAL.getStatus(), dealUserName));
    }

    /**
     * 获取所有状态为正常的企业客户集合
     * @param dealUserName
     * @return
     */
    @ApiOperation(value = "获取企业客户集合")
    @GetMapping("/getStoreUserList")
    public R getStoreUserList(@RequestParam(value="dealUserName", required=false) String dealUserName){
        return R.ok(dealUserService.getStoreUserList(Constant.StatusEnums.NORMAL.getStatus(), Constant.StoreType.ENTERPRISE.getType(), Constant.Examine.SUCCESS.getExamine(), dealUserName));
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
