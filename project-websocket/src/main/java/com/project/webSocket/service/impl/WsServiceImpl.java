package com.project.webSocket.service.impl;

import com.project.utils.JsonUtil;
import com.project.utils.R;
import com.project.utils.StatusCode;
import com.project.webSocket.config.WebSocketServer;
import com.project.webSocket.constant.WsConstant;
import com.project.webSocket.entity.WebSocketEntity;
import com.project.webSocket.service.WsService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * webSocket处理层Service
 *
 * @author liangyuding
 * @create 2020-04-15
 */
@Service
@Slf4j
public class WsServiceImpl implements WsService {

    /**
     * 处理业务消息
     */
    @Override
    public void process(WebSocketEntity webSocketEntity) {
        log.info("webSocketEntity:{}", webSocketEntity);
        // 消息处理类型
        Integer optione = webSocketEntity.getOperation();
        // 消息类型
        Integer type = webSocketEntity.getType();
        // 消息主体
        String data = JsonUtil.toJson(R.ok(type, webSocketEntity.getData()));
        // 消息接受者
        String source = webSocketEntity.getSource();
        // 原生消息
        String message = JsonUtil.toJson(webSocketEntity);
        if (optione.equals(WsConstant.SOCKET_TYPE_USER_SEND)) {
            log.debug("推送消息请求，用户：{},内容：{}", source, data);
            sendMessage(source, data);
        } else {
            log.debug("无法识别的处理类型：{}", message);
        }
    }

    @Override
    public void disconnect(String userId) {
        Channel channel = WebSocketServer.channelPool.get(userId);
        if (channel != null) {
            channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.toJson(R.ok(StatusCode.WS_DISCONNECT))));
            channel.close();
            log.debug("主动断开用户：{} 成功", userId);
        }
    }

    @Override
    public void sendMessage(String userId, String message) {

        // 群发消息
        if ("0".equals(userId)) {
            log.debug("群发消息,{}", message);
            WebSocketServer.CHANNEL_GROUP.writeAndFlush(new TextWebSocketFrame(message));
            return;
        }

        // 指定发送消息
        Channel channel = WebSocketServer.channelPool.get(userId);
        if (channel != null) {
            log.debug("用户：{} 推送消息成功", userId);
            channel.writeAndFlush(new TextWebSocketFrame(message));
        }

    }

    @Override
    public void processMessage(String userId, String message) {
        try {
            WebSocketEntity webSocketEntity = JsonUtil.fromJson(message, WebSocketEntity.class);
            webSocketEntity.setSource(userId);
            sendMessage(webSocketEntity.getReceive(), JsonUtil.toJson(webSocketEntity));
            log.debug("用户:{} 给：{} 发送消息：{}", userId, webSocketEntity.getReceive(), message);
        } catch (Exception e) {
            log.error("用户发送消息失败:{}:{}:{}", userId, message, e.getMessage());
        }
    }
}
