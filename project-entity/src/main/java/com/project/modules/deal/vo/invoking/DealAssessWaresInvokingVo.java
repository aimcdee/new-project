package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 个人出售商品InvokingVo
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Data
@Accessors(chain = true)
public class DealAssessWaresInvokingVo implements Serializable {
    private static final long serialVersionUID = -6079713952157188922L;

    /**商品ID*/
    @ApiModelProperty(value = "商品ID")
    private Long couWaresId;

    /**商品名称*/
    @ApiModelProperty(value = "商品名称")
    private String couWaresName;

}
