package com.xujiahui.edumain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XVideo;
import com.xujiahui.edumain.mapper.XVideoMapper;
import com.xujiahui.edumain.service.XVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 授業ビデオ 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Service
public class XVideoServiceImpl extends ServiceImpl<XVideoMapper, XVideo> implements XVideoService {

    @Override
    public List<XVideo> getVideoList(String chapterId) {
        QueryWrapper<XVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        List<XVideo> xVideos = baseMapper.selectList(queryWrapper);
        return xVideos;
    }

    @Override
    public void deleteVideoByCourseId(String courseId) {
        QueryWrapper<XVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }

    /**
     * countvideoNum
     * @param day
     * @return
     */
    @Override
    public int countvideoNum(String day) {
        return baseMapper.countvideoNum(day);
    }

    /**
     * videocountnum Updata
     */

    @Override
    public void videonumupdata(String videoId) {
        baseMapper.videonumupdata(videoId);
    }

}
