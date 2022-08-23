package com.dajuancai.knowledge_plant.config;

import com.dajuancai.knowledge_plant.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        注册拦截器 拦截所有请求 忽略登录接口的拦截
        registry.addInterceptor(logInterceptor).addPathPatterns("/**").excludePathPatterns("/login/");
    }
}