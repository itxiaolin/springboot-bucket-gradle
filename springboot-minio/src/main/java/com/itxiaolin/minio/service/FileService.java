package com.itxiaolin.minio.service;

import org.springframework.web.multipart.MultipartFile;


/**
 * 功能描述： 文件上传接口
 * @author lxq
 * @version 1.00
 */
public interface FileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception 异常
     */
    public String uploadFile(MultipartFile file) throws Exception;
}
