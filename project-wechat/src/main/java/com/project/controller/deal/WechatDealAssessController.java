package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealAssessService;
import com.project.modules.deal.vo.save.DealAssessSaveVo;
import com.project.service.deal.WxDealAssessService;
import com.project.service.upload.WxUploadService;
import com.project.utils.DateUtils;
import com.project.utils.R;
import com.project.utils.StatusCode;
import com.project.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

import static com.project.common.utils.ShiroUtils.getDealPhone;
import static com.project.common.utils.ShiroUtils.getDealUserId;

/**
 * 微信端商品评估端口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/deal/assess")
@Api(tags = "微信端商品评估端口", description = "WechatDealAssessController")
public class WechatDealAssessController {

    @Autowired
    private WxUploadService wxUploadService;
    @Autowired
    private WxDealAssessService wxDealAssessService;
    @Autowired
    private DealAssessService dealAssessService;

    /**
     * 分页查询个人商品评估列表
     * @return
     */
    @ApiOperation(value = "分页查询个人商品评估列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        params.put("dealUserId", getDealUserId());
        return R.ok(dealAssessService.queryWxPage(params));
//        return wxDealAssessService.list(params);
    }

    /**
     * 评估-商品图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "评估-商品图上传")
    @SysLog("评估-商品图上传")
    @PostMapping("/upload/waresImage")
    public R waresImage(@RequestParam("file") MultipartFile file){
        return R.ok(wxUploadService.uploadImage(file, getDealPhone(),
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.ASSESS.getText(),
                Constant.UploadImage.WARES.getText(),
                DateUtils.dateTime(new Date())));
    }

    /**
     * 评估-行驶证图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "评估-行驶证图上传")
    @SysLog("评估-行驶证图上传")
    @PostMapping("/upload/drivingImage")
    public R drivingIdImage(@RequestParam("file") MultipartFile file){
        return R.ok(wxUploadService.uploadImage(file, getDealPhone(),
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.ASSESS.getText(),
                Constant.UploadImage.DRIVINGID.getText(),
                DateUtils.dateTime(new Date())));
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
        wxUploadService.deleteImage(url);
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
    public R save(@RequestBody DealAssessSaveVo assess){
        ValidatorUtils.validateEntity(assess);
        assess.setDealUserId(getDealUserId());
        dealAssessService.saveEntity(assess);
        return R.ok();
//        return wxDealAssessService.saveEntity(assess);
    }

    /**
     * 根据商品评估ID获取商品评估详情
     * @param dealAssessId
     * @return
     */
    @ApiOperation(value = "根据商品评估ID获取商品评估详情")
    @GetMapping("/info/{dealAssessId}")
    public R info(@PathVariable("dealAssessId") Long dealAssessId) {
//        return wxDealAssessService.info(dealAssessId);
        return R.ok(dealAssessService.infoWx(dealAssessId));
    }
}
