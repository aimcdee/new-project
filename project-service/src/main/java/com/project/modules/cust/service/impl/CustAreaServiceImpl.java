package com.project.modules.cust.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.constant.RedisKeyConstant;
import com.project.constant.RedisListKeyConstant;
import com.project.modules.cust.dao.CustAreaDao;
import com.project.modules.cust.entity.CustAreaEntity;
import com.project.modules.cust.service.CustAreaService;
import com.project.modules.cust.vo.invoking.CustAreaInvokingVo;
import com.project.modules.cust.vo.list.CustAreaListVo;
import com.project.modules.cust.vo.list.CustAreaTreeVo;
import com.project.modules.sys.service.SysConfigService;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private SysConfigService sysConfigService;
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
        List<CustAreaTreeVo> custAreaTreeVos = JSONArray.parseArray(redisUtils.get(RedisKeys.CustArea.AreaTree(RedisListKeyConstant.CUST_AREA_TREE_LIST)), CustAreaTreeVo.class);
        if (CollectionUtils.isEmpty(custAreaTreeVos)){
            custAreaTreeVos = baseMapper.getCustAreaTreeList();
            checkUtils.checkEntitiesNotNull(custAreaTreeVos);
            custAreaTreeVos.forEach(custAreaTreeVo -> {
                custAreaTreeVo.setParentName(Optional.ofNullable(baseMapper.getParentNameByParentId(custAreaTreeVo.getParentId())).orElse(Constant.DEFAUL_NAME));
            });
            redisUtils.set(RedisKeys.CustArea.AreaTree(RedisListKeyConstant.CUST_AREA_TREE_LIST), custAreaTreeVos);
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
        List<Long> areaIdList = getAreaIdList(params);
        Page<CustAreaListVo> page = new Query<CustAreaListVo>(params).getPage();
        List<CustAreaListVo> custAreaListVos = baseMapper.queryPage(page, getRedisAreaIdList(areaIdList));
        checkUtils.checkEntitiesNotNull(custAreaListVos);
        custAreaListVos.forEach(custAreaListVo -> {
            custAreaListVo.setParentName(baseMapper.getParentNameByParentId(custAreaListVo.getParentId()));
        });
        return new PageUtils(page.setRecords(custAreaListVos));
    }

    /**
     * 查询区域
     * @param parentId
     * @param type
     * @return
     */
    @Override
    public List<CustAreaInvokingVo> getArea(Long parentId, Integer type) {
        List<Long> areaIdList = new ArrayList<>();
        String redisKey = null;
        List<CustAreaInvokingVo> custAreaInvokingVos = new ArrayList<>();
        if (type.equals(Constant.AreaType.PROVINCE.getType())){
            areaIdList = JSONArray.parseArray(redisUtils.get(RedisKeys.Sys.Config(RedisKeyConstant.DEFAUL_TPROVINCE)), Long.class);
            String name = new String();
            if (CollectionUtils.isEmpty(areaIdList)){
                areaIdList = new ArrayList<>();
                Map map = JSON.parseObject(sysConfigService.getDefaultValue(RedisKeyConstant.DEFAUL_TPROVINCE));
                for (Object obj : map.keySet()){
                    areaIdList.add(Long.parseLong(String.valueOf(map.get(obj))));
                    name = name + "_" + String.valueOf(obj);
                }
                redisUtils.set(RedisKeys.Sys.Config(RedisKeyConstant.DEFAUL_TPROVINCE), areaIdList);
            }
            if (areaIdList.contains(Constant.DEFAUL_ID)){
                redisKey = getRedisKey(RedisKeyConstant.DEFAUL_TPROVINCE, null, type);
                areaIdList = areaIdList.stream().filter(areaId -> areaId == Constant.DEFAUL_ID).collect(Collectors.toList());
            } else {
                redisKey = getRedisKey(name, null, type);
            }
        } else {
            redisKey = getRedisKey(null, parentId, type);
        }
        custAreaInvokingVos = JSONArray.parseArray(redisUtils.get(RedisKeys.CustArea.AreaList(redisKey)), CustAreaInvokingVo.class);
        if (CollectionUtils.isEmpty(custAreaInvokingVos)){
            custAreaInvokingVos = baseMapper.getCustAreaInvokingVo(areaIdList, parentId, type);
            redisUtils.set(RedisKeys.CustArea.AreaList(redisKey), custAreaInvokingVos);
        }

        return custAreaInvokingVos;
    }

    //获取rediskey
    private String getRedisKey(String defaulName, Long parentId, Integer type) {
        String redisKey = null;
        switch (Constant.AreaType.getTypeValues(type)){
            case PROVINCE:
                redisKey = new StringBuilder().append(RedisListKeyConstant.CUST_AREA_PROVINCE_LIST).append(defaulName).toString();
                break;
            case CITY:
                redisKey = new StringBuilder().append(RedisListKeyConstant.CUST_AREA_CITY_LIST).append("_").append(parentId).toString();
                break;
            case COUNTY:
                redisKey = new StringBuilder().append(RedisListKeyConstant.CUST_AREA_COUNTY_LIST).append("_").append(parentId).toString();
                break;
            default:
                break;
        }
        return redisKey;
    }

    //获取区域的默认ID
    private List<Long> getAreaIdList(Map<String, Object> params) {
        List<Long> areaIdList = new ArrayList<>();
        Long areaId = MapUtils.getLong(params, "areaId");
        if (ObjectUtils.isEmpty(areaId)){
            areaIdList = JSONArray.parseArray(redisUtils.get(RedisKeys.Sys.Config(RedisKeyConstant.DEFAUL_TPROVINCE)), Long.class);
            if (CollectionUtils.isEmpty(areaIdList)){
                areaIdList = new ArrayList<>();
                Map map = JSON.parseObject(sysConfigService.getDefaultValue(RedisKeyConstant.DEFAUL_TPROVINCE));
                for (Object obj : map.keySet()){
                    areaIdList.add(Long.parseLong(String.valueOf(map.get(obj))));
                }
                redisUtils.set(RedisKeys.Sys.Config(RedisKeyConstant.DEFAUL_TPROVINCE), areaIdList);
            }
        } else {
            areaIdList.add(areaId);
        }

        return areaIdList;
    }

    //获取所有省市县/区的区域ID集合
    private List<Long> getRedisAreaIdList(List<Long> areaIdList){
        List<Long> newAreaIdList = new ArrayList<>();
        for (Long areaId : areaIdList) {
            //获取根据区域ID获取redis上该区域及其子区域的ID
            List<Long> redisAreaIdList = JSONArray.parseArray(redisUtils.get(RedisKeys.CustArea.AreaId(new StringBuilder().append(RedisListKeyConstant.CUST_AREA_ID_LIST).append("_").append(areaId).toString())), Long.class);
            List<Long> queryAreaIdList = CollectionUtils.isNotEmpty(redisAreaIdList) ? redisAreaIdList : new ArrayList<>();
            if (CollectionUtils.isEmpty(queryAreaIdList)){
                queryAreaIdList = getAreaIdList(queryAreaIdList, areaId);
                redisUtils.set(RedisKeys.CustArea.AreaId(new StringBuilder().append(RedisListKeyConstant.CUST_AREA_ID_LIST).append("_").append(areaId).toString()), newAreaIdList);
            }
            newAreaIdList.addAll(queryAreaIdList);
        }
        return newAreaIdList;
    }

    //递归获取广东省下所有省市县/区的区域ID集合
    private List<Long> getAreaIdList(List<Long> areaIdList, Long areaId) {
        areaIdList.add(areaId);
        //获取该区域的子区域
        List<Long> childAreaIdList = baseMapper.getChildAreaIdList(areaId);
        if (CollectionUtils.isNotEmpty(childAreaIdList)) {
            childAreaIdList.forEach(childAreaId -> {
                getAreaIdList(areaIdList, childAreaId);
            });
        }
        return areaIdList;
    }
}
