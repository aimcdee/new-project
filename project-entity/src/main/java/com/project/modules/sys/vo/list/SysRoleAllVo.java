package com.project.modules.sys.vo.list;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色Entity
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
public class SysRoleAllVo implements Serializable {
    private static final long serialVersionUID = 6498883053473461319L;

    /**角色ID*/
    @TableId
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**角色名称*/
    @ApiModelProperty(value = "角色名称")
    private String roleName;

}
