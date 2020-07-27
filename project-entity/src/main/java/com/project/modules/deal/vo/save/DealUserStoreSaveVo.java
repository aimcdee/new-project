package com.project.modules.deal.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 客户SaveVo
 *
 * @author liangyuding
 * @date 2020-04-09
 */
@Data
@Accessors(chain = true)
public class DealUserStoreSaveVo implements Serializable {
    private static final long serialVersionUID = 2516910683247645961L;

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

    /**归属人ID*/
    @ApiModelProperty(value = "归属人ID")
    private Long sysUserId;
}
