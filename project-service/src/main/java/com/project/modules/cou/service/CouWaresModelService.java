package com.project.modules.cou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.cou.entity.CouWaresModelEntity;
import com.project.modules.cou.vo.Invoking.CouWaresModelInvokingVo;
import com.project.modules.cou.vo.info.CouWaresModelInfoVo;
import com.project.modules.cou.vo.save.CouWaresModelSaveVo;
import com.project.modules.cou.vo.update.CouWaresModelUpdateVo;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品型号Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
public interface CouWaresModelService extends IService<CouWaresModelEntity> {

    /**
     * 分页查询商品型号列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增商品型号
     * @param model
     * @param sysUserId
     */
    void saveEntity(CouWaresModelSaveVo model, Long sysUserId);

    /**
     * 根据商品型号ID获取商品型号详情
     * @param couModelId
     * @return
     */
    CouWaresModelInfoVo info(Long couModelId);

    /**
     * 更新商品型号
     * @param model
     * @param sysUserId
     */
    void updateEntity(CouWaresModelUpdateVo model, Long sysUserId);

    /**
     * 修改商品型号的状态
     * @param couModelId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long couModelId, Integer status, Long sysUserId);

    /**
     * 获取所有状态为正常商品型号的ID和名称
     * @return
     */
    List<CouWaresModelInvokingVo> getCouModelList();
}
