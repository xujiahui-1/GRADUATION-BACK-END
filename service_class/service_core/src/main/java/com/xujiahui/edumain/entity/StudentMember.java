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
 * 学生表
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="StudentMember对象", description="学生表")
public class StudentMember implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学生id")
    private String id;

    @ApiModelProperty(value = "電話")
    private String mobile;

    @ApiModelProperty(value = "ｐｗｓ")
    private String password;

    @ApiModelProperty(value = "ニックネーム")
    private String nickname;

    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;

    @ApiModelProperty(value = "age")
    private Integer age;

    @ApiModelProperty(value = "avatar")
    private String avatar;

    @ApiModelProperty(value = "sign")
    private String sign;

    @ApiModelProperty(value = "禁用されているかどうか 1（true）禁用されている，  0（false）未禁用")
    private Boolean isDisabled;

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
