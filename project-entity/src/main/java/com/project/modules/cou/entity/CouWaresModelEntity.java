package com.project.modules.cou.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 型号Entity
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
@TableName("cou_wares_model")
public class CouWaresModelEntity implements Serializable {
    private static final long serialVersionUID = 5845556492979737006L;

    /**型号ID*/
    @TableId
    @ApiModelProperty(value = "型号ID")
    private Long couModelId;

    /**型号名称*/
    @ApiModelProperty(value = "型号名称")
    private String couModelName;

    /**上级型号ID*/
    @ApiModelProperty(value = "上级型号ID")
    private Long parentId;

    /**型号图片路径*/
    @ApiModelProperty(value = "型号图片路径")
    private String image;

    /**型号状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "型号状态 0.禁用 1.正常")
    private Integer status;

    /**创建者ID*/
    @ApiModelProperty(value = "创建者ID")
    private Long createUserId;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**更新者ID*/
    @ApiModelProperty(value = "更新者ID")
    private Long updateUserId;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
