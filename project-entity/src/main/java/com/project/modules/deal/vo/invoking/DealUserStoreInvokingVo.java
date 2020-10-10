package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 客户企业表InvokingVo
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Data
@Accessors(chain = true)
public class DealUserStoreInvokingVo implements Serializable {
    private static final long serialVersionUID = -5207355031071790935L;

    /**客户企业ID*/
    @ApiModelProperty(value = "客户企业ID")
    private Long dealStoreId;

    /**客户企业名称*/
    @ApiModelProperty(value = "客户企业名称")
    private String dealStoreName;

    /**保证金金额*/
    @ApiModelProperty(value = "保证金金额")
    private BigDecimal depositPrice;

    /**信用等级*/
    @ApiModelProperty(value = "信用等级")
    private Integer creditGrade;

    /**客户企业门面图*/
    @ApiModelProperty(value = "客户企业门面图")
    private String image;

    /**归属人名称*/
    @ApiModelProperty(value = "归属人名称")
    private String sysUserName;

}
