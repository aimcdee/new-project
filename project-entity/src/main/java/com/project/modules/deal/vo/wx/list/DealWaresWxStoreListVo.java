package com.project.modules.deal.vo.wx.list;

import com.project.modules.deal.entity.DealWaresImageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业端商品ListVo
 *
 * @author liangyuding
 * @date 2020-06-11
 */
@Data
@Accessors(chain = true)
public class DealWaresWxStoreListVo implements Serializable {
    private static final long serialVersionUID = 6056734119618931921L;

    /**出售商品ID*/
    @ApiModelProperty(value = "出售商品ID")
    private String dealWaresId;

    /**出售商品标题*/
    @ApiModelProperty(value = "出售商品标题")
    private String dealWaresTitle;

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

    /**所属商品型号ID*/
    @ApiModelProperty(value = "所属商品型号ID")
    private Long couModelId;

    /**所属商品型号名称*/
    @ApiModelProperty(value = "所属商品型号名称")
    private String couModelName;

    /**年款*/
    @ApiModelProperty(value = "年款")
    private Integer marketYear;

    /**批发价*/
    @ApiModelProperty(value = "批发价")
    private BigDecimal tradePrice;

    /**上牌时间*/
    @ApiModelProperty(value = "上牌时间")
    private Date registerTime;

    /**上线状态 0.审核失败 1.待审核 2.上架 3.下架*/
    @ApiModelProperty(value = "上线状态 0.审核失败 1.待审核 2.上架 3.下架")
    private Integer onlineStatus;

    /**商品封面图*/
    @ApiModelProperty(value = "商品封面图")
    private DealWaresImageEntity coverImage;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    /**企业名称*/
    @ApiModelProperty(value = "企业名称")
    private String dealStoreName;

    /**信用等级*/
    @ApiModelProperty(value = "信用等级")
    private Integer creditGrade;
}
