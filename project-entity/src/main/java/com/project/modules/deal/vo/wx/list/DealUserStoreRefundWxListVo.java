package com.project.modules.deal.vo.wx.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户退费表ListVo
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Data
@Accessors(chain = true)
public class DealUserStoreRefundWxListVo implements Serializable {
    private static final long serialVersionUID = -7043698967175822941L;

    /**退费单ID*/
    @ApiModelProperty(value = "退费单ID")
    private String refundId;

    /**退费单编号*/
    @ApiModelProperty(value = "退费单编号")
    private String refundNo;

    /**退费单金额*/
    @ApiModelProperty(value = "退费单金额")
    private BigDecimal refundPrice;

    /**状态 0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过*/
    @ApiModelProperty(value = "状态 0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过")
    private Integer status;

    /**退费类型 0.扣费 1.提现*/
    @ApiModelProperty(value = "退费类型 0.扣费 1.提现")
    private Integer refundType;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;
}
