package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户企业表Entity
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Data
@Accessors(chain = true)
@TableName("deal_user_store")
public class DealUserStoreEntity implements Serializable {
    private static final long serialVersionUID = 668846720065048914L;

    /**主键ID*/
    @TableId
    @ApiModelProperty(value = "主键ID")
    private Long dealStoreId;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**客户所属公司名称*/
    @ApiModelProperty(value = "客户所属企业名称")
    private String dealStoreName;

    /**商家门面图URL*/
    @ApiModelProperty(value = "企业门面图URL")
    private String image;

    /**客户职位*/
    @ApiModelProperty(value = "客户职位")
    private String dealUserJob;

    /**保证金金额*/
    @ApiModelProperty(value = "保证金金额")
    private BigDecimal depositPrice;

    /**信用等级*/
    @ApiModelProperty(value = "信用等级")
    private Integer creditGrade;

    /**审核状态 0.审核失败 1.审核中 2.审核通过*/
    @ApiModelProperty(value = "审核状态 0.审核失败 1.审核中 2.审核通过")
    private Integer examine;

    /**归属人ID*/
    @ApiModelProperty(value = "归属人ID")
    private Long sysUserId;

    /**归属人名称*/
    @ApiModelProperty(value = "归属人名称")
    private String sysUserName;

    /**申请时间*/
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

    /**审核人ID*/
    @ApiModelProperty(value = "审核人ID")
    private Long examineUserId;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Date examineTime;
}
