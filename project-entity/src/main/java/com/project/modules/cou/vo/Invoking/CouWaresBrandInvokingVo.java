package com.project.modules.cou.vo.Invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品品牌ListVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresBrandInvokingVo implements Serializable {
    private static final long serialVersionUID = -3815097533242803280L;

    /**品牌ID*/
    @ApiModelProperty(value = "品牌ID")
    private Long couBrandId;

    /**品牌名称*/
    @ApiModelProperty(value = "品牌名称")
    private String couBrandName;
}
