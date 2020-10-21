package com.project.modules.cou.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisListKeyConstant;
import com.project.modules.cou.dao.CouWaresSeriesDao;
import com.project.modules.cou.entity.CouWaresSeriesEntity;
import com.project.modules.cou.service.CouWaresSeriesService;
import com.project.modules.cou.vo.Invoking.CouWaresSeriesInvokingVo;
import com.project.modules.cou.vo.info.CouWaresSeriesInfoVo;
import com.project.modules.cou.vo.list.CouWaresSeriesListVo;
import com.project.modules.cou.vo.save.CouWaresSeriesSaveVo;
import com.project.modules.cou.vo.update.CouWaresSeriesUpdateVo;
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
 * 系列Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@Service("couWaresSeriesService")
public class CouWaresSeriesServiceImpl extends ServiceImpl<CouWaresSeriesDao, CouWaresSeriesEntity> implements CouWaresSeriesService {

    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分页查询系列列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CouWaresSeriesListVo> page = new Query<CouWaresSeriesListVo>(params).getPage();
        List<CouWaresSeriesListVo> couWaresSeriesListVos = baseMapper.queryPage(
                page, MapUtils.getLong(params, "couBrandId"),
                StringUtils.trim(MapUtils.getString(params, "couSeriesName")),
                MapUtils.getInteger(params, "status"));
        return new PageUtils(page.setRecords(couWaresSeriesListVos));
    }

    /**
     * 新增系列
     * @param series
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(CouWaresSeriesSaveVo series, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验新增对象属性非空
        checkUtils.checkNotNull(series);
        save(getCouWaresSeriesSaveEntity(series, sysUserId));
        //更新redis上的列表信息
        updateRedis(series.getCouBrandId());
    }

    /**
     * 根据系列ID获取系列详情
     * @param couSeriesId
     * @return
     */
    @Override
    public CouWaresSeriesInfoVo info(Long couSeriesId) {
        return baseMapper.info(couSeriesId);
    }

    /**
     * 更新系列
     * @param series
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateEntity(CouWaresSeriesUpdateVo series, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验更新对象属性非空
        checkUtils.checkNotNull(series);
        updateById(getCouWaresSeriesUpdateEntity(series, sysUserId));
        //更新redis上的列表信息
        updateRedis(series.getCouBrandId());
    }

    /**
     * 修改系列的状态
     * @param couSeriesId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long couSeriesId, Integer status, Long sysUserId) {
        CouWaresSeriesEntity couWaresSeriesEntity = getOne(new QueryWrapper<CouWaresSeriesEntity>().eq("cou_series_id", couSeriesId).last("LIMIT 1"));
        //校验更新对象属性非空
        checkUtils.checkEntityNotNull(couWaresSeriesEntity);
        couWaresSeriesEntity.setStatus(status).setUpdateUserId(sysUserId).setUpdateTime(new Date());
        updateById(couWaresSeriesEntity);
        //更新redis上的列表信息
        updateRedis(couWaresSeriesEntity.getCouBrandId());
    }

    /**
     * 根据品牌ID获取所有状态为正常系列的ID和名称
     * @param couBrandId
     * @return
     */
    @Override
    public List<CouWaresSeriesInvokingVo> getCouSeriesList(Long couBrandId) {
        List<CouWaresSeriesInvokingVo> seriesList = JSONArray.parseArray(redisUtils.get(RedisKeys.CouWares.CouSeries(new StringBuilder().append(RedisListKeyConstant.COU_SERIES_LIST).append("_").append(couBrandId).toString())), CouWaresSeriesInvokingVo.class);
        seriesList = CollectionUtils.isEmpty(seriesList) ? baseMapper.getCouSeriesList(couBrandId, Constant.StatusEnums.NORMAL.getStatus()) : seriesList;
        redisUtils.set(RedisKeys.CouWares.CouSeries(new StringBuilder().append(RedisListKeyConstant.COU_SERIES_LIST).append("_").append(couBrandId).toString()), seriesList);
        return seriesList;
    }

    //更新redis上的系列列表信息
    private void updateRedis(Long couBrandId) {
        redisUtils.delete(RedisKeys.CouWares.CouSeries(new StringBuilder().append(RedisListKeyConstant.COU_SERIES_LIST).append("_").append(couBrandId).toString()));
        redisUtils.set(RedisKeys.CouWares.CouSeries(new StringBuilder().append(RedisListKeyConstant.COU_SERIES_LIST).append("_").append(couBrandId).toString()), baseMapper.getCouSeriesList(couBrandId, Constant.StatusEnums.NORMAL.getStatus()));
    }

    //获取CouWaresSeriesEntity更新对象
    private CouWaresSeriesEntity getCouWaresSeriesUpdateEntity(CouWaresSeriesUpdateVo series, Long sysUserId) {
        CouWaresSeriesEntity couWaresSeriesEntity = getOne(new QueryWrapper<CouWaresSeriesEntity>().eq("cou_series_id", series.getCouSeriesId()).last("LIMIT 1"));
        //校验更新对象属性非空
        checkUtils.checkEntityNotNull(couWaresSeriesEntity);
        couWaresSeriesEntity
                .setCouSeriesName(series.getCouSeriesName())
                .setCouBrandId(series.getCouBrandId())
                .setUpdateUserId(sysUserId);
        return couWaresSeriesEntity;
    }

    //获取CouWaresSeriesEntity新增对象
    private CouWaresSeriesEntity getCouWaresSeriesSaveEntity(CouWaresSeriesSaveVo series, Long sysUserId) {
        CouWaresSeriesEntity couWaresSeriesEntity = new CouWaresSeriesEntity();
        //校验更新对象属性非空
        checkUtils.checkEntityNotNull(couWaresSeriesEntity);
        couWaresSeriesEntity
                .setCouSeriesName(series.getCouSeriesName())
                .setCouBrandId(series.getCouBrandId())
                .setStatus(Constant.StatusEnums.NORMAL.getStatus())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return couWaresSeriesEntity;
    }
}
