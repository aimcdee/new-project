package com.project.modules.cust.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.modules.cust.dao.CustAreaDao;
import com.project.modules.cust.entity.CustAreaEntity;
import com.project.modules.cust.service.CustAreaService;
import com.project.modules.cust.vo.invoking.CustAreaInvokingVo;
import com.project.modules.cust.vo.list.CustAreaListVo;
import com.project.modules.cust.vo.list.CustAreaTreeVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 区域Service
 *
 * @author liangyuding
 * @date 2020-03-26
 */
@Slf4j
@Service("custAreaService")
public class CustAreaServiceImpl extends ServiceImpl<CustAreaDao, CustAreaEntity> implements CustAreaService {

    @Autowired
    private CheckUtils checkUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查看树状区域
     * @return
     */
    @Override
    public List<CustAreaTreeVo> getTreeList() {
        List<CustAreaTreeVo> custAreaTreeVos = JSONArray.parseArray(redisUtils.get(RedisKeys.CustArea.AreaTree(RedisKeyConstant.CUST_AREA_TREE_LIST)), CustAreaTreeVo.class);
        if (CollectionUtils.isEmpty(custAreaTreeVos)){
            custAreaTreeVos = baseMapper.getCustAreaTreeList();
            checkUtils.checkEntitiesNotNull(custAreaTreeVos);
            custAreaTreeVos.forEach(custAreaTreeVo -> {
                custAreaTreeVo.setParentName(Optional.ofNullable(baseMapper.getParentNameByParentId(custAreaTreeVo.getParentId())).orElse(Constant.DEFAUL_NAME));
            });
            redisUtils.set(RedisKeys.CustArea.AreaTree(RedisKeyConstant.CUST_AREA_TREE_LIST), custAreaTreeVos);
        }
        return custAreaTreeVos;
    }

    /**
     * 分页查询区域列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long areaId = getAreaId(params);
        Page<CustAreaListVo> page = new Query<CustAreaListVo>(params).getPage();
        List<CustAreaListVo> custAreaListVos = baseMapper.queryPage(page, getRedisAreaIdList(areaId));
        checkUtils.checkEntitiesNotNull(custAreaListVos);
        custAreaListVos.forEach(custAreaListVo -> {
            custAreaListVo.setParentName(baseMapper.getParentNameByParentId(custAreaListVo.getParentId()));
        });
        return new PageUtils(page.setRecords(custAreaListVos));
    }

    /**
     * 查询区域
     * @param areaId
     * @param parentId
     * @param type
     * @return
     */
    @Override
    public List<CustAreaInvokingVo> getArea(Long areaId, Long parentId, Integer type) {
        String redisKey = getRedisKey(parentId, type);
        List<CustAreaInvokingVo> custAreaInvokingVos = JSONArray.parseArray(redisUtils.get(RedisKeys.CustArea.AreaList(redisKey)), CustAreaInvokingVo.class);
        if (CollectionUtils.isEmpty(custAreaInvokingVos)){
            custAreaInvokingVos = baseMapper.getCustAreaInvokingVo(areaId, parentId, type);
            redisUtils.set(RedisKeys.CustArea.AreaList(redisKey), custAreaInvokingVos);
        }
        return custAreaInvokingVos;
    }

    private String getRedisKey(Long parentId, Integer type) {
        String redisKey = null;
        switch (Constant.AreaType.getTypeValues(type)){
            case PROVINCE:
                redisKey = RedisKeyConstant.CUST_AREA_PROVINCE_LIST;
                break;
            case CITY:
                redisKey = new StringBuilder().append(RedisKeyConstant.CUST_AREA_CITY_LIST).append("_").append(parentId).toString();
                break;
            case COUNTY:
                redisKey = new StringBuilder().append(RedisKeyConstant.CUST_AREA_COUNTY_LIST).append("_").append(parentId).toString();
                break;
            default:
                break;
        }
        return redisKey;
    }

    //获取区域的默认ID
    private Long getAreaId(Map<String, Object> params) {
        Long areaId = MapUtils.getLong(params, "areaId");
        if (Objects.isNull(areaId)){
            areaId = Constant.DEFAUL_TPROVINCE;
        }
        return areaId;
    }

    //获取所有省市县/区的区域ID集合
    private List<Long> getRedisAreaIdList(Long areaId){
        List<Long> redisAreaIdList = JSONArray.parseArray(redisUtils.get(RedisKeys.CustArea.AreaId(new StringBuilder().append(RedisKeyConstant.CUST_AREA_ID_LIST).append("_").append(areaId).toString())), Long.class);
        List <Long> areaIdList = CollectionUtils.isNotEmpty(redisAreaIdList) ? redisAreaIdList : new ArrayList<>();
        if (CollectionUtils.isEmpty(areaIdList)){
            areaIdList = getAreaIdList(areaIdList, areaId);
            redisUtils.set(RedisKeys.CustArea.AreaId(new StringBuilder().append(RedisKeyConstant.CUST_AREA_ID_LIST).append("_").append(areaId).toString()), areaIdList);
        }
        return areaIdList;
    }

    //递归获取广东省下所有省市县/区的区域ID集合
    private List<Long> getAreaIdList(List<Long> areaIdList, Long areaId) {
        areaIdList.add(areaId);
        //获取该部门的子部门
        List<Long> childAreaIdList = baseMapper.getChildAreaIdList(areaId);
        if (CollectionUtils.isNotEmpty(childAreaIdList)) {
            childAreaIdList.forEach(childAreaId -> {
                getAreaIdList(areaIdList, childAreaId);
            });
        }
        return areaIdList;
    }
}
