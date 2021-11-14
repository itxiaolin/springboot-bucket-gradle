package com.itxiaolin.minio.service;

import com.itxiaolin.minio.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述： Minio 文件存储
 * @author lxq
 * @version 1.00
 */
@Primary
@Service
public class MinioFileServiceImpl implements FileService{
    private final MinioConfig minioConfig;
    private final MinioClient client;
    public MinioFileServiceImpl(MinioConfig minioConfig, MinioClient client){
        this.minioConfig=minioConfig;
        this.client=client;
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        client.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }
}
