package com.xujiahui.vod.service;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.xujiahui.commonutils.XException;
import com.xujiahui.vod.utils.InitObject;
import com.xujiahui.vod.utils.VideoUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频到阿里云
     */
    @Override
    public String uploadVideo(MultipartFile file) {
        //文件原始名
        String fileName=file.getOriginalFilename();

        //文件名
        String title=fileName.substring(0,fileName.lastIndexOf("."));
        String accessKeyId = VideoUtils.ACCESS_KEY_ID;
        String accessKeySecret =VideoUtils.ACCESS_KEY_SECRET ;
        //流
        InputStream inputStream = null;


        try {
            inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(accessKeyId,
                    accessKeySecret,
                    title, fileName, inputStream);
            System.out.print("RequestId=" + request.getTitle() + "\n");
            /* 点播服务接入点 */
//            request.setApiRegionId("a9p-northeast-1");
            request.setApiRegionId("ap-northeast-1");

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId=null;
            if (response.isSuccess()) {
                videoId=response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            return  videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }

    }


    /**
     * 删除视频方法
     * @param id
     */
    @Override
    public void deleteById(String id) {
        String accessKeyId = VideoUtils.ACCESS_KEY_ID;
        String accessKeySecret =VideoUtils.ACCESS_KEY_SECRET ;
        try {
            //初始化对象
            DefaultAcsClient client = InitObject.initVodClient(accessKeyId,
                    accessKeySecret);
            //创建request设置视频id
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            DeleteVideoResponse response = new DeleteVideoResponse();
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPlayAuthByid(String id) {

        try{
//            创建初始话对象
            DefaultAcsClient client = InitObject.initVodClient("LTAI5tMDrXQVk5hw8w568f9i", "kstUxN3PWburcpV66LBGzO7P0WaDmL");
//            创建获取凭证得request和resp
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
//          设置视频id
            request.setVideoId(id);
//           调用方法得到凭证
            GetVideoPlayAuthResponse response =client.getAcsResponse(request);
//
            String playAuth = response.getPlayAuth();
            System.out.println("***"+playAuth);
            return playAuth;
        }catch (Exception e){
           throw new XException(20001,"生成凭证失败");
        }
    }

}
