package com.project.service.deal;

import com.project.constant.Constant;
import com.project.modules.deal.vo.invoking.DealWaresChangeOnlineStatusInvokingVo;
import com.project.modules.deal.vo.save.DealWaresSaveVo;
import com.project.modules.deal.vo.update.DealWaresUpdateVo;
import com.project.service.deal.impl.WxDealWaresServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信商品调用层
 *
 * @author liangyuding
 * @date 2020-06-10
 */
@FeignClient(name = "project-controller", fallback = WxDealWaresServiceImpl.class)
public interface WxDealWaresService {

    /**
     * 企业客户分页查询自己上传的企业商品列表
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_WARES_PATH + "/personalList")
    R queryPersonalPage(@RequestParam Map<String, Object> params);

    /**
     * 企业端分页显示商品列表
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_WARES_PATH + "/storeList")
    R queryStorePage(@RequestParam Map<String, Object> params);

    /**
     * 零售端分页显示商品列表
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_WARES_PATH + "/retailList")
    R queryRetailPage(@RequestParam Map<String, Object> params);

    /**
     * 企业客户新增商品信息
     * @param wares
     * @return
     */
    @PostMapping(Constant.DEAL_WARES_PATH + "/save")
    R saveEntity(@RequestBody DealWaresSaveVo wares);

    /**
     * 企业客户获取企业客户个人商品的详情
     * @param dealWaresId
     * @return
     */
    @GetMapping(Constant.DEAL_WARES_PATH + "/personal/{dealWaresId}")
    R info(@PathVariable("dealWaresId") String dealWaresId);

    /**
     * 企业客户获取企业端商品的详情
     * @param dealWaresId
     * @return
     */
    @GetMapping(Constant.DEAL_WARES_PATH + "/store/{dealWaresId}")
    R store(@PathVariable("dealWaresId") String dealWaresId);

    /**
     * 客户获取零售端商品的详情
     * @param dealWaresId
     * @return
     */
    @GetMapping(Constant.DEAL_WARES_PATH + "/retail/{dealWaresId}")
    R retail(@PathVariable("dealWaresId") String dealWaresId);

    /**
     * 企业客户修改个人商品信息
     * @param wares
     * @return
     */
    @PostMapping(Constant.DEAL_WARES_PATH + "/update")
    R updateEntity(@RequestBody DealWaresUpdateVo wares);

    /**
     * 修改企业商品出售情况为已出售
     * @param dealWaresId
     * @return
     */
    @GetMapping(Constant.DEAL_WARES_PATH + "/sale/{dealWaresId}")
    R changeSellStatus(String dealWaresId);

    /**
     * 修改商品上线状态为上架
     * @param wares
     * @return
     */
    @PostMapping(value = Constant.DEAL_WARES_PATH + "/onLine", consumes = MediaType.APPLICATION_JSON_VALUE)
    R onLine(@RequestBody DealWaresChangeOnlineStatusInvokingVo wares);

    /**
     * 修改商品上线状态为下架
     * @param wares
     * @return
     */
    @PostMapping(value = Constant.DEAL_WARES_PATH + "/unLine", consumes = MediaType.APPLICATION_JSON_VALUE)
    R unLine(@RequestBody DealWaresChangeOnlineStatusInvokingVo wares);
}
