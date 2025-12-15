package org.taobao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.taobao.interceptor.TokenInterceptor;

@Configuration // 配置类。加上@Configuration注解，表示当前类是一个配置类
public class WebConfig implements WebMvcConfigurer {

    // 拦截器对象
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器对象
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")// 设置拦截器拦截的请求路径（ /** 表示拦截所有请求）
                .excludePathPatterns("/user/login", "/user/register", "/error", "/user/avatar/**",
                        "/upload", "/product/home/list");// 设置不拦截的请求路径，使用相对路径（去除context-path前缀）
    }
}