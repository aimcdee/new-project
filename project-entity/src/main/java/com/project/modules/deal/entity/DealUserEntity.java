package com.project.modules.deal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户Entity
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Data
@Accessors(chain = true)
@TableName("deal_user")
public class DealUserEntity implements Serializable {
    private static final long serialVersionUID = -2683711734476844473L;

    /**客户ID*/
    @TableId
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**客户名称*/
    @ApiModelProperty(value = "客户名称")
    private String dealUserName;

    /**客户手机号码*/
    @ApiModelProperty(value = "客户手机号码")
    private String phone;

    /**客户状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "客户状态 0.禁用 1.正常")
    private Integer status;

    /**客户类型 0.个人 1.商家*/
    @ApiModelProperty(value = "客户类型 0.个人 1.商家")
    private Integer type;

    /**个人积分*/
    @ApiModelProperty(value = "个人积分")
    private Long integral;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
