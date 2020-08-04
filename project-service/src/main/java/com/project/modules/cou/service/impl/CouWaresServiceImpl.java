package com.project.modules.cou.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisListKeyConstant;
import com.project.modules.cou.dao.CouWaresDao;
import com.project.modules.cou.entity.CouWaresEntity;
import com.project.modules.cou.service.CouInvokingService;
import com.project.modules.cou.service.CouWaresService;
import com.project.modules.cou.vo.Invoking.CouWaresInvokingVo;
import com.project.modules.cou.vo.info.CouWaresInfoVo;
import com.project.modules.cou.vo.list.CouWaresListVo;
import com.project.modules.cou.vo.save.CouWaresSaveVo;
import com.project.modules.cou.vo.update.CouWaresUpdateVo;
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
 * 商品Service
 *
 * @author liangyuding
 * @date 2020-05-14
 */
@Slf4j
@Service("couWaresService")
public class CouWaresServiceImpl extends ServiceImpl<CouWaresDao, CouWaresEntity> implements CouWaresService {

    @Autowired
    private CouInvokingService couInvokingService;

    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分页查询商品列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CouWaresListVo> page = new Query<CouWaresListVo>(params).getPage();
        List<CouWaresListVo> couWaresListVos = baseMapper.queryPage(
                page,
                MapUtils.getLong(params, "couBrandId"),
                MapUtils.getLong(params, "couSeriesId"),
                MapUtils.getLong(params, "couModelId"),
                StringUtils.trim(MapUtils.getString(params, "couWaresName")),
                MapUtils.getInteger(params, "status"));
        couWaresListVos.forEach(couWaresListVo -> {
            couWaresListVo
                    .setCouBrandName(couInvokingService.getCouBrandNameById(couWaresListVo.getCouBrandId()))
                    .setCouSeriesName(couInvokingService.getCouSeriesNameById(couWaresListVo.getCouSeriesId()))
                    .setCouModelName(couInvokingService.getCouModelNameById(couWaresListVo.getCouModelId()));
        });
        return new PageUtils(page.setRecords(couWaresListVos));
    }

    /**
     * 新增商品
     * @param wares
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(CouWaresSaveVo wares, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(wares);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkUtils.checkNotNull(wares);
        CouWaresEntity couWaresEntity = getCouWaresSaveEntity(wares, sysUserId);
        save(couWaresEntity);
        updateRedis(couWaresEntity.getCouSeriesId());
    }

    /**
     * 根据商品ID获取商品详情
     * @param waresId
     * @return
     */
    @Override
    public CouWaresInfoVo info(Long waresId) {
        CouWaresInfoVo couWaresInfoVo = baseMapper.info(waresId);
        couWaresInfoVo
                .setCouBrandName(couInvokingService.getCouBrandNameById(couWaresInfoVo.getCouBrandId()))
                .setCouSeriesName(couInvokingService.getCouSeriesNameById(couWaresInfoVo.getCouSeriesId()))
                .setCouModelName(couInvokingService.getCouModelNameById(couWaresInfoVo.getCouModelId()));
        return couWaresInfoVo;
    }

    /**
     * 更新商品
     * @param wares
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateEntity(CouWaresUpdateVo wares, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(wares);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkUtils.checkNotNull(wares);
        CouWaresEntity couWaresEntity = getCouWaresUpdateEntity(wares, sysUserId);
        updateById(couWaresEntity);
        updateRedis(couWaresEntity.getCouSeriesId());
    }

    /**
     * 修改商品的状态
     * @param waresId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long waresId, Integer status, Long sysUserId) {
        CouWaresEntity couWaresEntity = getOne(new QueryWrapper<CouWaresEntity>().eq("cou_wares_id", waresId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(couWaresEntity);
        couWaresEntity.setStatus(status).setUpdateUserId(sysUserId).setUpdateTime(new Date());
        updateById(couWaresEntity);
        updateRedis(couWaresEntity.getCouSeriesId());
    }

    /**
     * 根据品牌系列ID获取所有状态为正常商品对象
     * @param couSeriesId
     * @return
     */
    @Override
    public List<CouWaresInvokingVo> getCouWaresList(Long couSeriesId) {
        List<CouWaresInvokingVo> queryWaresList = JSONArray.parseArray(redisUtils.get(RedisKeys.CouWares.CouWares(new StringBuilder().append(RedisListKeyConstant.COU_WARES_LIST).append("_").append(couSeriesId).toString())), CouWaresInvokingVo.class);
        List<CouWaresInvokingVo>  waresList = CollectionUtils.isNotEmpty(queryWaresList) ? queryWaresList : baseMapper.getCouWaresList(couSeriesId, Constant.Status.NORMAL.getStatus());
        redisUtils.set(RedisKeys.CouWares.CouSeries(new StringBuilder().append(RedisListKeyConstant.COU_WARES_LIST).append("_").append(couSeriesId).toString()), waresList);
        return waresList;
    }

    //更新redis上的列表信息
    private void updateRedis(Long couSeriesId) {
        redisUtils.delete(RedisKeys.CouWares.CouWares(new StringBuilder().append(RedisListKeyConstant.COU_WARES_LIST).append("_").append(couSeriesId).toString()));
        redisUtils.set(RedisKeys.CouWares.CouWares(new StringBuilder().append(RedisListKeyConstant.COU_WARES_LIST).append("_").append(couSeriesId).toString()), baseMapper.getCouWaresList(couSeriesId, Constant.Status.NORMAL.getStatus()));
    }

    //获取CouWaresEntity更新对象
    private CouWaresEntity getCouWaresUpdateEntity(CouWaresUpdateVo wares, Long sysUserId) {
        CouWaresEntity couWaresEntity = getOne(new QueryWrapper<CouWaresEntity>().eq("cou_wares_id", wares.getCouWaresId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(couWaresEntity);
        couWaresEntity
                .setCouWaresName(wares.getCouWaresName())
                .setCouBrandId(wares.getCouSeriesId())
                .setCouSeriesId(wares.getCouSeriesId())
                .setCouModelId(wares.getCouModelId())
                .setCouWaresPrice(wares.getCouWaresPrice())
                .setMarketYear(wares.getMarketYear())
                .setMarketTime(wares.getMarketTime())
                .setDisMent(wares.getDisMent())
                .setVarBox(wares.getVarBox())
                .setDrive(wares.getDrive())
                .setConsume(wares.getConsume())
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        return couWaresEntity;
    }

    //获取CouWaresEntity新增对象
    private CouWaresEntity getCouWaresSaveEntity(CouWaresSaveVo wares, Long sysUserId) {
        CouWaresEntity couWaresEntity = new CouWaresEntity();
        couWaresEntity
                .setCouWaresName(wares.getCouWaresName())
                .setCouBrandId(wares.getCouSeriesId())
                .setCouSeriesId(wares.getCouSeriesId())
                .setCouModelId(wares.getCouModelId())
                .setCouWaresPrice(wares.getCouWaresPrice())
                .setMarketYear(wares.getMarketYear())
                .setMarketTime(wares.getMarketTime())
                .setDisMent(wares.getDisMent())
                .setVarBox(wares.getVarBox())
                .setDrive(wares.getDrive())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setConsume(wares.getConsume())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return couWaresEntity;
    }
}
