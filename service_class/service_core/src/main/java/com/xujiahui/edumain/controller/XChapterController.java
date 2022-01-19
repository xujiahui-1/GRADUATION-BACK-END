package com.xujiahui.edumain.controller;


import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XChapter;
import com.xujiahui.edumain.service.XChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 回数 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Api(tags = "毎回授業情報API")
@RestController
@RequestMapping("/servicemain/x-chapter")
public class XChapterController {
    @Autowired
    XChapterService xChapterServiceImpl;
    /**
     * getAll chapter by coourseId
     */
    @ApiOperation("courseIdによって、すべてのchapterを選択")
    @GetMapping("/getAllchapter/{courseId}")
    public X getAllchapter(@PathVariable String courseId){
       List<XChapter> list=xChapterServiceImpl.getAllchapterByCourseId(courseId);
       return  X.ok().data("chapterList",list);
    }
    /**
     * add chapter
     */
    @ApiOperation("chapter追加")
    @PostMapping("/addChapter")
    public X addChapter(@RequestBody XChapter xChapter){
        xChapterServiceImpl.saveChapter(xChapter);

        return X.ok();
    }
    /**
     * get chapter by chapterId
     */
    @ApiOperation("chapterIdによって、chapterを選択")
    @GetMapping("/getChapterById/{chapterId}")
    public X getChapterById(@PathVariable String chapterId){
        XChapter xChapter = xChapterServiceImpl.getById(chapterId);
        return X.ok().data("chapter",xChapter);
    }

    /**
     * update chapter
     */
    @ApiOperation("chapter更新")
    @PostMapping("/updataChapter")
    public X updataChapter(@RequestBody XChapter xChapter){
        xChapterServiceImpl.updateById(xChapter);
        return X.ok();
    }
    /**
     * delete chapter ById
     */
    @ApiOperation("chapter削除")
    @DeleteMapping("/deleteChapterById/{chapterId}")
    public X delete (@PathVariable String chapterId){
        boolean b = xChapterServiceImpl.removeById(chapterId);
        if(b){
            return X.ok();
        }else {
            return X.error();
        }
    }
}

