package com.project.common.utils;

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

    public static Long getDealUserId() {
        if (isLogin()) {
            return getDealUserLoginVo().getDealUserId();
        }
        return null;
    }

    public static String getDealPhone() {
        if (isLogin()) {
            return getDealUserLoginVo().getPhone();
        }
        return null;
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
