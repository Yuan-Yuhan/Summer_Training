package com.example.demo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 赵思阳
 * @Date 2021/7/15 11:14
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
