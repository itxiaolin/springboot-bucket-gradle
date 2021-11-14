package com.itxiaolin.minio.controller;


import com.itxiaolin.minio.entity.SysFile;
import com.itxiaolin.minio.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述： 文件请求处理
 * @author lxq
 * @version 1.00
 */

@RestController
public class FileController {

    private final FileService fileService;
    public FileController(FileService fileService){
        this.fileService=fileService;
    }
    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public SysFile upload(MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            String url = fileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(file.getOriginalFilename());
            sysFile.setUrl(url);
            return sysFile;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
