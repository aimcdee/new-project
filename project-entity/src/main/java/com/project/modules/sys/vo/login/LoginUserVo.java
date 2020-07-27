package com.project.modules.sys.vo.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录Vo
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Data
@Accessors(chain = true)
public class LoginUserVo implements Serializable {
    private static final long serialVersionUID = 7574456654354768213L;

    /**用户ID*/
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**用户名称*/
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**手机号码*/
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "状态 0.禁用 1.正常")
    private Integer status;

    /**登录ID*/
    @ApiModelProperty(value = "登录ID")
    private String loginIp;

    /**登录时间*/
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    /**token*/
    @ApiModelProperty(value = "token")
    private String token;

    /**token到期时间*/
    @ApiModelProperty(value = "token到期时间")
    private Date expireTime;
}
