package com.project.modules.deal.vo.info;

import com.project.modules.deal.vo.invoking.DealBillExamineInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户退费表InfoVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealUserStoreRefundInfoVo implements Serializable {
    private static final long serialVersionUID = 7952659265931140219L;

    /**退费单ID*/
    @ApiModelProperty(value = "退费单ID")
    private String refundId;

    /**客户企业表ID*/
    @ApiModelProperty(value = "客户企业表ID")
    private Long dealStoreId;

    /**企业客户ID*/
    @ApiModelProperty(value = "客户企业表ID")
    private Long dealUserId;

    /**企业客户名称*/
    @ApiModelProperty(value = "客户企业表ID")
    private String dealUserName;

    /**退费单金额*/
    @ApiModelProperty(value = "退费单金额")
    private BigDecimal refundPrice;

    /**退费类型 0.扣费 1.提现*/
    @ApiModelProperty(value = "退费类型 0.扣费 1.提现")
    private Integer refundType;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;

    /**状态 0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过*/
    @ApiModelProperty(value = "状态 0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过")
    private Integer status;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    /**单据审核对象*/
    @ApiModelProperty(value = "单据审核对象")
    private DealBillExamineInvokingVo dealBillExamineInvokingVo;
}
