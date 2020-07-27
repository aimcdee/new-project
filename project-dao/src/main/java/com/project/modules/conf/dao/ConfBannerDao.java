package com.project.modules.conf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.conf.entity.ConfBannerEntity;
import com.project.modules.conf.vo.info.ConfBannerInfoVo;
import com.project.modules.conf.vo.list.ConfBannerListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播图Dao
 *
 * @author liangyuding
 * @date 2020-04-14
 */
@Mapper
public interface ConfBannerDao extends BaseMapper<ConfBannerEntity> {

    /**
     * 分页查询轮播图列表
     * @param page
     * @param bannerName
     * @param status
     * @return
     */
    List<ConfBannerListVo> queryPage(
            Page<ConfBannerListVo> page,
            @Param("bannerName") String bannerName,
            @Param("status") Integer status);

    /**
     * 根据轮播图ID获取轮播图详情
     * @param bannerId
     * @return
     */
    ConfBannerInfoVo getConfbannerInfoVo(@Param("bannerId") Long bannerId);

    /**
     * 查询状态为正常的轮播图集合
     * @param status
     * @return
     */
    List<ConfBannerInfoVo> normalList(@Param("status") Integer status);
}
