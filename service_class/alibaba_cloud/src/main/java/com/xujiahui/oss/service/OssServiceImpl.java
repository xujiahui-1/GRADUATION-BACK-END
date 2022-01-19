package com.xujiahui.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xujiahui.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements  OssService{
    /**
     * 处理具体的java操作阿里云oss
     * @param multipartFile
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile multipartFile) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret =ConstantPropertiesUtils.ACCESS_KEY_SECRET ;
        String bucketName=ConstantPropertiesUtils.BUCKET_NAME;



        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件的输出流
            InputStream   inputStream = multipartFile.getInputStream();
            //获取文件名称
            String fileName = multipartFile.getOriginalFilename();
            //1.不希望文件重名，所以给每个文件添加一个唯一的值
                //replaceAll("-","");把里面得横全部替
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;
            //2.希望文件按照日期分类，
                //  获取当前日期  ,使用joda-time 工具类
            String data = new DateTime().toString("yyyy/MM/dd");
                //拼接  2021/05/28/sadsa.jpg
            fileName=data+"/"+fileName;
            //调用oss的上传方法实现上传  三个参数，1.水桶名  2.文件路径。 3.流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            //操作结束后，我想返回他的路径
            //需要自己手动拼接路径
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
