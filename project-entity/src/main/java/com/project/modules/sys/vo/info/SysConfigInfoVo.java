package com.project.modules.sys.vo.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统配置InfoVo
 *
 * @author liangyuding
 * @date 2020-03-19
 */
@Data
@Accessors(chain = true)
public class SysConfigInfoVo implements Serializable {
    private static final long serialVersionUID = -7589912312769529615L;

    /**编号*/
    @ApiModelProperty(value = "编号")
    private Long configId;

    /**配置名称*/
    @ApiModelProperty(value = "配置名称")
    private String name;

    /**配置编码*/
    @ApiModelProperty(value = "配置编码")
    private String code;

    /**配置值*/
    @ApiModelProperty(value = "配置值")
    private String value;

    /**配置值*/
    @ApiModelProperty(value = "配置显示值")
    private String memo;

}