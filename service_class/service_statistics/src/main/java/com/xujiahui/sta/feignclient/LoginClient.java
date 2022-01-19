package com.xujiahui.sta.feignclient;

import com.xujiahui.commonutils.X;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface LoginClient {

    /**
     * registerNum
     */
    @GetMapping("/servicelogin/ucenter-member/getRegisterNumByDay/{day}")
    public X getRegisterNumByDay(@PathVariable String day);
}
