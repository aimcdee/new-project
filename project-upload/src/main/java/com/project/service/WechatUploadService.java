package com.project.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 前端页面文件上传Service
 *
 * @author liangyuding
 * @date 2020-04-09
 */
public interface WechatUploadService {

    /**
     * 商家门面图上传
     * @param file
     * @param phone
     * @param text
     */
    String uploadImage(MultipartFile file, String phone, String text);
}
