package com.project.modules.cou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.cou.entity.CouWaresSeriesEntity;
import com.project.modules.cou.vo.Invoking.CouWaresSeriesInvokingVo;
import com.project.modules.cou.vo.info.CouWaresSeriesInfoVo;
import com.project.modules.cou.vo.list.CouWaresSeriesListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品品牌系列Dao
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Mapper
public interface CouWaresSeriesDao extends BaseMapper<CouWaresSeriesEntity> {

    /**
     * 分页查询商品品牌系列
     * @param page
     * @param couBrandId
     * @param couSeriesName
     * @return
     */
    List<CouWaresSeriesListVo> queryPage(
            Page<CouWaresSeriesListVo> page,
            @Param("couBrandId") Long couBrandId,
            @Param("couSeriesName") String couSeriesName,
            @Param("status") Integer status);

    /**
     * 根据商品品牌系列ID获取商品品牌系列详情
     * @param couSeriesId
     * @return
     */
    CouWaresSeriesInfoVo info(@Param("couSeriesId") Long couSeriesId);

    /**
     * 根据品牌ID获取所有状态为正常品牌系列的ID和名称
     * @param couBrandId
     * @param status
     * @return
     */
    List<CouWaresSeriesInvokingVo> getCouSeriesList(@Param("couBrandId") Long couBrandId, @Param("status") Integer status);
}
