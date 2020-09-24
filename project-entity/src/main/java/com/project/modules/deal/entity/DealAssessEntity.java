package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品评估价格Entity
 *
 * @author liangyuding
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
@TableName("deal_assess")
public class DealAssessEntity implements Serializable {
    private static final long serialVersionUID = -3220885864111188129L;

    /**评估ID*/
    @TableId
    @ApiModelProperty(value = "评估ID")
    private Long dealAssessId;

    /**品牌ID*/
    @ApiModelProperty(value = "品牌ID")
    private Long couBrandId;

    /**品牌系列ID*/
    @ApiModelProperty(value = "品牌系列ID")
    private Long couSeriesId;

    /**评估商品名称*/
    @ApiModelProperty(value = "评估商品名称")
    private String assessWaresTitle;

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

    /**状态 0.待审核 1.已审核*/
    @ApiModelProperty(value = "状态 0.待审核 1.已审核")
    private Integer status;

    /**评估价钱*/
    @ApiModelProperty(value = "评估价钱")
    private BigDecimal dealAssessPrice;

    /**交易状态 0.未交易 1.交易中 2.已交易*/
    @ApiModelProperty(value = "交易状态 0.未交易 1.交易中 2.已交易")
    private Integer sellStatus;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**审核人ID*/
    @ApiModelProperty(value = "审核人ID")
    private Long examineUserId;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Date examineTime;

}
