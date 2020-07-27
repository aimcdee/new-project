package com.project.modules.cou.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品品牌Entity
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
@TableName("cou_wares_brand")
public class CouWaresBrandEntity implements Serializable {
    private static final long serialVersionUID = -3220885864111188129L;

    /**品牌ID*/
    @TableId
    @ApiModelProperty(value = "品牌ID")
    private Long couBrandId;

    /**品牌名称*/
    @ApiModelProperty(value = "品牌名称")
    private String couBrandName;

    /**品牌logo路径*/
    @ApiModelProperty(value = "品牌logo路径")
    private String image;

    /**品牌首字母*/
    @ApiModelProperty(value = "品牌首字母")
    private String firstLetter;

    /**商品品牌状态状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "商品品牌状态 0.禁用 1.正常")
    private Integer status;

    /**商品品牌排序*/
    @ApiModelProperty(value = "商品品牌排序")
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
