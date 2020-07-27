package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealWaresInstallmentEntity;
import com.project.modules.deal.vo.info.DealWaresInstallmentInfoVo;
import com.project.modules.deal.vo.list.DealWaresInstallmentListVo;
import com.project.modules.deal.vo.wx.DealWaresInstallmentWxListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 咨询分期客户管理表Dao
 *
 * @author liangyuding
 * @date 2020-06-06
 */
@Mapper
public interface DealWaresInstallmentDao extends BaseMapper<DealWaresInstallmentEntity> {

    /**
     * 分页查询咨询分期客户列表
     * @param page
     * @param dealUserName
     * @param dealUserId
     * @param dealWaresTitle
     * @param contactName
     * @param contactPhone
     * @param followStatus
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealWaresInstallmentListVo> queryPage(
            Page<DealWaresInstallmentListVo> page,
            @Param("dealUserName") String dealUserName,
            @Param("dealUserId") Long dealUserId,
            @Param("dealWaresTitle") String dealWaresTitle,
            @Param("contactName") String contactName,
            @Param("contactPhone") String contactPhone,
            @Param("followStatus") Integer followStatus,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 客户分页查询个人咨询分期记录列表
     * @param page
     * @param dealUserId
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealWaresInstallmentWxListVo> queryWxPage(
            Page<DealWaresInstallmentWxListVo> page,
            @Param("dealUserId") Long dealUserId,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 获取咨询分期客户详情
     * @param installmentId
     * @return
     */
    DealWaresInstallmentInfoVo info(@Param("installmentId") Long installmentId);
}
