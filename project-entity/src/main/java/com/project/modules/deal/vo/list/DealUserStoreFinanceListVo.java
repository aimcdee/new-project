package com.project.modules.deal.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户金融单表ListVo
 *
 * @author liangyuding
 * @date 2020-06-04
 */
@Data
@Accessors(chain = true)
public class DealUserStoreFinanceListVo implements Serializable {
    private static final long serialVersionUID = -7530816147251305805L;

    /**金融单ID*/
    @ApiModelProperty(value = "金融单ID")
    private String financeId;

    /**金融单编号*/
    @ApiModelProperty(value = "金融单编号")
    private String financeNo;

    /**所属客户企业ID*/
    @ApiModelProperty(value = "所属客户企业ID")
    private Long dealStoreId;

    /**所属企业客户ID*/
    @ApiModelProperty(value = "所属企业客户ID")
    private Long dealUserId;

    /**所属企业客户*/
    @ApiModelProperty(value = "所属企业客户名称")
    private String dealUserName;

    /**联系电话*/
    @ApiModelProperty(value = "联系电话")
    private String contactPhone;

    /**联系人名称*/
    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    /**性别 0.男 1.女*/
    @ApiModelProperty(value = "性别 0.男 1.女")
    private Integer sex;

    /**跟进人ID*/
    @ApiModelProperty(value = "跟进人ID")
    private Long sysUserId;

    /**跟进人名称*/
    @ApiModelProperty(value = "跟进人名称")
    private String sysUserName;

    /**金融单交易金额*/
    @ApiModelProperty(value = "金融单交易金额")
    private BigDecimal financePrice;

    /**状态 0.作废 1.处理中 4.已完成*/
    @ApiModelProperty(value = "状态 0.作废 1.处理中 4.已完成")
    private Integer status;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;
}
