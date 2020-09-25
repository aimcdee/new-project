package com.project.modules.deal.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "请选择申请的保证值单")
    private String depositId;

    /**保证金金额*/
    @ApiModelProperty(value = "保证金金额")
    @NotNull(message = "请输入保证金金融")
    private BigDecimal depositPrice;

    /**备注*/
    @ApiModelProperty(value = "备注")
    @NotBlank(message = "请输入备注")
    private String remark;
}
