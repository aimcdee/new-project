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
public class DealUserStoreAuditInvokingVo implements Serializable {
    private static final long serialVersionUID = 8471206785281780516L;

    /**企业认证ID*/
    @ApiModelProperty(value = "企业认证ID")
    private Long storeId;

    /**业务归属人ID*/
    @ApiModelProperty(value = "业务归属人ID")
    private Long belongUserId;
}
