package com.xujiahui.edumain.mapper;

import com.xujiahui.edumain.entity.XVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 授業ビデオ Mapper 接口
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Mapper
public interface XVideoMapper extends BaseMapper<XVideo> {

    int countvideoNum(String day);

    void videonumupdata(String videoId);
}
