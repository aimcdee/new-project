package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统部门与系统角色关系Entity
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_dept")
public class SysRoleDeptEntity implements Serializable {
    private static final long serialVersionUID = -5385028972055519376L;

    /**主键ID*/
    @TableId
    @ApiModelProperty(value = "主键ID")
    private Long Id;

    /**系统角色ID*/
    @ApiModelProperty(value = "系统角色ID")
    private Long roleId;

    /**系统部门ID*/
    @ApiModelProperty(value = "系统部门ID")
    private Long deptId;
}
