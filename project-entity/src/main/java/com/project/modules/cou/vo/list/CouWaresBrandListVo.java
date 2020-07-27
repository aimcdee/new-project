package com.project.modules.cou.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品品牌ListVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresBrandListVo implements Serializable {
    private static final long serialVersionUID = -6317718922884437500L;

    /**品牌ID*/
    @ApiModelProperty(value = "品牌ID")
    private Long couBrandId;

    /**品牌名称*/
    @ApiModelProperty(value = "品牌名称")
    private String couBrandName;

    /**图片路径集合*/
    @ApiModelProperty(value = "图片路径集合")
    private String image;

    /**品牌首字母*/
    @ApiModelProperty(value = "品牌首字母")
    private String firstLetter;

    /**商品品牌状态状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "商品品牌状态 0.禁用 1.正常")
    private Integer status;
}
