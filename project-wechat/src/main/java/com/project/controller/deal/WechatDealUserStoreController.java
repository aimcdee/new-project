package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealUserStoreService;
import com.project.modules.deal.vo.save.DealUserStoreSaveVo;
import com.project.service.deal.WxDealUserStoreService;
import com.project.service.upload.WxUploadService;
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

import java.util.Map;

import static com.project.common.utils.ShiroUtils.getDealPhone;
import static com.project.common.utils.ShiroUtils.getDealUserId;

/**
 * 微信端客户企业接口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/deal/user/store")
@Api(tags = "微信端客户企业接口", description = "WechatDealUserStoreController")
public class WechatDealUserStoreController {

    @Autowired
    private WxUploadService wxUploadService;
    @Autowired
    private WxDealUserStoreService wxDealUserStoreService;
    @Autowired
    private DealUserStoreService dealUserStoreService;

    /**
     * 客户查看申请企业验证的申请记录
     * @return
     */
    @ApiOperation(value = "客户查看申请企业验证的申请记录")
    @GetMapping("/recordList")
    public R recordList(@RequestParam Map<String, Object> params) {
        params.put("dealUserId", getDealUserId());
        return R.ok(dealUserStoreService.queryPage(params));
//        return wxDealUserStoreService.recordList(params);
    }

    /**
     * 门面图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "门面图上传")
    @SysLog("门面图上传")
    @PostMapping("/upload")
    public R store(@RequestParam("file") MultipartFile file){
        return R.ok(wxUploadService.uploadImage(file, getDealPhone(), Constant.DEAL_LINUX_IMAGE_PATH, Constant.UploadImage.STORE.getText(), null));
    }

    /**
     * 删除客户企业门面图
     * @param url
     * @return
     */
    @ApiOperation(value = "删除客户企业门面图")
    @SysLog("删除客户企业门面图")
    @GetMapping("/delete/image")
    public R deleteImage(@RequestParam("url") String url){
        wxUploadService.deleteImage(url);
        return R.ok(StatusCode.DELETE_IMAGE_SUCCESS);
    }

    /**
     * 客户申请企业验证
     * @param store
     * @return
     */
    @ApiOperation(value = "客户申请企业验证")
    @ApiImplicitParam(paramType = "body", name = "store", value = "客户申请对象", required = true, dataType = "DealUserStoreSaveVo")
    @SysLog("客户申请企业验证")
    @PostMapping("/save")
    public R save(@RequestBody DealUserStoreSaveVo store){
        ValidatorUtils.validateEntity(store);
        store.setDealUserId(getDealUserId());
        dealUserStoreService.saveEntity(store);
        return R.ok();
//        return wxDealUserStoreService.saveEntity(store);
    }
}
