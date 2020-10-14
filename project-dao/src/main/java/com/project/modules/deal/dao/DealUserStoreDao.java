package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealUserStoreEntity;
import com.project.modules.deal.vo.info.DealUserStoreInfoVo;
import com.project.modules.deal.vo.invoking.DealUserStoreInvokingVo;
import com.project.modules.deal.vo.list.DealStoreListVo;
import com.project.modules.deal.vo.list.DealUserStoreListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 客户公司表Dao
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Mapper
public interface DealUserStoreDao extends BaseMapper<DealUserStoreEntity> {

    /**
     * 校验客户是否存在还未审核的企业申请验证
     * @param dealUserId
     * @param examine
     * @return
     */
    Integer getCountExamineForInreview(@Param("dealUserId") Long dealUserId, @Param("examine") Integer examine);

    /**
     * 分页查询企业申请验证
     * @param page
     * @param dealUserId
     * @param dealUserPhone
     * @param examine
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealStoreListVo> listPage(
            Page<DealStoreListVo> page,
            @Param("dealUserId") Long dealUserId,
            @Param("dealUserPhone") String dealUserPhone,
            @Param("examine") Integer examine,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 分页查询企业申请验证
     * @param page
     * @param dealUserId
     * @return
     */
    List<DealUserStoreListVo> list(
            Page<DealUserStoreListVo> page,
            @Param("dealUserId") Long dealUserId);

    /**
     * 查看客户企业验证详情
     * @param dealStoreId
     * @return
     */
    DealUserStoreInfoVo info(@Param("dealStoreId") Long dealStoreId);

    /**
     * 将用户除了该条记录以外的所有状态为成功的申请记录全都改为作废
     * @param dealUserId
     * @param success
     * @param storeId
     * @param waste
     */
    void updateDealUserStoreExamine(
            @Param("dealUserId") Long dealUserId,
            @Param("success") Integer success,
            @Param("dealStoreId") Long storeId,
            @Param("waste") Integer waste);

    /**
     * 根据用户ID获取企业信息
     * @param dealUserId
     * @return
     */
    DealUserStoreInvokingVo getDealUserStoreInvokingVo(
            @Param("dealUserId") Long dealUserId,
            @Param("examine") Integer examine);
}
