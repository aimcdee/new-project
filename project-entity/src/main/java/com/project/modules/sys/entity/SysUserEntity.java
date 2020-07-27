package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户Entity
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = -8543399569924522313L;

    /**用户ID*/
    @TableId
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**用户名*/
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**密码*/
    @ApiModelProperty(value = "密码")
    private String password;

    /**盐*/
    @ApiModelProperty(value = "盐")
    private String salt;

    /**手机号码*/
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "状态 0.禁用 1.正常")
    private Integer status;

    /**创建者ID*/
    @ApiModelProperty(value = "创建者ID")
    private Long createUserId;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**最后操作者ID*/
    @ApiModelProperty(value = "最后操作者ID")
    private Long  updateUserId;

    /**最后操作时间*/
    @ApiModelProperty(value = "最后操作时间")
    private Date updateTime;

    /**最后登录IP*/
    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    /**最后登录时间*/
    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;
}
