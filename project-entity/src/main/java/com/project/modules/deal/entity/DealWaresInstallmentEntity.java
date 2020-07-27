package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 咨询分期客户管理表Entity
 *
 * @author liangyuding
 * @date 2020-06-06
 */
@Data
@Accessors(chain = true)
@TableName("deal_wares_installment")
public class DealWaresInstallmentEntity implements Serializable {
    private static final long serialVersionUID = -3223583447272848689L;

    /**咨询分期ID*/
    @TableId
    @ApiModelProperty(value = "咨询分期ID")
    private Long installmentId;

    /**所属客户ID*/
    @ApiModelProperty(value = "所属客户ID")
    private Long dealUserId;

    /**咨询商品ID*/
    @ApiModelProperty(value = "咨询商品ID")
    private String dealWaresId;

    /**咨询商品标题*/
    @ApiModelProperty(value = "咨询商品标题")
    private String dealWaresTitle;

    /**联系人名称*/
    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    /**联系电话*/
    @ApiModelProperty(value = "联系电话")
    private String contactPhone;

    /**性别 0.先生 1.小姐*/
    @ApiModelProperty(value = "性别 0.先生 1.小姐")
    private Integer sex;

    /**提交咨询时间*/
    @ApiModelProperty(value = "提交咨询时间")
    private Date submitTime;

    /**跟进状态 0.作废 1.待处理 2.已处理*/
    @ApiModelProperty(value = "跟进状态 0.作废 1.待处理 2.已处理")
    private Integer followStatus;

    /**跟进人ID*/
    @ApiModelProperty(value = "跟进人ID")
    private Long sysUserId;

    /**跟进备注*/
    @ApiModelProperty(value = "跟进备注")
    private String followRemark;

    /**跟进时间*/
    @ApiModelProperty(value = "跟进时间")
    private Date followTime;
}
