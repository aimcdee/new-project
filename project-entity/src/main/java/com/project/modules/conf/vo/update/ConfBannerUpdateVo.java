package com.project.modules.conf.vo.update;

import com.project.modules.conf.vo.Invoking.ConfBannerInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 轮播图UpdateVo
 *
 * @author liangyuding
 * @date 2020-04-15
 */
@Data
@Accessors(chain = true)
public class ConfBannerUpdateVo implements Serializable {
    private static final long serialVersionUID = -1222761224159583446L;

    /**轮播图ID*/
    @ApiModelProperty(value = "轮播图ID")
    private Long bannerId;

    /**轮播图标题*/
    @ApiModelProperty(value = "轮播图标题")
    private String name;

    /**排序*/
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**企业商品对像集合*/
    private List<ConfBannerInvokingVo> bannerWaresList;
}
