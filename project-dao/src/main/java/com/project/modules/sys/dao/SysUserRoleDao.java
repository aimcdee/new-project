package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.sys.entity.SysUserRoleEntity;
import com.project.modules.sys.vo.invoking.SysRoleInvokingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户与系统角色关系Dao
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

    /**
     * 根据系统用户ID查询该用户的系统角色关系
     * @param userId
     * @return
     */
    List<Long> getRoleIdListByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID获取用户的角色列表
     * @param userId
     * @return
     */
    List<SysRoleInvokingVo> findRoleList(@Param("userId") Long userId);
}
