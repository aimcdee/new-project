package com.project.service.upload.impl;

import com.project.constant.Constant;
import com.project.service.WechatUploadService;
import com.project.utils.CheckUtils;
import com.project.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 前端页面文件上传Service
 *
 * @author liangyuding
 * @date 2020-04-09
 */
@Slf4j
@Service("wechatUploadService")
public class WechatUploadServiceImpl implements WechatUploadService {

    @Autowired
    private CheckUtils checkUtils;

    /**
     * 图片上传
     * @param file
     * @param phone
     * @param text
     */
    @Override
    public String uploadImage(MultipartFile file, String phone, String text) {
        checkUtils.checkPrictureNotNull(file);
        //保存图片的路径（这是存在我项目中的目录）
        String filePath = getFilePath(phone, text);
        //获取封装上传文件位置的全路径
        File targetFile = getTargetFile(file, filePath);
        //把本地文件上传到封装上传文件位置的全路径
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(targetFile);
    }

    //设置封装上传文件位置的全路径
    private File getTargetFile(MultipartFile file, String filePath) {
        // 原文件名
        String oldFileName = file.getOriginalFilename();
        //获取图片后缀
        String fileType = oldFileName.substring(oldFileName.lastIndexOf("."));
        // 新文件名
        String newFileName = System.currentTimeMillis() + fileType;
        return new File(filePath, newFileName);
    }

    //设置存储图片的路径
    private String getFilePath(String phone, String text) {
        File file = new File(new StringBuilder(Constant.DEAL_LINUX_IMAGE_PATH).append(phone).append("/").append(text).append("/").append(DateUtils.dateTime(new Date())).toString());
        if(!file.exists()) {//如果文件夹不存在
            //创建目录
            file.mkdirs();
        }
        return String.valueOf(file);
    }
}
