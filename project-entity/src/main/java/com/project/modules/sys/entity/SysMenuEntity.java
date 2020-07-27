package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单Entity
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {
    private static final long serialVersionUID = 6305128663498713215L;

    /**系统菜单ID*/
    @TableId
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    /**系统菜单名称*/
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**上级菜单ID 一级菜单为0*/
    @ApiModelProperty(value = "上级菜单ID 一级菜单为0")
    private Long parentId;

    /**父菜单名称*/
    @ApiModelProperty(value = "父菜单名称")
    @TableField(exist = false)
    private String parentName;

    /**菜单URL*/
    @ApiModelProperty(value = "菜单URL")
    private String url;

    /**授权(多个用逗号分隔，如：user:list,user:create)*/
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    /**类型 0：目录 1：菜单 2：按钮*/
    @ApiModelProperty(value = "类型 0：目录 1：菜单 2：按钮")
    private Integer type;

    /**菜单图标*/
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**菜单排序*/
    @ApiModelProperty(value = "菜单排序")
    private Integer orderNum;

    @TableField(exist = false)
    private List<?> list;

    /**ztree属性*/
    @TableField(exist = false)
    private Boolean open;
}
