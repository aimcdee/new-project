package com.project.modules.cust.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 区域ListVo
 *
 * @author liangyuding
 * @date 2020-03-26
 */
@Data
@Accessors(chain = true)
public class CustAreaListVo implements Serializable {
    private static final long serialVersionUID = 7919698937177404016L;

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

    /**区域类型 0.国 1.省 2.市 3.县/区*/
    @ApiModelProperty(value = "区域类型 0.国 1.省 2.市 3.县/区")
    private Integer type;
}
