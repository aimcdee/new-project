package com.project.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * shiro操作的 持久化层
 */
@Mapper
public interface ShiroDao {

    /**
     * 查询用户的所有角色
     *
     * @param userId 用户ID
     */
    List<String> findAllRoleByUserId(Long userId);

}