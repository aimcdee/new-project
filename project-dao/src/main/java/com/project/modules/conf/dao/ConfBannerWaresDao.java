package com.project.modules.conf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.conf.entity.ConfBannerWaresEntity;
import com.project.modules.conf.vo.Invoking.ConfBannerWaresInvokingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播企业商品管理Dao
 *
 * @author liangyuding
 * @date 2020-06-08
 */
@Mapper
public interface ConfBannerWaresDao extends BaseMapper<ConfBannerWaresEntity> {

    /**
     * 根据轮播图ID获取轮播商品对象集合
     * @param bannerId
     * @return
     */
    List<ConfBannerWaresInvokingVo> getBannerWaresListByBannerId(@Param("bannerId") Long bannerId);

    /**
     * 根据企业商品ID获取企业商品标题和企业商品封面图路径
     * @param dealWaresId
     * @return
     */
    String getDealWaresInvokingVoByDealWaresId(@Param("dealWaresId") String dealWaresId);
}
