package com.project.modules.deal.vo.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户LoinVo
 *
 * @author liangyuding
 * @date 2020-03-28
 */
@Data
@Accessors(chain = true)
public class DealUserLoginVo implements Serializable {
    private static final long serialVersionUID = 5858456621617647405L;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**客户名称*/
    @ApiModelProperty(value = "客户名称")
    private String DealUserName;

    /**客户手机号码*/
    @ApiModelProperty(value = "客户手机号码")
    private String phone;

    /**客户企业ID*/
    @ApiModelProperty(value = "客户企业ID")
    private Long dealStoreId;

    /**客户类型 0.个人 1.车商*/
    @ApiModelProperty(value = "客户类型 0.个人 1.车商")
    private Integer type;

    /**token*/
    @ApiModelProperty(value = "token")
    private String token;

    /**token到期时间*/
    @ApiModelProperty(value = "token到期时间")
    private Date expireTime;
}
