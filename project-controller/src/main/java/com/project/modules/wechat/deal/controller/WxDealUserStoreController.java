package com.project.modules.wechat.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealUserStoreService;
import com.project.modules.deal.vo.save.DealUserStoreSaveVo;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信客户企业Controller
 *
 * @author liangyuding
 * @date 2020-03-27
 */
@Slf4j
@RestController
@RequestMapping(Constant.DEAL_USER_STORE_PATH)
@Api(tags = "微信客户企业端管理", description = "WxDealUserStoreController")
public class WxDealUserStoreController {

    @Autowired
    private DealUserStoreService dealUserStoreService;

    /**
     * 分页查看申请成为企业的申请记录
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查看申请成为企业的申请记录")
    @GetMapping("/recordList")
    public R recordList(@RequestParam Map<String, Object> params){
        return R.ok(dealUserStoreService.queryPage(params));
    }

    /**
     * 客户申请成为企业用户
     * @param store
     * @return
     */
    @ApiOperation(value = "客户申请成为企业用户")
    @ApiImplicitParam(paramType = "body", name = "store", value = "客户申请对象", required = true, dataType = "DealUserStoreSaveVo")
    @SysLog("客户申请成为企业用户")
    @PostMapping("/saveStore")
    public R saveStore(@RequestBody DealUserStoreSaveVo store){
        dealUserStoreService.saveEntity(store);
        return R.ok();
    }
}
