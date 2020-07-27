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
public class DealUserStoreDepositInvokingVo implements Serializable {
    private static final long serialVersionUID = 7991027224557742260L;

    /**保证金ID*/
    @ApiModelProperty(value = "保证金ID")
    private String depositId;

    /**审核理由*/
    @ApiModelProperty(value = "审核理由")
    private String remark;
}
