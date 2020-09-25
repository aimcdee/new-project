package com.project.modules.cust.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.cust.entity.CustAreaLicenseEntity;
import com.project.modules.cust.vo.list.CustAreaLicenseListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 牌照Dao
 *
 * @author liangyuding
 * @date 2020-03-26
 */
@Mapper
public interface CustAreaLicenseDao extends BaseMapper<CustAreaLicenseEntity> {

    /**
     * 根据所属省份ID和所属市级ID查询所有牌照
     * @param provinceId
     * @param cityId
     * @return
     */
    List<CustAreaLicenseListVo> getList(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
}
