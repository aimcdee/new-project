package com.project.modules.cou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.cou.entity.CouWaresBrandEntity;
import com.project.modules.cou.vo.Invoking.CouBrandInvokingVo;
import com.project.modules.cou.vo.Invoking.CouWaresBrandInvokingVo;
import com.project.modules.cou.vo.info.CouWaresBrandInfoVo;
import com.project.modules.cou.vo.save.CouWaresBrandSaveVo;
import com.project.modules.cou.vo.update.CouWaresBrandUpdateVo;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 品牌Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
public interface CouWaresBrandService extends IService<CouWaresBrandEntity> {

    /**
     * 分页查询品牌列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增品牌
     * @param brand
     * @param sysUserId
     */
    void saveEntity(CouWaresBrandSaveVo brand, Long sysUserId);

    /**
     * 根据品牌ID获取品牌详情
     * @param couBrandId
     * @return
     */
    CouWaresBrandInfoVo info(Long couBrandId);

    /**
     * 更新品牌
     * @param brand
     * @param sysUserId
     */
    void updateEntity(CouWaresBrandUpdateVo brand, Long sysUserId);

    /**
     * 修改品牌的状态
     * @param couBrandId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long couBrandId, Integer status, Long sysUserId);

    /**
     * 获取所有状态为正常品牌的ID和名称
     * @return
     */
    List<CouWaresBrandInvokingVo> getHotCouBrandList();

    /**
     * 获取所有状态为正常品牌的ID和名称
     * @return
     */
    List<CouWaresBrandInvokingVo> getCouBrandList();

    /**
     * 按字母分类查询所有品牌
     * @return
     */
    List<CouBrandInvokingVo> getBrandList();
}
