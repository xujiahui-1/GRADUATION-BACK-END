package com.xujiahui.edumain.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 回数
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XChapter对象", description="回数")
public class XChapter implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "授業ID")
    private String courseId;

    @ApiModelProperty(value = "今回授業の内容")
    private String title;

    @ApiModelProperty(value = "何回目")
    private Integer sort;

    @ApiModelProperty(value = "登入時間")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新時間")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
