package com.project.webSocket.constant;

import io.netty.util.AttributeKey;

/**
 * @author liangyuding
 * @create 2020-04-15
 */
public class WsConstant {
    /**
     * 主动断开连接
     */
    public static final Integer SOCKET_TYPE_DISCONNECT = 0;
    /**
     * 消息推送用户
     */
    public static final Integer SOCKET_TYPE_USER_SEND = 1;
    /**
     * websocket连接的url websocket
     */
    public static final String WEBSOCKET_PATH = "/websocket";
    /**
     * 通道处理器之间存放token参数的key
     */
    public static final AttributeKey<String> CHANNEL_USERID_KEY = AttributeKey.valueOf("netty.websocket.userId");

}
