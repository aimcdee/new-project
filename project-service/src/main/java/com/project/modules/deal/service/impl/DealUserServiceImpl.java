package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.exception.RRException;
import com.project.modules.deal.dao.DealUserDao;
import com.project.modules.deal.entity.DealUserEntity;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.service.DealUserService;
import com.project.modules.deal.service.DealUserStoreRefundService;
import com.project.modules.deal.service.DealUserStoreService;
import com.project.modules.deal.vo.info.DealUserInfoVo;
import com.project.modules.deal.vo.invoking.DealUserInvokingVo;
import com.project.modules.deal.vo.invoking.DealUserStoreCheckInvokingVo;
import com.project.modules.deal.vo.invoking.DealUserStoreInvokingVo;
import com.project.modules.deal.vo.list.DealUserListVo;
import com.project.modules.deal.vo.login.DealUserLoginVo;
import com.project.modules.deal.vo.save.DealUserSaveVo;
import com.project.modules.deal.vo.save.DealUserStoreRefundSaveVo;
import com.project.modules.deal.vo.update.DealUserUpdateVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import static com.project.utils.ShiroUtils.getSysUserId;

/**
 * 客户Service
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Slf4j
@Service("dealUserService")
public class DealUserServiceImpl extends ServiceImpl<DealUserDao, DealUserEntity> implements DealUserService {

    @Autowired
    private DealUserStoreRefundService dealUserStoreRefundService;
    @Autowired
    private DealUserStoreService dealUserStoreService;
    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;

    /**
     * 分页查询客户列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealUserListVo> page = new Query<DealUserListVo>(params).getPage();
        List<DealUserListVo> dealUserListVos = baseMapper.queryPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "dealUserName")),
                StringUtils.trim(MapUtils.getString(params, "phone")),
                MapUtils.getInteger(params, "type"),
                MapUtils.getInteger(params, "status"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        dealUserListVos.forEach(dealUserListVo -> {
            DealUserStoreInvokingVo dealUserStoreInvokingVo = dealUserListVo.getType().equals(Constant.StoreType.ENTERPRISE.getType()) ? getDealUserStoreInvokingVo(dealUserListVo.getDealUserId()) : new DealUserStoreInvokingVo();
            dealUserListVo
                    //设置信用等级
                    .setCreditGrade(Optional.ofNullable(dealUserStoreInvokingVo).map(DealUserStoreInvokingVo::getCreditGrade).orElse(Constant.CREDITGRADE))
                    //设置保证金金额
                    .setDepositPrice(Optional.ofNullable(dealUserStoreInvokingVo).map(DealUserStoreInvokingVo::getDepositPrice).orElse(new BigDecimal(0)))
                    //设置企业ID
                    .setDealStoreId(Optional.ofNullable(dealUserStoreInvokingVo).map(DealUserStoreInvokingVo::getDealStoreId).orElse(Constant.DEFAUL_ID))
                    //设置企业名称
                    .setDealStoreName(Optional.ofNullable(dealUserStoreInvokingVo).map(DealUserStoreInvokingVo::getDealStoreName).orElse(Constant.DEFAUL_NAME))
                    //设置归属人名称
                    .setSysUserName(Optional.ofNullable(dealUserStoreInvokingVo).map(DealUserStoreInvokingVo::getSysUserName).orElse(Constant.DEFAUL_NAME));
        });
        return new PageUtils(page.setRecords(dealUserListVos));
    }

    /**
     * 新增客户
     * @param user
     */
    @Override
    @Transactional
    public String saveEntity(DealUserSaveVo user) {
        try {
            trimUtils.beanValueTrim(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DealUserEntity dealUserEntity = getSaveDealUserEntity(user);
        //新增客户
        save(dealUserEntity);
        //创建token
        String token = TokenGeneratorUtils.generateValue();
        // 缓存token
        DealUserLoginVo dealUserLoginVo = getDealUserLoginVo(token, dealUserEntity);
        dealInvokingService.saveRedis(dealUserLoginVo);
        //创建文件夹
        mkdirFolder(user.getPhone());
        return token;
    }

    /**
     * 根据客户ID获取客户详情
     * @param userId
     * @return
     */
    @Override
    public DealUserInfoVo info(Long userId) {
        DealUserInfoVo dealUserInfoVo = baseMapper.getDealUserInfoVo(userId);
        if (Objects.nonNull(dealUserInfoVo)){
            DealUserStoreInvokingVo dealUserStoreInvokingVo = dealUserInfoVo.getType().equals(Constant.StoreType.ENTERPRISE.getType()) ? getDealUserStoreInvokingVo(dealUserInfoVo.getDealUserId()) : new DealUserStoreInvokingVo();
            dealUserInfoVo
                    //设置信用等级
                    .setCreditGrade(Optional.ofNullable(dealUserStoreInvokingVo.getCreditGrade()).orElse(Constant.CREDITGRADE))
                    //设置保证金金额
                    .setDepositPrice(Optional.ofNullable(dealUserStoreInvokingVo.getDepositPrice()).orElse(new BigDecimal(0)))
                    //设置企业ID
                    .setDealStoreId(Optional.ofNullable(dealUserStoreInvokingVo).map(DealUserStoreInvokingVo::getDealStoreId).orElse(Constant.DEFAUL_ID))
                    //设置企业名称
                    .setDealStoreName(Optional.ofNullable(dealUserStoreInvokingVo.getDealStoreName()).orElse(Constant.DEFAUL_NAME))
                    //设置门面图
                    .setImage(dealUserStoreInvokingVo.getImage())
                    //设置归属人名称
                    .setSysUserName(Optional.ofNullable(dealUserStoreInvokingVo.getSysUserName()).orElse(Constant.DEFAUL_NAME));
        }
        return dealUserInfoVo;
    }

    /**
     * 更新客户
     * @param user
     */
    @Override
    @Transactional
    public void updateEntity(DealUserUpdateVo user) {
        try {
            trimUtils.beanValueTrim(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取DealUserEntity更新对象
        DealUserEntity dealUserEntity = getUpdateDealUserEntity(user);
        updateById(dealUserEntity);
        //更新新的登录信息
        dealInvokingService.saveRedis(getUpdateDealUserLoginVo(dealUserEntity));
    }

    /**
     * 修改客户的状态
     * @param dealUserId
     * @param status
     */
    @Override
    @Transactional
    public void changeStatus(Long dealUserId, Integer status) {
        DealUserEntity dealUserEntity = getOne(new QueryWrapper<DealUserEntity>().eq("deal_user_id", dealUserId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(dealUserEntity);
        dealUserEntity.setStatus(status).setUpdateTime(new Date());
        updateById(dealUserEntity);
        //如果修改的状态为禁用
        if (status.equals(Constant.Status.DISABLE.getStatus())){
            //如果该客户是企业用户则修改企业认证
            if (dealUserEntity.getType().equals(Constant.StoreType.ENTERPRISE.getType())){
                dealUserStoreService.changeUserStore(dealUserId, getSysUserId());
            }
            //旧的登录信息
            String wxDealIdKey = RedisKeys.Wx.WxLogin(new StringBuilder().append(RedisKeyConstant.WX_LOGIN).append("_").append(dealUserEntity.getDealUserId()).toString());
            DealUserLoginVo dealUserLoginVo = redisUtils.get(wxDealIdKey, DealUserLoginVo.class);
            //删除redis上缓存
            if (Objects.nonNull(dealUserLoginVo)){
                dealInvokingService.deleteOldUserInfo(wxDealIdKey, dealUserLoginVo);
            }
        }
    }

    /**
     * 获取所有客户
     * @return
     */
    @Override
    public List<DealUserInvokingVo> getDealUserList() {
        List<DealUserInvokingVo> dealUserInvokingVos = baseMapper.getDealUserList(Constant.Status.NORMAL.getStatus());
        if (CollectionUtils.isNotEmpty(dealUserInvokingVos)){
            dealUserInvokingVos.forEach(dealUserInvokingVo -> {
                dealUserInvokingVo.setDealStoreId(dealInvokingService.getDealStoreId(dealUserInvokingVo.getDealUserId(), Constant.Examine.SUCCESS.getExamine()));
            });
        }
        return dealUserInvokingVos;
    }

    /**
     * 获取企业用户ID集合
     * @return
     */
    @Override
    public List<DealUserInvokingVo> getStoreUserList() {
        return baseMapper.getStoreUserList(Constant.StoreType.ENTERPRISE.getType(), Constant.Examine.SUCCESS.getExamine());
    }

    /**
     * 客户提现
     * @param dealStoreId
     */
    @Override
    @Transactional
    public void cashOut(Long dealStoreId) {
        DealUserStoreCheckInvokingVo storeCheck = dealInvokingService.getDealUserStoreRefundCheckInvokingVo(dealStoreId, Constant.Examine.SUCCESS.getExamine());
        checkBefore(storeCheck);
        DealUserStoreRefundSaveVo refund = new DealUserStoreRefundSaveVo();
        refund.setDealStoreId(dealStoreId).setRefundPrice(storeCheck.getDepositPrice()).setRemark(Constant.DEFAUL_CASH_OUT);
        dealUserStoreRefundService.saveEntity(refund, Constant.RefundType.CASHOUT.getType(), null);
    }

    //提现前校验
    private void checkBefore(DealUserStoreCheckInvokingVo storeCheck) {
        if (Objects.isNull(storeCheck)){
            throw new RRException("提现失败,该企业客户已提现成功");
        }
        if (baseMapper.getCountDealUser(storeCheck.getDealUserId(), Constant.StoreType.ENTERPRISE.getType(), Constant.Status.NORMAL.getStatus()) <= 0){
            throw new RRException("提现失败,客户状态已被禁用或客户非企业客户");
        }
    }

    //已客户的手机号码新增客户的文件夹
    private void mkdirFolder(String phone) {
        //客户企业文件夹
        File fileStore = new File(new StringBuilder().append(Constant.DEAL_LINUX_IMAGE_PATH).append(phone).append(Constant.UploadImage.STORE.getText()).toString());
        //客户销售文件夹
        File fileDeal = new File(new StringBuilder().append(Constant.DEAL_LINUX_IMAGE_PATH).append(phone).append(Constant.UploadImage.DEAL.getText()).toString());
        //客户评估文件夹
        File fileAssess = new File(new StringBuilder().append(Constant.DEAL_LINUX_IMAGE_PATH).append(phone).append(Constant.UploadImage.ASSESS.getText()).toString());
        if(!fileStore.exists()) {//如果客户的企业门面目录文件夹
            //创建目录
            fileStore.mkdirs();
        }
        if(!fileDeal.exists()) {//如果客户的商品目录文件夹不存在
            //创建目录
            fileDeal.mkdirs();
        }
        if(!fileAssess.exists()) {//如果客户评估价钱商品目录文件夹
            //创建目录
            fileAssess.mkdirs();
        }
    }

    //设置DealUserLoginVo更新登录信息
    private DealUserLoginVo getUpdateDealUserLoginVo(DealUserEntity dealUserEntity) {
        //旧的登录信息
        String wxDealIdKey = RedisKeys.Wx.WxLogin(new StringBuilder().append(RedisKeyConstant.WX_LOGIN).append("_").append(dealUserEntity.getDealUserId()).toString());
        DealUserLoginVo dealUserLoginVo = redisUtils.get(wxDealIdKey, DealUserLoginVo.class);
        if (Objects.nonNull(dealUserLoginVo)){
            dealInvokingService.deleteOldUserInfo(wxDealIdKey, dealUserLoginVo);
            dealUserLoginVo
                    //用户名称
                    .setDealUserName(dealUserEntity.getDealUserName())
                    // token到期时间
                    .setExpireTime((new Date(new Date(System.currentTimeMillis()).getTime() + RedisKeys.TOKEN_EXPIRE_MS)));
        }else{
            // 缓存token
            dealUserLoginVo = getDealUserLoginVo(TokenGeneratorUtils.generateValue(), dealUserEntity);
        }
        return dealUserLoginVo;
    }

    //获取DealUserEntity更新对象
    private DealUserEntity getUpdateDealUserEntity(DealUserUpdateVo user) {
        DealUserEntity dealUserEntity = getOne(new QueryWrapper<DealUserEntity>().eq("deal_user_id", user.getDealUserId()).last("LIMIT 1"));
        dealUserEntity.setDealUserName(user.getDealUserName()).setUpdateTime(new Date());
        return dealUserEntity;
    }

    //设置DealUserLoginVo登录对象
    private DealUserLoginVo getDealUserLoginVo(String token, DealUserEntity dealUserEntity) {
        DealUserLoginVo dealUserLoginVo = new DealUserLoginVo();
        dealUserLoginVo
                //客户ID
                .setDealUserId(dealUserEntity.getDealUserId())
                //客户名称
                .setDealUserName(dealUserEntity.getDealUserName())
                //客户手机号码
                .setPhone(dealUserEntity.getPhone())
                // token到期时间
                .setExpireTime(new Date(new Date(System.currentTimeMillis()).getTime() + RedisKeys.TOKEN_EXPIRE_MS))
                //token
                .setToken(token);
        return dealUserLoginVo;
    }

    //设置DealUserEntity新增对象
    private DealUserEntity getSaveDealUserEntity(DealUserSaveVo user) {
        DealUserEntity dealUserEntity = new DealUserEntity();
        dealUserEntity
                .setDealUserName(user.getDealUserName())
                .setPhone(user.getPhone())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setIntegral(Constant.INTEGRAL)
                .setType(Constant.StoreType.INDIVIDUAL.getType());
        return dealUserEntity;
    }

    //根据用户ID获取企业信息
    private DealUserStoreInvokingVo getDealUserStoreInvokingVo(Long DealUserId) {
        return dealUserStoreService.getDealUserStoreInvokingVo(DealUserId);
    }
}
