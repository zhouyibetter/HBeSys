package com.zyq.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 过滤所有访问无权限的内部访问
 */
@WebFilter("/*")
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 转换成http类型的 请求/响应
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        // 必须开通的登录通达
        // Access也会产生静态资源的请求！！！记得放行
        if (url.contains("login") || url.contains("logout") || url.endsWith("/") || url.endsWith(".css") || url.endsWith(".js") || url.endsWith("html") || url.contains("images")) {
            filterChain.doFilter(req, resp);
        } else {
            // 判断Session中是否有角色
            Object admin = req.getSession().getAttribute("admin");
            Object doctor = req.getSession().getAttribute("doctor");
            // 如果当前没有登录任何一个角色
            if (admin == null && doctor == null) {
                resp.sendRedirect(req.getContextPath() + "/login.html");
            } else if (admin == null) {
                // 已有医生角色
                if (url.contains("/doctor")) {
                    filterChain.doFilter(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/login.html");
                }
            } else if (doctor == null) {
                // 已有管理员身份
                filterChain.doFilter(req, resp);
            } else {
                // 两个角色都有就直接放行
                filterChain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
