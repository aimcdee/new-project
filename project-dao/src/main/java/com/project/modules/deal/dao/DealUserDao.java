package com.project.modules.deal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.deal.entity.DealUserEntity;
import com.project.modules.deal.vo.info.DealUserInfoVo;
import com.project.modules.deal.vo.invoking.DealUserInvokingVo;
import com.project.modules.deal.vo.list.DealUserListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 客户Dao
 *
 * @author liangyuding
 * @date 2020-03-37
 */
@Mapper
public interface DealUserDao extends BaseMapper<DealUserEntity> {

    /**
     * 分页查询客户列表
     * @param page
     * @param dealUserName
     * @param phone
     * @param type
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealUserListVo> queryPage(
            Page<DealUserListVo> page,
            @Param("dealUserName") String dealUserName,
            @Param("dealPhone") String phone,
            @Param("type") Integer type,
            @Param("status") Integer status,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 根据userID获取客户信息
     * @param dealUserId
     * @return
     */
    DealUserInfoVo getDealUserInfoVo(@Param("dealUserId") Long dealUserId);

    /**
     * 获取所有用户ID集合
     * @param status
     * @return
     */
    List<DealUserInvokingVo> getDealUserList(@Param("status") Integer status);

    /**
     * 获取企业用户ID集合
     * @param type
     * @param examine
     * @return
     */
    List<DealUserInvokingVo> getStoreUserList(@Param("type") Integer type, @Param("examine") Integer examine);

    /**
     * 根据客户ID,客户类型和客户状态查询是否存在该客户
     * @param dealUserId
     * @param type
     * @param status
     * @return
     */
    Integer getCountDealUser(@Param("dealUserId") Long dealUserId, @Param("type") Integer type, @Param("status") Integer status);
}
