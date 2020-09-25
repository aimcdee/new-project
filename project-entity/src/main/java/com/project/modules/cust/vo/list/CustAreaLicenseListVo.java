package com.project.modules.cust.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 牌号ListVo
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
}
