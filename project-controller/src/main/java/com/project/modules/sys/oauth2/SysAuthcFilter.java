package com.project.modules.sys.oauth2;

import com.project.modules.sys.service.ShiroService;
import com.project.modules.sys.vo.login.LoginUserVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证token
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Slf4j
public class SysAuthcFilter extends AccessControlFilter {
    private final static String TOKEN_PARAM_KEY = "token";

    @Autowired
    private ShiroService shiroService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String accessToken = ((HttpServletRequest) request).getHeader(TOKEN_PARAM_KEY);
        try {
            Long userId = JjwtUtils.getUserId(accessToken);
            //读取redis中token信息，但是不改变原来的超时时间
            LoginUserVo userVo = shiroService.queryByToken(accessToken);
            if (null != userVo) {
                //验证jwt token的完整性和有效性
                JjwtUtils.verify(accessToken);
                //委托给Realm进行登录
                try {
                    getSubject(request, response).login(new SysAuthcToken(userId, accessToken, userVo));
                } catch (Exception e) {
                    // userToken验证失败
                    log.debug("[ userId:" + userId + "] userToken验证失败：" + accessToken);
                    response(httpResponse, HttpStatus.OK.value(), R.error(StatusCode.TOKEN_OUT));
                    return false;
                }
                //token续命
                redisUtils.set(RedisKeys.SysLogin.Login(userId), userVo, JjwtUtils.getProlong());
                return true;
            }
            // token不在缓存中
            response(httpResponse, HttpStatus.OK.value(), R.error(StatusCode.TOKEN_OVERDUE));
        } catch (Exception e) {
            response(httpResponse, HttpStatus.FORBIDDEN.value(), R.error(StatusCode.TOKEN_FAULT));
        }
        return false;
    }

    public static void response(HttpServletResponse httpResponse, int statusCode, R returns) {
        httpResponse.setStatus(statusCode);
        //设置编码格式
        httpResponse.setCharacterEncoding("UTF-8");
        //设置ContentType，返回内容的MIME类型
        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //告诉所有的缓存机制是否可以缓存及哪种类型
        httpResponse.setHeader("Cache-Control", "no-cache");
        String json = JsonUtil.toJSONString(returns);
        try {
            httpResponse.getWriter().write(json);
            httpResponse.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

