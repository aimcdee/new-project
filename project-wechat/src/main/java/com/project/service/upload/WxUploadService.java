package com.project.service.upload;

import com.project.modules.image.vo.ImageVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 前端页面文件上传Service
 *
 * @author liangyuding
 * @date 2020-04-09
 */
public interface WxUploadService {

    /**
     * 图片上传
     * @param file
     * @param phone
     * @param urlPath
     * @param imagePath
     * @param lastPath
     * @return
     */
    ImageVo uploadImage(MultipartFile file, String phone, String urlPath, String imagePath, String lastPath);

    /**
     * 删除图片
     * @param url
     */
    void deleteImage(String url);
}
