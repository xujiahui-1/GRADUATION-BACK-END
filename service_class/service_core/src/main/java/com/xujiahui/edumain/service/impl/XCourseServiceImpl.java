package com.xujiahui.edumain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujiahui.commonutils.XException;
import com.xujiahui.edumain.entity.XChapter;
import com.xujiahui.edumain.entity.XCourse;
import com.xujiahui.edumain.entity.XVideo;
import com.xujiahui.edumain.entity.vo.CourseFrontVo;
import com.xujiahui.edumain.entity.vo.CoursePublishVo;
import com.xujiahui.edumain.entity.vo.CourseWebVo;
import com.xujiahui.edumain.mapper.XCourseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xujiahui.edumain.service.XChapterService;
import com.xujiahui.edumain.service.XCourseService;
import com.xujiahui.edumain.service.XVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class XCourseServiceImpl extends ServiceImpl<XCourseMapper, XCourse> implements XCourseService {

    @Autowired
    XChapterServiceImpl xChapterService;
    @Autowired
    XCourseMapper xCourseMapper;
    @Autowired
    XVideoService xVideoService;
    /**
     * Add Course
     */
    @Override
    public String saveCourseInfo(XCourse xCourse) {
        int insert = baseMapper.insert(xCourse);
        if(insert==0){
            throw new XException(20001,"失败");
        }
        String cid=xCourse.getId();
        return cid;
    }
    /**
     * delete Course
     */
    public void deleteById(String courseId) {
//        delete course
        baseMapper.deleteById(courseId);
//        delete chapter
        xChapterService.deleteChapterBycourseId(courseId);
//        delete video
        xVideoService.deleteVideoByCourseId(courseId);
        baseMapper.deleteById(courseId);
    }

    /**
     * course publish info
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public Map<String, Object> getCourseList(Page<XCourse> pageCourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<XCourse> queryWrapper=new QueryWrapper<>();
        if(StringUtils.hasLength(courseFrontVo.getGmtCreateSort())){
            queryWrapper.orderByDesc("gmt_create");
        }
        Page<XCourse> eduCoursePage = baseMapper.selectPage(pageCourse,queryWrapper );

        List<XCourse> records = pageCourse.getRecords();//每页数据的list
        long current = pageCourse.getCurrent(); //当前页
        long total = pageCourse.getTotal();//总记录树
        long size = pageCourse.getSize();//每页记录
        long pages = pageCourse.getPages();//总页数

        boolean p = pageCourse.hasPrevious(); //是否有上页
        boolean b = pageCourse.hasNext(); //是否有下一页
        Map<String,Object> map=new HashMap<>();




        map.put("items",records);
        map.put("current",current);
        map.put("total",total);
        map.put("size",size);
        map.put("pages",pages);
        map.put("p",p);
        map.put("b",b);

        return map;
    }

    @Override
    public CourseWebVo getFrontCourseInfo(String courseId) {
        return baseMapper.getFrontCourseInfo(courseId);
    }

    /**
     * この授業の 阅覧量 更新　byId
     */
    @Override
    public void courseViewUpdate(String courseId) {
             xCourseMapper.courseViewUpdate(courseId);
    }

    /**
     * get Course
     */
    public XCourse getCourseById(String courseId) {
        XCourse xCourse = baseMapper.selectById(courseId);
        return xCourse;
    }
    /**
     * update Course
     */
    public void updateCourseInfo(XCourse xCourse) {
        XCourse has = baseMapper.selectById(xCourse.getId());
        if(null==has){
            throw new XException(20001,"NO HAVE THE COURSE");
        }
        baseMapper.updateById(xCourse);
    }


}
