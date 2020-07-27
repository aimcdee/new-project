package com.project.modules.conf.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 轮播企业商品管理Entity
 *
 * @author liangyuding
 * @date 2020-06-08
 */
@Data
@Accessors(chain = true)
@TableName("conf_banner_wares")
public class ConfBannerWaresEntity implements Serializable {
    private static final long serialVersionUID = 1551585006241783062L;

    /**主键ID*/
    @TableId
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**所属轮播图ID*/
    @ApiModelProperty(value = "所属轮播图ID")
    private Long bannerId;

    /**企业商品ID*/
    @ApiModelProperty(value = "企业商品ID")
    private String dealWaresId;

    /**图片路径*/
    @ApiModelProperty(value = "图片路径")
    private String image;
}
