package com.tmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author qiuxin
 */
public interface IFileService {

    /**
     * 商品图片上传
     * @param file 文件
     * @param path 路径
     * @return 上传文件名
     */
    String upload(MultipartFile file, String path);
}
