package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealUserStoreEntity;
import com.project.modules.deal.vo.info.DealUserStoreInfoVo;
import com.project.modules.deal.vo.invoking.DealUserStoreInvokingVo;
import com.project.modules.deal.vo.save.DealUserStoreSaveVo;
import com.project.utils.PageUtils;

import java.util.Map;

/**
 * 客户公司表Service
 *
 * @author liangyuding
 * @date 2020-03-37
 */
public interface DealUserStoreService extends IService<DealUserStoreEntity> {

    /**
     * 客户申请成为企业用户
     * @param userStore
     */
    void saveEntity(DealUserStoreSaveVo userStore);

    /**
     * 查看客户企业验证详情
     * @param dealStoreId
     * @return
     */
    DealUserStoreInfoVo info(Long dealStoreId);

    /**
     * 审核客户企业验证
     * @param dealStoreId
     * @param examine
     * @param sysUserId
     * @param belongUserId
     */
    void changeExamine(Long dealStoreId, Integer examine, Long sysUserId, Long belongUserId);

    /**
     * 分页查询企业验证记录
     * @param params
     * @return
     */
    PageUtils listPage(Map<String, Object> params);

    /**
     * 分页查看客户申请成为企业的申请记录
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户ID获取企业信息
     * @param DealUserId
     * @return
     */
    DealUserStoreInvokingVo getDealUserStoreInvokingVo(Long DealUserId);

    /**
     * 修改企业认证
     * @param userId
     * @param sysUserId
     */
    void changeUserStore(Long userId, Long sysUserId);
}
