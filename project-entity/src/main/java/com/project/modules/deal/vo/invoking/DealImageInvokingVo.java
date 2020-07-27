package com.project.modules.deal.vo.invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 图片InvokingVo
 *
 * @author liangyuding
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
public class DealImageInvokingVo implements Serializable {
    private static final long serialVersionUID = 5987559111601034345L;

    /**图片路径*/
    @ApiModelProperty(value = "图片路径")
    private String image;
}
