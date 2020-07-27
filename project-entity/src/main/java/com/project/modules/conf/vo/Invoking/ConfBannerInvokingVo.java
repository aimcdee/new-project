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
public class ConfBannerInvokingVo implements Serializable {
    private static final long serialVersionUID = -6997116383122626949L;

    /**企业商品ID*/
    @ApiModelProperty(value = "企业商品ID")
    private String dealWaresId;

    /**企业商品图标题*/
    @ApiModelProperty(value = "企业商品图标题")
    private String  dealWaresTitle;

    /**图片路径*/
    @ApiModelProperty(value = "图片路径")
    private String image;
}
