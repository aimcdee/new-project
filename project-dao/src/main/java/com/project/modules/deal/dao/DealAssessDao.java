package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealAssessEntity;
import com.project.modules.deal.vo.info.DealAssessInfoVo;
import com.project.modules.deal.vo.list.DealAssessListVo;
import com.project.modules.deal.vo.wx.info.DealAssessWxInfoVo;
import com.project.modules.deal.vo.wx.list.DealAssessWxListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 商品评估价格Dao
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Mapper
public interface DealAssessDao extends BaseMapper<DealAssessEntity> {

    /**
     * 分页查询商品评估列表
     * @param page
     * @param dealUserId
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealAssessListVo> queryPage(
            Page<DealAssessListVo> page,
            @Param("dealUserId") Long dealUserId,
            @Param("status") Integer status,
            @Param("sellStatus") Integer sellStatus,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 根据商品评估ID获取商品评估详情
     * @param dealAssessId
     * @return
     */
    DealAssessInfoVo info(@Param("dealAssessId") Long dealAssessId);

    /**
     * 分页查询微信端个人商品评估列表
     * @return
     */
    List<DealAssessWxListVo> queryWxPage(Page<DealAssessWxListVo> page, @Param("dealUserId") Long dealUserId);

    /**
     * 根据商品评估ID获取微信端商品评估详情
     * @param dealAssessId
     * @return
     */
    DealAssessWxInfoVo infoWx(@Param("dealAssessId") Long dealAssessId);
}
