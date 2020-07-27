package com.project.modules.deal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.deal.entity.DealWaresEntity;
import com.project.modules.deal.vo.info.DealWaresInfoVo;
import com.project.modules.deal.vo.save.DealWaresSaveVo;
import com.project.modules.deal.vo.update.DealWaresUpdateVo;
import com.project.modules.deal.vo.wx.DealWaresWxRetailInfoVo;
import com.project.modules.deal.vo.wx.DealWaresWxStoreInfoVo;
import com.project.utils.PageUtils;

import java.util.Map;

/**
 * 企业客户商品Service
 *
 * @author liangyuding
 * @date 2020-06-02
 */
public interface DealWaresService extends IService<DealWaresEntity> {

    /**
     * 分页企业商品列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 企业客户分页查询自己上传的企业商品列表
     * @param params
     * @return
     */
    PageUtils queryPersonalPage(Map<String, Object> params);

    /**
     * 企业端分页查询商品列表
     * @param params
     * @return
     */
    PageUtils queryStorePage(Map<String, Object> params);

    /**
     * 零售端分页查询商品列表
     * @param params
     * @return
     */
    PageUtils queryRetailPage(Map<String, Object> params);

    /**
     * 企业客户新增商品信息
     * @param wares
     */
    void saveEntity(DealWaresSaveVo wares);

    /**
     * 获取企业商品的详情
     * @param dealWaresId
     * @return
     */
    DealWaresInfoVo info(String dealWaresId);


    /**
     * 获取企业端商品的详情
     * @param dealWaresId
     * @return
     */
    DealWaresWxStoreInfoVo store(String dealWaresId);

    /**
     * 获取零售端商品的详情
     * @param dealWaresId
     * @return
     */
    DealWaresWxRetailInfoVo retail(String dealWaresId);

    /**
     * 企业客户更新商品信息
     * @param wares
     */
    void updateEntity(DealWaresUpdateVo wares);

    /**
     * 审核企业商品上线状态
     * @param dealWaresId
     * @param remark
     * @param status
     * @param sysUserId
     */
    void changeOnLineStatus(String dealWaresId, String remark, Integer status, Long sysUserId);

    /**
     * 修改企业商品出售情况为已出售
     * @param dealWaresId
     * @param status
     */
    void changeSellStatus(String dealWaresId, Integer status);

}
