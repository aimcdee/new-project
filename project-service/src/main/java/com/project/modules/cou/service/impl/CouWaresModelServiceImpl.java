package com.project.modules.cou.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisListKeyConstant;
import com.project.modules.cou.dao.CouWaresModelDao;
import com.project.modules.cou.entity.CouWaresModelEntity;
import com.project.modules.cou.service.CouWaresModelService;
import com.project.modules.cou.vo.Invoking.CouWaresModelInvokingVo;
import com.project.modules.cou.vo.info.CouWaresModelInfoVo;
import com.project.modules.cou.vo.list.CouWaresModelListVo;
import com.project.modules.cou.vo.save.CouWaresModelSaveVo;
import com.project.modules.cou.vo.update.CouWaresModelUpdateVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品型号Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@Service("couWaresModelService")
public class CouWaresModelServiceImpl extends ServiceImpl<CouWaresModelDao, CouWaresModelEntity> implements CouWaresModelService {


    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分页查询商品型号列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CouWaresModelListVo> page = new Query<CouWaresModelListVo>(params).getPage();
        List<CouWaresModelListVo> couWaresModelListVos = baseMapper.queryPage(page, StringUtils.trim(MapUtils.getString(params, "couModelName")), MapUtils.getInteger(params, "status"));
        return new PageUtils(page.setRecords(couWaresModelListVos));
    }

    /**
     * 新增商品型号
     * @param waresModel
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(CouWaresModelSaveVo waresModel, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(waresModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验更新对象属性非空
        checkUtils.checkNotNull(waresModel);
        save(getCouWaresModelSaveEntity(waresModel, sysUserId));
        updateRedis();
    }

    /**
     * 根据商品型号ID获取商品型号详情
     * @param couModelId
     * @return
     */
    @Override
    public CouWaresModelInfoVo info(Long couModelId) {
        return baseMapper.info(couModelId);
    }

    /**
     * 更新商品型号
     * @param model
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateEntity(CouWaresModelUpdateVo model, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验更新对象属性非空
        checkUtils.checkNotNull(model);
        CouWaresModelEntity couWaresModelEntity = getCouWaresModelUpdateEntity(model, sysUserId);
        updateById(couWaresModelEntity);
        updateRedis();
    }

    /**
     * 修改商品型号的状态
     * @param couModelId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long couModelId, Integer status, Long sysUserId) {
        CouWaresModelEntity couWaresModelEntity = getOne(new QueryWrapper<CouWaresModelEntity>().eq("cou_model_id", couModelId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(couWaresModelEntity);
        couWaresModelEntity.setStatus(status).setUpdateUserId(sysUserId).setUpdateTime(new Date());
        updateById(couWaresModelEntity);
        updateRedis();
    }

    /**
     * 获取所有状态为正常商品型号的ID和名称
     * @return
     */
    @Override
    public List<CouWaresModelInvokingVo> getCouModelList() {
        List<CouWaresModelInvokingVo> modelList = JSONArray.parseArray(redisUtils.get(RedisKeys.CouWares.CouModel(RedisListKeyConstant.COU_MODEL_LIST)), CouWaresModelInvokingVo.class);
        modelList = CollectionUtils.isNotEmpty(modelList) ? modelList : baseMapper.getCouModelList(Constant.Status.NORMAL.getStatus());
        redisUtils.set(RedisKeys.CouWares.CouSeries(RedisListKeyConstant.COU_MODEL_LIST), modelList);
        return modelList;
    }

    //更新redis上的列表信息
    private void updateRedis() {
        redisUtils.delete(RedisKeys.CouWares.CouModel(RedisListKeyConstant.COU_MODEL_LIST));
        redisUtils.set(RedisKeys.CouWares.CouModel(RedisListKeyConstant.COU_MODEL_LIST), baseMapper.getCouModelList(Constant.Status.NORMAL.getStatus()));
    }

    //获取DealWaresTypeEntity更新对象
    private CouWaresModelEntity getCouWaresModelUpdateEntity(CouWaresModelUpdateVo model, Long sysUserId) {
        CouWaresModelEntity couWaresModelEntity = getOne(new QueryWrapper<CouWaresModelEntity>().eq("cou_model_id", model.getCouModelId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(couWaresModelEntity);
        couWaresModelEntity
                .setCouModelName(model.getCouModelName())
                .setImage(model.getImage())
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        return couWaresModelEntity;
    }

    //获取DealWaresTypeEntity新增对象
    private CouWaresModelEntity getCouWaresModelSaveEntity(CouWaresModelSaveVo model, Long sysUserId) {
        CouWaresModelEntity couWaresModelEntity = new CouWaresModelEntity();
        couWaresModelEntity
                .setCouModelName(model.getCouModelName())
                .setImage(model.getImage())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return couWaresModelEntity;
    }
}
