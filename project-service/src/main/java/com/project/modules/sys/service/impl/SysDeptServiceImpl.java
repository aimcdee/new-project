package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.exception.RRException;
import com.project.modules.sys.dao.SysDeptDao;
import com.project.modules.sys.entity.SysDeptEntity;
import com.project.modules.sys.service.SysCheckInvokingService;
import com.project.modules.sys.service.SysConfigService;
import com.project.modules.sys.service.SysDeptService;
import com.project.modules.sys.service.SysRoleDeptService;
import com.project.modules.sys.vo.info.SysDeptInfoVo;
import com.project.modules.sys.vo.list.SysDeptListVo;
import com.project.modules.sys.vo.list.SysDeptTreeVo;
import com.project.modules.sys.vo.save.SysDeptSaveVo;
import com.project.modules.sys.vo.update.SysDeptUpdateVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 部门Service
 *
 * @author liangyuding
 * @date 2020-03-11
 */

@Slf4j
@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    @Autowired
    private SysRoleDeptService sysRoleDeptService;
    @Autowired
    private SysCheckInvokingService sysCheckInvokingService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查看树形部门列表
     * @param params
     * @return
     */
    @Override
    public List<SysDeptTreeVo> getTreeList(Map<String, Object> params) {
        List<SysDeptTreeVo> sysDeptTreeVoList = baseMapper.getSysDeptTreeList();
        if (CollectionUtils.isNotEmpty(sysDeptTreeVoList)) {
            sysDeptTreeVoList.stream().forEach(sysDeptTreeVo -> {
                sysDeptTreeVo.setParentName(Optional.ofNullable(
                        getOne(new QueryWrapper<SysDeptEntity>().eq("dept_id", sysDeptTreeVo.getParentId()).last("LIMIT 1")))
                        .map(SysDeptEntity::getDeptName)
                        .orElse(null));
            });
        }
        return sysDeptTreeVoList;
    }

    /**
     * 校验部门名称是否已存在
     * @param deptName
     * @param deptId
     */
    @Override
    public void checkNameRepeat(String deptName, Long deptId) {
        if (baseMapper.checkDeptNameRepeat(com.project.utils.StringUtils.trim(deptName), deptId) > 0){
            throw new RRException("该部门名称已存在,请重新输入");
        }
    }

    /**
     * 分页查询部门列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysDeptListVo> page = new Query<SysDeptListVo>(params).getPage();
        List<SysDeptListVo> sysUserListVos = baseMapper.queryByPage(
                page, StringUtils.trim(MapUtils.getString(params, "deptName")),
                getDeptIdList(new ArrayList<>(), MapUtils.getLong(params, "deptId")),
                MapUtils.getInteger(params, "status"));
        return new PageUtils(page.setRecords(sysUserListVos));
    }

    //获取该系统部门及所有的子部门
    private List<Long> getDeptIdList(List<Long> deptIdList, Long deptId) {
        if (ObjectUtils.isEmpty(deptId)){
            String redisMsg = redisUtils.get(RedisKeys.Sys.Config(RedisKeyConstant.DEFAUL_DEPT));
            deptId = StringUtils.isNotBlank(redisMsg) ? Long.parseLong(redisMsg) : sysConfigService.getDefaultValue(RedisKeyConstant.DEFAUL_DEPT);
            if (StringUtils.isBlank(redisMsg)){
                redisUtils.set(RedisKeys.Sys.Config(RedisKeyConstant.DEFAUL_DEPT), deptId);
            }
        }
        deptIdList.add(deptId);
        //获取该部门的子部门
        List<Long> childDeptIdList = getChildDeptIdList(deptId);
        if (CollectionUtils.isNotEmpty(childDeptIdList)) {
            childDeptIdList.forEach(childDeptId -> {
                getDeptIdList(deptIdList, childDeptId);
            });
        }
        return deptIdList;
    }

    /**
     * 新增系统部门
     * @param dept
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveSysDept(SysDeptSaveVo dept, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(dept);
        } catch (Exception e) {
            e.printStackTrace();
        }
        save(getSaveSysDeptEntity(dept, sysUserId));
    }

    /**
     * 根据系统部门ID获取系统部门详情
     * @param deptId
     * @return
     */
    @Override
    public SysDeptInfoVo getSysDeptInfoVo(Long deptId) {
        SysDeptInfoVo sysDeptInfoVo = baseMapper.getSysDeptInfoVo(deptId);
        sysDeptInfoVo.setParentName(baseMapper.getParentName(sysDeptInfoVo.getParentId()));
        return sysDeptInfoVo;
    }

    /**
     * 修改系统部门
     * @param dept
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateSysDept(SysDeptUpdateVo dept, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(dept);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateById(getUpdateSysDeptEntity(dept, sysUserId));
    }


    /**
     * 修改系统部门状态
     * @param deptId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long deptId, Integer status, Long sysUserId) {
//        SysDeptEntity sysDeptEntity = getOne(new QueryWrapper<SysDeptEntity>().eq(Objects.nonNull(deptId),"dept_id", deptId).last("LIMIT 1"));
        SysDeptEntity sysDeptEntity = getOne(new QueryWrapper<SysDeptEntity>().eq(ObjectUtils.isNotBlank(deptId),"dept_id", deptId).last("LIMIT 1"));
        //校验对象是否存在
        checkUtils.checkEntityNotNull(sysDeptEntity);
        sysDeptEntity.setStatus(status).setUpdateUserId(sysUserId).setUpdateTime(new Date());
        updateById(sysDeptEntity);
    }

    /**
     * 删除部门
     * @param deptId
     */
    @Override
    @Transactional
    public void delete(Long deptId) {
        //校验该角色是否被使用
        sysCheckInvokingService.checkDeptIdIsUse(deptId);
        //删除系统部门与系统角色关系
        sysRoleDeptService.deleteSysRoleDeptByDeptId(deptId);
        //删除系统部门
        remove(new QueryWrapper<SysDeptEntity>().eq("dept_id", deptId));
    }

    /**
     * 根据部门ID查询该部门下所有的子部门ID
     * @param deptId
     * @return
     */
    @Override
    public List<Long> getChildDeptIdList(Long deptId) {
        return baseMapper.getChildDeptIdList(deptId);
    }

    //获取并设置SysDeptEntity更新对象
    private SysDeptEntity getUpdateSysDeptEntity(SysDeptUpdateVo dept, Long sysUserId) {
        //校验部门名称是否存在
        checkNameRepeat(dept.getDeptName(), dept.getDeptId());
        //非空校验
        checkUtils.checkDeptNotNull(dept.getDeptName(), dept.getParentId());
//        SysDeptEntity sysDeptEntity = getOne(new QueryWrapper<SysDeptEntity>().eq(Objects.nonNull(dept.getDeptId()),"dept_id", dept.getDeptId()).last("LIMIT 1"));
        SysDeptEntity sysDeptEntity = getOne(new QueryWrapper<SysDeptEntity>().eq(ObjectUtils.isNotBlank(dept.getDeptId()),"dept_id", dept.getDeptId()).last("LIMIT 1"));
        //校验对象是否存在
        checkUtils.checkEntityNotNull(sysDeptEntity);
        sysDeptEntity.setDeptName(dept.getDeptName()).setParentId(dept.getParentId()).setUpdateUserId(sysUserId).setUpdateTime(new Date());
        return sysDeptEntity;
    }

    //设置SysDeptEntity新增对象
    private SysDeptEntity getSaveSysDeptEntity(SysDeptSaveVo dept, Long sysUserId) {
        //非空校验
        checkUtils.checkDeptNotNull(dept.getDeptName(), dept.getParentId());
        checkNameRepeat(dept.getDeptName(), null);
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        sysDeptEntity
                .setDeptName(dept.getDeptName())
                .setParentId(dept.getParentId())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return sysDeptEntity;
    }
}
