package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 单据审核表Entity
 *
 * @author liangyuding
 * @date 2020-05-22
 */
@Data
@Accessors(chain = true)
@TableName("deal_bill_examine")
public class DealBillExamineEntity implements Serializable {
    private static final long serialVersionUID = 2065194583879434592L;

    /**审核表ID*/
    @TableId
    @ApiModelProperty(value = "审核表ID")
    private Long examineId;

    /**单据ID*/
    @ApiModelProperty(value = "单据ID")
    private String billId;

    /**单据类型 0.保证金单 1.退费单*/
    @ApiModelProperty(value = "单据类型 0.保证金单 1.退费单")
    private Integer billType;

    /**审核人ID*/
    @ApiModelProperty(value = "审核人ID")
    private Long examineUserId;

    /**审核理由*/
    @ApiModelProperty(value = "审核理由")
    private String examineRemark;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Date examineTime;
}
