package com.project.modules.conf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.conf.entity.ConfBannerEntity;
import com.project.modules.conf.vo.info.ConfBannerInfoVo;
import com.project.modules.conf.vo.save.ConfBannerSaveVo;
import com.project.modules.conf.vo.update.ConfBannerUpdateVo;
import com.project.utils.PageUtils;

import java.util.Map;

/**
 * 轮播图Service
 *
 * @author liangyuding
 * @date 2020-04-14
 */
public interface ConfBannerService extends IService<ConfBannerEntity> {

    /**
     * 分页轮播图列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增轮播图
     * @param banner
     * @param sysUserId
     */
    void saveEntity(ConfBannerSaveVo banner, Long sysUserId);

    /**
     * 根据轮播图ID获取轮播图详情
     * @param bannerId
     * @return
     */
    ConfBannerInfoVo info(Long bannerId);

    /**
     * 更新轮播图
     * @param banner
     * @param sysUserId
     */
    void updateEntity(ConfBannerUpdateVo banner, Long sysUserId);

    /**
     * 修改轮播图的状态
     * @param bannerId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long bannerId, Integer status, Long sysUserId);

    /**
     * 查询状态为正常的零售端/企业轮播图集合
     * @param displayType
     * @param status
     * @return
     */
    ConfBannerInfoVo typeList(Integer displayType, Integer status);
}
