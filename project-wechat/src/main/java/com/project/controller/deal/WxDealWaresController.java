package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.vo.save.DealWaresSaveVo;
import com.project.modules.deal.vo.update.DealWaresUpdateVo;
import com.project.service.deal.WxDealWaresService;
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

import static com.project.common.utils.ShiroUtils.*;

/**
 * 微信端商品接口
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/deal/wares")
@Api(tags = "微信端商品接口", description = "WxDealWaresController")
public class WxDealWaresController {

    @Autowired
    private WxUploadService wxUploadService;
    @Autowired
    private WxDealWaresService wxDealWaresService;

    /**
     * 企业客户分页查询自己上传的企业商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "企业客户分页查询自己上传的企业商品列表")
    @GetMapping("/personalList")
    public R personalList(@RequestParam Map<String, Object> params){
        if (isEnterprise()){
            params.put("dealStoreId", getDealStoreId());
            return wxDealWaresService.queryPersonalPage(params);
        }
        return R.ok();
    }

    /**
     * 企业端分页显示商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "企业端分页显示商品列表")
    @GetMapping("/storeList")
    public R storeList(@RequestParam Map<String, Object> params){
        return wxDealWaresService.queryStorePage(params);
    }

    /**
     * 零售端分页显示商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "零售端分页显示商品列表")
    @GetMapping("/retailList")
    public R retailList(@RequestParam Map<String, Object> params){
        return wxDealWaresService.queryRetailPage(params);
    }

    /**
     * 企业商品图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "企业商品图上传")
    @SysLog("企业商品图上传")
    @PostMapping("/upload/waresImage")
    public R waresImage(@RequestParam("file") MultipartFile file){
        return R.ok(wxUploadService.uploadImage(file, getDealPhone(),
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.DEAL.getText(),
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
    public R drivingIdImage(@RequestParam("file") MultipartFile file){
        return R.ok(wxUploadService.uploadImage(file, getDealPhone(),
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.DEAL.getText(),
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
        wxUploadService.deleteImage(url);
        return R.ok(StatusCode.DELETE_IMAGE_SUCCESS);
    }

    /**
     * 企业客户新增商品信息
     * @param wares
     * @return
     */
    @ApiOperation(value = "企业客户新增商品信息")
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresSaveVo")
    @SysLog("企业客户新增金融单")
    @PostMapping("/save")
    public R save(@RequestBody DealWaresSaveVo wares){
        ValidatorUtils.validateEntity(wares);
        if (isEnterprise()){
            return wxDealWaresService.saveEntity(wares);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 企业客户获取企业客户个人商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "企业客户获取企业客户个人商品的详情")
    @GetMapping("/personal/{dealWaresId}")
    public R personal(@PathVariable("dealWaresId") String dealWaresId){
        return wxDealWaresService.info(dealWaresId);
    }

    /**
     * 企业客户获取企业端商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "企业客户获取企业端商品的详情")
    @GetMapping("/store/{dealWaresId}")
    public R store(@PathVariable("dealWaresId") String dealWaresId){
        return wxDealWaresService.store(dealWaresId);
    }

    /**
     * 客户获取零售端商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "客户获取零售端商品的详情")
    @GetMapping("/retail/{dealWaresId}")
    public R retail(@PathVariable("dealWaresId") String dealWaresId){
        return wxDealWaresService.retail(dealWaresId);
    }

    /**
     * 企业客户修改个人商品信息
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresUpdateVo")
    @SysLog("企业客户修改个人商品信息")
    @PostMapping("/update")
    public R update (@RequestBody DealWaresUpdateVo wares){
        ValidatorUtils.validateEntity(wares);
        if (isEnterprise()){
            return wxDealWaresService.updateEntity(wares);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }
}
