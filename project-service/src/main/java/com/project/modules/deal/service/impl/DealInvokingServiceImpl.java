package com.project.modules.deal.service.impl;

import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.exception.RRException;
import com.project.modules.cou.vo.Invoking.CouWaresNameAndYearInvokingVo;
import com.project.modules.deal.dao.DealInvokingDao;
import com.project.modules.deal.entity.DealUserStoreEntity;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.vo.invoking.DealStoreUserInvokingVo;
import com.project.modules.deal.vo.invoking.DealUserStoreCheckInvokingVo;
import com.project.modules.deal.vo.login.DealUserLoginVo;
import com.project.utils.CheckUtils;
import com.project.utils.JsonUtil;
import com.project.utils.RedisKeys;
import com.project.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 成交模块中间调用Service
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Slf4j
@Service("dealInvokingService")
public class DealInvokingServiceImpl implements DealInvokingService {

    @Autowired
    private DealInvokingDao dealInvokingDao;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 更新Redis上的登录信息
     * @param dealUserLoginVo
     */
    @Override
    public void saveRedis(DealUserLoginVo dealUserLoginVo) {
        //以token获取redisKey
        String wxDealIdKey = RedisKeys.Wx.WxLogin(new StringBuilder().append(RedisKeyConstant.WX_LOGIN).append("_").append(dealUserLoginVo.getDealUserId()).toString());
        //设置到DealUserLoginVo对象到redis上
        redisUtils.set(wxDealIdKey, JsonUtil.toJson(dealUserLoginVo), dealUserLoginVo.getExpireTime().getTime());
        //以token获取redisKey
        String wxTokenKey = RedisKeys.Wx.WxLoginToken(dealUserLoginVo.getToken());
        //设置userId到redis上
        redisUtils.set(wxTokenKey, dealUserLoginVo.getDealUserId(), dealUserLoginVo.getExpireTime().getTime());
    }


    /**
     * 删除redis原先的数据
     * @param wxDealIdKey
     * @param dealUserLoginVo
     */
    @Override
    public void deleteOldUserInfo(String wxDealIdKey, DealUserLoginVo dealUserLoginVo) {
        //删除缓存在redis上的UserId
        redisUtils.delete(RedisKeys.Wx.WxLoginToken(dealUserLoginVo.getToken()));
        //删除以前用户登录信息
        redisUtils.delete(wxDealIdKey);
    }

    /**
     * 校验客户状态是否为正常
     * @param dealUserId
     * @param status
     * @return
     */
    @Override
    public Integer checkDealUserStatus(Long dealUserId, Integer status) {
        return dealInvokingDao.checkDealUserStatus(dealUserId, status);
    }

    /**
     * 根据归属人ID获取归属人名称
     * @param sysUserId
     * @return
     */
    @Override
    public String getSysUserNameBySysUserId(Long sysUserId) {
        return dealInvokingDao.getSysUserNameBySysUserId(sysUserId);
    }

    /**
     * 修改客户类型
     * @param dealUserId
     * @param type
     */
    @Override
    @Transactional
    public void updateDealUserType(Long dealUserId, Integer type) {
        dealInvokingDao.updateDealUserType(dealUserId, type);
    }

    /**
     * 根据区域ID获取区域名称
     * @param areaId
     * @return
     */
    @Override
    public String getAreaNameById(Long areaId) {
        return dealInvokingDao.getAreaNameById(areaId);
    }

    /**
     * 根据评估ID和交易状态查询商品的ID和名称
     * @param dealAssessId
     * @param status
     * @return
     */
    public Long getAssessWares(Long dealAssessId, Integer status){
        return dealInvokingDao.getAssessWares(dealAssessId, status);
    }

    /**
     * 根据评估ID获取评估商品归属客户ID
     * @param dealAssessId
     * @param status
     * @return
     */
    @Override
    public Long getDealUserIdByDealAssessId(Long dealAssessId, Integer status) {
        return dealInvokingDao.getDealUserIdByDealAssessId(dealAssessId, status);
    }

    /**
     * 根据品牌ID获取品牌名称
     * @param couBrandId
     * @return
     */
    @Override
    public String getCouBrandNameById(Long couBrandId) {
        return dealInvokingDao.getCouBrandNameById(couBrandId);
    }

    /**
     * 根据品牌系列ID获取品牌系列名称
     * @param couSeriesId
     * @return
     */
    @Override
    public String getCouSeriesNameById(Long couSeriesId) {
        return dealInvokingDao.getCouSeriesNameById(couSeriesId);
    }

    /**
     * 根据商品ID获取商品名称
     * @param couWaresId
     * @return
     */
    @Override
    public String getCouWaresNameById(Long couWaresId) {
        return dealInvokingDao.getCouWaresNameById(couWaresId);
    }

    /**
     * 根据商品ID获取商品名称和年款
     * @param couWaresId
     * @return
     */
    @Override
    public CouWaresNameAndYearInvokingVo getCouWaresNameAndYearById(Long couWaresId) {
        return dealInvokingDao.getCouWaresNameAndYearById(couWaresId);
    }

    /**
     * 根据商品型号ID获取商品型号名称
     * @param couModelId
     * @return
     */
    @Override
    public String getCouModelNameById(Long couModelId) {
        return dealInvokingDao.getCouModelNameById(couModelId);
    }

    /**
     * 更新个人评估商品交易状态
     * @param dealAssessId
     * @param status
     */
    @Override
    @Transactional
    public void updateAssessSellStstus(Long dealAssessId, Integer status) {
        dealInvokingDao.updateAssessSellStstus(dealAssessId, status);
    }

    /**
     * 根据客户ID获取客户名称
     * @param dealUserId
     * @return
     */
    @Override
    public String getDealUserNameById(Long dealUserId) {
        return dealInvokingDao.getDealUserNameById(dealUserId);
    }

    /**
     * 根据客户企业表ID查询客户是否是企业客户
     * @param dealStoreId
     * @param type
     * @return
     */
    @Override
    public Integer checkUserStore(Long dealStoreId, Integer type) {
        return dealInvokingDao.checkUserStore(dealStoreId, type);
    }

    /**
     * 根据客户企业表ID查询该条企业认证信息是否已通过认证
     * @param dealStoreId
     * @param examine
     * @return
     */
    @Override
    public Integer checkStoreStatus(Long dealStoreId, Integer examine) {
        return dealInvokingDao.checkStoreStatus(dealStoreId, examine);
    }

    /**
     * 修改客户类型成个人和企业认证为作废
     * @param dealStoreId
     */
    @Override
    @Transactional
    public void changeUserStore(Long dealStoreId) {
        Long dealUserId = dealInvokingDao.getDealUserIdByStoreId(dealStoreId);
        if (Objects.isNull(dealUserId) || dealUserId == 0){
            throw new RRException("操作失败,该客户不存在");
        }
        //修改客户类型成个人
        dealInvokingDao.updateDealUserType(dealUserId, Constant.StoreType.INDIVIDUAL.getType());
        //修改企业认证为作废
        dealInvokingDao.updateDealUserStoreExamine(dealStoreId, new BigDecimal(0), Constant.Examine.WASTE.getExamine());
    }

    /**
     * 根据牌照ID获取牌照编码
     * @param licenseId
     * @return
     */
    @Override
    public String getLicenseCodeById(Long licenseId) {
        return dealInvokingDao.getLicenseCodeById(licenseId);
    }

//    /**
//     * 根据客户企业ID获取客户名称
//     * @param dealStoreId
//     * @return
//     */
//    @Override
//    public String getDealUserNameByStoreId(Long dealStoreId) {
//        return dealInvokingDao.getDealUserNameByStoreId(dealStoreId);
//    }

    /**
     * 根据客户企业ID获取客户名称和ID
     * @param dealStoreId
     * @return
     */
    @Override
    public DealStoreUserInvokingVo getDealStoreUserInvokingVoByStoreId(Long dealStoreId) {
        return dealInvokingDao.getDealStoreUserInvokingVoByStoreId(dealStoreId);
    }

    /**
     * 获取出售商品标题
     * @param dealWaresId
     * @return
     */
    @Override
    public String getDealWaresTitleById(String dealWaresId) {
        return dealInvokingDao.getDealWaresTitleById(dealWaresId);
    }

    /**
     * 获取企业认证校验对象
     * @param dealStoreId
     * @param examine
     * @return
     */
    @Override
    public DealUserStoreCheckInvokingVo getDealUserStoreRefundCheckInvokingVo(Long dealStoreId, Integer examine) {
        return dealInvokingDao.getDealUserStoreRefundCheckInvokingVo(dealStoreId, examine);
    }

    /**
     * 根据用户ID和企业认证状态获取客户企业ID
     * @param dealUserId
     * @param examine
     * @return
     */
    @Override
    public Long getDealStoreId(Long dealUserId, Integer examine) {
        return dealInvokingDao.getDealStoreId(dealUserId, examine);
    }

    /**
     * 修改企业客户表的保证金总金额和信用等级
     * @param storeId
     * @param depositPrice
     */
    @Override
    @Transactional
    public void changeUserPrice(Long storeId, BigDecimal depositPrice) {
        DealUserStoreEntity dealUserStoreEntity = dealInvokingDao.getDealUserStoreEntity(storeId);
        checkUtils.checkEntityNotNull(dealUserStoreEntity);
        BigDecimal price = dealUserStoreEntity.getDepositPrice().add(depositPrice);
        if (price.compareTo(new BigDecimal(0)) == -1){
            throw new RRException("操作失败,客户保证金的总金额不能小于0");
        }
        Integer creditGrade = price.divide(new BigDecimal(1000)).setScale(0, BigDecimal.ROUND_DOWN).intValue();
        dealInvokingDao.updateDealUserStoreEntity(dealUserStoreEntity.getDealStoreId(), price, creditGrade);
    }
}
