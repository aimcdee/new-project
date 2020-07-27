package com.project.utils;

/**
 * 业务状态码
 *
 * @author liangyuding
 * @create 2020-04-15
 */
public enum StatusCode {
    WS_UNAUTHORIZED(1000, "token验证失败"),
    WS_DISCONNECT(1001, "主动断开"),
    WS_CONNECTION(1002, "连接成功"),
    WS_HEARTBEAT(1003, "heartbeat");

    private final int code;
    private final String msg;

    private StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}