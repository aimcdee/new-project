package com.project.modules.cust.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品所在地区牌号表ListVo
 *
 * @author liangyuding
 * @date 2020-06-03
 */
@Data
@Accessors(chain = true)
public class CustAreaLicenseListVo implements Serializable {
    private static final long serialVersionUID = 5489805985158951453L;

    /**所在区域牌号表ID*/
    @ApiModelProperty(value = "所在区域牌号表ID")
    private Long licenseId;

    /**所在区域牌号*/
    @ApiModelProperty(value = "所在区域牌号")
    private String licenseCode;

    /**所属区域ID*/
    @ApiModelProperty(value = "所属区域ID")
    private Long areaId;

    /**所属区域名称*/
    @ApiModelProperty(value = "所属区域名称")
    private String areaName;
}
