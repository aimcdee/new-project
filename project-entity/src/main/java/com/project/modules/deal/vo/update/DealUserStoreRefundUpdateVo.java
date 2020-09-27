package com.project.modules.deal.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 企业用户退费表UpdateVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealUserStoreRefundUpdateVo implements Serializable {
    private static final long serialVersionUID = 8832917707632526980L;

    /**退费单ID*/
    @ApiModelProperty(value = "退费单ID")
    @NotBlank(message = "请选择申请的退费单")
    private String refundId;

    /**退费单金额*/
    @ApiModelProperty(value = "退费单金额")
    @NotNull(message = "请输入退费金额")
    private BigDecimal refundPrice;

    /**备注*/
    @ApiModelProperty(value = "备注")
    @NotBlank(message = "请输入退费单备注")
    private String remark;


}
