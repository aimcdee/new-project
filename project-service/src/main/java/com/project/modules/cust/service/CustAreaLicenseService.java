package com.project.modules.cust.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.cust.entity.CustAreaLicenseEntity;
import com.project.modules.cust.vo.list.CustAreaLicenseListVo;

import java.util.List;

/**
 * 牌照Service
 *
 * @author liangyuding
 * @date 2020-09-25
 */
public interface CustAreaLicenseService  extends IService<CustAreaLicenseEntity> {

    /**
     * 根据所属省份ID和所属市级ID查询所有牌照
     * @param provinceId
     * @param cityId
     * @return
     */
    List<CustAreaLicenseListVo> getList(Long provinceId, Long cityId);
}
