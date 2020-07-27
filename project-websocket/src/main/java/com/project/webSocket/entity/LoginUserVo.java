package com.project.webSocket.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录对象
 *
 * @author liangyuding
 * @create 2020-04-15
 */
@Data
@Accessors(chain = true)
public class LoginUserVo implements Serializable {
    private static final long serialVersionUID = -3968630814065137350L;

    /**用户ID*/
    private Long userId;

    /**用户名称*/
    private String username;

    /**token*/
    private String token;

    /**登录时间*/
    private Date loginTime;

    /**到期时间*/
    private Date expireTime;
}
