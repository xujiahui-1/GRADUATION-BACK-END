package com.xujiahui.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//不去加载数据库配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.xujiahui")
@EnableDiscoveryClient
@EnableSwagger2
public class ServiceVod8114 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVod8114.class,args);
    }
}
