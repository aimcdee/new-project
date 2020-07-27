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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "微信端企业客户金融单接口", description = "WxDealUserStoreFinanceController")
public class WxDealUserStoreFinanceController {

    @Autowired
    private WxDealUserStoreFinanceService wxDealUserStoreFinanceService;

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
        ValidatorUtils.validateEntity(finance);
        if (isEnterprise()){
            return wxDealUserStoreFinanceService.saveEntity(finance);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }
}
