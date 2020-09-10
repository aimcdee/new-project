package com.project.modules.deal.vo.save;

import com.project.modules.deal.vo.invoking.DealImageInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 出售商品SaveVo
 *
 * @author liangyuding
 * @date 2020-06-01
 */
@Data
@Accessors(chain = true)
public class DealWaresSaveVo implements Serializable {
    private static final long serialVersionUID = 5477710073750686747L;

    /**发布区域ID*/
    @ApiModelProperty(value = "发布区域ID")
    private Long releaseAreaId;

    /**联系电话*/
    @ApiModelProperty(value = "联系电话")
    private String contactPhone;

    /**联系人名称*/
    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    /**性别 0.男 1.女*/
    @ApiModelProperty(value = "性别 0.男 1.女")
    private Integer sex;

    /**商品框架号*/
    @ApiModelProperty(value = "商品框架号")
    private String waresFrameCode;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    private Long couBrandId;

    /**所属系列ID*/
    @ApiModelProperty(value = "所属系列ID")
    private Long couSeriesId;

    /**所属商品ID*/
    @ApiModelProperty(value = "所属商品ID")
    private Long couWaresId;

    /**所属型号ID*/
    @ApiModelProperty(value = "所属型号ID")
    private Long couModelId;

    /**批发价*/
    @ApiModelProperty(value = "批发价")
    private BigDecimal tradePrice;

    /**零售价*/
    @ApiModelProperty(value = "零售价")
    private BigDecimal retailPrice;

    /**上牌时间*/
    @ApiModelProperty(value = "上牌时间")
    private Date registerTime;

    /**行驶里程*/
    @ApiModelProperty(value = "行驶里程")
    private Long distance;

    /**牌照ID*/
    @ApiModelProperty(value = "牌照ID")
    private Long licenseId;

    /**省级区域ID*/
    @ApiModelProperty(value = "省级区域ID")
    private Long proAreaId;

    /**市级区域ID*/
    @ApiModelProperty(value = "市级区域ID")
    private Long cityAreaId;

    /**县/区级区域ID*/
    @ApiModelProperty(value = "县/区级区域ID")
    private Long countyAreaId;

    /**详细地址*/
    @ApiModelProperty(value = "详细地址")
    private String addr;

    /**商品描述*/
    @ApiModelProperty(value = "商品描述")
    private String waresRemark;

    /**过户次数*/
    @ApiModelProperty(value = "过户次数")
    private Integer transferNum;

    /**是否包含过户费 0.无 1.是*/
    @ApiModelProperty(value = "是否包含过户费 0.无 1.是")
    private Integer isTransfer;

    /**是否有抵押 0.无 1.是*/
    @ApiModelProperty(value = "是否有抵押 0.无 1.是")
    private Integer isMortgage;

    /**是否有定期4s保养 0.无 1.是*/
    @ApiModelProperty(value = "是否有定期4s保养 0.无 1.是")
    private Integer isMaintain;

    /**商品封面图*/
    @ApiModelProperty(value = "商品封面图")
    private DealImageInvokingVo coverImage;

    /**商品图集合*/
    @ApiModelProperty(value = "商品图集合")
    private List<DealImageInvokingVo> waresImages;

    /**行驶证图对象*/
    @ApiModelProperty(value = "行驶证图对象")
    private DealImageInvokingVo driveImage;

    /**所属客户企业ID*/
    @ApiModelProperty(value = "所属企业客户ID")
    private Long dealStoreId;
}
