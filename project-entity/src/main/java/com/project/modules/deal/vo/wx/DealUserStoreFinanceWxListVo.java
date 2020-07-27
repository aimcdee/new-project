package com.project.modules.deal.vo.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户金融单表ListVo
 *
 * @author liangyuding
 * @date 2020-06-11
 */
@Data
@Accessors(chain = true)
public class DealUserStoreFinanceWxListVo implements Serializable {
    private static final long serialVersionUID = -5976527150732196040L;

    /**金融单ID*/
    @ApiModelProperty(value = "金融单ID")
    private String financeId;

    /**金融单编号*/
    @ApiModelProperty(value = "金融单编号")
    private String financeNo;

    /**跟进人ID*/
    @ApiModelProperty(value = "跟进人ID")
    private Long sysUserId;

    /**跟进人名称*/
    @ApiModelProperty(value = "跟进人名称")
    private String sysUserName;

    /**金融单交易金额*/
    @ApiModelProperty(value = "金融单交易金额")
    private BigDecimal financePrice;

    /**状态 0.作废 1.处理中 4.已完成*/
    @ApiModelProperty(value = "状态 0.作废 1.处理中 4.已完成")
    private Integer status;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;
}
