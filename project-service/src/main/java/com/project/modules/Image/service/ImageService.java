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
     * @param file             上传的文件
     * @param phone            上传文件用户的手机号
     * @param ServerPath       服务器图片文件存储通用路径
     * @param filePrefixPath   图片文件存储路径前缀
     * @param fileSuffixPath   图片文件存储路径后缀
     * @param lastPath         图片文件存储最后路径
     * @return
     */
    ImageVo uploadImage(MultipartFile file, String phone, String ServerPath, String filePrefixPath, String fileSuffixPath, String lastPath);

    /**
     * 后台删除图片
     * @param url
     */
    void deleteImage(String url);
}
