package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.deal.entity.DealAssessImageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品评估价格图片管理Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Mapper
public interface DealAssessImageDao extends BaseMapper<DealAssessImageEntity> {

    /**
     * 根据评估ID查询行驶证图对象
     * @param dealAssessId
     * @param type
     * @return
     */
    DealAssessImageEntity getDriveImage(@Param("dealAssessId") Long dealAssessId, @Param("type") Integer type);

    /**
     * 根据评估ID查询评估商品图集合对象
     * @param dealAssessId
     * @param type
     * @return
     */
    List<DealAssessImageEntity> getWaresImages(@Param("dealAssessId") Long dealAssessId, @Param("type") Integer type);
}
