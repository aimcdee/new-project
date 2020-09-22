package com.project.modules.deal.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 评估商品出售表SaveVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealAssessSellSaveVo implements Serializable {
    private static final long serialVersionUID = 6951461169702239915L;

    /**评估商品出售标题*/
    @ApiModelProperty(value = "评估商品出售标题")
    private String DealSellTitle;

    /**所属评估ID*/
    @ApiModelProperty(value = "所属评估ID")
    private Long DealAssessId;

    /**联系人名称*/
    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    /**联系人电话*/
    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;

    /**性别*/
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**省级地区ID*/
    @ApiModelProperty(value = "省级地区ID")
    private Long proAreaId;

    /**市级地区ID*/
    @ApiModelProperty(value = "市级地区ID")
    private Long cityAreaId;

    /**县/区地区Id*/
    @ApiModelProperty(value = "县/区地区Id")
    private Long countyAreaId;

    /**详细地址*/
    @ApiModelProperty(value = "详细地址")
    private String addr;
}
