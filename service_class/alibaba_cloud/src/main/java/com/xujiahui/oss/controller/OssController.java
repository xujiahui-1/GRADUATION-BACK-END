package com.xujiahui.oss.controller;


import com.xujiahui.commonutils.X;
import com.xujiahui.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*

    处理上传控制器
 */
@Api(tags="头像上传")
@RestController
@RequestMapping("/serviceoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;


//    上传头像方法
    @PostMapping
    @ApiOperation("上传头像方法")
    public X uploadOss(MultipartFile file){
//        让该方法返回头像的阿里云地址
       String url= ossService.uploadFileAvatar(file);
        return X.ok().data("url",url);
    }
}
