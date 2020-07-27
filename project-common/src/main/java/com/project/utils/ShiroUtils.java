package com.project.utils;

import com.project.constant.Constant;
import com.project.modules.sys.vo.login.LoginUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 管理后台shiro工具
 *
 * @author liangyuding
 * @date 2020-03-18
 */
public class ShiroUtils {

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static LoginUserVo getSysUser() {
		return (LoginUserVo)SecurityUtils.getSubject().getPrincipal();
	}

	/**获取登陆用户ID*/
	public static Long getSysUserId() {
		return isLogin() ? getSysUser().getUserId() : 0;
	}

	/**获取登陆用户名称*/
	public static String getSysUserName(){
		return isLogin() ? getSysUser().getUserName() : null;
	}

	/**登录用户的ID为admin或string的超级管理员*/
	public static Boolean isSuperAdmin() {
		if (Constant.SUPER_ADMIN.equals(getSysUserId()) || Constant.SUPER_ADMIN_STRING.equals(getSysUserId())){
			return true;
		}
		return false;
	}

	/**判断登录用户是否是管理角色*/
	public static Boolean isAdmin() {
		return SecurityUtils.getSubject().hasRole(Constant.Role.ADMIN.getRoleName());
	}

	/**判断登录用户是否是总经理角色*/
	public static Boolean isManager() {
		return SecurityUtils.getSubject().hasRole(Constant.Role.MANAGER.getRoleName());
	}

	/**判断登录用户是否是财务经理角色*/
	public static Boolean isFinanceManager() {
		return SecurityUtils.getSubject().hasRole(Constant.Role.FINANCE_MANAGER.getRoleName());
	}

	/**判断登录用户是否是财务专员角色*/
	public static Boolean isFinance() {
		return SecurityUtils.getSubject().hasRole(Constant.Role.FINANCE.getRoleName());
	}

	/**判断登录用户是否是销售经理角色*/
	public static Boolean isSaleManager() {
		return SecurityUtils.getSubject().hasRole(Constant.Role.SALE_MANAGER.getRoleName());
	}

	/**判断登录用户是否是销售专员角色*/
	public static Boolean isSale() {
		return SecurityUtils.getSubject().hasRole(Constant.Role.SALE.getRoleName());
	}

	/**判断是否已登录*/
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}


}
