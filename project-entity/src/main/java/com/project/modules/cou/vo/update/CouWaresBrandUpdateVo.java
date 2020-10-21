package com.project.modules.cou.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 品牌UpdateVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresBrandUpdateVo implements Serializable {
    private static final long serialVersionUID = -5367187344472261823L;

    /**品牌ID*/
    @ApiModelProperty(value = "品牌ID")
    private Long couBrandId;

    /**品牌名称*/
    @ApiModelProperty(value = "品牌名称")
    private String couBrandName;

    /**品牌logo路径*/
    @ApiModelProperty(value = "品牌logo路径")
    private String image;

    /**品牌首字母*/
    @ApiModelProperty(value = "品牌首字母")
    private String firstLetter;

    /**品牌排序*/
    @ApiModelProperty(value = "品牌排序")
    private Integer sort;
}
