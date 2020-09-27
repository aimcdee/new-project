package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealUserStoreFinanceEntity;
import com.project.modules.deal.vo.info.DealUserStoreFinanceInfoVo;
import com.project.modules.deal.vo.list.DealUserStoreFinanceListVo;
import com.project.modules.deal.vo.wx.list.DealUserStoreFinanceWxListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 企业用户金融单Dao
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Mapper
public interface DealUserStoreFinanceDao extends BaseMapper<DealUserStoreFinanceEntity> {

    /**
     * 分页查询金融单列表
     * @param page
     * @param dealUserId
     * @param dealPhone
     * @param contactPhone
     * @param contactName
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealUserStoreFinanceListVo> queryPage(
            Page<DealUserStoreFinanceListVo> page,
            @Param("dealStoreId") Long dealStoreId,
            @Param("dealPhone") String dealPhone,
            @Param("contactPhone") String contactPhone,
            @Param("contactName") String contactName,
            @Param("status") Integer status,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 企业客户分页查询金融单列表
     * @param page
     * @param dealStoreId
     * @return
     */
    List<DealUserStoreFinanceWxListVo> queryWxPage(Page<DealUserStoreFinanceWxListVo> page, @Param("dealStoreId") Long dealStoreId);

    /**
     * 获取金融单详情
     * @param financeId
     * @return
     */
    DealUserStoreFinanceInfoVo info(@Param("financeId") String financeId);
}
