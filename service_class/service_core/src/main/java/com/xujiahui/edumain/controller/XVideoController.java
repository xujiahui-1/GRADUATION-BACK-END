package com.xujiahui.edumain.controller;



import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XVideo;
import com.xujiahui.edumain.fegin.VodClient;
import com.xujiahui.edumain.service.XVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 授業ビデオ
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Api(tags = "ビデオ情報API")
@RestController
@RequestMapping("/servicemain/x-video")
public class XVideoController {
    @Autowired
   private XVideoService xVideoServiceImpl;
    @Autowired
    private VodClient vodClient;

    /**
     * add video
     */
    @ApiOperation("ビデオ情報追加")
    @PostMapping("/addvideoBychapterId")
    public X addvideoBychapterId(@RequestBody XVideo xVideo){
        xVideoServiceImpl.save(xVideo);
        return X.ok();
    }
    /**
     *delete video
     */
    public X sss(@PathVariable("id") String id){
        System.out.println("操。怎么又出错了");
        return  X.error().message("操。怎么又出错了");
    }
    @HystrixCommand(fallbackMethod = "sss" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")

    })
    @ApiOperation("ビデオ情報削除")
    @DeleteMapping("/deleteVideo/{id}")
    public X deleteVideo(@PathVariable String id){
        //根据小节id获取视频id
        XVideo xVideo = xVideoServiceImpl.getById(id);
        String videoSourceId = xVideo.getVideoSourceId();
        System.out.println("**********"+videoSourceId);

        //删除视频
        X x = vodClient.deleteById(videoSourceId);

        //删除小节
        xVideoServiceImpl.removeById(id);
        return X.ok();
    }
    /**
     *update video
     */
    @ApiOperation("idによって、ビデオ情報選択")
    @GetMapping("/selectById/{id}")
    public  X selectById(@PathVariable String id){
        XVideo xVideo = xVideoServiceImpl.getById(id);
        return X.ok().data("video",xVideo);
    }

    @ApiOperation("ビデオ情報更新")
    @PostMapping("/updateVideo")
    public  X updateVideo(@RequestBody XVideo xVideo){
        xVideoServiceImpl.updateById(xVideo);
        return  X.ok();
    }
    /**
     * getVideoListByChapterId
     */
    @ApiOperation("ChapterIdによって、VideoListを選択")
    @GetMapping("/getVideoListByChapterId/{chapterId}")
    public X getVideoListByChapterId(@PathVariable String chapterId){
     List<XVideo> videoList= xVideoServiceImpl.getVideoList(chapterId);
     return  X.ok().data("videoList",videoList);
    }
    /**
     * videonum updata
     */
    @ApiOperation("ビデオ再生回数を更新")
    @GetMapping("/videonumupdata/{videoId}")
    public X videonumupdata(@PathVariable String videoId){
        xVideoServiceImpl.videonumupdata(videoId);
        return X.ok();
    }
}

