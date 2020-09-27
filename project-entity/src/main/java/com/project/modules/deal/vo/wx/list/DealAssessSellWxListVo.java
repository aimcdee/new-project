package com.project.modules.deal.vo.wx.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 个人评估商品出售表ListVo
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
public class DealAssessSellWxListVo implements Serializable {
    private static final long serialVersionUID = -5826423353061154107L;

    /**评估商品出售ID*/
    @ApiModelProperty(value = "评估商品出售ID")
    private Long dealSellId;

    /**评估商品出售标题*/
    @ApiModelProperty(value = "评估商品出售标题")
    private String dealSellTitle;

    /**评估商品名称*/
    @ApiModelProperty(value = "评估商品名称")
    private String assessWaresTitle;

    /**跟进人ID*/
    @ApiModelProperty(value = "跟进人ID")
    private Long sysUserId;

    /**跟进人名称*/
    @ApiModelProperty(value = "跟进人名称")
    private String sysUserName;

    /**最终出售金额*/
    @ApiModelProperty(value = "最终出售金额")
    private BigDecimal sellPrice;

    /**出售情况 0.已取消 1.待处理 2.处理中 3.已完成*/
    @ApiModelProperty(value = "出售情况 0.已取消 1.待处理 2.处理中 3.已完成")
    private Integer status;
}
