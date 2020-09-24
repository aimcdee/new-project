package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.Image.service.ImageService;
import com.project.modules.deal.service.DealWaresExamineService;
import com.project.modules.deal.service.DealWaresService;
import com.project.modules.deal.vo.invoking.DealWaresInvokingVo;
import com.project.modules.deal.vo.save.DealWaresSaveVo;
import com.project.modules.deal.vo.update.DealWaresUpdateVo;
import com.project.utils.DateUtils;
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

import java.util.Date;
import java.util.Map;

import static com.project.utils.ShiroUtils.getSysUserId;

/**
 * 企业客户商品Controller
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@RestController
@RequestMapping("/deal/wares")
@Api(tags = "商品管理", description = "DealWaresController")
public class DealWaresController {

    @Autowired
    private DealWaresService dealWaresService;
    @Autowired
    private DealWaresExamineService dealWaresExamineService;
    @Autowired
    private ImageService imageService;

    /**
     * 分页企业商品列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页企业商品列表")
    @GetMapping("/list")
    @RequiresPermissions("deal:wares:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealWaresService.queryPage(params));
    }

    /**
     * 企业商品图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "企业商品图上传")
    @SysLog("企业商品图上传")
    @PostMapping("/upload/waresImage")
    public R waresImage(@RequestParam("file") MultipartFile file, @RequestParam("phone") String phone){
        return R.ok(imageService.uploadImage(file, phone,
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.DEAL.getText(),
                DateUtils.dateTime(new Date()),
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
                Constant.UploadImage.DEAL.getText(),
                DateUtils.dateTime(new Date()),
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
     * 企业客户新增商品信息
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresSaveVo")
    @SysLog("企业客户新增商品信息")
    @PostMapping("/save")
    @RequiresPermissions("deal:wares:save")
    public R save (@RequestBody DealWaresSaveVo wares){
        ValidatorUtils.validateEntity(wares);
        dealWaresService.saveEntity(wares);
        return R.ok();
    }

    /**
     * 获取企业商品的详情
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "获取企业商品的详情")
    @GetMapping("/info/{dealWaresId}")
    @RequiresPermissions("deal:wares:info")
    public R info(@PathVariable("dealWaresId") String dealWaresId){
        return R.ok(dealWaresService.info(dealWaresId));
    }

    /**
     * 企业客户更新商品信息
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品信息对象", required = true, dataType = "DealWaresUpdateVo")
    @SysLog("企业客户更新商品信息")
    @PostMapping("/update")
    @RequiresPermissions("deal:wares:update")
    public R update (@RequestBody DealWaresUpdateVo wares){
        ValidatorUtils.validateEntity(wares);
        dealWaresService.updateEntity(wares);
        return R.ok();
    }

    /**
     * 修改企业商品上线状态为驳回
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品审核信息", required = true, dataType = "DealWaresInvokingVo")
    @ApiOperation(value = "修改企业商品上线状态为驳回")
    @SysLog("修改企业商品上线状态为驳回")
    @PostMapping("/reject")
    @RequiresPermissions("deal:wares:update")
    public R reject(@RequestBody DealWaresInvokingVo wares){
        ValidatorUtils.validateEntity(wares);
        dealWaresService.changeOnLineStatus(wares.getDealWaresId(), wares.getRemark(), Constant.WaresOnLineStatus.REJECT.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改企业商品上线状态为经理审核
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品审核信息", required = true, dataType = "DealWaresInvokingVo")
    @ApiOperation(value = "修改企业商品上线状态为经理审核")
    @SysLog("修改企业商品上线状态为经理审核")
    @PostMapping("/manager")
    @RequiresPermissions("deal:wares:update")
    public R manager(@RequestBody DealWaresInvokingVo wares){
        dealWaresService.changeOnLineStatus(wares.getDealWaresId(), wares.getRemark(), Constant.WaresOnLineStatus.MANAGER.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改企业商品上线状态为上架
     * @param wares
     * @return
     */
    @ApiImplicitParam(paramType = "body", name = "wares", value = "商品审核信息", required = true, dataType = "DealWaresInvokingVo")
    @ApiOperation(value = "修改企业商品上线状态为上架")
    @SysLog("修改企业商品上线状态为上架")
    @PostMapping("/onLine")
    @RequiresPermissions("deal:wares:update")
    public R onLine(@RequestBody DealWaresInvokingVo wares){
        dealWaresService.changeOnLineStatus(wares.getDealWaresId(), wares.getRemark(), Constant.WaresOnLineStatus.ONLINE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改企业商品上线状态为下架
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "修改企业商品上线状态为下架")
    @SysLog("修改企业商品上线状态为下架")
    @GetMapping("/unLine/{dealWaresId}")
    @RequiresPermissions("deal:wares:update")
    public R unLine(@PathVariable("dealWaresId") String dealWaresId){
        dealWaresService.changeOnLineStatus(dealWaresId, null, Constant.WaresOnLineStatus.UNLINE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改企业商品出售情况为已出售
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "修改企业商品出售情况为已出售")
    @SysLog("修改企业商品出售情况为已出售")
    @GetMapping("/sale/{dealWaresId}")
    @RequiresPermissions("deal:wares:update")
    public R sale(@PathVariable("dealWaresId") String dealWaresId){
        dealWaresService.changeSellStatus(dealWaresId, Constant.WaresSellStatus.SALE.getStatus());
        return R.ok();
    }

    /**
     * 获取商品的审核记录
     * @param dealWaresId
     * @return
     */
    @ApiOperation(value = "获取商品的审核记录")
    @GetMapping("/getExamineList/{dealWaresId}")
    @RequiresPermissions("deal:wares:info")
    public R getExamineList(@PathVariable("dealWaresId") String dealWaresId){
        return R.ok(dealWaresExamineService.getExamineList(dealWaresId));
    }
}
