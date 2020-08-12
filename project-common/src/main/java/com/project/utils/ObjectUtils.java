package com.project.utils;

import sun.misc.Contended;

/**
 * ObjectUtils工具类
 * 判断Integer和Long或者int或者long是否为空或者等于0
 *
 * @author Mcdee
 * @date 2020-08-12
 */
@Contended
public class ObjectUtils {

    /**
     * 判断Integer和Long或者int和long是否为空或者为空字符串
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return (null == obj || ContainsOnlyBlank(obj));
    }

    /**
     * 判断Integer和Long或者int和long是否不为空或者不为空字符串
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return (null != obj || !ContainsOnlyBlank(obj));
    }

    /**
     * 判断Integer和Long或者int和long是否为空或者等于0
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        return ((obj == null) || ContainsOnlyZero(obj));
    }

    /**
     * 判断Integer和Long或者int和long是否不为空或者不等于0
     * @param obj
     * @return
     */
    public static boolean isNotBlank(Object obj) {
        return ((obj != null) || !ContainsOnlyZero(obj));
    }

    /**
     * 判断Integer和Long或者int和long是否等于0
     * @param obj
     * @return
     */
    private static boolean ContainsOnlyZero(Object obj) {
        Integer date = Integer.parseInt(String.valueOf(obj));
        if (date == 0){
            return true;
        }
        return false;
    }

    /**
     * 判断Integer和Long或者int和long是否为空字符串
     * @param obj
     * @return
     */
    private static boolean ContainsOnlyBlank(Object obj) {
        String date = StringUtils.trim(String.valueOf(obj));
        if (date == ""){
            return true;
        }
        return false;
    }
}
