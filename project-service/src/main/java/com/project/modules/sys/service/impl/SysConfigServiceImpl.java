package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.sys.dao.SysConfigDao;
import com.project.modules.sys.entity.SysConfigEntity;
import com.project.modules.sys.service.SysConfigService;
import com.project.modules.sys.vo.info.SysConfigInfoVo;
import com.project.modules.sys.vo.list.SysConfigListVo;
import com.project.modules.sys.vo.save.SysConfigSaveVo;
import com.project.modules.sys.vo.update.SysConfigUpdateVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author liangyuding
 * @date 2020-03-19
 */
@Slf4j
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;

    /**
     * 校验配置名称是否存在
     * @param name
     * @param configId
     */
    @Override
    public void checkNameRepeat(String name, Long configId) {
        if (baseMapper.checkNameRepeat(StringUtils.trim(name), configId) > 0){
            throw new RRException("配置名称已存在,请重新输入");
        }
    }

    /**
     * 校验配置编码是否存在
     * @param code
     * @param configId
     */
    @Override
    public void checkCodeRepeat(String code, Long configId) {
        if (baseMapper.checkCodeRepeat(StringUtils.trim(code), configId) > 0){
            throw new RRException("配置编码已存在,请重新输入");
        }
    }

    /**
     * 分页查询系统配置信息
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysConfigListVo> page = new Query<SysConfigListVo>(params).getPage();
        List<SysConfigListVo> sysConfigListVos = baseMapper.queryPage(page, MapUtils.getString(params, "name"));
//        sysConfigListVos.forEach(sysConfigListVo -> {
//            sysConfigListVo.setValue(JsonUtil.toJson(sysConfigListVo.getValue()));
//
//        });
        return new PageUtils(page.setRecords(sysConfigListVos));
    }

    /**
     * 新增系统配置
     * @param config
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(SysConfigSaveVo config, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        save(getSaveSysConfigEntity(config, sysUserId));
        redisUtils.set(RedisKeys.Sys.Config(config.getCode()), config.getValue());
    }

    /**
     * 根据configId获取系统配置信息
     * @param configId
     * @return
     */
    @Override
    public SysConfigInfoVo info(Long configId) {
        return baseMapper.info(configId);
    }

    /**
     * 更新系统配置
     * @param config
     * @param userId
     */
    @Override
    @Transactional
    public void updateSysConfigUpdateVo(SysConfigUpdateVo config, Long userId) {
        //去给Bean里的String属性去空格操作
        try {
            trimUtils.beanValueTrim(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateById(getUpdateSysConfigEntity(config, userId));
        updateRedis();
    }

    /**
     * 修改系统配置状态
     * @param configId
     * @param status
     * @param userId
     */
    @Override
    @Transactional
    public void changeStatus(Long configId, Integer status, Long userId) {
        SysConfigEntity sysConfigEntity = baseMapper.getSysConfigEntityById(configId);
        checkUtils.checkEntityNotNull(sysConfigEntity);
        sysConfigEntity.setStatus(status).setUpdateUserId(userId).setUpdateTime(new Date());
        updateById(sysConfigEntity);
        updateRedis();
    }

    /**
     * 获取配置好的默认ID
     * @param code
     * @return
     */
    @Override
    public String getDefaultValue(String code) {
        return baseMapper.getDefaultValue(code, Constant.Status.NORMAL.getStatus());
    }

    //新增redis缓存数据
    private void updateRedis() {
        List<SysConfigEntity> sysConfigEntities = list(new QueryWrapper<SysConfigEntity>().eq("status", Constant.Status.NORMAL.getStatus()));
        sysConfigEntities.forEach(sysConfigEntity -> {
            redisUtils.delete(RedisKeys.Sys.Config(sysConfigEntity.getCode()));
            redisUtils.set(RedisKeys.Sys.Config(sysConfigEntity.getCode()), sysConfigEntity.getValue());
        });
    }

    //设置更新的配置信息
    private SysConfigEntity getUpdateSysConfigEntity(SysConfigUpdateVo config, Long userId) {
        //操作前校验非空
        checkUtils.configNotNull(config.getName(), config.getCode(), config.getValue(), config.getMemo());
        checkNameRepeat(config.getName(), config.getConfigId());
        checkCodeRepeat(config.getCode(), config.getConfigId());
        SysConfigEntity sysConfigEntity = baseMapper.getSysConfigEntityById(config.getConfigId());
        checkUtils.checkEntityNotNull(sysConfigEntity);
        sysConfigEntity
                .setName(config.getName())
                .setCode(config.getCode())
                .setValue(config.getValue())
                .setMemo(config.getMemo())
                .setUpdateUserId(userId).setUpdateTime(new Date());
        return sysConfigEntity;
    }

    //设置新增的配置信息
    private SysConfigEntity getSaveSysConfigEntity(SysConfigSaveVo config, Long sysUserId) {
        //操作前校验非空
        checkUtils.configNotNull(config.getName(), config.getCode(), config.getValue(), config.getMemo());
        checkNameRepeat(config.getName(), null);
        checkCodeRepeat(config.getCode(), null);
        SysConfigEntity sysConfigEntity = new SysConfigEntity();
        sysConfigEntity
                .setName(config.getName()).setCode(config.getCode())
                .setValue(config.getValue())
                .setMemo(config.getMemo())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setCreateUserId(sysUserId).setUpdateUserId(sysUserId);
        return sysConfigEntity;
    }
}
