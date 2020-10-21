package com.project.modules.cou.vo.Invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系列ListVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresSeriesInvokingVo implements Serializable {
    private static final long serialVersionUID = -2417441091817799523L;

    /**系列ID*/
    @ApiModelProperty(value = "系列ID")
    private Long couSeriesId;

    /**系列名称*/
    @ApiModelProperty(value = "系列名称")
    private String couSeriesName;
}
