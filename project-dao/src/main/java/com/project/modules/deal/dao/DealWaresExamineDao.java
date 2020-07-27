package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.deal.entity.DealWaresExamineEntity;
import com.project.modules.deal.vo.invoking.DealWaresExamineInvokingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业商品审核表Dao
 *
 * @author liangyuding
 * @date 2020-06-02
 */
@Mapper
public interface DealWaresExamineDao extends BaseMapper<DealWaresExamineEntity> {

    /**
     * 获取商品的审核对象
     * @param dealWaresId
     * @return
     */
    DealWaresExamineInvokingVo getExamineUser(@Param("dealWaresId") String dealWaresId);

    /**
     * 获取商品的审核记录
     * @param dealWaresId
     * @return
     */
    List<DealWaresExamineInvokingVo> getExamineList(@Param("dealWaresId") String dealWaresId);
}
