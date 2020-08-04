package com.project.modules.conf.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.modules.conf.dao.ConfBannerDao;
import com.project.modules.conf.entity.ConfBannerEntity;
import com.project.modules.conf.service.ConfBannerService;
import com.project.modules.conf.service.ConfBannerWaresService;
import com.project.modules.conf.vo.info.ConfBannerInfoVo;
import com.project.modules.conf.vo.list.ConfBannerListVo;
import com.project.modules.conf.vo.save.ConfBannerSaveVo;
import com.project.modules.conf.vo.update.ConfBannerUpdateVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 轮播图Service
 *
 * @author liangyuding
 * @date 2020-04-14
 */
@Slf4j
@Service("confbannerService")
public class ConfBannerServiceImpl extends ServiceImpl<ConfBannerDao, ConfBannerEntity> implements ConfBannerService {

    @Autowired
    private ConfBannerWaresService confBannerWaresService;
    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分页轮播图列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ConfBannerListVo> page = new Query<ConfBannerListVo>(params).getPage();
        List<ConfBannerListVo> confBannerListVos = baseMapper.queryPage(
                page,
                StringUtils.trim(MapUtils.getString(params, "bannerName")),
                MapUtils.getInteger(params, "status"));
        confBannerListVos.forEach(confBannerListVo -> {
            confBannerListVo.setBannerWaresList(confBannerWaresService.getBannerWaresListByBannerId(confBannerListVo.getBannerId()));
        });
        return new PageUtils(page.setRecords(confBannerListVos));
    }

    /**
     * 新增轮播图
     * @param banner
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(ConfBannerSaveVo banner, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(banner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkUtils.checkNotNull(banner);
        //获取新增轮播图对象
        ConfBannerEntity confbannerEntity = getConfbannerSaveEntity(banner, sysUserId);
        //新增轮播图
        save(confbannerEntity);
        if (CollectionUtils.isNotEmpty(banner.getBannerWaresList())){
            banner.getBannerWaresList().forEach(bannerWare ->{
                //新增轮播企业商品关联及轮播图片路径
                confBannerWaresService.saveEntity(confbannerEntity.getBannerId(), bannerWare.getDealWaresId(), bannerWare.getImage());
            });
        }
        editRedis(Constant.Status.NORMAL.getStatus());
    }

    /**
     * 根据轮播图ID获取轮播图详情
     * @param bannerId
     * @return
     */
    @Override
    public ConfBannerInfoVo info(Long bannerId) {
        ConfBannerInfoVo confBannerInfoVo = baseMapper.getConfbannerInfoVo(bannerId);
        confBannerInfoVo.setBannerWaresList(confBannerWaresService.getBannerWaresListByBannerId(bannerId));
        return confBannerInfoVo;
    }

    /**
     * 更新轮播图
     * @param banner
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateEntity(ConfBannerUpdateVo banner, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(banner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkUtils.checkNotNull(banner);
        //获取更新轮播图对象
        ConfBannerEntity confbannerEntity = getConfbannerUpdateEntity(banner, sysUserId);
        //更新轮播图
        updateById(confbannerEntity);
        //更新轮播图
        updateById(confbannerEntity);
        if (CollectionUtils.isNotEmpty(banner.getBannerWaresList())){
            banner.getBannerWaresList().forEach(bannerWare ->{
                //更新轮播企业商品
                confBannerWaresService.updateEntity(confbannerEntity.getBannerId(), bannerWare.getDealWaresId(), bannerWare.getImage());
            });
        }
        editRedis(Constant.Status.NORMAL.getStatus());
    }

    /**
     * 修改轮播图的状态
     * @param bannerId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long bannerId, Integer status, Long sysUserId) {
        //获取ConfbannerEntity对象
        ConfBannerEntity confbannerEntity = getConfbannerEntity(bannerId);
        confbannerEntity.setStatus(status).setUpdateUserId(sysUserId).setUpdateTime(new Date());
        //更新轮播图
        updateById(confbannerEntity);
        editRedis(Constant.Status.NORMAL.getStatus());
    }

    /**
     * 查询状态为正常的轮播图集合
     * @param status
     * @return
     */
    @Override
    public List<ConfBannerInfoVo> normalList(Integer status) {
        List<ConfBannerInfoVo> getRedisConfBannerInfoVos = JSONArray.parseArray(redisUtils.get(RedisKeys.ConfBanner.ConfBanner(String.valueOf(status))), ConfBannerInfoVo.class);
        List<ConfBannerInfoVo> confBannerInfoVos = CollectionUtils.isNotEmpty(getRedisConfBannerInfoVos) ? getRedisConfBannerInfoVos : new ArrayList<>();
        if (CollectionUtils.isEmpty(confBannerInfoVos)){
            confBannerInfoVos = baseMapper.normalList(status);
            confBannerInfoVos.forEach(confBannerInfoVo -> {
                confBannerInfoVo.setBannerWaresList(confBannerWaresService.getBannerWaresListByBannerId(confBannerInfoVo.getBannerId()));
            });
            redisUtils.set(RedisKeys.ConfBanner.ConfBanner(String.valueOf(status)), confBannerInfoVos);
        }
        return confBannerInfoVos;
    }

    //对redis操作
    private void editRedis(Integer status) {
        redisUtils.delete(RedisKeys.ConfBanner.ConfBanner(String.valueOf(status)));
        List<ConfBannerInfoVo> confBannerInfoVos = baseMapper.normalList(status);
        confBannerInfoVos.forEach(confBannerInfoVo -> {
            confBannerInfoVo.setBannerWaresList(confBannerWaresService.getBannerWaresListByBannerId(confBannerInfoVo.getBannerId()));
        });
        redisUtils.set(RedisKeys.ConfBanner.ConfBanner(String.valueOf(status)), confBannerInfoVos);
    }

    //设置更新轮播图对象
    private ConfBannerEntity getConfbannerUpdateEntity(ConfBannerUpdateVo banner, Long sysUserId) {
        //获取ConfbannerEntity对象
        ConfBannerEntity confbannerEntity = getConfbannerEntity(banner.getBannerId());
        confbannerEntity
                .setBannerName(banner.getName())
                .setSort(banner.getSort())
                .setUpdateUserId(sysUserId);
        return confbannerEntity;
    }

    //查询ConfbannerEntity对象
    private ConfBannerEntity getConfbannerEntity(Long bannerId) {
        ConfBannerEntity confbannerEntity = getOne(new QueryWrapper<ConfBannerEntity>().eq("banner_id", bannerId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(confbannerEntity);
        return confbannerEntity;
    }

    //设置新增轮播图对象
    private ConfBannerEntity getConfbannerSaveEntity(ConfBannerSaveVo banner, Long sysUserId) {
        ConfBannerEntity confbannerEntity = new ConfBannerEntity();
        confbannerEntity
                .setBannerName(banner.getBannerName())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setSort(banner.getSort())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return confbannerEntity;
    }
}
