package com.project.modules.cou.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.Image.service.ImageService;
import com.project.modules.cou.service.CouWaresBrandService;
import com.project.modules.cou.vo.save.CouWaresBrandSaveVo;
import com.project.modules.cou.vo.update.CouWaresBrandUpdateVo;
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
 * 品牌Controller
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@RestController
@RequestMapping("/cou/wares/brand")
@Api(tags = "商品管理", description = "CouWaresBrandController")
public class CouWaresBrandController {

    @Autowired
    private CouWaresBrandService couWaresBrandService;
    @Autowired
    private ImageService imageService;

    /**
     * 分页查询品牌列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询品牌列表")
    @GetMapping("/list")
    @RequiresPermissions("cou:wares:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(couWaresBrandService.queryPage(params));
    }

    /**
     * 品牌图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "品牌图上传")
    @SysLog("品牌图上传")
    @PostMapping("/upload/image")
    public R uploadImage(@RequestParam("file") MultipartFile file){
        return R.ok(imageService.uploadImage(file, null,
                Constant.COU_LINUX_IMAGE_PATH,
                Constant.UploadImage.BRAND.getText(),
                DateUtils.dateTime(new Date()),
                null));
    }

    /**
     * 删除品牌图
     * @param url
     * @return
     */
    @ApiOperation(value = "删除品牌图")
    @SysLog("删除品牌图")
    @GetMapping("/delete/image")
    public R deleteImage(@RequestParam("url") String url){
        imageService.deleteImage(url);
        return R.ok(StatusCode.DELETE_IMAGE_SUCCESS);
    }

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @ApiOperation(value = "新增品牌")
    @ApiImplicitParam(paramType = "body", name = "brand", value = "品牌信息对象", required = true, dataType = "CouWaresBrandSaveVo")
    @SysLog("新增品牌")
    @PostMapping("/save")
    @RequiresPermissions("cou:wares:brand:save")
    public R save(@RequestBody CouWaresBrandSaveVo brand){
        ValidatorUtils.validateEntity(brand);
        couWaresBrandService.saveEntity(brand, getSysUserId());
        return R.ok();
    }

    /**
     * 根据品牌ID获取品牌详情
     * @param couBrandId
     * @return
     */
    @ApiOperation(value = "根据品牌ID获取品牌详情")
    @GetMapping("/info/{couBrandId}")
    @RequiresPermissions("cou:wares:brand:info")
    public R info(@PathVariable("couBrandId") Long couBrandId) {
        return R.ok(couWaresBrandService.info(couBrandId));
    }

    /**
     * 更新品牌
     * @param brand
     * @return
     */
    @ApiOperation(value = "更新品牌")
    @ApiImplicitParam(paramType = "body", name = "brand", value = "品牌信息对象", required = true, dataType = "CouWaresBrandUpdateVo")
    @SysLog("更新品牌")
    @PostMapping("/update")
    @RequiresPermissions("cou:wares:brand:update")
    public R update(@RequestBody CouWaresBrandUpdateVo brand){
        ValidatorUtils.validateEntity(brand);
        couWaresBrandService.updateEntity(brand, getSysUserId());
        return R.ok();
    }

    /**
     * 修改品牌的状态为启用
     * @param couBrandId
     * @return
     */
    @ApiOperation(value = "修改品牌的状态为启用")
    @SysLog("修改品牌的状态为启用")
    @GetMapping("/normal/{couBrandId}")
    @RequiresPermissions("cou:wares:brand:update")
    public R normal(@PathVariable("couBrandId") Long couBrandId){
        couWaresBrandService.changeStatus(couBrandId, Constant.Status.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改品牌的状态为禁用
     * @param couBrandId
     * @return
     */
    @ApiOperation(value = "修改品牌的状态为禁用")
    @SysLog("修改品牌的状态为禁用")
    @GetMapping("/disable/{couBrandId}")
    @RequiresPermissions("cou:wares:brand:update")
    public R disable(@PathVariable("couBrandId") Long couBrandId){
        couWaresBrandService.changeStatus(couBrandId, Constant.Status.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 获取所有状态为正常品牌对象
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常品牌对象")
    @GetMapping("/getCouBrandList")
    public R getCouBrandList(){
        return R.ok(couWaresBrandService.getCouBrandList());
    }
}
