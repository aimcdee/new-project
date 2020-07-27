package com.project.utils;

import java.util.HashMap;

/**
 * 返回数据
 *
 * @author liangyuding
 * @create 2020-04-15
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private static final int INITIAL = 4;
    private static final String MSG = "msg";
    private static final String TYPE = "type";
    private static final String RESUTLT = "result";


    public R() {
        super();
    }

    public R(int initialCapacity) {
        super(initialCapacity);
    }

    public static R ok(int type, String msg) {
        //initialCapacity = (需要存储的元素个数 / 负载因子) + 1。注意负载因子（即loader factor）默认为 0.75，如果暂时无法确定初始值大小，请设置为 16
        R r = new R(INITIAL);
        r.put(TYPE, type);
        r.put(MSG, msg);
        return r;
    }

    public static R ok(int type, Object data) {
        //initialCapacity = (需要存储的元素个数 / 负载因子) + 1。注意负载因子（即loader factor）默认为 0.75，如果暂时无法确定初始值大小，请设置为 16
        R r = new R(INITIAL);
        r.put(TYPE, type);
        r.put(RESUTLT, data);
        return r;
    }

    public static R ok(int type, String msg, Object data) {
        R r = new R(INITIAL);
        r.put(TYPE, type);
        r.put(MSG, msg);
        r.put(RESUTLT, data);
        return r;
    }

    public static R ok(StatusCode status) {
        return ok(status.getCode(), status.getMsg());
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public R add(String key, Object value) {
        Object object = super.getOrDefault(RESUTLT, new HashMap<>());
        try {
            HashMap<String, Object> result = (HashMap<String, Object>) object;
            result.put(key, value);
            super.put(RESUTLT, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}