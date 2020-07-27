package com.project.modules.deal.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 企业用户保证金表UpdateVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealUserStoreDepositUpdateVo implements Serializable {
    private static final long serialVersionUID = -89763462540998207L;

    /**保证金ID*/
    @ApiModelProperty(value = "保证金ID")
    private String depositId;

    /**保证金金额*/
    @ApiModelProperty(value = "保证金金额")
    private BigDecimal depositPrice;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;
}
