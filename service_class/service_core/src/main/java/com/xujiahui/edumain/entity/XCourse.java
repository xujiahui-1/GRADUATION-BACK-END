package com.xujiahui.edumain.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 授業
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XCourse对象", description="授業")
public class XCourse implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "授業ID")
    private String id;

    @ApiModelProperty(value = "先生ID")
    private String teacherId;

    @ApiModelProperty(value = "授業title")
    private String title;


    @ApiModelProperty(value = "授業封面图片路径")
    private String cover;

    @ApiModelProperty(value = "浏览数量")
        private Long viewCount;

    @ApiModelProperty(value = "楽観的ロック")
    private Long version;



    @ApiModelProperty(value = "論理削除 1（true）削除済， 0（false）未削除")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "登入時間")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新時間")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
