package com.againstsky.camera.core.aop;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.lang.generator.UUIDGenerator;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * AccessLogAspect
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/23
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class AccessLogAspect {

    /**
     * 所有controller方法。
     */
    @Pointcut("execution(public * com.againstsky.camera..controller..*(..))")
    public void controllerPointCut() {
        // 空注释，避免sonar警告
    }

    @Around("controllerPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        // 请求流水号
        long start = System.currentTimeMillis();
        // 获取方法参数
        List<Object> httpReqArgs = new ArrayList<>();
        Object[] args = joinPoint.getArgs();
        for (Object object : args) {
            if (!(object instanceof HttpServletRequest)
                    && !(object instanceof HttpServletResponse)
                    && !(object instanceof MultipartFile)
                    && !(object instanceof MultipartFile[])) {
                httpReqArgs.add(object);
            }
        }
        String url = request.getRequestURI();
        String params = JSONUtil.toJsonStr(httpReqArgs);
        log.info("开始请求,url={}, reqData={}", url, params);
        Object result = null;
        try {
            // 调用原来的方法
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("请求报错， url={}, reqData={}, error={}", url, params, e.getMessage());
            throw e;
        } finally {
            // 获取应答报文及接口处理耗时
            String respData = result == null ? null : JSONUtil.toJsonStr(result);
            log.info("请求完成, url={}，elapse={}ms, respData={}",
                    url, (System.currentTimeMillis() - start), respData);
        }
        return result;
    }
}
