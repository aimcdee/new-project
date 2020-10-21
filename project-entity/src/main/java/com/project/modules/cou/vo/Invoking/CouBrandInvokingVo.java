package com.project.modules.cou.vo.Invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 品牌ListVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouBrandInvokingVo implements Serializable {
    private static final long serialVersionUID = 3611349896735322789L;

    /**首字母*/
    @ApiModelProperty(value = "首字母")
    private String firstLetter;

    /**品牌对象集合*/
    @ApiModelProperty(value = "品牌对象集合")
    List<CouWaresBrandInvokingVo> brandVoList;
}
