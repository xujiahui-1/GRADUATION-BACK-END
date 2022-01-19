package com.xujiahui.sta.controller;

import com.xujiahui.commonutils.X;
import com.xujiahui.sta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/servicesta/sta")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 一日中のビデオ再生回数
     */
    @PostMapping("/videocount/{day}")
    public X videocount(@PathVariable String day){
        statisticsDailyService.videocount(day);
        return  X.ok();
    }
    /**
     * 統計データ
     */
    @GetMapping("/showData/{type}/{begin}/{end}")
    public X showData(@PathVariable String type,
                      @PathVariable String begin,
                      @PathVariable String end){
      Map<String,Object> map= statisticsDailyService.getData(type,begin,end);
      return X.ok().data(map);
    }
}
