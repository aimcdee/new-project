package com.project.modules.cou.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系列Entity
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
@TableName("cou_wares_series")
public class CouWaresSeriesEntity implements Serializable {
    private static final long serialVersionUID = -3489719759343513357L;

    /**系列ID*/
    @TableId
    @ApiModelProperty(value = "系列ID")
    private Long couSeriesId;

    /**系列名称*/
    @ApiModelProperty(value = "系列名称")
    private String couSeriesName;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    private Long couBrandId;

    /**系列状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "系列状态 0.禁用 1.正常")
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
