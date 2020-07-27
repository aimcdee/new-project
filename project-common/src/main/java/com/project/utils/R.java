package com.project.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author liangyuding
 * @date 2020-03-10
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = -3206305449670175545L;
    private static final String CODE = "code";
    private static final String MSG = "msg";
    private static final String DATA = "data";
    private static final String SUCCESS = "success";
    private static final String ERROR_MESSAGE = "未知异常，请联系管理员";

    public static final Integer UPLOAD_EXCLE_CODE = 1001;
    public static final Integer AUTH_STUDENT_CODE = 2001;

    public R() {
        put(CODE, 0);
        put(MSG, SUCCESS);
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, ERROR_MESSAGE);
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(StatusCode code) {
        return error(code.getCode(), code.getMsg());
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put(CODE, code);
        r.put(MSG, msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put(MSG, msg);
        r.put(DATA, msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok(Object obj) {
        R r = new R();
        r.put(DATA, obj);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R result(int code, Object value) {
        R r = new R();
        r.put(CODE, code);
        r.put(DATA, value);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
