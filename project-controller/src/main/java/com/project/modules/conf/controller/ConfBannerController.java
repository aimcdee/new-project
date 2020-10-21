package com.project.modules.conf.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.Image.service.ImageService;
import com.project.modules.conf.service.ConfBannerService;
import com.project.modules.conf.vo.save.ConfBannerSaveVo;
import com.project.modules.conf.vo.update.ConfBannerUpdateVo;
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
 * 轮播图Controller
 *
 * @author liangyuding
 * @date 2020-04-14
 */
@Slf4j
@RestController
@RequestMapping("/conf/banner")
@Api(tags = "轮播图管理", description = "ConfBannerController")
public class ConfBannerController {

    @Autowired
    private ConfBannerService confBannerService;
    @Autowired
    private ImageService imageService;

    /**
     * 分页查询轮播图列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询状态为正常的轮播图列表")
    @GetMapping("/list")
    @RequiresPermissions("conf:banner:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(confBannerService.queryPage(params));
    }

    /**
     * 轮播图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "轮播图上传")
    @SysLog("轮播图上传")
    @PostMapping("/upload/image")
    public R uploadImage(@RequestParam("file") MultipartFile file){
        return R.ok(imageService.uploadImage(file, null,
                Constant.COU_LINUX_IMAGE_PATH,
                Constant.UploadImage.BANNER.getText(),
                DateUtils.dateTime(new Date()),
                null));
    }

    /**
     * 删除轮播图
     * @param url
     * @return
     */
    @ApiOperation(value = "删除轮播图")
    @SysLog("删除轮播图")
    @GetMapping("/delete/image")
    public R deleteImage(@RequestParam("url") String url){
        imageService.deleteImage(url);
        return R.ok(StatusCode.DELETE_IMAGE_SUCCESS);
    }

    /**
     * 新增轮播图
     * @param banner
     * @return
     */
    @ApiOperation(value = "新增轮播图")
    @ApiImplicitParam(paramType = "body", name = "banner", value = "轮播图信息对象", required = true, dataType = "ConfBannerSaveVo")
    @SysLog("新增轮播图")
    @PostMapping("/save")
    @RequiresPermissions("conf:banner:save")
    public R save(@RequestBody ConfBannerSaveVo banner){
        ValidatorUtils.validateEntity(banner);
        confBannerService.saveEntity(banner, getSysUserId());
        return R.ok();
    }

    /**
     * 根据轮播图ID获取轮播图详情
     * @param bannerId
     * @return
     */
    @ApiOperation(value = "根据轮播图ID获取轮播图详情")
    @GetMapping("/info/{bannerId}")
    @RequiresPermissions("conf:banner:info")
    public R info(@PathVariable("bannerId") Long bannerId) {
        return R.ok(confBannerService.info(bannerId));
    }

    /**
     * 更新轮播图
     * @param banner
     * @return
     */
    @ApiOperation(value = "更新轮播图")
    @ApiImplicitParam(paramType = "body", name = "banner", value = "轮播图信息对象", required = true, dataType = "ConfBannerUpdateVo")
    @SysLog("更新轮播图")
    @PostMapping("/update")
    @RequiresPermissions("conf:banner:update")
    public R update(@RequestBody ConfBannerUpdateVo banner){
        ValidatorUtils.validateEntity(banner);
        confBannerService.updateEntity(banner, getSysUserId());
        return R.ok();
    }

    /**
     * 修改轮播图的状态为启用
     * @param bannerId
     * @return
     */
    @ApiOperation(value = "修改轮播图的状态为启用")
    @SysLog("修改轮播图的状态为启用")
    @GetMapping("/normal/{bannerId}")
    @RequiresPermissions("conf:banner:update")
    public R normal(@PathVariable("bannerId") Long bannerId){
        confBannerService.changeStatus(bannerId, Constant.StatusEnums.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改轮播图的状态为禁用
     * @param bannerId
     * @return
     */
    @ApiOperation(value = "修改轮播图的状态为禁用")
    @SysLog("修改轮播图的状态为禁用")
    @GetMapping("/disable/{bannerId}")
    @RequiresPermissions("conf:banner:update")
    public R disable(@PathVariable("bannerId") Long bannerId){
        confBannerService.changeStatus(bannerId, Constant.StatusEnums.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }
}
