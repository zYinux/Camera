package com.againstsky.camera.config;

import com.againstsky.camera.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CameraWebMvcConfigurer
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Configuration
public class CameraWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("auth/login")
                .excludePathPatterns("/static/**");
    }
}
