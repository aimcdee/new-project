package com.project.modules.deal.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.modules.Image.service.ImageService;
import com.project.modules.deal.service.DealUserStoreService;
import com.project.modules.deal.vo.invoking.DealUserStoreAuditInvokingVo;
import com.project.modules.deal.vo.save.DealUserStoreSaveVo;
import com.project.utils.DateUtils;
import com.project.utils.R;
import com.project.utils.StatusCode;
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
 * 客户企业验证Controller
 *
 * @author liangyuding
 * @date 2020-03-27
 */
@Slf4j
@RestController
@RequestMapping("/deal/user/store")
@Api(tags = "客户管理", description = "DealUserStoreController")
public class DealUserStoreController {

    @Autowired
    private DealUserStoreService dealUserStoreService;
    @Autowired
    private ImageService imageService;

    /**
     * 分页企业的申请记录
     * @param params
     * @return
     */
    @ApiOperation(value = "分页企业的申请记录")
    @GetMapping("/listPage")
    @RequiresPermissions("deal:user:store:list")
    public R listPage(@RequestParam Map<String, Object> params){
        return R.ok(dealUserStoreService.listPage(params));
    }

    /**
     * 客户上传企业门面图
     * @param file
     * @param phone
     * @return
     */
    @ApiOperation(value = "客户上传企业门面图")
    @SysLog("客户上传企业门面图")
    @PostMapping("/upload/image")
    public R store(@RequestParam("file") MultipartFile file, @RequestParam("phone") String phone){
        return R.ok(imageService.uploadImage(file, phone,
                Constant.DEAL_LINUX_IMAGE_PATH,
                Constant.UploadImage.STORE.getText(),
                Constant.UploadImage.FAÇADE.getText(),
                DateUtils.dateTime(new Date())));
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
        imageService.deleteImage(url);
        return R.ok(StatusCode.DELETE_IMAGE_SUCCESS);
    }

    /**
     * 客户申请成为企业用户
     * @param userStore
     * @return
     */
    @ApiOperation(value = "客户申请成为企业用户")
    @ApiImplicitParam(paramType = "body", name = "userStore", value = "客户申请对象", required = true, dataType = "DealUserStoreSaveVo")
    @SysLog("客户申请成为企业用户")
    @PostMapping("/save")
    @RequiresPermissions("deal:user:store:save")
    public R save(@RequestBody DealUserStoreSaveVo userStore){
        dealUserStoreService.saveEntity(userStore);
        return R.ok();
    }

    /**
     * 分页客户查看申请成为企业的申请记录
     * @param params
     * @return
     */
    @ApiOperation(value = "分页客户查看申请成为企业的申请记录")
    @GetMapping("/list")
    @RequiresPermissions("deal:user:store:list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok(dealUserStoreService.queryPage(params));
    }

    /**
     * 查看客户企业验证详情
     * @param storeId
     * @return
     */
    @ApiOperation(value = "查看客户企业验证详情")
    @GetMapping("/info/{storeId}")
    @RequiresPermissions("deal:user:store:info")
    public R info(@PathVariable("storeId") Long storeId){
        return R.ok(dealUserStoreService.info(storeId));
    }

    /**
     * 审核客户企业认证为成功
     * @return
     */
    @ApiOperation(value = "审核客户企业验证为成功")
    @PostMapping("/success")
    @SysLog("审核客户企业验证为成功")
    @RequiresPermissions("deal:user:store:update")
    public R success(@RequestBody DealUserStoreAuditInvokingVo storeAudit){
        dealUserStoreService.changeExamine(storeAudit.getStoreId(), Constant.Examine.SUCCESS.getExamine(), getSysUserId(), storeAudit.getBelongUserId());
        return R.ok();
    }

    /**
     * 审核客户企业验证为失败
     * @return
     */
    @ApiOperation(value = "审核客户企业验证为失败")
    @GetMapping("/fail/{storeId}")
    @SysLog("审核客户企业验证为失败")
    @RequiresPermissions("deal:user:store:update")
    public R fail(@PathVariable("storeId") Long storeId){
        dealUserStoreService.changeExamine(storeId, Constant.Examine.FAIL.getExamine(), getSysUserId(), null);
        return R.ok();
    }

    /**
     * 审核客户企业验证为作废
     * @return
     */
    @ApiOperation(value = "审核客户企业验证为作废")
    @GetMapping("/waste/{storeId}")
    @SysLog("审核客户企业验证为作废")
    @RequiresPermissions("deal:user:store:update")
    public R waste(@PathVariable("storeId") Long storeId){
        dealUserStoreService.changeExamine(storeId, Constant.Examine.WASTE.getExamine(), getSysUserId(), null);
        return R.ok();
    }

}
