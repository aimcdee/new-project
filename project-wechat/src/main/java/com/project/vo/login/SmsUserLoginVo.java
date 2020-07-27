package com.project.vo.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author liangyuding
 * @date 2020-03-30
 */
@Data
@Accessors(chain = true)
public class SmsUserLoginVo {

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotBlank(message = "短信验证码不能为空")
    @ApiModelProperty(value = "短信验证")
    private String smsCode;

}
