package com.project.modules.deal.vo.wx;

import com.project.modules.deal.entity.DealWaresImageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 零售端商品ListVo
 *
 * @author liangyuding
 * @date 2020-06-11
 */
@Data
@Accessors(chain = true)
public class DealWaresWxRetailListVo implements Serializable {
    private static final long serialVersionUID = -2121026237638502366L;

    /**出售商品ID*/
    @ApiModelProperty(value = "出售商品ID")
    private String dealWaresId;

    /**出售商品标题*/
    @ApiModelProperty(value = "出售商品标题")
    private String dealWaresTitle;

    /**出售商品编号*/
    @ApiModelProperty(value = "出售商品编号")
    private String dealWaresNo;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    private Long couBrandId;

    /**所属品牌名称*/
    @ApiModelProperty(value = "所属品牌名称")
    private String couBrandName;

    /**上牌时间*/
    @ApiModelProperty(value = "上牌时间")
    private Date registerTime;

    /**零售价*/
    @ApiModelProperty(value = "零售价")
    private BigDecimal retailPrice;

    /**行驶里程*/
    @ApiModelProperty(value = "行驶里程")
    private Long distance;

    /**商品封面图*/
    @ApiModelProperty(value = "商品封面图")
    private DealWaresImageEntity coverImage;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    /**企业名称*/
    @ApiModelProperty(value = "企业名称")
    private String dealStoreName;

    /**信用等级*/
    @ApiModelProperty(value = "信用等级")
    private Integer creditGrade;
}
