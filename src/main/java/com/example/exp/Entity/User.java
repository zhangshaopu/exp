package com.example.exp.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: entity
 * @Author: zsp
 * @Date: 2023/6/23 0:37
 */

@TableName("user")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名字",required = true)
    @TableField(value = "name")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "成绩",required = true)
    @TableField(value = "grade")
    @NotNull
    private Integer grade;

    @ApiModelProperty(value = "年龄",required = true)
    @TableField(value = "age")
    @NotBlank
    private String age;
}
