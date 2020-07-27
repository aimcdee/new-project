package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 单据审核表InvokingVo
 *
 * @author liangyuding
 * @date 2020-05-22
 */
@Data
@Accessors(chain = true)
public class DealBillExamineInvokingVo implements Serializable {
    private static final long serialVersionUID = 1599536423990131956L;

    /**审核表ID*/
    @ApiModelProperty(value = "审核表ID")
    private Long examineId;

    /**审核人ID*/
    @ApiModelProperty(value = "审核人ID")
    private Long examineUserId;

    /**审核人名称*/
    @ApiModelProperty(value = "审核人名称")
    private String examineUserName;

    /**审核理由*/
    @ApiModelProperty(value = "审核理由")
    private String examineRemark;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Date examineTime;
}
