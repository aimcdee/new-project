package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 咨询分期跟进人InvokingVo
 *
 * @author liangyuding
 * @date 2020-06-08
 */
@Data
@Accessors(chain = true)
public class DealInstallmentFollowInvokingVo implements Serializable {
    private static final long serialVersionUID = 1844241736567737116L;

    /**咨询分期ID*/
    @ApiModelProperty(value = "咨询分期ID")
    private Long installmentId;

    /**跟进备注*/
    @ApiModelProperty(value = "跟进备注")
    private String followRemark;
}
