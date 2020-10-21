package com.project.modules.cou.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.Image.service.ImageService;
import com.project.modules.cou.service.CouWaresModelService;
import com.project.modules.cou.vo.save.CouWaresModelSaveVo;
import com.project.modules.cou.vo.update.CouWaresModelUpdateVo;
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
 * 型号Controller
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@RestController
@RequestMapping("/cou/wares/model")
@Api(tags = "商品管理", description = "CouWaresModelController")
public class CouWaresModelController {

    @Autowired
    private CouWaresModelService couWaresModelService;
    @Autowired
    private ImageService imageService;

    /**
     * 分页查询状态为正常的型号列表
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询状态为正常的型号列表")
    @GetMapping("/list")
    @RequiresPermissions("cou:wares:model:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(couWaresModelService.queryPage(params));
    }

    /**
     * 型号图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "型号图上传")
    @SysLog("型号图上传")
    @PostMapping("/upload/image")
    public R uploadImage(@RequestParam("file") MultipartFile file){
        return R.ok(imageService.uploadImage(file, null,
                Constant.COU_LINUX_IMAGE_PATH,
                Constant.UploadImage.MODEL.getText(),
                DateUtils.dateTime(new Date()),
                null));
    }

    /**
     * 删除型号图
     * @param url
     * @return
     */
    @ApiOperation(value = "删除型号图")
    @SysLog("删除型号图")
    @GetMapping("/delete/image")
    public R deleteImage(@RequestParam("url") String url){
        imageService.deleteImage(url);
        return R.ok(StatusCode.DELETE_IMAGE_SUCCESS);
    }

    /**
     * 新增型号
     * @param model
     * @return
     */
    @ApiOperation(value = "新增型号")
    @ApiImplicitParam(paramType = "body", name = "model", value = "型号信息对象", required = true, dataType = "CouWaresModelSaveVo")
    @SysLog("新增型号")
    @PostMapping("/save")
    @RequiresPermissions("cou:wares:model:save")
    public R save(@RequestBody CouWaresModelSaveVo model){
        ValidatorUtils.validateEntity(model);
        couWaresModelService.saveEntity(model, getSysUserId());
        return R.ok();
    }

    /**
     * 根据型号ID获取型号详情
     * @param couModelId
     * @return
     */
    @ApiOperation(value = "根据型号ID获取型号详情")
    @GetMapping("/info/{couModelId}")
    @RequiresPermissions("cou:wares:model:info")
    public R info(@PathVariable("couModelId") Long couModelId) {
        return R.ok(couWaresModelService.info(couModelId));
    }

    /**
     * 更新型号
     * @param model
     * @return
     */
    @ApiOperation(value = "更新型号")
    @ApiImplicitParam(paramType = "body", name = "model", value = "型号信息对象", required = true, dataType = "CouWaresModelUpdateVo")
    @SysLog("更新型号")
    @PostMapping("/update")
    @RequiresPermissions("cou:wares:model:update")
    public R update(@RequestBody CouWaresModelUpdateVo model){
        ValidatorUtils.validateEntity(model);
        couWaresModelService.updateEntity(model, getSysUserId());
        return R.ok();
    }

    /**
     * 修改型号的状态为启用
     * @param couModelId
     * @return
     */
    @ApiOperation(value = "修改型号的状态为启用")
    @SysLog("修改型号的状态为启用")
    @GetMapping("/normal/{couModelId}")
    @RequiresPermissions("cou:wares:model:update")
    public R normal(@PathVariable("couModelId") Long couModelId){
        couWaresModelService.changeStatus(couModelId, Constant.StatusEnums.NORMAL.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 修改型号的状态为禁用
     * @param couModelId
     * @return
     */
    @ApiOperation(value = "修改型号的状态为禁用")
    @SysLog("修改型号的状态为禁用")
    @GetMapping("/disable/{couModelId}")
    @RequiresPermissions("cou:wares:model:update")
    public R disable(@PathVariable("couModelId") Long couModelId){
        couWaresModelService.changeStatus(couModelId, Constant.StatusEnums.DISABLE.getStatus(), getSysUserId());
        return R.ok();
    }

    /**
     * 获取所有状态为正常型号对象
     * @return
     */
    @ApiOperation(value = "获取所有状态为正常型号对象")
    @GetMapping("/getCouModelList")
    public R getCouModelList(){
        return R.ok(couWaresModelService.getCouModelList());
    }
}
