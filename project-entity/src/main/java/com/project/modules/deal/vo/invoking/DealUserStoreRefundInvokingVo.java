package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业用户退费单InvokingVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealUserStoreRefundInvokingVo implements Serializable {
    private static final long serialVersionUID = 1432271456780578050L;

    /**退费单ID*/
    @ApiModelProperty(value = "退费单ID")
    private String refundId;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;
}
