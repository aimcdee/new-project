package com.project.modules.cou.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品型号UpdateVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresModelUpdateVo implements Serializable {
    private static final long serialVersionUID = -1920254162306649040L;

    /**商品型号ID*/
    @ApiModelProperty(value = "商品型号ID")
    private Long couModelId;

    /**商品型号名称*/
    @ApiModelProperty(value = "商品型号名称")
    private String couModelName;

    /**商品型号图片路径*/
    @ApiModelProperty(value = "商品型号图片路径")
    private String image;

}
