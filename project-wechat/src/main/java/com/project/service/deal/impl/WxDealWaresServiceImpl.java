package com.project.service.deal.impl;

import com.project.modules.deal.vo.invoking.DealWaresChangeOnlineStatusInvokingVo;
import com.project.modules.deal.vo.save.DealWaresSaveVo;
import com.project.modules.deal.vo.update.DealWaresUpdateVo;
import com.project.service.deal.WxDealWaresService;
import com.project.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信调用异常处理层
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@Slf4j
@Service("wxDealWaresService")
public class WxDealWaresServiceImpl implements WxDealWaresService {

    /**
     * 企业客户分页查询自己上传的企业商品列表
     * @param params
     * @return
     */
    @Override
    public R queryPersonalPage(Map<String, Object> params) {
        log.error("调用{}异常:{}", "企业客户分页查询自己上传的企业商品列表");
        return null;
    }

    /**
     * 企业端分页显示商品列表
     * @param params
     * @return
     */
    @Override
    public R queryStorePage(Map<String, Object> params) {
        log.error("调用{}异常:{}", "企业端分页显示商品列表");
        return null;
    }

    /**
     * 零售端分页显示商品列表
     * @param params
     * @return
     */
    @Override
    public R queryRetailPage(Map<String, Object> params) {
        log.error("调用{}异常:{}", "零售端分页显示商品列表");
        return null;
    }

    /**
     * 企业客户新增商品信息
     * @param wares
     * @return
     */
    @Override
    public R saveEntity(DealWaresSaveVo wares) {
        log.error("调用{}异常:{}, 企业商品对象:{}", "企业客户新增商品信息", wares);
        return null;
    }

    /**
     * 企业客户获取企业客户个人商品的详情
     * @param dealWaresId
     * @return
     */
    @Override
    public R info(String dealWaresId) {
        log.error("调用{}异常:{}, 企业商品ID:{}", "企业客户获取企业客户个人商品的详情", dealWaresId);
        return null;
    }

    /**
     * 企业客户获取企业端商品的详情
     * @param dealWaresId
     * @return
     */
    @Override
    public R store(String dealWaresId) {
        log.error("调用{}异常:{}, 企业商品ID:{}", "企业客户获取企业端商品的详情", dealWaresId);
        return null;
    }

    /**
     * 客户获取零售端商品的详情
     * @param dealWaresId
     * @return
     */
    @Override
    public R retail(String dealWaresId) {
        log.error("调用{}异常:{}, 企业商品ID:{}", "客户获取零售端商品的详情", dealWaresId);
        return null;
    }

    /**
     * 企业客户修改个人商品信息
     * @param wares
     * @return
     */
    @Override
    public R updateEntity(DealWaresUpdateVo wares) {
        log.error("调用{}异常:{}, 企业商品对象:{}", "企业客户修改个人商品信息", wares);
        return null;
    }

    /**
     * 修改商品上线状态为上架
     * @param wares
     * @return
     */
    @Override
    public R onLine(DealWaresChangeOnlineStatusInvokingVo wares) {
        log.error("调用{}异常:{}, 企业商品ID:{}", "修改商品上线状态为上架", wares.getDealWaresId());
        return null;
    }

    /**
     * 修改商品上线状态为下架
     * @param wares
     * @return
     */
    @Override
    public R unLine(DealWaresChangeOnlineStatusInvokingVo wares) {
        log.error("调用{}异常:{}, 企业商品ID:{}", "修改商品上线状态为下架", wares.getDealWaresId());
        return null;
    }

    /**
     * 企业客户修改商品出售状态
     * @param dealWaresId
     * @return
     */
    @Override
    public R changeSellStatus(String dealWaresId) {
        log.error("调用{}异常:{}, 企业商品ID:{}", "修改企业商品出售情况为已出售", dealWaresId);
        return null;
    }
}
