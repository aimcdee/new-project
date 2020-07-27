package com.project.modules.conf.vo.list;

import com.project.modules.conf.vo.Invoking.ConfBannerWaresInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 轮播图ListVo
 *
 * @author liangyuding
 * @date 2020-04-15
 */
@Data
@Accessors(chain = true)
public class ConfBannerListVo implements Serializable {
    private static final long serialVersionUID = 7274401564542028758L;

    /**轮播图ID*/
    @ApiModelProperty(value = "轮播图ID")
    private Long bannerId;

    /**轮播图标题*/
    @ApiModelProperty(value = "轮播图标题")
    private String bannerName;

    /**状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "状态 0.禁用 1.正常")
    private Integer status;

    /**排序*/
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**企业商品对象*/
    private List<ConfBannerWaresInvokingVo> bannerWaresList;
}
