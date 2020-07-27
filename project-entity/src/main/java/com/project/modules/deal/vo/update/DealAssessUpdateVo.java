package com.project.modules.deal.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品评估价格UpdateVo
 *
 * @author liangyuding
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
public class DealAssessUpdateVo implements Serializable {
    private static final long serialVersionUID = -8677989310405164101L;

    /**评估ID*/
    @ApiModelProperty(value = "评估ID")
    private Long dealAssessId;

    /**评估价钱*/
    @ApiModelProperty(value = "评估价钱")
    private BigDecimal dealAssessPrice;
}
