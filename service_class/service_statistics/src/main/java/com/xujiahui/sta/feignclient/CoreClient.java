package com.xujiahui.sta.feignclient;

import com.xujiahui.commonutils.X;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-main")
public interface CoreClient {
    /**
     * countvideoNum
     */
    @GetMapping("/servicemain/CourseSta/countvideoNum/{day}")
    public X countvideoNum(@PathVariable("day") String day);

}
