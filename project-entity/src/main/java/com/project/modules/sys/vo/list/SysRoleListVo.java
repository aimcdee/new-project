package com.project.modules.sys.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色ListVo
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
public class SysRoleListVo implements Serializable {
    private static final long serialVersionUID = 2839544348545678894L;

    /**角色ID*/
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**角色名称*/
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "状态 0.禁用 1.正常")
    private Integer status;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remake;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
