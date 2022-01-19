package com.xujiahui.edumain.fegin;


import com.xujiahui.commonutils.X;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 该接口用于调用vod服务
 */
@FeignClient(name="service-vod")
@Component
public interface VodClient {

    //定义要调用的方法的路径
    @DeleteMapping("/eduvod/video/deleteById/{id}")
    public X deleteById(@PathVariable("id") String id);
}
