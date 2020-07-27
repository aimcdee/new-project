package com.project.modules.cou.vo.Invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品调用Vo
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Data
@Accessors(chain = true)
public class CouWaresNameAndYearInvokingVo implements Serializable {
    private static final long serialVersionUID = 5203919570718070365L;

    /**商品名称*/
    @ApiModelProperty(value = "商品名称")
    private String couWaresName;

    /**年款*/
    @ApiModelProperty(value = "年款")
    private Long marketYear;

}
