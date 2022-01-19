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
 * 授業ビデオ
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XVideo对象", description="授業ビデオ")
public class XVideo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ビデオID")
    private String id;

    @ApiModelProperty(value = "授業ID")
    private String courseId;

    @ApiModelProperty(value = "回数ID")
    private String chapterId;

    @ApiModelProperty(value = "ノード名")
    private String title;

    @ApiModelProperty(value = "Cloudビデオ資源")
    private String videoSourceId;

    @ApiModelProperty(value = "元filename")
    private String videoOriginalName;


    @ApiModelProperty(value = "播放次数")
    private Long playCount;



    @ApiModelProperty(value = "楽観的ロック")
    private Long version;

    @ApiModelProperty(value = "登入時間")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新時間")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
