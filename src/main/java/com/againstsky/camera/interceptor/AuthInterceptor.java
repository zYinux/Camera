package com.againstsky.camera.interceptor;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.againstsky.camera.config.AppConfig;
import com.againstsky.camera.core.annotation.NoAuthInterface;
import com.againstsky.camera.em.ResponseCode;
import com.againstsky.camera.object.ResponseResult;
import com.againstsky.camera.object.TokenData;
import com.againstsky.camera.util.JwtUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AuthInterceptor
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    AppConfig appConfig;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 如果接口方法标记NoAuthInterface注解，可以直接跳过Token鉴权验证，这里主要为了测试接口方便
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            if (hm.getBeanType().getAnnotation(NoAuthInterface.class) != null
                    || hm.getMethodAnnotation(NoAuthInterface.class) != null) {
                return true;
            }
        }

        // 获取请求头中的token
        String token = request.getHeader(appConfig.getTokenHeaderKey());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(appConfig.getTokenHeaderKey());
        }
        // 解析token,如果没token检验失败则拦截
        Claims c = JwtUtil.parseToken(token, appConfig.getTokenSigningKey());
        if (JwtUtil.isNullOrExpired(c)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            this.outputResponseMessage(response,
                    ResponseResult.error(ResponseCode.AUTH_ERROR));
            return false;
        }

        //把用户名存入当前请求里面
        String userId= (String) c.get("userId");
        TokenData tokenData=new TokenData();
        tokenData.setUserId(userId);
        tokenData.setToken(token);
        TokenData.addToRequest(tokenData);

        return true;
    }

    private void outputResponseMessage(HttpServletResponse response, ResponseResult respObj) {
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            log.error("Failed to call OutputResponseMessage.", e);
            return;
        }
        response.setContentType("application/json; charset=utf-8");
        out.print(JSONUtil.toJsonStr(respObj));
        out.flush();
        out.close();
    }


}
