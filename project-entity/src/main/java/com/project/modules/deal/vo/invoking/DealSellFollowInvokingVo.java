package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 个人出售商品跟进人InvokingVo
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Data
@Accessors(chain = true)
public class DealSellFollowInvokingVo implements Serializable {
    private static final long serialVersionUID = -7170890506964388311L;

    /**个人出售商品ID*/
    @ApiModelProperty(value = "个人出售商品ID")
    private Long dealSellId;

    /**平台跟进人ID*/
    @ApiModelProperty(value = "平台跟进人ID")
    private Long followUserId;
}
