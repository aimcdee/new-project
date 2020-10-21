package com.project.modules.conf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.conf.entity.ConfBannerEntity;
import com.project.modules.conf.vo.info.ConfBannerInfoVo;
import com.project.modules.conf.vo.list.ConfBannerListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
     * @param displayType
     * @return
     */
    List<ConfBannerListVo> queryPage(
            Page<ConfBannerListVo> page,
            @Param("bannerName") String bannerName,
            @Param("status") Integer status,
            @Param("displayType") Integer displayType);

    /**
     * 修改轮播图状态为禁用
     * @param bannerIdList
     * @param disable
     */
    void updateBannerToDisable(
            @Param("bannerIdList") List<Long> bannerIdList,
            @Param("displayType") Integer displayType,
            @Param("disable") Integer disable,
            @Param("sysUserId") Long sysUserId,
            @Param("newTime") Date newTime);

    /**
     * 根据轮播图ID获取轮播图详情
     * @param bannerId
     * @return
     */
    ConfBannerInfoVo getConfbannerInfoVo(@Param("bannerId") Long bannerId);

    /**
     * 查询状态为正常的零售端/企业轮播图集合
     * @param displayType
     * @param status
     * @return
     */
    ConfBannerInfoVo getConfBanner(@Param("displayType") Integer displayType, @Param("status") Integer status);

    /**
     * 查询除了ID和属于该展示类型的所有状态为正常的轮播图ID
     * @param bannerId
     * @param status
     * @return
     */
    List<Long> getNormalBannerId(@Param("bannerId") Long bannerId, @Param("displayType") Integer displayType, @Param("status") Integer status);
}
