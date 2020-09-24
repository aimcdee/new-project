package com.project.service.upload.impl;

import com.project.modules.image.vo.ImageVo;
import com.project.service.upload.WxUploadService;
import com.project.utils.CheckUtils;
import com.project.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 前端页面文件上传Service
 *
 * @author liangyuding
 * @date 2020-04-09
 */
@Slf4j
@Service("wechatUploadService")
public class WxUploadServiceImpl implements WxUploadService {

    @Autowired
    private CheckUtils checkUtils;

    @Value("${file.path.ipUrl}")
    private String ipUrl;

    @Value("${file.path.fileUrl}")
    private String fileUrl;

    /**
     * 后台图片上传
     * @param file             上传的文件
     * @param phone            上传文件用户的手机号
     * @param ServerPath       服务器图片文件存储通用路径
     * @param filePrefixPath   图片文件存储路径前缀
     * @param fileSuffixPath   图片文件存储路径后缀
     * @param lastPath         图片文件存储最后路径
     * @return
     */
    @Override
    public ImageVo uploadImage(MultipartFile file, String phone, String ServerPath, String filePrefixPath, String fileSuffixPath, String lastPath) {
        checkUtils.checkPrictureNotNull(file);
        //保存图片的路径（这是存在项目中的目录）
        String filePath = getFilePath(ServerPath, filePrefixPath, fileSuffixPath, phone, lastPath);
        //获取封装上传文件位置的全路径
        File targetFile = getTargetFile(file, filePath);
        //把本地文件上传到封装上传文件位置的全路径
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageVo imageVo = new ImageVo();
        imageVo.setImg(String.valueOf(targetFile)).setUrl(ipUrl + String.valueOf(targetFile).substring(fileUrl.length()));
        return imageVo;
    }

    /**
     * 删除图片
     * @param url
     */
    @Override
    public void deleteImage(String url) {
        String pathName = fileUrl + String.valueOf(url).substring(ipUrl.length());
        File file = new File(pathName);
        if (file.exists()){
            file.delete();
            log.debug("删除文件成功");
        }
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

    //判断是否存在该文件夹,如果不存在测新增文件夹,并返回文件夹路径
    private String getFilePath(String ServerPath, String filePrefixPath, String fileSuffixPath, String phone, String lastPath) {
        String pathName = new StringBuilder().append(ServerPath).toString();
        if (StringUtils.isNotBlank(phone)){
            pathName = new StringBuilder().append(pathName).append(phone).toString();
        }
        pathName = new StringBuilder().append(pathName).append(filePrefixPath).toString();
        if (StringUtils.isNotBlank(fileSuffixPath)){
            pathName = new StringBuilder().append(pathName).append(fileSuffixPath).toString();
        }
        if (StringUtils.isNotBlank(lastPath)){
            pathName = new StringBuilder().append(pathName).append(lastPath).toString();
        }
        File file = new File(pathName.toString());
        if(!file.exists()) {//如果文件夹不存在
            //创建目录
            file.mkdirs();
            log.debug("上传图片");
        }
        return String.valueOf(file);
    }
}
