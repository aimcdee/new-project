package com.project.modules.deal.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 客户SaveVo
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Data
@Accessors(chain = true)
public class DealUserSaveVo implements Serializable {
    private static final long serialVersionUID = -9043179138905992749L;

    /**客户名称*/
    @ApiModelProperty(value = "客户名称")
    @NotBlank(message = "客户名称不能为空")
    private String dealUserName;

    /**客户手机号码*/
    @ApiModelProperty(value = "客户手机号码")
    @NotBlank(message = "客户手机号码不能为空")
    private String phone;

}
