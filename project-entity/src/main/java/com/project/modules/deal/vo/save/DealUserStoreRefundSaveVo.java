package com.project.modules.deal.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 企业用户退费表SaveVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealUserStoreRefundSaveVo implements Serializable {
    private static final long serialVersionUID = 1482582299598791623L;

    /**客户企业表ID*/
    @ApiModelProperty(value = "客户企业表ID")
    private Long dealStoreId;

    /**退费单金额*/
    @ApiModelProperty(value = "退费单金额")
    private BigDecimal refundPrice;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;
}
