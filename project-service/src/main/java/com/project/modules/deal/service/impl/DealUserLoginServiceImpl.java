package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.modules.deal.entity.DealUserEntity;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.service.DealUserLoginService;
import com.project.modules.deal.service.DealUserService;
import com.project.modules.deal.vo.login.DealUserLoginVo;
import com.project.modules.deal.vo.save.DealUserSaveVo;
import com.project.utils.CheckUtils;
import com.project.utils.RedisKeys;
import com.project.utils.RedisUtils;
import com.project.utils.TokenGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * 移动端登录Service
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Slf4j
@Service("dealUserLoginService")
public class DealUserLoginServiceImpl implements DealUserLoginService {

    @Autowired
    private DealUserService dealUserService;
    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 微信端登录
     * @param phone
     * @return
     */
    @Override
    @Transactional
    public String wxDealUserlogin(String phone) {
        checkUtils.checkPhone(phone);
        DealUserEntity dealUserEntity = dealUserService.getOne(new QueryWrapper<DealUserEntity>().eq("phone", phone).eq("status", Constant.Status.NORMAL.getStatus()).last("LIMIT 1"));
        //如果该客户是第一次登录,则新增客户并返回token
        if (Objects.isNull(dealUserEntity)){
            DealUserSaveVo dealUserSaveVo = new DealUserSaveVo();
            dealUserSaveVo.setDealUserName(phone).setPhone(phone);
            return dealUserService.saveEntity(dealUserSaveVo);
        }
        //如果该客户已存在则更新redis消息并生成token返回
        return createDealUserToken(dealUserEntity);
    }

    /**
     * 退出登录
     * @param dealUserId
     */
    @Override
    public void logout(Long dealUserId) {
        String wxDealIdKey = RedisKeys.Wx.WxLogin(new StringBuilder().append(RedisKeyConstant.WX_LOGIN).append("_").append(dealUserId).toString());
        DealUserLoginVo dealUserLoginVo = redisUtils.get(wxDealIdKey, DealUserLoginVo.class);
        dealInvokingService.deleteOldUserInfo(wxDealIdKey, dealUserLoginVo);
    }

    //创建客户登录返回的token
    private String createDealUserToken(DealUserEntity dealUserEntity) {
        //创建token
        String token = TokenGeneratorUtils.generateValue();
        //登录对象 保存到redis中,如果用户以前存在token信息,则清楚用户登录信息
        DealUserLoginVo dealUserLoginVo = new DealUserLoginVo();
        dealUserLoginVo
                //客户ID
                .setDealUserId(dealUserEntity.getDealUserId())
                //客户名称
                .setDealUserName(dealUserEntity.getDealUserName())
                //客户手机号码
                .setPhone(dealUserEntity.getPhone())
                //客户类型
                .setType(dealUserEntity.getType())
                // token到期时间
                .setExpireTime(new Date(new Date(System.currentTimeMillis()).getTime() + RedisKeys.TOKEN_EXPIRE_MS))
                //token
                .setToken(token);
        if (dealUserEntity.getType().equals(Constant.StoreType.ENTERPRISE.getType())){
            //客户企业ID
            dealUserLoginVo.setDealStoreId(dealInvokingService.getDealStoreId(dealUserEntity.getDealUserId(), Constant.Examine.SUCCESS.getExamine()));
        }
        //以userId获取redisKey
        String wxDealIdKey = RedisKeys.Wx.WxLogin(new StringBuilder().append(RedisKeyConstant.WX_LOGIN).append("_").append(dealUserLoginVo.getDealUserId()).toString());
        DealUserLoginVo oldDealUserLoginVo = redisUtils.get(wxDealIdKey, DealUserLoginVo.class);
        if (Objects.nonNull(oldDealUserLoginVo)){
            dealInvokingService.deleteOldUserInfo(wxDealIdKey, oldDealUserLoginVo);
        }
        //更新新的登录信息
        dealInvokingService.saveRedis(dealUserLoginVo);
        return dealUserLoginVo.getToken();
    }
}
