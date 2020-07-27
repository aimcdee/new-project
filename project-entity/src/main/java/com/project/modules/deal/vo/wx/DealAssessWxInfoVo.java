package com.project.modules.deal.vo.wx;

import com.project.modules.deal.entity.DealAssessImageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品评估价格InfoVo
 *
 * @author liangyuding
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
public class DealAssessWxInfoVo implements Serializable {
    private static final long serialVersionUID = 4873952946901471129L;

    /**评估ID*/
    @ApiModelProperty(value = "评估ID")
    private Long dealAssessId;

    /**商品ID*/
    @ApiModelProperty(value = "商品ID")
    private Long couWaresId;

    /**商品名称*/
    @ApiModelProperty(value = "商品名称")
    private String couWaresName;

    /**商品评估图集合*/
    @ApiModelProperty(value = "商品评估图集合")
    private List<DealAssessImageEntity> waresImages;

    /**状态 0.待审核 1.已审核*/
    @ApiModelProperty(value = "状态 0.待审核 1.已审核")
    private Integer status;

    /**评估价钱*/
    @ApiModelProperty(value = "评估价钱")
    private BigDecimal dealAssessPrice;

    /**交易状态 0.未交易 1.交易中 2.已交易*/
    @ApiModelProperty(value = "交易状态 0.未交易 1.交易中 2.已交易")
    private Integer sellStatus;
}
