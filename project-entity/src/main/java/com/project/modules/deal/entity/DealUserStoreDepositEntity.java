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
 * 企业用户保证金表Entity
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Data
@Accessors(chain = true)
@TableName("deal_user_store_deposit")
public class DealUserStoreDepositEntity implements Serializable {
    private static final long serialVersionUID = -6240365849307460713L;

    /**保证金ID*/
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "保证金单ID")
    private String depositId;

    /**保证金单编号*/
    @ApiModelProperty(value = "保证金单编号")
    private String depositNo;

    /**客户企业表ID*/
    @ApiModelProperty(value = "客户企业表ID")
    private Long dealStoreId;

    /**保证金金额*/
    @ApiModelProperty(value = "保证金金额")
    private BigDecimal depositPrice;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;

    /**状态 0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过*/
    @ApiModelProperty(value = "状态 0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过")
    private Integer status;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    /**创建人ID*/
    @ApiModelProperty(value = "创建人ID")
    private Long createUserId;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**更新者ID*/
    @ApiModelProperty(value = "更新者ID")
    private Long updateUserId;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
