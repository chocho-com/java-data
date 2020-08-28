package com.chocho.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/*
登录Servlet
* 收集登录请求，做简单判断发给对应Servlet处理
* */
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if("张大仙".equals(username) && "123".equals(password)){
            //说明账户密码正确
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
        }
    }
}
