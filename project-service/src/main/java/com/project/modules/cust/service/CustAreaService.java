package com.project.modules.cust.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.cust.entity.CustAreaEntity;
import com.project.modules.cust.vo.invoking.CustAreaInvokingVo;
import com.project.modules.cust.vo.list.CustAreaTreeVo;
import com.project.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 区域Service
 *
 * @author liangyuding
 * @date 2020-03-26
 */
public interface CustAreaService extends IService<CustAreaEntity> {

    /**
     * 查看树形菜单区域列表
     * @return
     */
    List<CustAreaTreeVo> getTreeList();

    /**
     * 分页查询区域列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

//    /**
//     * 查看区域
//     * @param parentId
//     * @param type
//     * @return
//     */
//    List<CustAreaInvokingVo> getArea(Long parentId, Integer type);

    /**
     * 查看区域
     * @param areaId
     * @param parentId
     * @param type
     * @return
     */
    List<CustAreaInvokingVo> getArea(Long areaId, Long parentId, Integer type);
}
