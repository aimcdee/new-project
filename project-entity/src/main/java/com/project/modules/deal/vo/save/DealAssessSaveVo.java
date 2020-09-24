package com.project.modules.deal.vo.save;

import com.project.modules.deal.vo.invoking.DealImageInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
    private Long couBrandId;

    /**所属系列ID*/
    @ApiModelProperty(value = "所属系列ID")
    private Long couSeriesId;

    /**评估商品名称*/
    @ApiModelProperty(value = "评估商品名称")
    private String assessWaresTitle;

    /**上牌时间*/
    @ApiModelProperty(value = "上牌时间")
    private Date registerTime;

    /**省级地区ID*/
    @ApiModelProperty(value = "省级地区ID")
    private Long proAreaId;

    /**市级地区ID*/
    @ApiModelProperty(value = "市级地区ID")
    private Long cityAreaId;

    /**县/区地区Id*/
    @ApiModelProperty(value = "县/区地区Id")
    private Long countyAreaId;

    /**行驶里程*/
    @ApiModelProperty(value = "行驶里程")
    private Long distance;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**行驶证图对象*/
    @ApiModelProperty(value = "行驶证图对象")
    private DealImageInvokingVo driveImage;

    /**商品评估图集合*/
    @ApiModelProperty(value = "商品评估图集合")
    private List<DealImageInvokingVo> waresImages;
}
