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
public class DealWaresChangeOnlineStatusInvokingVo implements Serializable {
    private static final long serialVersionUID = -6326617587448756624L;

    /**企业商品ID*/
    @ApiModelProperty(value = "企业商品ID")
    private String dealWaresId;

    /**所属客户企业ID*/
    @ApiModelProperty(value = "所属客户企业ID")
    private Long dealStoreId;
}
