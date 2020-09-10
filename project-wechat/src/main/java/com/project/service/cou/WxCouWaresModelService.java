package com.project.service.cou;

import com.project.constant.Constant;
import com.project.service.cou.impl.WxCouWaresModelServiceImpl;
import com.project.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微信系统商品型号调用层
 *
 * @author liangyuding
 * @date 2020-06-09
 */
@FeignClient(name = "project-controller", fallback = WxCouWaresModelServiceImpl.class)
public interface WxCouWaresModelService {

    /**
     * 获取所有状态为正常商品型号对象
     * @return
     */
    @GetMapping(Constant.COU_MODEL_PATH + "/getCouModelList")
    R getCouModelList();
}
