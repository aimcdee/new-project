package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.Image.service.ImageService;
import com.project.modules.deal.service.DealAssessService;
import com.project.modules.deal.vo.save.DealAssessSaveVo;
import com.project.modules.deal.vo.update.DealAssessUpdateVo;
import com.project.utils.R;
import com.project.utils.StatusCode;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.project.utils.ShiroUtils.getSysUserId;

/**
 * 商品评估Controller
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@RestController
@RequestMapping("/deal/assess")
@Api(tags = "商品管理", description = "DealAssessController")
public class DealAssessController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private DealAssessService dealAssessService;

    /**
     * 分页查询商品评估列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询商品评估列表")
    @GetMapping("/list")
    @RequiresPermissions("deal:assess:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealAssessService.queryPage(params));
    }

    /**
     * 评估商品图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "评估商品图上传")
    @SysLog("评估商品图上传")
    @PostMapping("/upload/waresImage")
    public R waresImage(@RequestParam("file") MultipartFile file, @RequestParam("phone") String phone){
        return R.ok(imageService.uploadImage(file, phone,
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.ASSESS.getText(),
                Constant.UploadImage.WARES.getText()));
    }

    /**
     * 行驶证图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "行驶证图上传")
    @SysLog("行驶证图上传")
    @PostMapping("/upload/drivingImage")
    public R drivingIdImage(@RequestParam("file") MultipartFile file, @RequestParam("phone") String phone){
        return R.ok(imageService.uploadImage(file, phone,
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.ASSESS.getText(),
                Constant.UploadImage.DRIVINGID.getText()));
    }

    /**
     * 删除图片
     * @param url
     * @return
     */
    @ApiOperation(value = "删除图片")
    @SysLog("删除图片")
    @GetMapping("/delete/image")
    public R deleteImage(@RequestParam("url") String url){
        imageService.deleteImage(url);
        return R.ok(StatusCode.DELETE_IMAGE_SUCCESS);
    }

    /**
     * 新增商品评估
     * @param assess
     * @return
     */
    @ApiOperation(value = "新增商品评估")
    @ApiImplicitParam(paramType = "body", name = "assess", value = "商品评估信息对象", required = true, dataType = "DealAssessSaveVo")
    @SysLog("新增商品评估")
    @PostMapping("/save")
    @RequiresPermissions("deal:assess:save")
    public R save(@RequestBody DealAssessSaveVo assess){
        ValidatorUtils.validateEntity(assess);
        dealAssessService.saveEntity(assess);
        return R.ok();
    }

    /**
     * 根据商品评估ID获取商品评估详情
     * @param dealAssessId
     * @return
     */
    @ApiOperation(value = "根据商品评估ID获取商品评估详情")
    @GetMapping("/info/{dealAssessId}")
    @RequiresPermissions("deal:assess:info")
    public R info(@PathVariable("dealAssessId") Long dealAssessId) {
        return R.ok(dealAssessService.info(dealAssessId));
    }

    /**
     * 评估价钱
     * @param assess
     * @return
     */
    @ApiOperation(value = "评估价钱")
    @ApiImplicitParam(paramType = "body", name = "assess", value = "商品评估信息对象", required = true, dataType = "DealAssessUpdateVo")
    @SysLog("评估价钱")
    @PostMapping("/update")
    @RequiresPermissions("deal:assess:update")
    public R update(@RequestBody DealAssessUpdateVo assess){
        ValidatorUtils.validateEntity(assess);
        dealAssessService.updateEntity(assess, getSysUserId());
        return R.ok();
    }

    /**
     * 审核客户提交的评估作废
     * @param dealAssessId
     * @return
     */
    @ApiOperation(value = "审核客户提交的评估作废")
    @GetMapping("/waste/{dealAssessId}")
    @SysLog("审核客户提交的评估作废")
    @RequiresPermissions("deal:assess:update")
    public R waste(@PathVariable("dealAssessId") Long dealAssessId){
        dealAssessService.changeStatus(dealAssessId, Constant.AssessStatus.WASTE.getStatus(), getSysUserId());
        return R.ok();
    }
}
