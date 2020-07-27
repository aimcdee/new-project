package com.project.modules.sys.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 系统角色SaveVo
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Data
@Accessors(chain = true)
public class SysRoleSaveVo implements Serializable {
    private static final long serialVersionUID = 6387935355727289685L;

    /**角色名称*/
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remake;

    /**部门ID集合*/
    @ApiModelProperty(value = "部门ID集合")
    private List<Long> deptIdList;

    /**菜单ID集合*/
    @ApiModelProperty(value = "菜单ID集合")
    private List<Long> menuIdList;
}
