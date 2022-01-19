package com.xujiahui.edumain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xujiahui.edumain.entity.XChapter;
import com.xujiahui.edumain.entity.XVideo;
import com.xujiahui.edumain.entity.vo.ChapterVo;
import com.xujiahui.edumain.entity.vo.VideoVo;
import com.xujiahui.edumain.mapper.XChapterMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xujiahui.edumain.service.XChapterService;
import com.xujiahui.edumain.service.XVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 回数 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Service
public class XChapterServiceImpl extends ServiceImpl<XChapterMapper, XChapter> implements XChapterService {
    @Autowired
    XVideoService xVideoService;


    @Override
    public void deleteChapterBycourseId(String courseId) {
    QueryWrapper<XChapter> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq(" course_id",courseId);
    baseMapper.delete(queryWrapper);
    }

    @Override
    public List<XChapter> getAllchapterByCourseId(String courseId) {
        QueryWrapper<XChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<XChapter> xChapters = baseMapper.selectList(queryWrapper);
        return xChapters;
    }

    @Override
    public String saveChapter(XChapter xChapter) {
        int insert = baseMapper.insert(xChapter);
        if(insert>0){
            return xChapter.getId();
        }
        return "失败";
    }

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        //先根据课程id查询出所有的章节
        QueryWrapper<XChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<XChapter> chapterList = baseMapper.selectList(queryWrapper);
        //在根据课程id查询出所有的小节
        QueryWrapper<XVideo> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.eq("course_id",courseId);
        List<XVideo> videoList = xVideoService.list(queryWrapper2);



        //真正返回的ChapterVo数组
        List<ChapterVo> list=new ArrayList<>();

        for (int i = 0; i < chapterList.size(); i++) {
            XChapter  eduChapter=chapterList.get(i);
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            list.add(chapterVo);

            //遍历小节
            List<VideoVo> videoVoList=new ArrayList<>();
            for (int m = 0; m < videoList.size(); m++) {
                XVideo  eduVideo = videoList.get(m);
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            //把封装的小节集合放到章节的list中
            chapterVo.setChildren(videoVoList);
        }
        return list;
    }


}
