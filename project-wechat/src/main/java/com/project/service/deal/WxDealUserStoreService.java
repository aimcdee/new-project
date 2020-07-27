package com.project.service.deal;

import com.project.constant.Constant;
import com.project.modules.deal.vo.save.DealUserStoreSaveVo;
import com.project.service.deal.impl.WxDealUserStoreServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信客户企业调用层
 *
 * @author liangyuding
 * @date 2020-04-07
 */
@FeignClient(name = "project-controller", fallback = WxDealUserStoreServiceImpl.class)
public interface WxDealUserStoreService {

    /**
     * 客户查看企业认证申请记录
     * @param params
     * @return
     */
    @GetMapping(Constant.DEAL_USER_STORE_PATH + "/recordList")
    R recordList(@RequestParam Map<String, Object> params);

    /**
     * 客户申请企业认证
     * @param userStore
     * @return
     */
    @PostMapping(Constant.DEAL_USER_STORE_PATH + "/saveStore")
    R saveEntity(@RequestBody DealUserStoreSaveVo userStore);
}
