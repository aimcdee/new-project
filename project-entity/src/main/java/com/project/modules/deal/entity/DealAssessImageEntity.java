package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品评估价格图片管理Entity
 *
 * @author liangyuding
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
@TableName("deal_assess_image")
public class DealAssessImageEntity implements Serializable {
    private static final long serialVersionUID = -407804906794863022L;

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

    /**评估ID*/
    @ApiModelProperty(value = "评估ID")
    private Long dealAssessId;

}
