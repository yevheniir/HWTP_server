package com.yevheniir.hwtp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir = "src/main/resources/images";
    private String uploadDir2 = "src/main/resources/files";

    public String getUploadDir() {
        return uploadDir;
    }
    public String getUploadDir2() {
        return uploadDir2;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}