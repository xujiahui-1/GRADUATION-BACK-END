package com.xujiahui.sta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xujiahui.commonutils.X;

import com.xujiahui.sta.entity.Statistics;
import com.xujiahui.sta.feignclient.LoginClient;
import com.xujiahui.sta.mapper.StatisticsDailyMapper;
import com.xujiahui.sta.service.StatisticsDailyService;
import com.xujiahui.sta.feignclient.CoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 授業 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, Statistics> implements StatisticsDailyService {

    @Autowired
    private CoreClient coreClient;
    @Autowired
    private LoginClient loginClient;
    @Override
    public void videocount(String day) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("date_calculated",day);
        List list = baseMapper.selectList(queryWrapper);
        if(list!=null){
            baseMapper.delete(queryWrapper);
        }
        X x = coreClient.countvideoNum(day);
        Integer countvideoNum = (Integer )x.getData().get("countvideoNum");
        X registerNumByDay = loginClient.getRegisterNumByDay(day);
        Integer registerNum = (Integer )registerNumByDay.getData().get("registerNum");
        Statistics statisticsDaily=new Statistics();
        statisticsDaily.setVideoViewNum(countvideoNum);
        statisticsDaily.setRegisterNum(registerNum);
        statisticsDaily.setDateCalculated(day);
        baseMapper.insert(statisticsDaily);

    }

    @Override
    public Map<String, Object> getData(String type, String begin, String end) {
        QueryWrapper<Statistics> wrapper=new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<Statistics> statisticsDailies = baseMapper.selectList(wrapper);
        List<String> data = new ArrayList();
        List<Integer> num = new ArrayList();
        for (int i = 0; i < statisticsDailies.size(); i++) {
            Statistics statisticsDaily = statisticsDailies.get(i);

            data.add(statisticsDaily.getDateCalculated());
            if(type.equals("register_num")){
                num.add(statisticsDaily.getRegisterNum());
            }else if(type.equals("video_view_num")){
                num.add(statisticsDaily.getVideoViewNum());
            }

        }
        Map<String,Object> map =new HashMap<>();
        map.put("data",data);
        map.put("num",num);
        return map;
    }
}
