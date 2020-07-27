package com.project.modules.Image.service.impl;

import com.project.modules.Image.service.ImageService;
import com.project.modules.image.vo.ImageVo;
import com.project.utils.CheckUtils;
import com.project.utils.DateUtils;
import com.project.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 后台图片上传Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private CheckUtils checkUtils;

    @Value("${file.path.ipUrl}")
    private String ipUrl;

    @Value("${file.path.fileUrl}")
    private String fileUrl;

    /**
     * 后台图片上传
     * @param file
     * @param phone
     * @param urlPath
     * @param imagePath
     * @param lastPath
     * @return
     */
    @Override
    @Transactional
    public ImageVo uploadImage(MultipartFile file, String phone, String urlPath, String imagePath, String lastPath) {
        checkUtils.checkPrictureNotNull(file);
        //保存图片的路径（这是存在我项目中的目录）
        String filePath = getFilePath(urlPath, imagePath, phone, lastPath);
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
     * 后台删除面图
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
    private String getFilePath(String urlPath, String imagePath, String phone, String lastPath) {
        StringBuilder pathName = new StringBuilder().append(urlPath);
        if (StringUtils.isNotBlank(phone)){
            pathName = pathName.append(phone);
        }
        pathName = pathName.append(imagePath).append(DateUtils.dateTime(new Date()));
        if (StringUtils.isNotBlank(lastPath)){
            pathName = pathName.append(lastPath);
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
