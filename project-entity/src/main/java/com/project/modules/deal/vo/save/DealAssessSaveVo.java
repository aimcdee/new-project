package com.project.modules.deal.vo.save;

import com.project.modules.deal.vo.invoking.DealImageInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品评估价格SaveVo
 *
 * @author liangyuding
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
public class DealAssessSaveVo implements Serializable {
    private static final long serialVersionUID = -4806863139761682525L;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    @NotNull(message = "请选择所属品牌")
    private Long couBrandId;

    /**所属系列ID*/
    @ApiModelProperty(value = "所属系列ID")
    @NotNull(message = "请选择所属系列")
    private Long couSeriesId;

    /**评估商品名称*/
    @ApiModelProperty(value = "评估商品名称")
    @NotBlank(message = "请输入评估商品名称")
    private String assessWaresTitle;

    /**上牌时间*/
    @ApiModelProperty(value = "上牌时间")
    @NotNull(message = "请输入上牌时间")
    private Date registerTime;

    /**省级地区ID*/
    @ApiModelProperty(value = "省级地区ID")
    @NotNull(message = "请选择所在区域省份")
    private Long proAreaId;

    /**市级地区ID*/
    @ApiModelProperty(value = "市级地区ID")
    private Long cityAreaId;

    /**县/区地区Id*/
    @ApiModelProperty(value = "县/区地区Id")
    private Long countyAreaId;

    /**行驶里程*/
    @ApiModelProperty(value = "行驶里程")
    @NotNull(message = "请输入行驶里程")
    private Long distance;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**行驶证图对象*/
    @ApiModelProperty(value = "行驶证图对象")
    @NotNull(message = "请上传行驶证图")
    private DealImageInvokingVo driveImage;

    /**评估商品图集合*/
    @ApiModelProperty(value = "评估商品图集合")
    @NotNull(message = "请上传需要评估的商品图")
    private List<DealImageInvokingVo> waresImages;
}
