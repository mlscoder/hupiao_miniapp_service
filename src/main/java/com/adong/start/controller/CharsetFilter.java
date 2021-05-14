package com.adong.start.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/hello/test", filterName = "CharsetFilter")
public class CharsetFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);//交给下一个过滤器或servlet处理
    }

    public void destroy() {
        /*销毁时调用*/
    }
}