package com.xujiahui.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    /**
     * 上传视频到阿里云
     */
    public String  uploadVideo(MultipartFile file);

    /**
     * 删除视频方法
     */
    void deleteById(String id);

    String getPlayAuthByid(String id);
}
