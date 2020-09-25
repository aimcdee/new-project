package com.project.modules.deal.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 评估商品出售表UpdateVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealAssessSellUpdateVo implements Serializable {
    private static final long serialVersionUID = -3866657145519619354L;

    /**评估商品出售ID*/
    @ApiModelProperty(value = "评估商品出售ID")
    @NotNull(message = "请选择评估商品")
    private Long dealSellId;

    /**评估商品出售标题*/
    @ApiModelProperty(value = "评估商品出售标题")
    @NotBlank(message = "请输入评估商品出售标题")
    private String dealSellTitle;

    /**联系人名称*/
    @ApiModelProperty(value = "联系人名称")
    @NotBlank(message = "请输入联系人名称")
    private String contactName;

    /**联系人电话*/
    @ApiModelProperty(value = "联系人电话")
    @NotBlank(message = "请输入联系人电话")
    private String contactPhone;

    /**联系人电话*/
    @ApiModelProperty(value = "性别")
    @NotNull(message = "请选择性别")
    private Integer sex;

    /**省级地区ID*/
    @ApiModelProperty(value = "省级地区ID")
    @NotNull(message = "请选择区域省份")
    private Long proAreaId;

    /**市级地区ID*/
    @ApiModelProperty(value = "市级地区ID")
    private Long cityAreaId;

    /**县/区地区Id*/
    @ApiModelProperty(value = "县/区地区Id")
    private Long countyAreaId;

    /**详细地址*/
    @ApiModelProperty(value = "详细地址")
    @NotBlank(message = "请输入详细地址")
    private String addr;
}
