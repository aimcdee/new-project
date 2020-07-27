package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.deal.entity.DealBillExamineEntity;
import com.project.modules.deal.vo.invoking.DealBillExamineInvokingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审核单Dao
 *
 * @author liangyuding
 * @date 2020-05-56
 */
@Mapper
public interface DealBillExamineDao extends BaseMapper<DealBillExamineEntity> {

    /**
     * 查询财务审核对象
     * @param billId
     * @param type
     * @return
     */
    DealBillExamineInvokingVo getExamineUser(@Param("billId") String billId, @Param("type") Integer type);

    /**
     * 获取单据的审核记录
     * @param billId
     * @param type
     * @return
     */
    List<DealBillExamineInvokingVo> getExamineList(@Param("billId") String billId, @Param("type") Integer type);
}
