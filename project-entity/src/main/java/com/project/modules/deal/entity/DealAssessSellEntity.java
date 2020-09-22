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
 * 评估商品出售表Entity
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
@TableName("deal_assess_sell")
public class DealAssessSellEntity implements Serializable {
    private static final long serialVersionUID = -1033329802712434009L;

    /**评估商品出售ID*/
    @TableId
    @ApiModelProperty(value = "评估商品出售ID")
    private Long dealSellId;

    /**评估商品出售标题*/
    @ApiModelProperty(value = "评估商品出售标题")
    private String dealSellTitle;

    /**所属评估ID*/
    @ApiModelProperty(value = "所属评估ID")
    private Long dealAssessId;

    /**商品ID*/
    @ApiModelProperty(value = "商品ID")
    private Long couWaresId;

    /**联系人名称*/
    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    /**联系人电话*/
    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;

    /**性别*/
    @ApiModelProperty(value = "性别")
    private Integer sex;

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

    /**详细地址*/
    @ApiModelProperty(value = "详细地址")
    private String addr;

    /**跟进人ID*/
    @ApiModelProperty(value = "跟进人ID")
    private Long sysUserId;

    /**最终出售金额*/
    @ApiModelProperty(value = "最终出售金额")
    private BigDecimal sellPrice;

    /**出售情况 0.已取消 1.待处理 2.处理中 3.已完成*/
    @ApiModelProperty(value = "出售情况 0.已取消 1.待处理 2.处理中 3.已完成")
    private Integer status;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

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
