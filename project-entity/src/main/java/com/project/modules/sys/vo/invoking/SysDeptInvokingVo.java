package com.project.modules.sys.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统部门InvokingVo
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
public class SysDeptInvokingVo implements Serializable {
    private static final long serialVersionUID = -2548275398221580178L;

    /**部门ID*/
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**部门名称*/
    @ApiModelProperty(value = "部门名称")
    private String deptName;
}
