package com.project.modules.sys.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 系统用户UpdateVo
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
public class SysUserUpdateVo implements Serializable {
    private static final long serialVersionUID = 2382060801723428943L;

    /**用户ID*/
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**用户名*/
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**手机号码*/
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**密码*/
    @ApiModelProperty(value = "密码")
    private String password;

    /**确认密码*/
    @ApiModelProperty(value = "确认密码")
    private String confirm;

    /**部门ID*/
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**角色ID集合*/@ApiModelProperty(value = "角色ID集合")
    private List<Long> roleIdList;
}
