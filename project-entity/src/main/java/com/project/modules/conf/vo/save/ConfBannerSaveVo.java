package com.project.modules.conf.vo.save;

import com.project.modules.conf.vo.Invoking.ConfBannerInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 轮播图SaveVo
 *
 * @author liangyuding
 * @date 2020-04-15
 */
@Data
@Accessors(chain = true)
public class ConfBannerSaveVo implements Serializable {
    private static final long serialVersionUID = 5447586439778315389L;

    /**轮播图标题*/
    @ApiModelProperty(value = "轮播图标题")
    private String bannerName;

    /**排序*/
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**企业商品对像集合*/
    private List<ConfBannerInvokingVo> bannerWaresList;
}
