package team.f4.javaee.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.f4.javaee.projo.Admin;
import team.f4.javaee.service.AdminService;

/**
 * Servlet implementation class CheckAdmin
 */
@WebServlet("/CheckAdmin")
public class CheckAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession httpSession = request.getSession();
		String name=request.getParameter("userName");
		String password=request.getParameter("password");
		
		try {
			List<Admin> admins = new AdminService().GetAdminInf();
			
			for (Admin admin:admins) {
				if(name.equals(admin.getUsername())) {
					if (password.equals(admin.getPassword())){
						httpSession.setAttribute("ok", admin.getUsername());
						RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/other.jsp");
						dispatcher.forward(request, response);
					}
				}
				response.getWriter().print("<script type='text/javascript'>alert('��¼ʧ�ܣ�');"
						+ "window.history.back();</script>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
