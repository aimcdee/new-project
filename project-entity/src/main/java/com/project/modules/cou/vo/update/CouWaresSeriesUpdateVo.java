package com.project.modules.cou.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系列UpdateVo
 *
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresSeriesUpdateVo implements Serializable {
    private static final long serialVersionUID = -5993837820199319258L;

    /**系列ID*/
    @ApiModelProperty(value = "系列ID")
    private Long couSeriesId;

    /**系列名称*/
    @ApiModelProperty(value = "系列名称")
    private String couSeriesName;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    private Long couBrandId;
}
