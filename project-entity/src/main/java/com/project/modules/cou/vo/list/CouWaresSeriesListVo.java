package com.project.modules.cou.vo.list;

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
public class CouWaresSeriesListVo implements Serializable {
    private static final long serialVersionUID = -2417441091817799523L;

    /**系列ID*/
    @ApiModelProperty(value = "系列ID")
    private Long couSeriesId;

    /**系列名称*/
    @ApiModelProperty(value = "系列名称")
    private String couSeriesName;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    private Long couBrandId;

    /**所属品牌名称*/
    @ApiModelProperty(value = "所属品牌名称")
    private String couBrandName;

    /**系列状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "系列状态 0.禁用 1.正常")
    private Integer status;
}
