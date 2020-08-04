package com.project.modules.sys.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统用户UpdatePasswordVo
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
public class SysUserUpdatePasswordVo implements Serializable {
    private static final long serialVersionUID = 2513265990592563573L;

    /**用户ID*/
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**原密码*/
    @ApiModelProperty(value = "原密码")
    private String oldPassword;

    /**新密码*/
    @ApiModelProperty(value = "新密码")
    private String newPassword;

    /**确认密码*/
    @ApiModelProperty(value = "确认密码")
    private String confirm;
}
