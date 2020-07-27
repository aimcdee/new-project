package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统用户与系统角色关系Entity
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 5627903071854541030L;

    /**主键ID*/
    @TableId
    @ApiModelProperty(value = "主键ID")
    private Long Id;

    /**系统用户ID*/
    @ApiModelProperty(value = "系统用户ID")
    private Long userId;

    /**系统角色ID*/
    @ApiModelProperty(value = "系统角色ID")
    private Long roleId;
}
