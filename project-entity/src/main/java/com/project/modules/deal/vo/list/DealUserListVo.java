package com.project.modules.deal.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 客户ListVo
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Data
@Accessors(chain = true)
public class DealUserListVo implements Serializable {
    private static final long serialVersionUID = 460906880306887096L;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**客户名称*/
    @ApiModelProperty(value = "客户名称")
    private String dealUserName;

    /**客户手机号码*/
    @ApiModelProperty(value = "客户手机号码")
    private String phone;

    /**客户状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "客户状态 0.禁用 1.正常")
    private Integer status;

    /**客户类型 0.个人 1.商家*/
    @ApiModelProperty(value = "客户类型 0.个人 1.商家")
    private Integer type;

    /**个人积分*/
    @ApiModelProperty(value = "个人积分")
    private Long integral;

    /**保证金金额*/
    @ApiModelProperty(value = "保证金金额")
    private BigDecimal depositPrice;

    /**信用等级*/
    @ApiModelProperty(value = "信用等级")
    private Integer creditGrade;

    /**客户企业ID*/
    @ApiModelProperty(value = "客户企业ID")
    private Long dealStoreId;

    /**客户企业名称*/
    @ApiModelProperty(value = "客户企业名称")
    private String dealStoreName;

    /**归属人ID*/
    @ApiModelProperty(value = "归属人ID")
    private Long sysUserId;

    /**归属人名称*/
    @ApiModelProperty(value = "归属人名称")
    private String sysUserName;
}
