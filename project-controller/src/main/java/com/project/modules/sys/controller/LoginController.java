package com.project.modules.sys.controller;

import com.project.modules.sys.service.LoginService;
import com.project.modules.sys.vo.login.LoginVo;
import com.project.utils.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.project.utils.ShiroUtils.getSysUserId;

/**
 * 登录控制器Controller
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Slf4j
@Api(tags = "登录接口", description = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        //登录
        return R.ok(loginService.login(loginVo.getPhone(), loginVo.getPassword(), request));
    }

    /**
     * 退出登陆
     */
    @PostMapping("/logout")
    public R logout() {
        loginService.logout(getSysUserId());
        return R.ok();
    }
}
