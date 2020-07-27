package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.modules.sys.entity.SysUserDeptEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户与系统部门关系Dao
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Mapper
public interface SysUserDeptDao extends BaseMapper<SysUserDeptEntity> {

}
