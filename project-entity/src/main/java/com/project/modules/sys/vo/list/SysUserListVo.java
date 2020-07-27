package com.project.modules.sys.vo.list;

import com.project.modules.sys.vo.invoking.SysRoleInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户ListVo
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
public class SysUserListVo implements Serializable {
    private static final long serialVersionUID = 7081819420590780250L;

    /**用户ID*/
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**用户名*/
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**手机号码*/
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**状态 0：禁用 1：正常*/
    @ApiModelProperty(value = "状态 0：禁用 1：正常")
    private Integer status;

    /**部门ID*/
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**部门名称*/
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**系统角色集合*/
    @ApiModelProperty(value = "系统角色集合")
    private List<SysRoleInvokingVo> roleList;

    /**最近一次登录IP*/
    @ApiModelProperty(value = "最近一次登录IP")
    private String loginIp;

    /**最近一次登录时间*/
    @ApiModelProperty(value = "最近一次登录时间")
    private Date loginTime;
}
