package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealUserEntity;
import com.project.modules.deal.vo.info.DealUserInfoVo;
import com.project.modules.deal.vo.invoking.DealUserInvokingVo;
import com.project.modules.deal.vo.save.DealUserSaveVo;
import com.project.modules.deal.vo.update.DealUserUpdateVo;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 客户Service
 *
 * @author liangyuding
 * @date 2020-03-37
 */
public interface DealUserService extends IService<DealUserEntity> {

    /**
     * 分页查询客户列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增客户
     * @param user
     */
    String saveEntity(DealUserSaveVo user);

    /**
     * 根据客户ID获取客户详情
     * @param dealUserId
     * @return
     */
    DealUserInfoVo info(Long dealUserId);

    /**
     * 更新客户
     * @param user
     */
    void updateEntity(DealUserUpdateVo user);

    /**
     * 修改客户的状态
     * @param dealUserId
     * @param status
     */
    void changeStatus(Long dealUserId, Integer status);

    /**
     * 获取用户ID集合
     * @return
     */
    List<DealUserInvokingVo> getDealUserList();

    /**
     * 获取企业用户ID集合
     * @return
     */
    List<DealUserInvokingVo> getStoreUserList();

    /**
     * 客户提现
     * @param dealStoreId
     */
    void cashOut(Long dealStoreId);
}
