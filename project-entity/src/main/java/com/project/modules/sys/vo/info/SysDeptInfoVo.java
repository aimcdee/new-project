package com.project.modules.sys.vo.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统部门InfoVo
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Data
@Accessors(chain = true)
public class SysDeptInfoVo implements Serializable {
    private static final long serialVersionUID = -3106716817391087956L;

    /**部门ID*/
    @ApiModelProperty(value = "部门ID")
    private String deptId;

    /**部门名称*/
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**上级部门ID*/
    @ApiModelProperty(value = "上级部门ID")
    private Long parentId;

    /**上级部门名称*/
    @ApiModelProperty(value = "上级部门名称")
    private String parentName;
}
