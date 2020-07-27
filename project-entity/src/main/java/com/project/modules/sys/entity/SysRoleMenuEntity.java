package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sys_role_menu")
public class SysRoleMenuEntity implements Serializable {
    private static final long serialVersionUID = 249412220561998602L;

    /**主键ID*/
    @TableId
    private Long Id;

    /**系统角色ID*/
    private Long roleId;

    /**系统部门ID*/
    private Long MenuId;
}
