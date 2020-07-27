package com.project.modules.sys.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 系统部门UpdateVo
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Data
@Accessors(chain = true)
public class SysDeptUpdateVo implements Serializable {
    private static final long serialVersionUID = 3103730757925029405L;

    /**部门ID*/
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**部门名称*/
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**上级部门ID*/
    @ApiModelProperty(value = "上级部门ID")
    private Long parentId;
}
