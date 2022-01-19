package com.xujiahui.edumain.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQuery {

    @ApiModelProperty(value = "teacherName")
    private String name;

    @ApiModelProperty(value = "beginTime" ,example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用String类型，前端传过来的数据无需进行转换

    @ApiModelProperty(value = "endTime" ,example = "2019-01-01 10:10:10")
    private String end;
}
