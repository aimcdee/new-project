package com.project.modules.sys.vo.login;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 登录Vo
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Data
@Accessors(chain = true)
public class LoginVo implements Serializable {
    private static final long serialVersionUID = -7690224571144919302L;

    /**手机号码*/
    private String phone;

    /**登录密码*/
    private String password;
}
