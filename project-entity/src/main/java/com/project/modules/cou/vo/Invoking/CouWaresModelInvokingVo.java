package com.project.modules.cou.vo.Invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品型号ListVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresModelInvokingVo implements Serializable {
    private static final long serialVersionUID = 5318567593668803794L;

    /**商品型号ID*/
    @ApiModelProperty(value = "商品型号ID")
    private Long couModelId;

    /**商品型号名称*/
    @ApiModelProperty(value = "商品型号名称")
    private String couModelName;



}
