package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户金融单表Entity
 *
 * @author liangyuding
 * @date 2020-06-04
 */
@Data
@Accessors(chain = true)
@TableName("deal_user_store_finance")
public class DealUserStoreFinanceEntity implements Serializable {
    private static final long serialVersionUID = -3414081476367181605L;

    /**金融单ID*/
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "金融单ID")
    private String financeId;

    /**金融单编号*/
    @ApiModelProperty(value = "金融单编号")
    private String financeNo;

    /**客户企业表ID*/
    @ApiModelProperty(value = "客户企业表ID")
    private Long dealStoreId;

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

    /**状态 0.作废 1.处理中 2.已完成*/
    @ApiModelProperty(value = "状态 0.作废 1.处理中 2.已完成")
    private Integer status;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
