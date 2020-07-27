package com.project.modules.deal.vo.info;

import com.project.modules.deal.vo.invoking.DealBillExamineInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户保证金表InfoVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealUserStoreDepositInfoVo implements Serializable {
    private static final long serialVersionUID = -1881539494243079504L;

    /**保证金ID*/
    @ApiModelProperty(value = "保证金ID")
    private String depositId;

    /**保证金单编号*/
    @ApiModelProperty(value = "保证金单编号")
    private String depositNo;

    /**客户企业ID*/
    @ApiModelProperty(value = "客户企业ID")
    private Long dealDtoreId;

    /**企业客户ID*/
    @ApiModelProperty(value = "客户企业表ID")
    private Long dealUserId;

    /**企业客户名称*/
    @ApiModelProperty(value = "客户企业表ID")
    private String dealUserName;

    /**保证金金额*/
    @ApiModelProperty(value = "保证金金额")
    private BigDecimal depositPrice;

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
    private DealBillExamineInvokingVo examine;
}
