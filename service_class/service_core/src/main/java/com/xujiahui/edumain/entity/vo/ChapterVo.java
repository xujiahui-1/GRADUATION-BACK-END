package com.xujiahui.edumain.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级分类用，章节实体类
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> children=new ArrayList<>();

}
