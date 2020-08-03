package com.project.utils;

import java.io.Serializable;

/**
 * Redis所有Keys
 *
 * @author liangyuding
 * @date 2020-03-18
 */
public class RedisKeys {

    /**
     * token超时时间 单位:秒  默认设置1小时
     */
    public static final int TOKEN_EXPIRE_SECOND = 1 * 3600;
    public static final int TOKEN_EXPIRE_MS = TOKEN_EXPIRE_SECOND * 1000;

    /**Redis Key*/
    public static class Prefix {
        /**系统*/
        private static String SysKey(String type, Object key) {
            return "sys:" + type + key;
        }
        /**区域*/
        private static String CustKey( Object key) {
            return "cust:area:" + key;
        }
        /**轮播图*/
        private static String ConfKey( Object key) {
            return "conf:banner:" + key;
        }
        /**品牌*/
        private static String CouKey(String type, Object key) {
            return "cou:" + type + key;
        }
        /**客户商品*/
        private static String DealKey(String type, Object key) {
            return "deal:" + type + key;
        }
        /**微信端*/
        private static String WxKey( String type, Object key) {
            return "wx:" + type + key;
        }
    }

    /**用户登录*/
    public static class SysLogin {
        /**用户前缀*/
        private static String Staff(String key){
            return "staff:" + key;
        }
        /**登录*/
        public static String Login(Long userId) {
            return Prefix.SysKey(Staff("login:"), userId);
        }
        /**权限*/
        public static String Perm(Serializable userId) {
            return Prefix.SysKey(Staff("perm:"), userId);
        }
        /**角色*/
        public static String Part(Serializable userId) {
            return Prefix.SysKey(Staff("part:"), userId);
        }
    }

    /**系统*/
    public static class Sys {
        /**系统用户*/
        public static String User(String key) {
            return Prefix.SysKey("user", key);
        }
        /**系统菜单*/
        public static String Menu(String key) {
            return Prefix.SysKey("menu", key);
        }
        /**系统参数*/
        public static String Config(String key) {
            return Prefix.SysKey("config", key);
        }
        /**系统角色*/
        public static String Role(String key) {
            return Prefix.SysKey("role", key);
        }
        /**系统部门*/
        public static String Dept(String key) {
            return Prefix.SysKey("dept", key);
        }

    }

    /**区域*/
    public static class CustArea {
        /**树形*/
        public static String AreaTree(String key) {
            return Prefix.CustKey(key);
        }
        /**ID*/
        public static String AreaId(String key) {
            return Prefix.CustKey(key);
        }
        /**集合*/
        public static String AreaList(String key) {
            return Prefix.CustKey(key);
        }
    }

    /**
     * 轮播图
     */
    public static class ConfBanner {
        public static String ConfBanner(String key){
            return Prefix.ConfKey(key);
        }
    }

    /**品牌商品*/
    public static class CouWares {
        /**商品*/
        public static String CouWares(String key) {
            return Prefix.CouKey("wares:",key);
        }
        /**商品品牌*/
        public static String CouBrand(String key) {
            return Prefix.CouKey("brand:", key);
        }
        /**品牌系列*/
        public static String CouSeries(String key) {
            return Prefix.CouKey("series:", key);
        }
        /**商品型号*/
        public static String CouModel(String key) {
            return Prefix.CouKey("model:", key);
        }
    }

    /**
     * 微信端
     */
    public static class Wx {
        /**登录前缀*/
        private static String Login(){
            return "wxLogin:";
        }
        /**用户商品前缀*/
        private static String WxDeal(String key){
            return "wxDeal:" + key;
        }
        /**登录*/
        public static String WxLogin(String key) {
            return Prefix.WxKey(Login(), key);
        }
        public static String WxLoginToken(String key) {
            return Prefix.WxKey(Login(), key);
        }
    }
}
