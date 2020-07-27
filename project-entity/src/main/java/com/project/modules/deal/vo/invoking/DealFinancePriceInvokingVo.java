package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 企业客户金融单金额InvokingVo
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Data
@Accessors(chain = true)
public class DealFinancePriceInvokingVo implements Serializable {
    private static final long serialVersionUID = -9082908275609356476L;

    /**金融单ID*/
    @ApiModelProperty(value = "金融单ID")
    private String financeId;

    /**金融单金额*/
    @ApiModelProperty(value = "金融单金额")
    private BigDecimal financePrice;

}
