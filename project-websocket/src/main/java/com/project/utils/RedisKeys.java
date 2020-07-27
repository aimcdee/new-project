package com.project.utils;

/**
 * Redis所有Keys
 *
 * @author liangyuding
 * @create 2020-04-15
 */
public class RedisKeys {

    /**
     * 公司key
     */
    public static class Prefix {
        /**
         * 不同公司生成不同key前缀
         */
        private static String buildKey(String type, Object key) {
            return "mustang:" + type + ":" + key;
        }
    }

    /**
     * 用户key
     */
    public static class User {
        public static String login(Long userId) {
            return aut("login:" + userId);
        }

        /**
         * 用户认证前缀
         */
        public static String aut(String key) {
            return "sys:user:aut:" + key;
        }

        /**
         * 获取用户token
         */
        public static String token(String userId) {
            return Prefix.buildKey(aut("token"), userId);
        }
    }
}