package com.project.modules.cou.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品型号SaveVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresModelSaveVo implements Serializable {
    private static final long serialVersionUID = 954001821325893782L;

    /**类型名称*/
    @ApiModelProperty(value = "商品型号")
    private String couModelName;

    /**商品型号图片路径*/
    @ApiModelProperty(value = "商品型号图片路径")
    private String image;

}
