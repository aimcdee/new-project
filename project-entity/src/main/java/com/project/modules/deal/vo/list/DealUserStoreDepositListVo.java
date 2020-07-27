package com.project.modules.deal.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户保证金表ListVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealUserStoreDepositListVo implements Serializable {
    private static final long serialVersionUID = -6971393138167242608L;

    /**保证金ID*/
    @ApiModelProperty(value = "保证金ID")
    private String depositId;

    /**保证金单编号*/
    @ApiModelProperty(value = "保证金单编号")
    private String depositNo;

    /**客户企业表ID*/
    @ApiModelProperty(value = "客户企业表ID")
    private Long dealDtoreId;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**客户名称*/
    @ApiModelProperty(value = "客户名称")
    private String dealUserName;

    /**保证金金额*/
    @ApiModelProperty(value = "保证金金额")
    private BigDecimal depositPrice;

    /**状态 0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过*/
    @ApiModelProperty(value = "状态 0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过")
    private Integer status;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    /**审核表ID*/
    @ApiModelProperty(value = "审核表ID")
    private Long examineId;

    /**审核人ID*/
    @ApiModelProperty(value = "审核人ID")
    private Long examineUserId;

    /**审核人名称*/
    @ApiModelProperty(value = "审核人名称")
    private String examineUserName;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Date examineTime;
}
