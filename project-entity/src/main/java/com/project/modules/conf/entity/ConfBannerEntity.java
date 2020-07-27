package com.project.modules.conf.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图Entity
 *
 * @author liangyuding
 * @date 2020-04-15
 */
@Data
@Accessors(chain = true)
@TableName("conf_banner")
public class ConfBannerEntity implements Serializable {
    private static final long serialVersionUID = -7465572403488458621L;

    /**轮播图ID*/
    @TableId
    @ApiModelProperty(value = "轮播图ID")
    private Long bannerId;

    /**轮播图标题*/
    @ApiModelProperty(value = "轮播图标题")
    private String bannerName;

    /**状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "状态 0.禁用 1.正常")
    private Integer status;

    /**排序*/
    @ApiModelProperty(value = "排序")
    private Integer sort;

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
