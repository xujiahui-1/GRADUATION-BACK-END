package com.xujiahui.edumain.entity;

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
 * 先生
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="XTeacher对象", description="先生")
public class XTeacher implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "先生ID")
    private String id;

    @ApiModelProperty(value = "先生名前")
    private String name;

    @ApiModelProperty(value = "先生履歴")
    private String intro;

    @ApiModelProperty(value = "讲师头像")
    private String headPortrait;

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
