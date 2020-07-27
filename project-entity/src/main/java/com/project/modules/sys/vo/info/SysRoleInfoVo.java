package com.project.modules.sys.vo.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统角色InfoVo
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Data
@Accessors(chain = true)
public class SysRoleInfoVo implements Serializable {
    private static final long serialVersionUID = 9150880393011837771L;

    /**角色ID*/
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    /**角色名称*/
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remake;

    /**状态  0：禁用   1：正常*/
    @ApiModelProperty(value = "状态  0：禁用   1：正常")
    private Integer status;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**部门ID集合*/
    @ApiModelProperty(value = "部门ID集合")
    private List<Long> deptIdList;

    /**菜单ID集合*/
    @ApiModelProperty(value = "菜单ID集合")
    private List<Long> menuIdList;
}
