package com.project.modules.cou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.cou.entity.CouWaresEntity;
import com.project.modules.cou.vo.Invoking.CouWaresInvokingVo;
import com.project.modules.cou.vo.info.CouWaresInfoVo;
import com.project.modules.cou.vo.list.CouWaresListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品Dao
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Mapper
public interface CouWaresDao extends BaseMapper<CouWaresEntity> {

    /**
     * 分页查询商品列表
     * @param page
     * @param couBrandlId
     * @param couSeriesId
     * @param couModelId
     * @param couWaresName
     * @param status
     * @return
     */
    List<CouWaresListVo> queryPage(
            Page<CouWaresListVo> page,
            @Param("couBrandlId") Long couBrandlId,
            @Param("couSeriesId") Long couSeriesId,
            @Param("couModelId") Long couModelId,
            @Param("couWaresName") String couWaresName,
            @Param("status") Integer status);

    /**
     * 根据商品ID获取商品详情
     * @param couWaresId
     * @return
     */
    CouWaresInfoVo info(@Param("couWaresId") Long couWaresId);

    /**
     * 获取所有状态为正常商品的ID和名称和厂商指导价
     * @param couSeriesId
     * @param status
     * @return
     */
    List<CouWaresInvokingVo> getCouWaresList(@Param("couSeriesId") Long couSeriesId, @Param("status") Integer status);
}
