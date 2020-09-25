package com.project.modules.deal.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 咨询分期客户管理表SaveVo
 *
 * @author liangyuding
 * @date 2020-06-06
 */
@Data
@Accessors(chain = true)
public class DealWaresInstallmentSaveVo implements Serializable {
    private static final long serialVersionUID = -7087612452406575967L;

    /**所属客户ID*/
    @ApiModelProperty(value = "所属客户ID")
    @NotBlank(message = "所属客户ID不能为空")
    private Long dealUserId;

    /**咨询商品ID*/
    @ApiModelProperty(value = "咨询商品ID")
    @NotBlank(message = "请选择需要咨询的商品")
    private String dealWaresId;

    /**联系人名称*/
    @ApiModelProperty(value = "联系人名称")
    @NotBlank(message = "请输入联系人名称")
    private String contactName;

    /**联系电话*/
    @ApiModelProperty(value = "联系电话")
    @NotBlank(message = "请输入联系电话")
    private String contactPhone;

    /**性别 0.先生 1.小姐*/
    @ApiModelProperty(value = "性别 0.先生 1.小姐")
    @NotBlank(message = "请选择性别")
    private Integer sex;

}
