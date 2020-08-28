package com.chocho.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/*
* 系统登录后主界面
* */
public class SystemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("system.jsp").forward(request,response);
    }
}
