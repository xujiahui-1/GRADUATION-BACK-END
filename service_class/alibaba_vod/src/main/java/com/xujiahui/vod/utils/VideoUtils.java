package com.xujiahui.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VideoUtils implements InitializingBean {
    //读取配置文件的内容

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    //定义几个静态量

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {

        ACCESS_KEY_ID=keyId;
        ACCESS_KEY_SECRET=keySecret;
    }
}
