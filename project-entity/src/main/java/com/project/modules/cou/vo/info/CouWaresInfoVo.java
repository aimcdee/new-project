package com.project.modules.cou.vo.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品InfoVo
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Data
@Accessors(chain = true)
public class CouWaresInfoVo implements Serializable {
    private static final long serialVersionUID = -6549005198489439273L;

    /**商品ID*/
    @ApiModelProperty(value = "商品ID")
    private Long couWaresId;

    /**商品名称*/
    @ApiModelProperty(value = "商品名称")
    private String couWaresName;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    private Long couBrandId;

    /**所属品牌名称*/
    @ApiModelProperty(value = "所属品牌名称")
    private String couBrandName;

    /**所属系列ID*/
    @ApiModelProperty(value = "所属系列ID")
    private Long couSeriesId;

    /**所属系列名称*/
    @ApiModelProperty(value = "所属系列名称")
    private String couSeriesName;

    /**所属型号ID*/
    @ApiModelProperty(value = "所属型号ID")
    private Long couModelId;

    /**所属型号名称*/
    @ApiModelProperty(value = "所属型号名称")
    private String couModelName;

    /**厂商指导价*/
    @ApiModelProperty(value = "厂商指导价")
    private BigDecimal couWaresPrice;

    /**年款*/
    @ApiModelProperty(value = "年款")
    private String marketYear;

    /**上市时间*/
    @ApiModelProperty(value = "上市时间")
    private Date marketTime;

    /**排量*/
    @ApiModelProperty(value = "排量")
    private String disMent;

    /**变速箱*/
    @ApiModelProperty(value = "变速箱")
    private String varBox;

    /**驱动方式*/
    @ApiModelProperty(value = "驱动方式")
    private String drive;

    /**油耗量*/
    @ApiModelProperty(value = "油耗量")
    private String consume;

    /**状态 0.禁用 1正常*/
    @ApiModelProperty(value = "状态 0.禁用 1正常")
    private Integer status;
}
