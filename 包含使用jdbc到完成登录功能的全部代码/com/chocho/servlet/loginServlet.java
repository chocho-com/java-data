package com.chocho.servlet;

import com.chocho.dao.UserDao;
import com.chocho.model.User;
import com.chocho.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginServlet extends HttpServlet {
    UserDao dao_user = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("_name");
        String password = request.getParameter("_password");
        request.setAttribute("_name", name);
        request.setAttribute("_password", password);
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(password)){
            request.setAttribute("error", "用户名或密码为空");
            //服务器跳转
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User user = new User(name, password);
        User currentUser = dao_user.login(user);
        if(currentUser == null){
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
            //获取Session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser);
            //客户端跳转
            response.sendRedirect("main.jsp");
        }
    }
}
