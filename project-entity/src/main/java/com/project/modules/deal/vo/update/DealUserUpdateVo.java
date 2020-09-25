package com.project.modules.deal.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 客户UpdateVo
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Data
@Accessors(chain = true)
public class DealUserUpdateVo implements Serializable {
    private static final long serialVersionUID = -9043179138905992749L;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    @NotNull(message = "客户ID不能为空")
    private Long dealUserId;

    /**客户名称*/
    @ApiModelProperty(value = "客户名称")
    @NotBlank(message = "客户名称不能为空")
    private String dealUserName;

}
