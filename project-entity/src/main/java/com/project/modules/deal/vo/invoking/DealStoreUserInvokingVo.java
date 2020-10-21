package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业客户表InvokingVo
 *
 * @author liangyuding
 * @date 2020-05-27
 */
@Data
@Accessors(chain = true)
public class DealStoreUserInvokingVo implements Serializable {
    private static final long serialVersionUID = -6890056107263517846L;

    /**企业客户ID*/
    @ApiModelProperty(value = "企业客户ID")
    private Long dealUserId;

    /**企业客户名称*/
    @ApiModelProperty(value = "企业客户名称")
    private String dealUserName;

    /**企业客户手机号码*/
    @ApiModelProperty(value = "企业客户手机号码")
    private String dealUserPhone;
}
