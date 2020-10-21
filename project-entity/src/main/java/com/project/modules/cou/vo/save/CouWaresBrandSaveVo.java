package com.project.modules.cou.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 品牌SaveVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresBrandSaveVo implements Serializable {
    private static final long serialVersionUID = 3388429971187581244L;

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
