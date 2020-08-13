package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysDeptEntity;
import com.project.modules.sys.vo.info.SysDeptInfoVo;
import com.project.modules.sys.vo.list.SysDeptTreeVo;
import com.project.modules.sys.vo.save.SysDeptSaveVo;
import com.project.modules.sys.vo.update.SysDeptUpdateVo;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 部门Service
 *
 * @author liangyuding
 * @date 2020-03-10
 */

public interface SysDeptService extends IService<SysDeptEntity> {

    /**
     * 查看树形部门列表
     *
     * @param params
     * @return getTreeList
     */
    List<SysDeptTreeVo> getTreeList(Map<String, Object> params);

    /**
     * 校验部门名称是否已存在
     * @param deptName
     * @param deptId
     */
    void checkNameRepeat(String deptName, Long deptId);

    /**
     * 分页查询部门列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增系统部门
     * @param dept
     * @param sysUserId
     */
    void saveSysDept(SysDeptSaveVo dept, Long sysUserId);

    /**
     * 根据系统部门ID获取系统部门详情
     * @param deptId
     * @return
     */
    SysDeptInfoVo getSysDeptInfoVo(Long deptId);

    /**
     * 更新系统部门
     * @param dept
     * @param sysUserId
     */
    void updateSysDept(SysDeptUpdateVo dept, Long sysUserId);

    /**
     * 修改系统部门的状态
     * @param deptId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long deptId, Integer status, Long sysUserId);

    /**
     * 删除系统部门
     * @param deptId
     */
    void delete(Long deptId);

    /**
     * 根据部门ID查询该部门下所有的子部门ID
     * @param deptId
     * @return
     */
    List<Long> getChildDeptIdList(Long deptId);

}
