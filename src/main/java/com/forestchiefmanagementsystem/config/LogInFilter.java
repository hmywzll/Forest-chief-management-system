package com.forestchiefmanagementsystem.config;


import com.forestchiefmanagementsystem.util.MyObjectMapper;
import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤器
 */
@Slf4j
public class LogInFilter implements Filter {

    public String[] excludes={"/html","/js","/css","/dependency","verify","/login"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        是否为排除路径
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        log.info("拦截到请求"+request.getMethod()+":"+requestURI);
        for (String exclude : excludes) {
            if (requestURI.contains(exclude)){
                log.info(exclude+"放行");
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
//        是否登录
        Object id = request.getSession().getAttribute("id");
        Object position = request.getSession().getAttribute("position");
        if (id!=null&&position!=null){
            ThreadUtil.setPIId((String) id);
            ThreadUtil.setPIPosition((Integer) position);
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
            httpServletResponse.getWriter().write(MyObjectMapper.myWriteValueAsString(R.error("NOT_LOG_IN")));
        }


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
