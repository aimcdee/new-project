package com.project.modules.sys.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统部门TreeVo
 *
 * @author liangyuding
 * @date 2020-03-18
 */

@Data
@Accessors(chain = true)
public class SysDeptTreeVo implements Serializable {
    private static final long serialVersionUID = 2514820914294933611L;

    /**部门ID*/
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**部门名称*/
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**上级ID,没有上级填写0*/
    @ApiModelProperty(value = "上级ID,没有上级填写0")
    private Long parentId;

    /**上级部门名称*/
    @ApiModelProperty(value = "上级部门名称")
    private String parentName;
}
