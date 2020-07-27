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
public class DealUserInvokingVo implements Serializable {
    private static final long serialVersionUID = -6498787315948229663L;

    /**客户企业ID*/
    @ApiModelProperty(value = "客户企业ID")
    private Long dealStoreId;

    /**企业客户ID*/
    @ApiModelProperty(value = "企业客户ID")
    private Long dealUserId;

    /**企业客户名称*/
    @ApiModelProperty(value = "企业客户名称")
    private String dealUserName;
}
