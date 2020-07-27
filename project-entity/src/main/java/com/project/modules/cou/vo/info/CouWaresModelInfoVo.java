package com.project.modules.cou.vo.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品型号InfoVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresModelInfoVo implements Serializable {
    private static final long serialVersionUID = -4914821729773173452L;

    /**型号ID*/
    @ApiModelProperty(value = "型号ID")
    private Long couModelId;

    /**型号名称*/
    @ApiModelProperty(value = "型号名称")
    private String couModelName;

    /**型号图片路径*/
    @ApiModelProperty(value = "型号图片路径")
    private String image;

}
