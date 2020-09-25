package com.project.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static com.project.utils.ObjectUtils.isEmpty;

/**
 * 给Bean里面对象去前后空格
 */
@Component
public class TrimUtils {

    private final static String STRINGTYPE = "java.lang.String";

    /**
     * 去掉bean中所有属性为字符串的前后空格
     * @param javaBean
     * @throws Exception
     */
    public static void beanValueTrim(Object javaBean) throws Exception {
        if(null != javaBean){
            //获取所有的字段包括public,private,protected,private
            Field[] fields = javaBean.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.getType().getName().equals(STRINGTYPE)) {
                    String key = field.getName();//获取字段名
                    Object value = getFieldValue(javaBean, key);
                    if (null == value)
                        continue;
                    setFieldValue(javaBean, key, value.toString().trim());
                }
            }
        }
    }

    /**
     * 去掉bean中所有属性为字符串的前后空格
     * @param javaBean
     * @throws Exception
     */
    public static void one(Object javaBean) throws Exception {
        if(javaBean!=null){
            //获取所有的字段包括public,private,protected,private
            Field[] fields = javaBean.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                if (f.getType().getName().equals("java.lang.String")) {
                    String key = f.getName();//获取字段名
                    Object value = getFieldValue(javaBean, key);
                    if (value == null)
                        continue;
                    setFieldValue(javaBean, key, value.toString().trim());
                }
            }
        }
    }

    /**
     * 利用反射通过get方法获取bean中字段fieldName的值
     * @param javaBean
     * @param fieldName
     * @return
     * @throws Exception
     */
    private static Object getFieldValue(Object javaBean, String fieldName) throws Exception {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();

        Object rObject = null;
        Method method = null;

        @SuppressWarnings("rawtypes")
        Class[] classArr = new Class[0];
        method = javaBean.getClass().getMethod(methodName, classArr);
        rObject = method.invoke(javaBean, new Object[0]);

        return rObject;
    }

    /**
     * 利用发射调用bean.set方法将value设置到字段
     * @param javaBean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    private static void setFieldValue(Object javaBean, String fieldName, Object value) throws Exception {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();

        /**
         * 利用发射调用bean.set方法将value设置到字段
         */
        Class[] classArr = new Class[1];
        classArr[0]="java.lang.String".getClass();
        Method method=javaBean.getClass().getMethod(methodName,classArr);
        method.invoke(javaBean,value);
    }

    /**
     * 删除所有br
     * @param charSequence 字符序列
     * @return 删除html标签后的字符序列
     */
    public static String deleteNotBrHtml(CharSequence charSequence){
        if(isEmpty(charSequence)){
            return "";
        }
        return Pattern.compile("<br([^>]*)>").matcher(charSequence).replaceAll("").replaceAll("</br>","");
    }
}
