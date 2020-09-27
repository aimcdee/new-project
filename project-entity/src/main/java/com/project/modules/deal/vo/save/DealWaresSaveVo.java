package com.project.modules.deal.vo.save;

import com.project.modules.deal.vo.invoking.DealImageInvokingVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    /**商品名称*/
    @ApiModelProperty(value = "商品名称")
    @NotBlank(message = "请输入商品名称")
    private String dealWaresTitle;

    /**发布区域ID*/
    @ApiModelProperty(value = "发布区域ID")
    @NotNull(message = "请选择发布区域")
    private Long releaseAreaId;

    /**联系电话*/
    @ApiModelProperty(value = "联系电话")
    @NotBlank(message = "请输入联系电话")
    private String contactPhone;

    /**联系人名称*/
    @ApiModelProperty(value = "联系人名称")
    @NotBlank(message = "请输入联系人名称")
    private String contactName;

    /**性别 0.男 1.女*/
    @ApiModelProperty(value = "性别 0.男 1.女")
    @NotNull(message = "请选择性别")
    private Integer sex;

    /**商品框架号*/
    @ApiModelProperty(value = "商品框架号")
    @NotBlank(message = "请输入商品框架号")
    private String waresFrameCode;

    /**所属品牌ID*/
    @ApiModelProperty(value = "所属品牌ID")
    @NotNull(message = "请选择所属品牌")
    private Long couBrandId;

    /**所属系列ID*/
    @ApiModelProperty(value = "所属系列ID")
    @NotNull(message = "请选择所属系列")
    private Long couSeriesId;

    /**所属型号ID*/
    @ApiModelProperty(value = "所属型号ID")
    @NotNull(message = "请选择所属型号")
    private Long couModelId;
    
    /**年款*/
    @ApiModelProperty(value = "年款")
    @NotNull(message = "请输入年款")
    private Integer marketYear;

    /**批发价*/
    @ApiModelProperty(value = "批发价")
    @NotNull(message = "请输入批发价")
    private BigDecimal tradePrice;

    /**零售价*/
    @ApiModelProperty(value = "零售价")
    @NotNull(message = "请输入零售价")
    private BigDecimal retailPrice;

    /**上牌时间*/
    @ApiModelProperty(value = "上牌时间")
    @NotNull(message = "请输入上牌时间")
    private Date registerTime;

    /**行驶里程*/
    @ApiModelProperty(value = "行驶里程")
    @NotNull(message = "请输入行驶里程")
    private Long distance;

    /**牌照编码*/
    @ApiModelProperty(value = "牌照编码")
    @NotBlank(message = "请输入牌照编码")
    private String licenseCode;

    /**省级区域ID*/
    @ApiModelProperty(value = "省级区域ID")
    @NotNull(message = "请选择所在区域省份")
    private Long proAreaId;

    /**市级区域ID*/
    @ApiModelProperty(value = "市级区域ID")
    private Long cityAreaId;

    /**县/区级区域ID*/
    @ApiModelProperty(value = "县/区级区域ID")
    private Long countyAreaId;

    /**详细地址*/
    @ApiModelProperty(value = "详细地址")
    @NotBlank(message = "请输入详细地址")
    private String addr;

    /**商品描述*/
    @ApiModelProperty(value = "商品描述")
    private String waresRemark;

    /**过户次数*/
    @ApiModelProperty(value = "过户次数")
    @NotNull(message = "请输入过户次数")
    private Integer transferNum;

    /**是否包含过户费 0.无 1.是*/
    @ApiModelProperty(value = "是否包含过户费 0.无 1.是")
    @NotNull(message = "请选择是否包含过户费")
    private Integer isTransfer;

    /**是否有抵押 0.无 1.是*/
    @ApiModelProperty(value = "是否有抵押 0.无 1.是")
    @NotNull(message = "请选择是否有抵押")
    private Integer isMortgage;

    /**是否有定期4s保养 0.无 1.是*/
    @ApiModelProperty(value = "是否有定期4s保养 0.无 1.是")
    @NotNull(message = "请选择是否有定期4s保养")
    private Integer isMaintain;

    /**商品封面图*/
    @ApiModelProperty(value = "商品封面图")
    @NotNull(message = "请上传商品封面图")
    private DealImageInvokingVo coverImage;

    /**商品图集合*/
    @ApiModelProperty(value = "商品图集合")
    @NotNull(message = "请上传商品图集合")
    private List<DealImageInvokingVo> waresImages;

    /**行驶证图对象*/
    @ApiModelProperty(value = "行驶证图对象")
    @NotNull(message = "请上传行驶证图")
    private DealImageInvokingVo driveImage;

    /**所属客户企业ID*/
    @ApiModelProperty(value = "所属企业客户ID")
    private Long dealStoreId;
}
