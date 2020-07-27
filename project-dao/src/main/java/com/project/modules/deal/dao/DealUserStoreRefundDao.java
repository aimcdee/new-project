package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealUserStoreRefundEntity;
import com.project.modules.deal.vo.info.DealUserStoreRefundInfoVo;
import com.project.modules.deal.vo.list.DealUserStoreRefundListVo;
import com.project.modules.deal.vo.wx.DealUserStoreRefundWxInfoVo;
import com.project.modules.deal.vo.wx.DealUserStoreRefundWxListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 企业用户退费Dao
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Mapper
public interface DealUserStoreRefundDao extends BaseMapper<DealUserStoreRefundEntity> {

    /**
     * 分页查询退费单列表
     * @param page
     * @param dealPhone
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealUserStoreRefundListVo> queryPage(
            Page<DealUserStoreRefundListVo> page,
            @Param("dealPhone") String dealPhone,
            @Param("status") Integer status,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 分页查询退费单列表
     * @param page
     * @param dealStoreId
     * @return
     */
    List<DealUserStoreRefundWxListVo> queryWxPage(Page<DealUserStoreRefundWxListVo> page, @Param("dealStoreId") Long dealStoreId);

    /**
     * 获取退费单详情
     * @param refundId
     * @return
     */
    DealUserStoreRefundInfoVo info(@Param("refundId") String refundId);

    /**
     * 获取退费单详情
     * @param refundId
     * @return
     */
    DealUserStoreRefundWxInfoVo infoWx(@Param("refundId") String refundId);
}
