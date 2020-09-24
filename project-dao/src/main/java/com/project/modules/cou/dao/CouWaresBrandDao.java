package com.project.modules.cou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.cou.entity.CouWaresBrandEntity;
import com.project.modules.cou.vo.Invoking.CouWaresBrandInvokingVo;
import com.project.modules.cou.vo.info.CouWaresBrandInfoVo;
import com.project.modules.cou.vo.list.CouWaresBrandListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品品牌Dao
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Mapper
public interface CouWaresBrandDao extends BaseMapper<CouWaresBrandEntity> {

    /**
     * 分页查询商品品牌列表
     * @param page
     * @param couBrandName
     * @param status
     * @return
     */
    List<CouWaresBrandListVo> queryPage(
            Page<CouWaresBrandListVo> page,
            @Param("couBrandName") String couBrandName,
            @Param("status") Integer status);

    /**
     * 根据商品品牌ID获取商品品牌详情
     * @param couBrandId
     * @return
     */
    CouWaresBrandInfoVo info(@Param("couBrandId") Long couBrandId);

    /**
     * 获取热门品牌对象列表
     * @param status
     * @return
     */
    List<CouWaresBrandInvokingVo> getHotCouBrandList(@Param("status") Integer status);

    /**
     * 获取所有状态为正常商品品牌的ID和名称
     * @param status
     * @return
     */
    List<CouWaresBrandInvokingVo> getCouBrandList(@Param("status") Integer status);
}
