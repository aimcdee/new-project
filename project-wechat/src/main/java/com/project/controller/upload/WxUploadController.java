package com.project.controller.upload;

import com.project.service.upload.WxUploadService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端页面文件上传Controller
 *
 * @author liangyuding
 * @date 2020-04-09
 */
@Slf4j
@RestController
@RequestMapping("/wechat/upload")
@Api(tags = "前端页面文件上传", description = "WxUploadController")
public class WxUploadController {

    @Autowired
    private WxUploadService wxUploadService;


//    /**
//     * 商品图上传
//     * @param file
//     * @return
//     */
//    @ApiOperation(value = "商品图上传")
//    @SysLog("商品图上传")
//    @PostMapping("/wares")
//    public R wares(@RequestParam("file") MultipartFile file){
//        return R.ok(wxUploadService.uploadImage(file, getDealPhone(), Constant.UploadImage.WARES.getText()));
//    }
//
//    /**
//     * 在线评估价钱图上传
//     * @param file
//     * @return
//     */
//    @ApiOperation(value = "在线评估价钱图上传")
//    @SysLog("在线评估价钱图上传")
//    @PostMapping("/assess")
//    public R assess(@RequestParam("file") MultipartFile file){
//        return R.ok(wxUploadService.uploadImage(file, getDealPhone(), Constant.UploadImage.ASSESS.getText()));
//    }
}
