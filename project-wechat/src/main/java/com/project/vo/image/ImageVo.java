package com.project.vo.image;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 图片对象Vo
 *
 * @author liangyuding
 * @dare 2020-04-20
 */
@Data
@Accessors(chain = true)
public class ImageVo implements Serializable {
    private static final long serialVersionUID = -5579353422193946L;

    /**图片具体路径*/
    @ApiModelProperty(value = "图片具体路径")
    private String img;

    /**图片访问路径*/
    @ApiModelProperty(value = "图片访问路径")
    private String url;

}
