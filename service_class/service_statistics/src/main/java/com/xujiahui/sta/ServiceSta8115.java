package com.xujiahui.sta;

import io.swagger.annotations.SwaggerDefinition;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableScheduling
@SpringBootApplication
@CrossOrigin
@EnableDiscoveryClient
@EnableFeignClients
@SwaggerDefinition
@MapperScan("com.xujiahui.sta.mapper")
@ComponentScan("com.xujiahui")
public class ServiceSta8115 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSta8115.class);
    }
}
