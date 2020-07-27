package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色Entity
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@TableName("sys_role")
@Accessors(chain = true)
public class SysRoleEntity implements Serializable {
    private static final long serialVersionUID = -3911862551433837827L;

    /**角色ID*/
    @TableId
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**角色名称*/
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**所属角色ID*/
    @ApiModelProperty(value = "所属角色ID")
    private Long parentId;

    /**状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "状态 0.禁用 1.正常")
    private Integer status;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remake;

    /**创建者ID*/
    @ApiModelProperty(value = "创建者ID")
    private Long createUserId;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**最后操作人ID*/
    @ApiModelProperty(value = "最后操作人ID")
    private Long updateUserId;

    /**最后操作时间*/
    @ApiModelProperty(value = "最后操作时间")
    private Date updateTime;
}
