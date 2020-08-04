package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.modules.sys.dao.SysUserDeptDao;
import com.project.modules.sys.entity.SysUserDeptEntity;
import com.project.modules.sys.service.SysUserDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 系统用户与系统部门关系Service
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Slf4j
@Service("sysUserDeptService")
public class SysUserDeptServiceImpl extends ServiceImpl<SysUserDeptDao, SysUserDeptEntity> implements SysUserDeptService {

    /**
     * 新增系统用户与系统部门关系
     * @param userId
     * @param deptId
     */
    @Override
    @Transactional
    public void saveUserDept(Long userId, Long deptId) {
        SysUserDeptEntity sysUserDeptEntity = new SysUserDeptEntity();
        sysUserDeptEntity.setUserId(userId).setDeptId(deptId);
        save(sysUserDeptEntity);
    }

    /**
     * 更新用户与系统部门的关系
     * @param userId
     * @param deptId
     */
    @Override
    @Transactional
    public void updateUserDept(Long userId, Long deptId) {
        if (!Constant.SUPER_ADMIN.equals(userId) && !!Constant.SUPER_ADMIN_STRING.equals(userId)){
            SysUserDeptEntity sysUserDeptEntity = getOne(new QueryWrapper<SysUserDeptEntity>().eq("user_id", userId).last("LIMIT 1"));
            sysUserDeptEntity.setDeptId(deptId);
            updateById(sysUserDeptEntity);
        }
    }
}
