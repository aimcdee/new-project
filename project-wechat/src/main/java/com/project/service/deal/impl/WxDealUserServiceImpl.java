package com.project.service.deal.impl;

import com.project.modules.deal.vo.update.DealUserUpdateVo;
import com.project.service.deal.WxDealUserService;
import com.project.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信调用异常处理层
 *
 * @author liangyuding
 * @date 2020-04-07
 */
@Slf4j
@Service("wxDealUserService")
public class WxDealUserServiceImpl implements WxDealUserService {

    /**
     * 获取自己的详细信息
     * @param userId
     * @return
     */
    @Override
    public R getDealUserInfoVo(Long userId) {
        log.error("调用{}异常:{}, 客户ID:{}", "获取自己的详细信息", userId);
        return null;
    }

    /**
     * 更新个人信息
     * @param user
     * @return
     */
    @Override
    public R updateDealUser(DealUserUpdateVo user) {
        log.error("调用{}异常:{}, user:{}", "更新个人信息", user);
        return null;
    }

    /**
     * 企业客户提现
     * @param dealStoreId
     * @return
     */
    @Override
    public R cashOut(Long dealStoreId) {
        log.error("调用{}异常:{}, 客户企业ID:{}", "企业客户提现", dealStoreId);
        return null;
    }

}
