package com.project.modules.cou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.cou.entity.CouWaresModelEntity;
import com.project.modules.cou.vo.Invoking.CouWaresModelInvokingVo;
import com.project.modules.cou.vo.info.CouWaresModelInfoVo;
import com.project.modules.cou.vo.list.CouWaresModelListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 型号Dao
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Mapper
public interface CouWaresModelDao extends BaseMapper<CouWaresModelEntity> {

    /**
     * 分页查询型号列表
     * @param page
     * @param couModelName
     * @param status
     * @return
     */
    List<CouWaresModelListVo> queryPage(
            Page<CouWaresModelListVo> page,
            @Param("couModelName") String couModelName,
            @Param("status") Integer status);

    /**
     * 根据型号ID获取型号详情
     * @param couModelId
     * @return
     */
    CouWaresModelInfoVo info(@Param("couModelId") Long couModelId);

    /**
     * 获取所有状态为正常型号的ID和名称
     * @param status
     * @return
     */
    List<CouWaresModelInvokingVo> getCouModelList(@Param("status") Integer status);

    /**
     * 根据型号ID获取型号名称
     * @param couModelId
     * @return
     */
    String getCouModelNameById(@Param("couModelId") Long couModelId);
}
