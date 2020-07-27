package com.project.webSocket.service;

import com.project.webSocket.entity.WebSocketEntity;

/**
 * webSocket处理层Service
 *
 * @author liangyuding
 * @create 2020-04-15
 */
public interface WsService {

    /**
     * 处理业务消息
     */
    void process(WebSocketEntity webSocketEntity);

    /**
     * 主动断开本地连接
     *
     * @param userId 用户id
     */
    void disconnect(String userId);

    /**
     * 发送消息，只在本地服务进行发送
     *
     * @param userId  用户id
     * @param message 发送的消息
     */
    void sendMessage(String userId, String message);

    /**
     * 处理用户发送的消息
     *
     * @param userId  用户id
     * @param message 发送的消息
     */
    void processMessage(String userId, String message);
}
