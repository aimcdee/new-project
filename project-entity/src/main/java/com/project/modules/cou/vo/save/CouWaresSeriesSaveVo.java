package com.project.modules.cou.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品品牌系列SaveVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresSeriesSaveVo implements Serializable {
    private static final long serialVersionUID = -369051025886771870L;

    /**系列名称*/
    @ApiModelProperty(value = "系列名称")
    private String couSeriesName;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    private Long couBrandId;
}
