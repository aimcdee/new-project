package com.project.common.utils;

import com.project.constant.Constant;
import com.project.modules.deal.vo.login.DealUserLoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * @author liangyuding
 * @date 2020-04-07
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static DealUserLoginVo getDealUserLoginVo() {
        return (DealUserLoginVo) SecurityUtils.getSubject().getPrincipal();
    }

    //获取登陆客户ID
    public static Long getDealUserId() {
        if (isLogin()) {
            return getDealUserLoginVo().getDealUserId();
        }
        return null;
    }
    //获取登陆客户手机号码
    public static String getDealPhone() {
        if (isLogin()) {
            return getDealUserLoginVo().getPhone();
        }
        return null;
    }

    //获取登陆客户企业ID
    public static Long getDealStoreId() {
        if (isLogin()) {
            return getDealUserLoginVo().getDealStoreId();
        }
        return null;
    }

    //获取判断登陆客户是否为企业客户
    public static Boolean isEnterprise() {
        if (isLogin()) {
            return getDealUserLoginVo().getType().equals(Constant.StoreType.ENTERPRISE.getType());
        }
        return false;
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

}
