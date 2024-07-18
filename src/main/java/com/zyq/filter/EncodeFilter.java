package com.zyq.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*") // 过滤所有的请求
public class EncodeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 把请求和响应对象转换成Http
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("/") || requestURI.endsWith(".html") || requestURI.endsWith(".css") || requestURI.endsWith(".js") || requestURI.contains("images")) {
            filterChain.doFilter(request, response);
        } else {
            // 设置编码格式
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
