package com.project.modules.sys.vo.login;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 登录Vo
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Data
@Accessors(chain = true)
public class LoginVoTesy implements Serializable {
    private static final long serialVersionUID = -1597781886501035813L;
    /**手机号码*/
    private String phone;

    /**手机号码*/
    private Long num;

    /**登录密码*/
    private String password;

    /**登录密码*/
    private List<String> passwords;

    /**登录密码*/
    private LoginVo loginVo;

    /**登录密码*/
    private List<LoginVo> loginVos;

    /**登录密码*/
    private Map<String, String> map;
}
