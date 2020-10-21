package com.project.modules.cou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.cou.entity.CouWaresSeriesEntity;
import com.project.modules.cou.vo.Invoking.CouWaresSeriesInvokingVo;
import com.project.modules.cou.vo.info.CouWaresSeriesInfoVo;
import com.project.modules.cou.vo.save.CouWaresSeriesSaveVo;
import com.project.modules.cou.vo.update.CouWaresSeriesUpdateVo;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 系列Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
public interface CouWaresSeriesService extends IService<CouWaresSeriesEntity> {

    /**
     * 分页查询系列列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增系列
     * @param series
     * @param sysUserId
     */
    void saveEntity(CouWaresSeriesSaveVo series, Long sysUserId);

    /**
     * 根据系列ID获取系列详情
     * @param couSeriesId
     * @return
     */
    CouWaresSeriesInfoVo info(Long couSeriesId);

    /**
     * 更新系列
     * @param series
     * @param sysUserId
     */
    void updateEntity(CouWaresSeriesUpdateVo series, Long sysUserId);

    /**
     * 修改系列的状态
     * @param couSeriesId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long couSeriesId, Integer status, Long sysUserId);

    /**
     * 根据品牌ID获取所有状态为正常系列的ID和名称
     * @param couBrandId
     * @return
     */
    List<CouWaresSeriesInvokingVo> getCouSeriesList(Long couBrandId);
}
