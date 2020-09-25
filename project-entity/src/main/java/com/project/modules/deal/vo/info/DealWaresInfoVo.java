package com.project.modules.deal.vo.info;

import com.project.modules.deal.entity.DealWaresImageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 企业商品InfoVo
 *
 * @author liangyuding
 * @date 2020-06-01
 */
@Data
@Accessors(chain = true)
public class DealWaresInfoVo implements Serializable {
    private static final long serialVersionUID = -3794969928112504286L;

    /**出售商品ID*/
    @ApiModelProperty(value = "出售商品ID")
    private String dealWaresId;

    /**出售商品标题*/
    @ApiModelProperty(value = "出售商品标题")
    private String dealWaresTitle;

    /**出售商品编号*/
    @ApiModelProperty(value = "出售商品编号")
    private String dealWaresNo;

    /**发布区域ID*/
    @ApiModelProperty(value = "发布区域ID")
    private Long releaseAreaId;

    /**发布区域名称*/
    @ApiModelProperty(value = "发布区域名称")
    private String releaseAreaName;

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

    /**所属品牌名称*/
    @ApiModelProperty(value = "所属品牌名称")
    private String couBrandName;

    /**所属系列ID*/
    @ApiModelProperty(value = "所属系列ID")
    private Long couSeriesId;

    /**所属系列名称*/
    @ApiModelProperty(value = "所属系列名称")
    private String couSeriesName;

    /**所属商品型号ID*/
    @ApiModelProperty(value = "所属商品型号ID")
    private Long couModelId;

    /**所属商品型号名称*/
    @ApiModelProperty(value = "所属商品型号名称")
    private String couModelName;

    /**年款*/
    @ApiModelProperty(value = "年款")
    private Integer marketYear;

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

    /**牌照编码*/
    @ApiModelProperty(value = "牌照编码")
    private String licenseCode;

    /**省级区域ID*/
    @ApiModelProperty(value = "省级区域ID")
    private Long proAreaId;

    /**省级区域名称*/
    @ApiModelProperty(value = "省级区域名称")
    private String proAreaName;

    /**市级区域ID*/
    @ApiModelProperty(value = "市级区域ID")
    private Long cityAreaId;

    /**市级区域名称*/
    @ApiModelProperty(value = "市级区域名称")
    private String cityAreaName;

    /**县/区级区域ID*/
    @ApiModelProperty(value = "县/区级区域ID")
    private Long countyAreaId;

    /**县/区级区域名称*/
    @ApiModelProperty(value = "县/区级区域名称")
    private String countyAreaName;

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

    /**出售状态 0.未出售 1.已出售*/
    @ApiModelProperty(value = "出售状态 0.未出售 1.已出售")
    private Integer sellStatus;

    /**上线状态 0.审核失败 1.待审核 2.上架 3.下架*/
    @ApiModelProperty(value = "上线状态 0.审核失败 1.待审核 2.上架 3.下架")
    private Integer onlineStatus;

    /**商品封面图*/
    @ApiModelProperty(value = "商品封面图")
    private DealWaresImageEntity coverImage;

    /**商品评估图集合*/
    @ApiModelProperty(value = "商品图集合")
    private List<DealWaresImageEntity> waresImages;

    /**行驶证图对象*/
    @ApiModelProperty(value = "行驶证图对象")
    private DealWaresImageEntity driveImage;

    /**所属客户企业ID*/
    @ApiModelProperty(value = "所属企业客户ID")
    private Long dealStoreId;

    /**所属企业客户ID*/
    @ApiModelProperty(value = "所属企业客户ID")
    private Long dealUserId;

    /**所属企业客户名称*/
    @ApiModelProperty(value = "所属企业客户名称")
    private String dealUserName;

    /**提交时间*/
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;
}
