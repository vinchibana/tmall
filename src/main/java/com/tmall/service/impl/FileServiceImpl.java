package com.tmall.service.impl;

import com.google.common.collect.Lists;
import com.tmall.service.IFileService;
import com.tmall.util.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.io.File;

/**
 * @author qiuxin
 */
@Slf4j
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    @Override
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);

        // 用获取的原文件名、扩展名和随机UUID组成新文件名
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件，上传文件的文件名为：{}，上传的路径：{}，新文件名为：{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
            FtpUtil.uploadFile(Lists.newArrayList(targetFile));
            targetFile.delete();
        } catch (IOException e) {
            log.info("上传文件出现异常", e);
            return null;
        }
        return targetFile.getName();
    }
}
