package com.project.modules.deal.service;

import com.project.modules.cou.vo.Invoking.CouWaresNameAndYearInvokingVo;
import com.project.modules.deal.vo.invoking.DealStoreUserInvokingVo;
import com.project.modules.deal.vo.invoking.DealUserStoreCheckInvokingVo;
import com.project.modules.deal.vo.login.DealUserLoginVo;

import java.math.BigDecimal;

/**
 * 成交模块中间调用Service
 *
 * @author liangyuding
 * @date 2020-05-14
 */
public interface DealInvokingService {

    /**
     * 更新Redis上的登录信息
     * @param dealUserLoginVo
     */
    void saveRedis(DealUserLoginVo dealUserLoginVo);

    /**
     * 更新Redis上的登录信息
     * @param wxDealIdKey
     * @param dealUserLoginVo
     */
    void deleteOldUserInfo(String wxDealIdKey, DealUserLoginVo dealUserLoginVo);

    /**
     * 校验客户状态是否为正常
     * @param dealUserId
     * @param status
     * @return
     */
    Integer checkDealUserStatus(Long dealUserId, Integer status);

    /**
     * 根据归属人ID获取归属人名称
     * @param sysUserId
     * @return
     */
    String getSysUserNameBySysUserId(Long sysUserId);

    /**
     * 修改客户类型
     * @param dealUserId
     * @param type
     */
    void updateDealUserType(Long dealUserId, Integer type);

    /**
     * 根据区域ID获取区域名称
     * @param areaId
     * @return
     */
    String getAreaNameById(Long areaId);

    /**
     * 根据评估ID和交易状态查询商品的ID和名称
     * @param dealAssessId
     * @param status
     * @return
     */
    Long getAssessWares(Long dealAssessId, Integer status);

    /**
     * 根据评估ID获取评估商品归属客户ID
     * @param dealAssessId
     * @param status
     * @return
     */
    Long getDealUserIdByDealAssessId(Long dealAssessId, Integer status);

    /**
     * 根据品牌ID获取品牌名称
     * @param couBrandId
     * @return
     */
    String getCouBrandNameById(Long couBrandId);

    /**
     * 根据品牌系列ID获取品牌系列名称
     * @param couSeriesId
     * @return
     */
    String getCouSeriesNameById(Long couSeriesId);

    /**
     * 根据商品ID获取商品名称
     * @param couWaresId
     * @return
     */
    String getCouWaresNameById(Long couWaresId);

    /**
     * 根据商品ID获取商品名称和年款
     * @param couWaresId
     * @return
     */
    CouWaresNameAndYearInvokingVo getCouWaresNameAndYearById(Long couWaresId);

    /**
     * 根据商品型号ID获取商品型号名称
     * @param couModelId
     * @return
     */
    String getCouModelNameById(Long couModelId);

    /**
     * 更新个人评估商品交易状态
     * @param dealAssessId
     * @param status
     */
    void updateAssessSellStstus(Long dealAssessId, Integer status);

    /**
     * 修改企业客户表的保证金总金额和信用等级
     * @param dealStoreId
     * @param depositPrice
     */
    void changeUserPrice(Long dealStoreId, BigDecimal depositPrice);

    /**
     * 根据客户ID获取客户名称
     * @param dealUserId
     * @return
     */
    String getDealUserNameById(Long dealUserId);

    /**
     * 根据客户企业表ID查询客户是否是企业客户
     * @param dealStoreId
     * @param type
     * @return
     */
    Integer checkUserStore(Long dealStoreId, Integer type);

    /**
     * 根据客户企业表ID查询该条企业认证信息是否已通过认证
     * @param dealStoreId
     * @param examine
     * @return
     */
    Integer checkStoreStatus(Long dealStoreId, Integer examine);

    /**
     * 修改客户类型成个人和企业认证为作废
     * @param dealStoreId
     */
    void changeUserStore(Long dealStoreId);

    /**
     * 根据牌照ID获取牌照编码
     * @param licenseId
     * @return
     */
    String getLicenseCodeById(Long licenseId);

//    /**
//     * 根据客户企业ID获取客户名称
//     * @param dealStoreId
//     * @return
//     */
//    String getDealUserNameByStoreId(Long dealStoreId);

    /**
     * 根据客户企业ID获取客户名称和ID
     * @param dealStoreId
     * @return
     */
    DealStoreUserInvokingVo getDealStoreUserInvokingVoByStoreId(Long dealStoreId);

    /**
     * 获取出售商品标题
     * @param dealWaresId
     * @return
     */
    String getDealWaresTitleById(String dealWaresId);

    /**
     * 获取企业认证校验对象
     * @param dealStoreId
     * @param examine
     * @return
     */
    DealUserStoreCheckInvokingVo getDealUserStoreRefundCheckInvokingVo(Long dealStoreId, Integer examine);

    /**
     * 根据用户ID和企业认证状态获取客户企业ID
     * @param dealUserId
     * @param examine
     * @return
     */
    Long getDealStoreId(Long dealUserId, Integer examine);

    /**
     * 根据评估ID和出售状态获取评估名称
     * @param dealAssessId
     * @param sellStatus
     * @return
     */
    String getAssessWaresTitle(Long dealAssessId, Integer sellStatus);

    /**
     * 根据企业ID和商品ID查询是否存在该条商品
     * @param dealStoreId
     * @param dealWareId
     * @return
     */
    Integer checkWaresStore(Long dealStoreId, String dealWareId);
}
