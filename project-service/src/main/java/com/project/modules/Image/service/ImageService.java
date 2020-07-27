package com.project.modules.Image.service;

import com.project.modules.image.vo.ImageVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 后台图片上传Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
public interface ImageService {

    /**
     * 后台图片上传
     * @param file
     * @param phone
     * @param urlPath
     * @param imagePath
     * @param lastPath
     * @return
     */
    ImageVo uploadImage(MultipartFile file, String phone, String urlPath, String imagePath, String lastPath);

    /**
     * 后台删除图片
     * @param url
     */
    void deleteImage(String url);
}
