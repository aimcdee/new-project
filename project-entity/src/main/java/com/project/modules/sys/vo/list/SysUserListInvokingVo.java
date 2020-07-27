package com.project.modules.sys.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统用户ListVo
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Data
@Accessors(chain = true)
public class SysUserListInvokingVo implements Serializable {
    private static final long serialVersionUID = -3081616810626922791L;

    /**用户ID*/
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**用户名*/
    @ApiModelProperty(value = "用户名")
    private String userName;
}
