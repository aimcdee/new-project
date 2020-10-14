package com.project.modules.cust.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.cust.entity.CustAreaEntity;
import com.project.modules.cust.vo.invoking.CustAreaInvokingVo;
import com.project.modules.cust.vo.list.CustAreaListVo;
import com.project.modules.cust.vo.list.CustAreaTreeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域Dao
 *
 * @author liangyuding
 * @date 2020-03-26
 */
@Mapper
public interface CustAreaDao extends BaseMapper<CustAreaEntity> {

    /**
     * 查看树状区域列表
     * @return
     */
    List<CustAreaTreeVo> getCustAreaTreeList();

    /**
     * 查看该区域下所有的子区域
     * @param areaId
     * @return
     */
    List<CustAreaTreeVo> getAllAreaTreeList(@Param("areaId") Long areaId);

    /**
     * 分页查询区域列表
     * @param page
     * @param areaIdList
     * @return
     */
    List<CustAreaListVo> queryPage(
            Page<CustAreaListVo> page,
            @Param("areaIdList") List<Long> areaIdList);

    /**
     * 根据上级区域ID获取上级区域名称
     * @param parentId
     * @return
     */
    String getParentNameByParentId(@Param("parentId") Long parentId);

//    /**
//     * 查看区域
//     * @param parentId
//     * @param type
//     * @return
//     */
//    List<CustAreaInvokingVo> getCustAreaInvokingVo(@Param("parentId") Long parentId, @Param("type") Integer type);

    /**
     * 查看区域
     * @param areaIdList
     * @param parentId
     * @param type
     * @return
     */
//    List<CustAreaInvokingVo> getCustAreaInvokingVo(@Param("areaId") Long areaId, @Param("parentId") Long parentId, @Param("type") Integer type);
    List<CustAreaInvokingVo> getCustAreaInvokingVo(@Param("areaIdList") List<Long> areaIdList, @Param("parentId") Long parentId, @Param("type") Integer type);

    /**
     * 获取该区域下所有子区域
     * @param areaId
     * @return
     */
    List<Long> getChildAreaIdList(@Param("areaId") Long areaId);

    /**
     *
     * @param areaIdList
     * @param type
     * @return
     */
//    List<CustAreaInvokingVo> getCustAreaInvokingVoList(@Param("areaIdList") List<Long> areaIdList, @Param("type") Integer type);
}
