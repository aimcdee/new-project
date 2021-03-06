package com.project.modules.cou.vo.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 型号SaveVo
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Data
@Accessors(chain = true)
public class CouWaresModelSaveVo implements Serializable {
    private static final long serialVersionUID = 954001821325893782L;

    /**型号名称*/
    @NotBlank(message = "请输入型号名称")
    @ApiModelProperty(value = "型号名称")
    private String couModelName;

    /**型号图片路径*/
    @ApiModelProperty(value = "型号图片路径")
    private String image;
}
