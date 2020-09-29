package com.project.modules.cou.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品型号ListVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresModelListVo implements Serializable {
    private static final long serialVersionUID = -7660741613146671345L;

    /**商品型号ID*/
    @ApiModelProperty(value = "商品型号ID")
    private Long couModelId;

    /**商品型号名称*/
    @ApiModelProperty(value = "商品型号名称")
    private String couModelName;

    /**上级型号ID*/
    @ApiModelProperty(value = "上级型号ID")
    private Long parentId;

    /**上级型号名称*/
    @ApiModelProperty(value = "上级型号名称")
    private String parentName;

    /**商品型号图片路径*/
    @ApiModelProperty(value = "商品型号图片路径")
    private String image;

    /**商品型号状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "商品型号状态 0.禁用 1.正常")
    private Integer status;


}
