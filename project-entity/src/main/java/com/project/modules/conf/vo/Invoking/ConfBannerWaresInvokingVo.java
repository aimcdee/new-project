package com.project.modules.conf.vo.Invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 轮播企业商品管理Entity
 *
 * @author liangyuding
 * @date 2020-06-08
 */
@Data
@Accessors(chain = true)
public class ConfBannerWaresInvokingVo implements Serializable {
    private static final long serialVersionUID = 587076615539574099L;

    /**主键ID*/
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**所属轮播图ID*/
    @ApiModelProperty(value = "所属轮播图ID")
    private Long bannerId;

    /**企业商品ID*/
    @ApiModelProperty(value = "企业商品ID")
    private String dealWaresId;

    /**企业商品图标题*/
    @ApiModelProperty(value = "企业商品图标题")
    private String  dealWaresTitle;

    /**企业商品封面图路径*/
    @ApiModelProperty(value = "企业商品封面图路径")
    private String image;
}
