package com.project.controller.deal;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.deal.service.DealWaresService;
import com.project.modules.deal.vo.invoking.DealWaresChangeOnlineStatusInvokingVo;
import com.project.modules.deal.vo.save.DealWaresSaveVo;
import com.project.modules.deal.vo.update.DealWaresUpdateVo;
import com.project.service.deal.WxDealWaresService;
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
@Api(tags = "微信端商品接口", description = "WechatDealWaresController")
public class WechatDealWaresController {

    @Autowired
    private WxUploadService wxUploadService;
    @Autowired
    private WxDealWaresService wxDealWaresService;
    @Autowired
    private DealWaresService dealWaresService;

    /**
     * 个人中心-企业-商品模块-分页查询自己企业商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "个人中心-商品模块-企业-分页查询自己企业商品列表")
    @GetMapping("/personalList")
    public R personalList(@RequestParam Map<String, Object> params){
        if (isEnterprise()){
            params.put("dealStoreId", getDealStoreId());
//            return R.ok(dealWaresService.queryPersonalPage(params));
            return wxDealWaresService.queryPersonalPage(params);
        }
        return R.ok();
    }

    /**
     * 个人中心-企业-商品模块-新增商品-企业商品图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "个人中心-企业-商品模块-新增商品-企业商品图上传")
    @SysLog("个人中心-企业-商品模块-新增商品-企业商品图上传")
    @PostMapping("/upload/waresImage")
    public R waresImage(@RequestParam("file") MultipartFile file){
        return R.ok(wxUploadService.uploadImage(file, getDealPhone(),
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.DEAL.getText(),
                DateUtils.dateTime(new Date()),
                Constant.UploadImage.WARES.getText()));
    }

    /**
     * 个人中心-企业-商品模块-新增商品-行驶证图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "个人中心-企业-商品模块-新增商品-行驶证图上传")
    @SysLog("个人中心-企业-商品模块-新增商品-行驶证图上传")
    @PostMapping("/upload/drivingImage")
    public R drivingIdImage(@RequestParam("file") MultipartFile file){
        return R.ok(wxUploadService.uploadImage(file, getDealPhone(),
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.DEAL.getText(),
                DateUtils.dateTime(new Date()),
                Constant.UploadImage.DRIVINGID.getText()));
    }

    /**
     * 个人中心-企业-商品模块-新增商品-删除图片
     * @param url
     * @return
     */
    @ApiOperation(value = "个人中心-企业-商品模块-新增商品-删除图片")
    @SysLog("个人中心-企业-商品模块-新增商品-删除图片")
    @GetMapping("/delete/image")
    public R deleteImage(@RequestParam("url") String url){
        wxUploadService.deleteImage(url);
        return R.ok(StatusCode.DELETE_IMAGE_SUCCESS);
    }

    /**
     * 个人中心-企业-商品模块-新增商品-新增商品信息
     * @param wares
     * @return
     */
    @ApiOperation(value = "个人中心-企业-商品模块-新增商品-新增商品信息")
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresSaveVo")
    @SysLog("个人中心-企业-商品模块-新增商品-新增金融单")
    @PostMapping("/save")
    public R save(@RequestBody DealWaresSaveVo wares){
        if (isEnterprise()){
            ValidatorUtils.validateEntity(wares);
            wares.setDealStoreId(getDealStoreId());
            return wxDealWaresService.saveEntity(wares);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 个人中心-企业-商品模块-获取自己商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "企业客户获取自己商品的详情")
    @GetMapping("/personal/{dealWaresId}")
    public R personal(@PathVariable("dealWaresId") String dealWaresId){
        return wxDealWaresService.info(dealWaresId);
    }

    /**
     * 个人中心-企业-商品模块-修改商品-修改商品信息
     * @param wares
     * @return
     */
    @ApiOperation(value = "个人中心-企业-商品模块-修改商品-修改商品信息")
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresUpdateVo")
    @SysLog("个人中心-企业-商品模块-修改商品-修改商品信息")
    @PostMapping("/update")
    public R update (@RequestBody DealWaresUpdateVo wares){
        if (isEnterprise()){
            ValidatorUtils.validateEntity(wares);
            wares.setDealStoreId(getDealStoreId());
            return wxDealWaresService.updateEntity(wares);
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 个人中心-企业-商品模块-修改商品上线状态为上架
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "修改商品上线状态为上架")
    @SysLog("修改商品上线状态为上架")
    @GetMapping("/onLine/{dealWaresId}")
    public R onLine(@PathVariable("dealWaresId") String dealWaresId){
        if (isEnterprise()){
            DealWaresChangeOnlineStatusInvokingVo wares = new DealWaresChangeOnlineStatusInvokingVo();
            wares.setDealWaresId(dealWaresId).setDealStoreId(getDealStoreId());
            return R.ok(wxDealWaresService.onLine(wares));
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 个人中心-企业-商品模块-修改商品上线状态为下架
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "修改商品上线状态为下架")
    @SysLog("修改商品上线状态为下架")
    @GetMapping("/unLine/{dealWaresId}")
    public R unLine(@PathVariable("dealWaresId") String dealWaresId){
        if (isEnterprise()){
            DealWaresChangeOnlineStatusInvokingVo wares = new DealWaresChangeOnlineStatusInvokingVo();
            wares.setDealWaresId(dealWaresId).setDealStoreId(getDealStoreId());
            return R.ok(wxDealWaresService.unLine(wares));
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 个人中心-企业-商品模块-修改企业商品出售情况为已出售
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "修改企业商品出售情况为已出售")
    @SysLog("修改企业商品出售情况为已出售")
    @GetMapping("/sale/{dealWaresId}")
    public R sale(@PathVariable("dealWaresId") String dealWaresId){
        if (isEnterprise()){
            return R.ok(wxDealWaresService.changeSellStatus(dealWaresId));
        }
        return R.ok(Constant.DEFAUL_INDIVIDUAL);
    }

    /**
     * 企业端-分页显示商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "企业端-分页显示商品列表")
    @GetMapping("/storeList")
    public R storeList(@RequestParam Map<String, Object> params){
        return wxDealWaresService.queryStorePage(params);
    }

    /**
     * 企业端-获取商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "企业端-获取商品的详情")
    @GetMapping("/store/{dealWaresId}")
    public R store(@PathVariable("dealWaresId") String dealWaresId){
        return wxDealWaresService.store(dealWaresId);
    }

    /**
     * 零售端-分页显示商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "零售端分页显示商品列表")
    @GetMapping("/retailList")
    public R retailList(@RequestParam Map<String, Object> params){
        return wxDealWaresService.queryRetailPage(params);
    }

    /**
     * 零售端-获取商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "零售端-获取商品的详情")
    @GetMapping("/retail/{dealWaresId}")
    public R retail(@PathVariable("dealWaresId") String dealWaresId){
        return wxDealWaresService.retail(dealWaresId);
    }
}
