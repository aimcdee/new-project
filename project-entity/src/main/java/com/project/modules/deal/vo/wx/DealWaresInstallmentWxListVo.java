package com.project.modules.deal.vo.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 咨询分期客户管理表ListVo
 *
 * @author liangyuding
 * @date 2020-06-12
 */
@Data
@Accessors(chain = true)
public class DealWaresInstallmentWxListVo implements Serializable {
    private static final long serialVersionUID = 691762733470370380L;

    /**咨询分期ID*/
    @ApiModelProperty(value = "咨询分期ID")
    private Long installmentId;

    /**咨询商品ID*/
    @ApiModelProperty(value = "咨询商品ID")
    private String dealWaresId;

    /**咨询商品标题*/
    @ApiModelProperty(value = "咨询商品标题")
    private String dealWaresTitle;

    /**提交咨询时间*/
    @ApiModelProperty(value = "提交咨询时间")
    private Date submitTime;

    /**跟进状态 0.作废 1.待处理 2.已完成*/
    @ApiModelProperty(value = "跟进状态 0.作废 1.待处理 2.已完成")
    private Integer followStatus;

    /**跟进人ID*/
    @ApiModelProperty(value = "跟进人ID")
    private Long sysUserId;

    /**跟进人名称*/
    @ApiModelProperty(value = "跟进人名称")
    private String sysUserName;
}
