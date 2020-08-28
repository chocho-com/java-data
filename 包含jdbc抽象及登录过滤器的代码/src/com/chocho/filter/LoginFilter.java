package com.chocho.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Object username = request.getSession().getAttribute("username");
        if(username == null){
            //登录失败
            response.sendRedirect("index.jsp");  /*沒有重定向过去*/
            return;
        }else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
