package com.xujiahui.vod.controller;


import com.xujiahui.commonutils.X;
import com.xujiahui.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/servicevod/video")
public class VodController {
    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     */

    @PostMapping("/uploadAlyVideo")
    public X  uploadAlyVideo(MultipartFile file){
        //返回上传视频的云id
        String s = vodService.uploadVideo(file);
        return X.ok().data("videoId",s);
    }
    /**
     * 删除阿里云中视频
     */
    @DeleteMapping("/deleteById/{id}")
    public  X deleteById(@PathVariable String id){
        try {// 睡眠10s
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        vodService.deleteById(id);
        return X.ok();
    }
    /**
     * 删除多个视频方法TODO
     */

    /**
     * 获取视频播放凭证接口(根据id)
     */
    @GetMapping("getPlayAuth/{id}")
    public X getPlayAuth(@PathVariable String id ){
        String playAuth = vodService.getPlayAuthByid(id);
        return  X.ok().data("playAuth",playAuth);
    }
}
