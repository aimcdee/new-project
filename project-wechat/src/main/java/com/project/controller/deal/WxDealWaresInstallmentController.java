package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.modules.deal.service.DealWaresInstallmentService;
import com.project.modules.deal.vo.save.DealWaresInstallmentSaveVo;
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

/**
 * 微信端咨询商品分期接口
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Slf4j
@RestController
@RequestMapping("/wechat/deal/wares/installment")
@Api(tags = "微信端咨询商品分期接口", description = "WxDealWaresInstallmentController")
public class WxDealWaresInstallmentController {

//    @Autowired
//    private WxDealWaresInstallmentService wxDealWaresInstallmentService;
    @Autowired
    private DealWaresInstallmentService dealWaresInstallmentService;

    /**
     * 客户分页查询个人咨询分期记录列表
     * @param params
     * @return
     */
    @ApiOperation(value = "客户分页查询个人咨询分期记录列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("dealUserId", getDealUserId());
        return R.ok(dealWaresInstallmentService.queryWxPage(params));
//        return wxDealWaresInstallmentService.queryPage(params);
    }

    /**
     * 新增咨询分期
     * @param installment
     * @return
     */
    @ApiOperation(value = "新增咨询分期")
    @ApiImplicitParam(paramType = "body", name = "installment", value = "咨询分期信息对象", required = true, dataType = "DealWaresInstallmentSaveVo")
    @SysLog("新增咨询分期")
    @PostMapping("/save")
    public R save(@RequestBody DealWaresInstallmentSaveVo installment){
        ValidatorUtils.validateEntity(installment);
        dealWaresInstallmentService.saveEntity(installment);
        return R.ok();
//        ValidatorUtils.validateEntity(installment);
//        return wxDealWaresInstallmentService.saveEntity(installment);
    }
}
