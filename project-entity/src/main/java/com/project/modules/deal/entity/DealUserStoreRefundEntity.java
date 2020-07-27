package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户退费表Entity
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
@TableName("deal_user_store_refund")
public class DealUserStoreRefundEntity implements Serializable {
    private static final long serialVersionUID = 605728683109531648L;

    /**退费单ID*/
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "退费单ID")
    private String refundId;

    @ApiModelProperty(value = "退费单编号")
    private String refundNo;

    /**客户企业表ID*/
    @ApiModelProperty(value = "客户企业表ID")
    private Long dealStoreId;

    /**退费单金额*/
    @ApiModelProperty(value = "退费单金额")
    private BigDecimal refundPrice;

    /**退费类型 0.扣费 1.提现*/
    @ApiModelProperty(value = "退费类型 0.扣费 1.提现")
    private Integer refundType;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;

    /**状态 0.放弃 1.驳回 2.待提交 3.财务审核中 4.经理审核中 5.通过*/
    @ApiModelProperty(value = "状态 0.放弃 1.驳回 2.待提交 3.财务审核中 4.经理审核中 5.通过")
    private Integer status;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    /**创建人ID*/
    @ApiModelProperty(value = "创建人ID")
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
