package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统用户与系统部门关系Entity
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_dept")
public class SysUserDeptEntity implements Serializable {
    private static final long serialVersionUID = -6249777433483512697L;

    /**主键ID*/
    @TableId
    @ApiModelProperty(value = "主键ID")
    private Long Id;

    /**系统用户ID*/
    @ApiModelProperty(value = "系统用户ID")
    private Long userId;

    /**系统部门ID*/
    @ApiModelProperty(value = "系统部门ID")
    private Long deptId;
}
