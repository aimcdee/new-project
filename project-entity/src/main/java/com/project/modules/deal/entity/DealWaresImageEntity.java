package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业商品图片Entity
 *
 * @author liangyuding
 * @date 2020-06-01
 */
@Data
@Accessors(chain = true)
@TableName("deal_wares_image")
public class DealWaresImageEntity implements Serializable {
    private static final long serialVersionUID = 3235653957942569119L;

    /**图片ID*/
    @TableId
    @ApiModelProperty(value = "图片ID")
    private Long imageId;

    /**图片路径*/
    @ApiModelProperty(value = "图片路径")
    private String image;

    /**图片类型 0.商品图 1.行驶证图*/
    @ApiModelProperty(value = "图片类型 0.商品图 1.行驶证图")
    private Integer imageType;

    /**是否封面图 0.否 1.是*/
    @ApiModelProperty(value = "是否封面图 0.否 1.是")
    private Integer isCover;

    /**所属企业出售商品ID*/
    @ApiModelProperty(value = "所属企业出售商品ID")
    private String dealWaresId;
}
