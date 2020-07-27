package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.sys.dao.SysMenuDao;
import com.project.modules.sys.entity.SysMenuEntity;
import com.project.modules.sys.service.SysMenuService;
import com.project.modules.sys.service.SysRoleMenuService;
import com.project.modules.sys.service.SysUserService;
import com.project.utils.MapUtils;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 系统菜单Service
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Slf4j
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分页查询菜单列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysMenuEntity> page = new Query<SysMenuEntity>(params).getPage();
        List<SysMenuEntity> sysMenuEntities = baseMapper.queryPage(page);
        sysMenuEntities.forEach(sysMenuEntity -> {
            String parentName = baseMapper.getParentName(sysMenuEntity.getParentId());
            if (Objects.isNull(parentName)){
                parentName = Constant.DEFAUL_SYSTEM_NAME;
            }
            sysMenuEntity.setParentName(parentName);
        });
        return new PageUtils(page.setRecords(sysMenuEntities));
    }

    /**
     * 获取用户菜单列表
     * @param userId
     * @return
     */
    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN || userId == Constant.SUPER_ADMIN_STRING) {
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        //根据父菜单，查询子菜单
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @return
     */
    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    /**
     * 获取不包含按钮的菜单列表
     * @return
     */
    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        List<SysMenuEntity> sysMenuEntities = baseMapper.queryNotButtonList();
        sysMenuEntities.forEach(sysMenuEntity -> {
            String parentName = baseMapper.getParentName(sysMenuEntity.getParentId());
            if (Objects.isNull(parentName)){
                parentName = Constant.DEFAUL_SYSTEM_NAME;
            }
            sysMenuEntity.setParentName(parentName);
        });
        return sysMenuEntities;
    }

    /**
     * 保存系统菜单
     * @param menu
     */
    @Override
    @Transactional
    public void saveSysMenuEntity(SysMenuEntity menu) {
        //校验参数是否正确
        checkMenu(menu);
        save(menu);
    }

    /**
     * 修改系统菜单
     * @param menu
     */
    @Override
    @Transactional
    public void updateSysMenuEntity(SysMenuEntity menu) {
        checkMenu(menu);
        updateById(menu);
    }

    /**
     * 删除系统菜单
     * @param menuId
     */
    @Override
    public void delete(long menuId) {
        //删除菜单
        removeById(menuId);
        //删除菜单与角色关联
        sysRoleMenuService.removeByMap(new MapUtils().put("menu_id", menuId));
    }

    /**
     * 系统菜单信息
     * @param menuId
     * @return
     */
    @Override
    public SysMenuEntity getSysMenuEntity(Long menuId) {
        SysMenuEntity sysMenuEntity = getOne(new QueryWrapper<SysMenuEntity>().eq("menu_id", menuId).last("LIMIT 1"));
        String parentName = baseMapper.getParentName(sysMenuEntity.getParentId());
        if (Objects.isNull(parentName)){
            parentName = Constant.DEFAUL_SYSTEM_NAME;
        }
        sysMenuEntity.setParentName(parentName);
        return sysMenuEntity;
    }

    //校验参数是否正确
    private void checkMenu(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RRException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
//            SysMenuEntity parentMenu = getById(menu.getParentId());
            SysMenuEntity parentMenu = getOne(new QueryWrapper<SysMenuEntity>().eq("menu_id", menu.getParentId()).last("LIMIT 1"));
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }

    //获取所有菜单列表
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    //递归获取子菜单
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
        for (SysMenuEntity sysMenuEntity : menuList) {
            //目录
            if (sysMenuEntity.getType() == Constant.MenuType.CATALOG.getValue()) {
                sysMenuEntity.setList(getMenuTreeList(queryListParentId(sysMenuEntity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(sysMenuEntity);
        }
        return subMenuList;
    }
}
