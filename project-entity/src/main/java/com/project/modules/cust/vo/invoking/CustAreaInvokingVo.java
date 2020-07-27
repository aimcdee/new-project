package com.project.modules.cust.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 区域InvikingVo
 *
 * @author liangyuding
 * @date 2020-03-26
 */
@Data
@Accessors(chain = true)
public class CustAreaInvokingVo implements Serializable {
    private static final long serialVersionUID = -8190547659964042945L;

    /**区域ID*/
    @ApiModelProperty(value = "区域ID")
    private Long areaId;

    /**区域名称*/
    @ApiModelProperty(value = "区域名称")
    private String areaName;

}
