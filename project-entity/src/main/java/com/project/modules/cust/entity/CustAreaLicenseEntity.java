package com.project.modules.cust.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品所在地区牌号表Entity
 *
 * @author liangyuding
 * @date 2020-06-03
 */
@Data
@Accessors(chain = true)
@TableName("cust_area_license")
public class CustAreaLicenseEntity implements Serializable {
    private static final long serialVersionUID = -7559855327190612342L;

    /**所在区域牌号表ID*/
    @TableId
    @ApiModelProperty(value = "所在区域牌号表ID")
    private Long licenseId;

    /**所在区域牌号*/
    @ApiModelProperty(value = "所在区域牌号")
    private String licenseCode;

    /**所属区域ID*/
    @ApiModelProperty(value = "所属区域ID")
    private Long areaId;
}
