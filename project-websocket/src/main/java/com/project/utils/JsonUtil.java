package com.project.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * JSONG工具类
 *
 * @author liangyuding
 * @create 2020-04-15
 */
public class JsonUtil {

    public final static Gson GSON = new Gson();

    private JsonUtil() {
        throw new Error("工具类不能实例化！");
    }

    /**
     * gson序列化
     */
    public static String toJson(Object entity) {
        return GSON.toJson(entity);
    }

    /**
     * Gson Token反序列化
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    /**
     * 对象转Map
     */
    public static <T> T toMap(Object entity, Type typeOfT) {
        return fromJson(toJson(entity), typeOfT);
    }

}
