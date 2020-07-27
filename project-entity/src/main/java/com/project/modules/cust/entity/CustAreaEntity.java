package com.project.modules.cust.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 区域Entity
 *
 * @author liangyuding
 * @date 2020-03-26
 */
@Data
@Accessors(chain = true)
@TableName("cust_area")
public class CustAreaEntity implements Serializable {
    private static final long serialVersionUID = 1468649235869866129L;

    /**区域ID*/
    @TableId
    @ApiModelProperty(value = "区域ID")
    private Long areaId;

    /**区域名称*/
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**所属上级区域ID*/
    @ApiModelProperty(value = "所属上级区域ID")
    private Long parentId;

    /**区域类型 0.省级 1.市级 2.县/区*/
    @ApiModelProperty(value = "区域类型 0.省级 1.市级 2.县/区")
    private Integer type;
}
