package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealAssessSellEntity;
import com.project.modules.deal.vo.info.DealAssessSellInfoVo;
import com.project.modules.deal.vo.list.DealAssessSellListVo;
import com.project.modules.deal.vo.wx.info.DealAssessSellWxInfoVo;
import com.project.modules.deal.vo.wx.list.DealAssessSellWxListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 评估商品出售Dao
 *
 * @author liangyuding
 * @date 2020-05-16
 */
@Mapper
public interface DealAssessSellDao extends BaseMapper<DealAssessSellEntity> {

    /**
     * 分页查询评估商品出售列表
     * @param page
     * @param contactName
     * @param contactPhone
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealAssessSellListVo> queryPage(
            Page<DealAssessSellListVo> page,
            @Param("contactName") String contactName,
            @Param("contactPhone") String contactPhone,
            @Param("status") Integer status,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 分页查询个人评估商品出售列表
     * @param page
     * @param dealUserId
     * @return
     */
    List<DealAssessSellWxListVo> queryWxPage(Page<DealAssessSellWxListVo> page, @Param("dealUserId") Long dealUserId);

    /**
     * 根据评估商品出售ID获取评估商品出售详情
     * @param dealSellId
     * @return
     */
    DealAssessSellInfoVo info(@Param("dealSellId") Long dealSellId);

    /**
     * 根据评估商品出售ID获取个人评估商品出售详情
     * @param dealSellId
     * @return
     */
    DealAssessSellWxInfoVo infoWx(@Param("dealSellId") Long dealSellId);
}
