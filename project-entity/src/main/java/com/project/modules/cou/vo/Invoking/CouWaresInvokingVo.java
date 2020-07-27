package com.project.modules.cou.vo.Invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品ListVo
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Data
@Accessors(chain = true)
public class CouWaresInvokingVo implements Serializable {
    private static final long serialVersionUID = -3607611302637199763L;

    /**商品ID*/
    @ApiModelProperty(value = "商品ID")
    private Long couWaresId;

    /**商品名称*/
    @ApiModelProperty(value = "商品名称")
    private String couWaresName;

    /**厂商指导价*/
    @ApiModelProperty(value = "厂商指导价")
    private BigDecimal couWaresPrice;

}
