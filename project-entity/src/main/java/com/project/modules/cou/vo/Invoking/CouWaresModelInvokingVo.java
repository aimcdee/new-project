package com.project.modules.cou.vo.Invoking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 型号ListVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresModelInvokingVo implements Serializable {
    private static final long serialVersionUID = 5318567593668803794L;

    /**型号ID*/
    @ApiModelProperty(value = "型号ID")
    private Long couModelId;

    /**型号名称*/
    @ApiModelProperty(value = "型号名称")
    private String couModelName;

    /**图片路径*/
    @ApiModelProperty(value = "图片路径")
    private String image;

    /**上级型号ID*/
    @ApiModelProperty(value = "上级型号ID")
    private Long parentId;

    /**上级型号名称*/
    @ApiModelProperty(value = "上级型号名称")
    private String parentName;

}
