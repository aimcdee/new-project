package com.project.modules.sys.vo.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 系统用户ListVo
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
public class SysUserInfoVo implements Serializable {
    private static final long serialVersionUID = -4641172507228594556L;

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

    /**状态  0：禁用   1：正常*/
    @ApiModelProperty(value = "状态  0：禁用   1：正常")
    private Integer status;

    /**部门ID*/
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**部门名称*/
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**角色ID集合*/
    @ApiModelProperty(value = "角色ID集合")
    private List<Long> roleIdList;
}
