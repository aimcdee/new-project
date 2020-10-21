package com.project.modules.deal.dao;

import com.project.modules.cou.vo.Invoking.CouWaresNameAndYearInvokingVo;
import com.project.modules.deal.entity.DealUserStoreEntity;
import com.project.modules.deal.vo.invoking.DealStoreUserInvokingVo;
import com.project.modules.deal.vo.invoking.DealUserStoreCheckInvokingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 成交模块中间调用Dao
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Mapper
public interface DealInvokingDao {

    /**
     * 校验客户状态是否为正常
     * @param dealUserId
     * @param status
     * @return
     */
    Integer checkDealUserStatus(@Param("dealUserId") Long dealUserId, @Param("status") Integer status);

    /**
     * 根据归属人ID获取归属人名称
     * @param sysUserId
     * @return
     */
    String getSysUserNameBySysUserId(@Param("sysUserId") Long sysUserId);

    /**
     * 修改客户类型
     * @param dealUserId
     * @param type
     */
    void updateDealUserType(@Param("dealUserId") Long dealUserId, @Param("type") Integer type);

    /**
     * 根据区域ID获取区域名称
     * @param areaId
     * @return
     */
    String getAreaNameById(@Param("areaId") Long areaId);

    /**
     * 根据评估ID和交易状态查询商品的ID和名称
     * @param dealAssessId
     * @param status
     * @return
     */
    Long getAssessWares(@Param("dealAssessId") Long dealAssessId, @Param("status") Integer status);

    /**
     * 根据评估ID获取评估商品归属客户ID
     * @param dealAssessId
     * @param status
     * @return
     */
    Long getDealUserIdByDealAssessId(@Param("dealAssessId") Long dealAssessId, @Param("status") Integer status);

    /**
     * 根据品牌ID获取品牌名称
     * @param couBrandId
     * @return
     */
    String getCouBrandNameById(@Param("couBrandId") Long couBrandId);

    /**
     * 根据系列ID获取系列名称
     * @param couSeriesId
     * @return
     */
    String getCouSeriesNameById(@Param("couSeriesId") Long couSeriesId);

    /**
     * 根据商品ID获取商品名称
     * @param couWaresId
     * @return
     */
    String getCouWaresNameById(@Param("couWaresId") Long couWaresId);

    /**
     * 根据商品ID获取商品名称
     * @param couWaresId
     * @return
     */
    CouWaresNameAndYearInvokingVo getCouWaresNameAndYearById(@Param("couWaresId") Long couWaresId);

    /**
     * 根据商品型号ID获取商品型号名称
     * @param couModelId
     * @return
     */
    String getCouModelNameById(@Param("couModelId") Long couModelId);

    /**
     * 根据客户企业ID查询客户企业详情
     * @param dealStoreId
     * @return
     */
    DealUserStoreEntity getDealUserStoreEntity(@Param("dealStoreId") Long dealStoreId);

    /**
     * 根据客户ID获取客户名称
     * @param dealUserId
     * @return
     */
    String getDealUserNameById(@Param("dealUserId") Long dealUserId);

    /**
     * 更新客户企业详情中的保证金总金额和信用等级
     * @param dealStoreId
     * @param depositPrice
     * @param creditGrade
     */
    void updateDealUserStoreEntity(
            @Param("dealStoreId") Long dealStoreId,
            @Param("depositPrice") BigDecimal depositPrice,
            @Param("creditGrade") Integer creditGrade);

    /**
     * 根据客户企业表ID查询客户是否是企业客户
     * @param dealStoreId
     * @param type
     * @return
     */
    Integer checkUserStore(@Param("dealStoreId") Long dealStoreId, @Param("type") Integer type);

    /**
     * 根据客户企业表ID查询该条企业认证信息是否已通过认证
     * @param dealStoreId
     * @param examine
     * @return
     */
    Integer checkStoreStatus(@Param("dealStoreId") Long dealStoreId, @Param("examine") Integer examine);

    /**
     * 根据企业认证ID获取客户ID
     * @param dealStoreId
     * @return
     */
    Long getDealUserIdByStoreId(@Param("dealStoreId") Long dealStoreId);

    /**
     * 修改企业认证为作废
     * @param dealStoreId
     * @param depositPrice
     * @param examine
     */
    void updateDealUserStoreExamine(@Param("dealStoreId") Long dealStoreId, @Param("depositPrice") BigDecimal depositPrice, @Param("examine") Integer examine);

    /**
     * 更新个人评估商品交易状态
     * @param dealAssessId
     * @param status
     */
    void updateAssessSellStstus(@Param("dealAssessId") Long dealAssessId, @Param("status") Integer status);

    /**
     * 根据牌照ID获取牌照编码
     * @param licenseId
     * @return
     */
    String getLicenseCodeById(@Param("licenseId") Long licenseId);

//    /**
//     * 根据客户企业ID获取客户名称
//     * @param dealStoreId
//     * @return
//     */
//    String getDealUserNameByStoreId(@Param("dealStoreId") Long dealStoreId);

    /**
     * 根据客户企业ID获取客户名称和ID
     * @param dealStoreId
     * @return
     */
    DealStoreUserInvokingVo getDealStoreUserInvokingVoByStoreId(@Param("dealStoreId") Long dealStoreId);

    /**
     * 获取出售商品标题
     * @param dealWaresId
     * @return
     */
    String getDealWaresTitleById(@Param("dealWaresId") String dealWaresId);

    /**
     * 获取企业认证校验对象
     * @param dealStoreId
     * @param examine
     * @return
     */
    DealUserStoreCheckInvokingVo getDealUserStoreRefundCheckInvokingVo(@Param("dealStoreId") Long dealStoreId, @Param("examine") Integer examine);

    /**
     * 根据用户ID和企业认证状态获取客户企业ID
     * @param dealUserId
     * @param examine
     * @return
     */
    Long getDealStoreId(@Param("dealUserId") Long dealUserId, @Param("examine") Integer examine);

    /**
     * 根据评估ID和出售状态获取评估名称
     * @param dealAssessId
     * @param sellStatus
     * @return
     */
    String getAssessWaresTitle(@Param("dealAssessId") Long dealAssessId, @Param("sellStatus") Integer sellStatus);

    /**
     * 根据企业ID和商品ID查询是否存在该条商品
     * @param dealStoreId
     * @param dealWareId
     * @return
     */
    Integer checkWaresStore(@Param("dealStoreId") Long dealStoreId, @Param("dealWareId") String dealWareId);
}
