package com.project.modules.sys.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置ListVo
 *
 * @author liangyuding
 * @date 2020-03-19
 */
@Data
@Accessors(chain = true)
public class SysConfigListVo implements Serializable {
    private static final long serialVersionUID = 1790754772727067964L;

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

    /**配置显示值*/
    @ApiModelProperty(value = "配置显示值")
    private String memo;

    /**状态 0.禁用 1.启用*/
    @ApiModelProperty(value = "状态 0.禁用 1.启用")
    private Integer status;
}