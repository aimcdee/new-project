package com.project.webSocket.listener;

import com.project.utils.JsonUtil;
import com.project.webSocket.constant.RabbitQueueConstant;
import com.project.webSocket.entity.WebSocketEntity;
import com.project.webSocket.service.WsService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 接收rabbitMq消息
 *
 * @author liangyuding
 * @create 2020-04-15
 */
@Slf4j
@Component
public class WsRabbitMqMessageListener {
    @Autowired
    private WsService wsService;

    /**
     * Redis消息侦听器提供异步行为的容器。 处理侦听，转换和消息分派的低级细节
     */
    @RabbitListener(queues = {RabbitQueueConstant.WEB_SOCKET_MESSAGE_QUEUE})
    public void RedisMessageListenerContainer(Message message, Channel channel) throws IOException {
        String jsonMessage = new String(message.getBody());
        if (StringUtils.isNotEmpty(jsonMessage)) {
            log.debug("jsonMessage:{}", jsonMessage);
            try {
                wsService.process(JsonUtil.fromJson(jsonMessage, WebSocketEntity.class));
            } catch (Exception e) {
                log.error("获取消息失败：{}", e.getMessage());
            }
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}