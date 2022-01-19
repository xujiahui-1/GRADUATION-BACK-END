package com.xujiahui.edumain.service;

import com.xujiahui.edumain.entity.XVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 授業ビデオ 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
public interface XVideoService extends IService<XVideo> {

    List<XVideo> getVideoList(String chapterId);

    void deleteVideoByCourseId(String courseId);

    int countvideoNum(String day);

    void videonumupdata(String videoId);
}
