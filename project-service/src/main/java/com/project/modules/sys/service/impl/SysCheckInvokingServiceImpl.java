package com.project.modules.sys.service.impl;

import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.sys.dao.SysCheckInvokingDao;
import com.project.modules.sys.service.SysCheckInvokingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中间调用校验Service
 *
 * @author liangyuding
 * @date 2020-03-17
 */
@Slf4j
@Service("sysCheckInvokingService")
public class SysCheckInvokingServiceImpl implements SysCheckInvokingService {

    @Autowired
    private SysCheckInvokingDao sysCheckInvokingDao;

    /**
     * 根据菜单ID查询菜单是否存在
     * @param menuId
     */
    @Override
    public void checkMenuIdNotNull(Long menuId) {
        if (!Constant.DEFAUL_TROLE.equals(menuId) && sysCheckInvokingDao.checkMenuIdNotNull(menuId) <= 0){
            throw new RRException("菜单不存在");
        }
    }

    /**
     * 根据部门ID查询部门是否存在
     * @param deptId
     */
    @Override
    public void checkDeptIdNotNull(Long deptId) {
        if (!Constant.DEFAUL_TDEPT.equals(deptId) && sysCheckInvokingDao.checkDeptIdNotNull(deptId) <= 0){
            throw new RRException("部门不存在");
        }
    }

    /**
     * 校验该角色是否被用户使用
     * @param roleId
     */
    @Override
    public void checkRoleIdIsUse(Long roleId) {
        if (sysCheckInvokingDao.checkRoleIdUseForUser(roleId) > 0){
            throw new RRException("无法删除,该角色已被用户使用");
        }
    }

    /**
     * 校验该部门是否被用户使用
     * @param deptId
     */
    @Override
    public void checkDeptIdIsUse(Long deptId) {
        if (deptId == Constant.HEADQUARTERS){
            throw new RRException("总部无法删除");
        }
        if (sysCheckInvokingDao.checkDeptIdUseForUser(deptId) > 0){
            throw new RRException("无法删除,该部门已被用户使用");
        }
    }

    /**
     * 根据系统配置Id和系统配置编码查询系统设置是否存在
     *
     * @param infoId
     * @param code
     * @return
     */
    @Override
    public Integer getSysConfigEntityByInfoIdAndCode(Long infoId, String code) {
        return sysCheckInvokingDao.getSysConfigEntityByInfoIdAndCode(infoId, code);
    }
}
