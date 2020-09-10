package com.project.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class JavaBeanUtils {

    private final static String STRINGTYPE = "java.lang.String";

    /**
     * 对象转Map的方法
     * @param javaBean
     * @return
     * @throws Exception
     */
    public static Map<String, Object> javaBeanToMap(Object javaBean) throws Exception {
        TrimUtils.beanValueTrim(javaBean);
        Map<String, Object> map = new HashMap<>();
        Method[] methods = javaBean.getClass().getMethods(); // 获取所有方法
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                String field = method.getName(); // 拼接属性名
                field = field.substring(field.indexOf("get") + 3);
                field = field.toLowerCase().charAt(0) + field.substring(1);
                Object value = method.invoke(javaBean, (Object[]) null); // 执行方法
                map.put(field, value);
            }
        }
        return map;
    }

    /**
     * Map转对象的方法
     * @param clazz
     * @param map
     * @return
     * @throws Exception
     */
    public static Object mapToJavaBean(Class<?> clazz, Map<String, Object> map) throws Exception {
        Object javabean = clazz.newInstance(); // 构建对象
        Method[] methods = clazz.getMethods(); // 获取所有方法
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                String field = method.getName(); // 截取属性名
                field = field.substring(field.indexOf("set") + 3);
                field = field.toLowerCase().charAt(0) + field.substring(1);
                if (map.containsKey(field)) {
                    method.invoke(javabean, map.get(field));
                }
            }
        }
        return javabean;
    }
}
