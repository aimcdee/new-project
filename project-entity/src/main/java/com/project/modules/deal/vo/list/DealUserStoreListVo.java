package com.project.modules.deal.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户企业验证ListVo
 *
 * @author liangyuding
 * @date 2020-04-09
 */
@Data
@Accessors(chain = true)
public class DealUserStoreListVo implements Serializable {
    private static final long serialVersionUID = 2516910683247645961L;

    /**主键ID*/
    @ApiModelProperty(value = "主键ID")
    private Long dealStoreId;

    /**公司名称*/
    @ApiModelProperty(value = "公司名称")
    private String dealStoreName;

    /**客户职位*/
    @ApiModelProperty(value = "客户职位")
    private String dealUserJob;

    /**商家门面图URL*/
    @ApiModelProperty(value = "企业门面图URL")
    private String image;

    /**审核状态 0.审核失败 1.审核中 2.审核通过*/
    @ApiModelProperty(value = "审核状态 0.审核失败 1.审核中 2.审核通过")
    private Integer examine;

    /**归属人ID*/
    @ApiModelProperty(value = "归属人ID")
    private Long sysUserId;

    /**归属人名称*/
    @ApiModelProperty(value = "归属人名称")
    private String sysUserName;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Long examineUserId;

    /**审核时间*/
    @ApiModelProperty(value = "审核时间")
    private Date examineTime;

}
