package com.project.modules.sys.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统角色InvokingVo
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
public class SysRoleInvokingVo implements Serializable {
    private static final long serialVersionUID = -4737830672423819055L;

    /**角色ID*/
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**角色名称*/
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
