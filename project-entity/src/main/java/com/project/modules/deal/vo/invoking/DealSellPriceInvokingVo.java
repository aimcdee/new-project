package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 个人出售商品回收金融InvokingVo
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Data
@Accessors(chain = true)
public class DealSellPriceInvokingVo implements Serializable {
    private static final long serialVersionUID = -8955928515180361565L;

    /**个人出售商品ID*/
    @ApiModelProperty(value = "个人出售商品ID")
    private Long dealSellId;

    /**回收金额*/
    @ApiModelProperty(value = "回收金额")
    private BigDecimal sellPrice;
}
