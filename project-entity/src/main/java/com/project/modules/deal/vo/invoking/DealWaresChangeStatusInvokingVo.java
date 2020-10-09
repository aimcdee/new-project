package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业用户保证金表InvokingVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealWaresChangeStatusInvokingVo implements Serializable {
    private static final long serialVersionUID = 3230007901017023091L;

    /**企业商品ID*/
    @ApiModelProperty(value = "企业商品ID")
    private String dealWaresId;

    /**审核理由*/
    @ApiModelProperty(value = "审核理由")
    private String remark;
}
