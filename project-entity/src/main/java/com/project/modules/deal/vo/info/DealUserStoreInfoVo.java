package com.project.modules.deal.vo.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户企业验证InfoVo
 *
 * @author liangyuding
 * @date 2020-04-09
 */
@Data
@Accessors(chain = true)
public class DealUserStoreInfoVo implements Serializable {
    private static final long serialVersionUID = -6799543655525726453L;

    /**主键ID*/
    @ApiModelProperty(value = "主键ID")
    private Long dealStoreId;

    /**客户ID*/
    @ApiModelProperty(value = "客户ID")
    private Long dealUserId;

    /**客户名称*/
    @ApiModelProperty(value = "客户名称")
    private String dealUserName;

    /**公司名称*/
    @ApiModelProperty(value = "公司名称")
    private String dealStoreName;

    /**商家门面图URL*/
    @ApiModelProperty(value = "商家门面图URL")
    private String image;

    /**客户职位*/
    @ApiModelProperty(value = "客户职位")
    private String dealUserJob;

    /**归属人ID*/
    @ApiModelProperty(value = "归属人ID")
    private Long sysUserId;

    /**归属人名称*/
    @ApiModelProperty(value = "归属人名称")
    private String sysUserName;

    /**审核状态 0.审核失败 1.审核中 2.审核通过*/
    @ApiModelProperty(value = "审核状态 0.审核失败 1.审核中 2.审核通过")
    private Integer examine;

    /**申请时间*/
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Long examineUserId;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Date examineTime;
}
