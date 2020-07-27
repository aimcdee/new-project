package com.project.modules.sys.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统配置SaveVo
 *
 * @author liangyuding
 * @date 2020-03-19
 */
@Data
@Accessors(chain = true)
public class SysConfigSaveVo implements Serializable {
    private static final long serialVersionUID = 7837108453574149364L;

    /**配置名称*/
    @ApiModelProperty(value = "配置名称")
    private String name;

    /**配置编码*/
    @ApiModelProperty(value = "配置编码")
    private String code;

    /**配置值*/
    @ApiModelProperty(value = "配置值")
    private String value;

    /**配置显示值*/
    @ApiModelProperty(value = "配置显示值")
    private String memo;
}