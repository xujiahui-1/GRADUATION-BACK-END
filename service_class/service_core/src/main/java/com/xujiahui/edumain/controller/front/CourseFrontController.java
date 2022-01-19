package com.xujiahui.edumain.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XChapter;
import com.xujiahui.edumain.entity.XCourse;
import com.xujiahui.edumain.entity.vo.ChapterVo;
import com.xujiahui.edumain.entity.vo.CourseFrontVo;
import com.xujiahui.edumain.entity.vo.CourseWebVo;
import com.xujiahui.edumain.service.XChapterService;
import com.xujiahui.edumain.service.XCourseService;
import com.xujiahui.edumain.service.XTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Api(tags = "授業ページ用API")
@RestController
@RequestMapping("/servicemain/courseFront")
public class CourseFrontController {
    @Autowired
    private XCourseService xCourseService;

    @Autowired
    private XChapterService xChapterService;
//  条件查询带分页的课程列表功能

    @ApiOperation("授業のページ分け表示")
    @PostMapping("/selectCourseList/{page}/{limit}")
    public X selectCourseList(@PathVariable long page, @PathVariable long limit,
                              @RequestBody(required = false) CourseFrontVo courseFrontVo){

        Page<XCourse> pageCourse=new Page<>(page,limit);


        Map<String,Object> map=xCourseService.getCourseList(pageCourse,courseFrontVo);

         return  X.ok().data(map);

    }

//    根据id查询课程的全部信息，多表联合
    @ApiOperation("courseIdによって、この授業すべての情報を検索")
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public X getFrontCourseInfo(@PathVariable String courseId){
        //根绝课程id查询出基本信息
       CourseWebVo courseWebVo= xCourseService.getFrontCourseInfo(courseId);
        //根据id查询出章节小节信息
        List<ChapterVo> chapterVideoList = xChapterService.getChapterVideo(courseId);
        return X.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }
}
