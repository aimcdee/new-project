package com.project.modules.cust.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.modules.cust.dao.CustAreaLicenseDao;
import com.project.modules.cust.entity.CustAreaLicenseEntity;
import com.project.modules.cust.service.CustAreaLicenseService;
import com.project.modules.cust.vo.list.CustAreaLicenseListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 牌照Service
 *
 * @author liangyuding
 * @date 2020-09-25
 */
@Slf4j
@Service("custAreaLicenseService")
public class CustAreaLicenseServiceImpl extends ServiceImpl<CustAreaLicenseDao, CustAreaLicenseEntity> implements CustAreaLicenseService {

    /**
     * 根据所属省份ID和所属市级ID查询所有牌照
     * @param provinceId
     * @param cityId
     * @return
     */
    @Override
    public List<CustAreaLicenseListVo> getList(Long provinceId, Long cityId) {
        return baseMapper.getList(provinceId, cityId);
    }
}
