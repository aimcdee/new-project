package com.project.modules.conf.vo.update;

import com.project.modules.conf.vo.Invoking.ConfBannerInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "请选择轮播图")
    @ApiModelProperty(value = "轮播图ID")
    private Long bannerId;

    /**轮播图标题*/
    @NotBlank(message = "请输入轮播图标题")
    @ApiModelProperty(value = "轮播图标题")
    private String bannerName;

    /**排序*/
    @NotNull(message = "请输入排序")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**展示类型 0.零售 1.企业*/
    @NotNull(message = "请选择所属展示类型")
    @ApiModelProperty(value = "展示类型 0.零售 1.企业")
    private Integer displayType;

    /**企业商品对像集合*/
    @NotNull(message = "请选择所属商品")
    private List<ConfBannerInvokingVo> bannerWaresList;
}
