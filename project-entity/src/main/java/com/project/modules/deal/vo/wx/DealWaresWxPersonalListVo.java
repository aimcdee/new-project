package com.project.modules.deal.vo.wx;

import com.project.modules.deal.entity.DealWaresImageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业客户商品ListVo
 *
 * @author liangyuding
 * @date 2020-06-11
 */
@Data
@Accessors(chain = true)
public class DealWaresWxPersonalListVo implements Serializable {
    private static final long serialVersionUID = 1690386195828042440L;

    /**出售商品ID*/
    @ApiModelProperty(value = "出售商品ID")
    private String dealWaresId;

    /**出售商品标题*/
    @ApiModelProperty(value = "出售商品标题")
    private String dealWaresTitle;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    private Long couBrandId;

    /**所属品牌名称*/
    @ApiModelProperty(value = "所属品牌名称")
    private String couBrandName;

    /**所属品牌系列ID*/
    @ApiModelProperty(value = "所属品牌系列ID")
    private Long couSeriesId;

    /**所属品牌系列名称*/
    @ApiModelProperty(value = "所属品牌系列名称")
    private String couSeriesName;

    /**上牌时间*/
    @ApiModelProperty(value = "上牌时间")
    private Date registerTime;

    /**行驶里程*/
    @ApiModelProperty(value = "行驶里程")
    private Long distance;

    /**出售状态 0.未出售 1.已出售*/
    @ApiModelProperty(value = "出售状态 0.未出售 1.已出售")
    private Integer sellStatus;

    /**上线状态 0.审核失败 1.待审核 2.上架 3.下架*/
    @ApiModelProperty(value = "上线状态 0.审核失败 1.待审核 2.上架 3.下架")
    private Integer onlineStatus;

    /**商品封面图*/
    @ApiModelProperty(value = "商品封面图")
    private DealWaresImageEntity coverImage;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;
}
