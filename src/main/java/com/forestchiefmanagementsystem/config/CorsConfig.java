package com.forestchiefmanagementsystem.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 
 * @create 2021/8/26 15:38
 * @description:跨域请求
 */

//@Component
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        allowedOrigins：设置能跨域访问我的域名，其中*号代表任意域名。
//        allowCredentials：是否允许携带cookie？默认情况下值为false。
//        allowMethod：接受的请求方式。
//        maxAge：本次许可的有效时长，单位是秒。
//        allowHeaders：请求头信息。
//        path：“/**”表示的是拦截对应域名下的所有请求，也可以自行设置请求路径。
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}

