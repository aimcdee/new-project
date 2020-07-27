package com.project.modules.cust.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 区域TreeVo
 *
 * @author liangyuding
 * @date 2020-03-26
 */
@Data
@Accessors(chain = true)
public class CustAreaTreeVo implements Serializable {
    private static final long serialVersionUID = 4794858917618868240L;

    /**区域ID*/
    @ApiModelProperty(value = "区域ID")
    private Long areaId;

    /**区域名称*/
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**上级区域ID*/
    @ApiModelProperty(value = "上级区域ID")
    private Long parentId;

    /**上级区域名称*/
    @ApiModelProperty(value = "区域名称")
    private String parentName;
}
