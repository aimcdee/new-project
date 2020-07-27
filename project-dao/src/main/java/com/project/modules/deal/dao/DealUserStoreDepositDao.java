package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealUserStoreDepositEntity;
import com.project.modules.deal.vo.info.DealUserStoreDepositInfoVo;
import com.project.modules.deal.vo.list.DealUserStoreDepositListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 企业用户保证金单Dao
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Mapper
public interface DealUserStoreDepositDao extends BaseMapper<DealUserStoreDepositEntity> {

    /**
     * 分页查询保证金单列表
     * @param page
     * @param dealPhone
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealUserStoreDepositListVo> queryPage(
            Page<DealUserStoreDepositListVo> page,
            @Param("dealPhone") String dealPhone,
            @Param("status") Integer status,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 获取保证金单详情
     * @param depositId
     * @return
     */
    DealUserStoreDepositInfoVo info(@Param("depositId") String depositId);
}
