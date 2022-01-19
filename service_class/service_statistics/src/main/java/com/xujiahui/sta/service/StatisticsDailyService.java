package com.xujiahui.sta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xujiahui.sta.entity.Statistics;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface StatisticsDailyService extends IService<Statistics> {
    void videocount(String day);

    Map<String, Object> getData(String type, String begin, String end);
}
