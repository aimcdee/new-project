package com.project.webSocket.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * websocket操作对象封装
 *
 * @author liangyuding
 * @create 2020-04-15
 */
@Data
@Accessors(chain = true)
public class WebSocketEntity implements Serializable {
    private static final long serialVersionUID = 8840165573817300113L;

    /**操作消息的类型，0：主动断开指定用户 1：发送消息*/
    private Integer operation;

    /**消息类型， 2000：通知，2001：消息*/
    private Integer type;

    /**消息的接受者*/
    private String source;

    /**消息的发送者，如果为0，则为系统发送*/
    private String receive;

    /**业务消息体*/
    private Object data;
}
