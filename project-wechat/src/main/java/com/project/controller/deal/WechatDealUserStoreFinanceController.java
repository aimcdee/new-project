package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.vo.save.DealUserStoreFinanceSaveVo;
import com.project.service.deal.WxDealUserStoreFinanceService;
import com.project.utils.R;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.project.common.utils.ShiroUtils.getDealUserId;
import static com.project.common.utils.ShiroUtils.isEnterprise;

/**
 * 微信端企业客户金融单接口
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Slf4j
@RestController
@RequestMapping("/wechat/deal/user/store/finance")
@Api(tags = "微信端企业客户金融单接口", description = "WechatDealUserStoreFinanceController")
public class WechatDealUserStoreFinanceController {

    @Autowired
    private WxDealUserStoreFinanceService wxDealUserStoreFinanceService;

    /**
     * 企业客户分页查询金融单列表
     * @param params
     * @return
     */
    @ApiOperation(value = "企业客户分页查询金融单列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("dealUserId", getDealUserId());
        return wxDealUserStoreFinanceService.list(params);
    }

    /**
     * 企业客户新增金融单
     * @param finance
     * @return
     */
    @ApiOperation(value = "企业客户新增金融单")
    @ApiImplicitParam(paramType = "body", name = "finance", value = "金融单信息对象", required = true, dataType = "DealUserStoreFinanceSaveVo")
    @SysLog("企业客户新增金融单")
    @PostMapping("/save")
    public R save(@RequestBody DealUserStoreFinanceSaveVo finance){
        //判断当前操作的客户是否为企业客户
        if (isEnterprise()){
            ValidatorUtils.validateEntity(finance);
            return wxDealUserStoreFinanceService.saveEntity(finance);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }
}
