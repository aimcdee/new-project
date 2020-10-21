package com.project.modules.deal.vo.info;

import com.project.modules.deal.entity.DealAssessImageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品评估价格InfoVo
 *
 * @author liangyuding
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
public class DealAssessInfoVo implements Serializable {
    private static final long serialVersionUID = 6100621644323965939L;

    /**评估ID*/
    @ApiModelProperty(value = "评估ID")
    private Long dealAssessId;

    /**品牌ID*/
    @ApiModelProperty(value = "品牌ID")
    private Long couBrandId;

    /**品牌名称*/
    @ApiModelProperty(value = "品牌名称")
    private String couBrandName;

    /**系列ID*/
    @ApiModelProperty(value = "系列ID")
    private Long couSeriesId;

    /**系列名称*/
    @ApiModelProperty(value = "系列名称")
    private String couSeriesName;

    /**评估商品名称*/
    @ApiModelProperty(value = "评估商品名称")
    private Long assessWaresTitle;

    /**上牌时间*/
    @ApiModelProperty(value = "上牌时间")
    private Date registerTime;

    /**省级地区ID*/
    @ApiModelProperty(value = "省级地区ID")
    private Long proAreaId;

    /**省级地区名称*/
    @ApiModelProperty(value = "省级地区名称")
    private String proAreaName;

    /**市级地区ID*/
    @ApiModelProperty(value = "市级地区ID")
    private Long cityAreaId;

    /**市级地区名称*/
    @ApiModelProperty(value = "市级地区名称")
    private String cityAreaName;

    /**县/区地区Id*/
    @ApiModelProperty(value = "县/区地区Id")
    private Long countyAreaId;

    /**县/区地区名称*/
    @ApiModelProperty(value = "县/区地区名称")
    private String countyAreaName;

    /**行驶里程*/
    @ApiModelProperty(value = "行驶里程")
    private Long distance;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**行驶证图对象*/
    @ApiModelProperty(value = "行驶证图对象")
    private DealAssessImageEntity driveImage;

    /**商品评估图集合*/
    @ApiModelProperty(value = "商品评估图集合")
    private List<DealAssessImageEntity> waresImages;

    /**状态 0.待审核 1.已审核*/
    @ApiModelProperty(value = "状态 0.待审核 1.已审核")
    private Integer status;

    /**评估价钱*/
    @ApiModelProperty(value = "评估价钱")
    private BigDecimal dealAssessPrice;

    /**交易状态 0.未交易 1.交易中 2.已交易*/
    @ApiModelProperty(value = "交易状态 0.未交易 1.交易中 2.已交易")
    private Integer sellStatus;

    /**审核人*/
    @ApiModelProperty(value = "审核人")
    private Long examineUserId;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Date examineTime;
}
