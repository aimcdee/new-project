package com.project.modules.cou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.cou.entity.CouWaresEntity;
import com.project.modules.cou.vo.Invoking.CouWaresInvokingVo;
import com.project.modules.cou.vo.info.CouWaresInfoVo;
import com.project.modules.cou.vo.save.CouWaresSaveVo;
import com.project.modules.cou.vo.update.CouWaresUpdateVo;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品Service
 *
 * @author liangyuding
 * @date 2020-05-14
 */
public interface CouWaresService extends IService<CouWaresEntity> {

    /**
     * 分页查询商品列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增商品
     * @param wares
     * @param sysUserId
     */
    void saveEntity(CouWaresSaveVo wares, Long sysUserId);

    /**
     * 根据商品ID获取商品详情
     * @param waresId
     * @return
     */
    CouWaresInfoVo info(Long waresId);

    /**
     * 更新商品
     * @param wares
     * @param sysUserId
     */
    void updateEntity(CouWaresUpdateVo wares, Long sysUserId);

    /**
     * 修改商品的状态
     * @param waresId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long waresId, Integer status, Long sysUserId);

    /**
     * 根据品牌系列ID获取所有状态为正常商品对象
     * @param couSeriesId
     * @return
     */
    List<CouWaresInvokingVo> getCouWaresList(Long couSeriesId);
}
