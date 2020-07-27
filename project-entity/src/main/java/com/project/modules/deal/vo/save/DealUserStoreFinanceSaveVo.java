package com.project.modules.deal.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业用户金融单表SaveVo
 *
 * @author liangyuding
 * @date 2020-06-04
 */
@Data
@Accessors(chain = true)
public class DealUserStoreFinanceSaveVo implements Serializable {
    private static final long serialVersionUID = -8552272798027765605L;

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
}
