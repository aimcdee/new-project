package com.project.modules.cou.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisListKeyConstant;
import com.project.modules.cou.dao.CouWaresBrandDao;
import com.project.modules.cou.entity.CouWaresBrandEntity;
import com.project.modules.cou.service.CouWaresBrandService;
import com.project.modules.cou.vo.Invoking.CouWaresBrandInvokingVo;
import com.project.modules.cou.vo.info.CouWaresBrandInfoVo;
import com.project.modules.cou.vo.list.CouWaresBrandListVo;
import com.project.modules.cou.vo.save.CouWaresBrandSaveVo;
import com.project.modules.cou.vo.update.CouWaresBrandUpdateVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 商品品牌Service
 *
 * @author liangyuding
 * @date 2020-04-17
 */
@Slf4j
@Service("couWaresBrandService")
public class CouWaresBrandServiceImpl extends ServiceImpl<CouWaresBrandDao, CouWaresBrandEntity> implements CouWaresBrandService {

    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分页查询商品品牌列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CouWaresBrandListVo> page = new Query<CouWaresBrandListVo>(params).getPage();
        List<CouWaresBrandListVo> couWaresBrandListVos = baseMapper.queryPage(page, StringUtils.trim(MapUtils.getString(params, "couBrandName")), MapUtils.getInteger(params, "status"));
        return new PageUtils(page.setRecords(couWaresBrandListVos));
    }

    /**
     * 新增商品品牌
     * @param brand
     * @param sysUserId
     */
    @Override
    @Transactional
    public void saveEntity(CouWaresBrandSaveVo brand, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验更新对象属性非空
        checkUtils.checkNotNull(brand);
        CouWaresBrandEntity couWaresBrandEntity = getCouWaresBrandSaveEntity(brand, sysUserId);
        save(couWaresBrandEntity);
        updateRedis();
    }

    /**
     * 根据商品品牌ID获取商品品牌详情
     * @param couBrandId
     * @return
     */
    @Override
    public CouWaresBrandInfoVo info(Long couBrandId) {
        return baseMapper.info(couBrandId);
    }

    /**
     * 更新商品品牌
     * @param brand
     * @param sysUserId
     */
    @Override
    @Transactional
    public void updateEntity(CouWaresBrandUpdateVo brand, Long sysUserId) {
        try {
            trimUtils.beanValueTrim(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //校验更新对象属性非空
        checkUtils.checkNotNull(brand);
        updateById(getCouWaresBrandUpdateEntity(brand, sysUserId));
        updateRedis();
    }

    /**
     * 修改商品品牌的状态
     * @param couBrandId
     * @param status
     * @param sysUserId
     */
    @Override
    @Transactional
    public void changeStatus(Long couBrandId, Integer status, Long sysUserId) {
        CouWaresBrandEntity couWaresBrandEntity = getOne(new QueryWrapper<CouWaresBrandEntity>().eq("cou_brand_id", couBrandId).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(couWaresBrandEntity);
        couWaresBrandEntity
                .setStatus(status)
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        updateById(couWaresBrandEntity);
        updateRedis();
    }

    /**
     * 获取热门品牌对象列表
     * @return
     */
    @Override
    public List<CouWaresBrandInvokingVo> getHotCouBrandList() {
        List<CouWaresBrandInvokingVo> brandList = JSONArray.parseArray(redisUtils.get(RedisKeys.CouWares.CouBrand(RedisListKeyConstant.COU_HOT_BRAND_LIST)), CouWaresBrandInvokingVo.class);
        brandList = Optional.ofNullable(brandList).orElse(baseMapper.getHotCouBrandList(Constant.Status.NORMAL.getStatus()));
        redisUtils.set(RedisKeys.CouWares.CouBrand(RedisListKeyConstant.COU_HOT_BRAND_LIST), brandList);
        return brandList;
    }

    /**
     * 获取所有状态为正常商品品牌的ID和名称
     * @return
     */
    @Override
    public List<CouWaresBrandInvokingVo> getCouBrandList() {
        List<CouWaresBrandInvokingVo> brandList = JSONArray.parseArray(redisUtils.get(RedisKeys.CouWares.CouBrand(RedisListKeyConstant.COU_BRAND_LIST)), CouWaresBrandInvokingVo.class);
        brandList = Optional.ofNullable(brandList).orElse(baseMapper.getCouBrandList(Constant.Status.NORMAL.getStatus()));
        redisUtils.set(RedisKeys.CouWares.CouBrand(RedisListKeyConstant.COU_BRAND_LIST), brandList);
        return brandList;
    }

    //更新redis上的列表信息
    private void updateRedis() {
        redisUtils.delete(RedisKeys.CouWares.CouBrand(RedisListKeyConstant.COU_BRAND_LIST));
        redisUtils.set(RedisKeys.CouWares.CouBrand(RedisListKeyConstant.COU_BRAND_LIST), baseMapper.getCouBrandList(Constant.Status.NORMAL.getStatus()));
    }

    //设置DealWaresBrandEntity更新对象
    private CouWaresBrandEntity getCouWaresBrandUpdateEntity(CouWaresBrandUpdateVo brand, Long sysUserId) {
        CouWaresBrandEntity couWaresBrandEntity = getOne(new QueryWrapper<CouWaresBrandEntity>().eq("cou_brand_id", brand.getCouBrandId()).last("LIMIT 1"));
        checkUtils.checkEntityNotNull(couWaresBrandEntity);
        couWaresBrandEntity
                .setCouBrandName(brand.getCouBrandName())
                .setImage(brand.getImage())
                .setFirstLetter(brand.getFirstLetter())
                .setSort(brand.getSort())
                .setUpdateUserId(sysUserId)
                .setUpdateTime(new Date());
        return couWaresBrandEntity;
    }

    //设置DealWaresBrandEntity新增对象
    private CouWaresBrandEntity getCouWaresBrandSaveEntity(CouWaresBrandSaveVo brand, Long sysUserId) {
        CouWaresBrandEntity couWaresBrandEntity = new CouWaresBrandEntity();
        couWaresBrandEntity
                .setCouBrandName(brand.getCouBrandName())
                .setImage(brand.getImage())
                .setFirstLetter(brand.getFirstLetter())
                .setStatus(Constant.Status.NORMAL.getStatus())
                .setSort(brand.getSort())
                .setCreateUserId(sysUserId)
                .setUpdateUserId(sysUserId);
        return couWaresBrandEntity;
    }
}
