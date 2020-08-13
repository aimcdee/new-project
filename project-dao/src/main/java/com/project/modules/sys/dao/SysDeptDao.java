package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.sys.entity.SysDeptEntity;
import com.project.modules.sys.vo.info.SysDeptInfoVo;
import com.project.modules.sys.vo.list.SysDeptListVo;
import com.project.modules.sys.vo.list.SysDeptTreeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统部门Dao
 *
 * @author liangyuding
 * @date 2020-03-11
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

    /**
     * 查看树形部门列表
     *
     * @return
     */
    List<SysDeptTreeVo> getSysDeptTreeList();

    /**
     * 校验部门名称是否存在
     * @param deptName
     * @param deptId
     * @return
     */
    Integer checkDeptNameRepeat(@Param("deptName") String deptName, @Param("deptId") Long deptId);

    /**
     * 分页查询部门列表
     * @param page
     * @param deptName
     * @param deptIdList
     * @param status
     * @return
     */
    List<SysDeptListVo> queryByPage(
            Page<SysDeptListVo> page,
            @Param("deptName") String deptName,
            @Param("deptIdList") List<Long> deptIdList,
            @Param("status") Integer status);

    /**
     * 根据部门ID查询该部门下所有的子部门ID
     * @param deptId
     * @return
     */
    List<Long> getChildDeptIdList(@Param("deptId") Long deptId);

    /**
     * 根据系统部门ID获取系统部门详情
     * @param deptId
     * @return
     */
    SysDeptInfoVo getSysDeptInfoVo(Long deptId);

    /**
     * 获取上级部门名称
     * @param parentId
     * @return
     */
    String getParentName(@Param("parentName") Long parentId);
}
