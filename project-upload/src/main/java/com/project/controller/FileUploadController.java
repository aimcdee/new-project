package com.project.controller;

import com.project.annotation.SysLog;
import com.project.constant.Constant;
import com.project.service.WechatUploadService;
import com.project.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.project.common.utils.ShiroUtils.getDealPhone;

/**
 * 前端页面文件上传Controller
 *
 * @author liangyuding
 * @date 2020-04-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
@Api(tags = "前端页面文件上传", description = "WechatUploadController")
public class FileUploadController {

    @Autowired
    private WechatUploadService wechatUploadService;

    /**
     * 门面图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "门面图上传")
    @SysLog("门面图上传")
    @PostMapping("/store")
    public R store(@RequestParam("file") MultipartFile file){
        return R.ok(wechatUploadService.uploadImage(file, getDealPhone(), Constant.UploadImage.STORE.getText()));
    }

    /**
     * 商品图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "商品图上传")
    @SysLog("商品图上传")
    @PostMapping("/wares")
    public R wares(@RequestParam("file") MultipartFile file){
        return R.ok(wechatUploadService.uploadImage(file, getDealPhone(), Constant.UploadImage.WARES.getText()));
    }

    /**
     * 在线评估价钱图上传
     * @param file
     * @return
     */
    @ApiOperation(value = "在线评估价钱图上传")
    @SysLog("在线评估价钱图上传")
    @PostMapping("/assess")
    public R assess(@RequestParam("file") MultipartFile file){
        return R.ok(wechatUploadService.uploadImage(file, getDealPhone(), Constant.UploadImage.ASSESS.getText()));
    }
}
