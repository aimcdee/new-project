package com.project.utils;

/**
 * @author liangyuding
 * @date 2020-03-10
 */
public enum StatusCode {
    FAILURE(-1, "失败"),
    NO_EXIT(1, "查询数据不存在"),
    SUCCESS(0, "成功"),
    PARAMETER_ERROR(1001, "提交参数不符合规范"),
    USERTOKEN_PARAMETER_ERROR(1002, "Token参数错误"),
    LOGIN_FAILURE(1110, "登录失败"),
    LOGIN_PASSWORD_ERROR(1113, "用户名或密码错误"),
    LOGIN_USER_NOEXIST(1114, "用户不存在"),
    LOGIN_USER_LOCK(1115, "此用户已被禁用"),
    USER_UNAUTHORIZED(1118, "用户无权限进行此操作"),
    DATABASE_SAVE_FAILURE(2002, "添加失败"),
    DATABASE_UPDATE_FAILURE(2003, "修改失败"),
    DATABASE_DELETE_FAILURE(2004, "删除失败"),
    DATABASE_SELECT_FAILURE(2005, "资源不存在"),
    DATABASE_NOT_CHANGE(2006, "未作任何修改"),
    TOKEN_OVERDUE(1130, "登录过期，请重新登录"),
    TOKEN_OUT(1131, "登录失效，帐号在其他地方登录"),
    TOKEN_FAULT(1132, "未登录，无权限访问"),
    LOGIN_OUT(200, "退出登录成功"),
    DELETE_IMAGE_SUCCESS(200, "删除成功");

    private final int code;
    private final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
