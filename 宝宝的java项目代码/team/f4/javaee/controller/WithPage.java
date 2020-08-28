package team.f4.javaee.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.f4.javaee.projo.Emp;
import team.f4.javaee.service.EmpService;

/**
 * Servlet implementation class WithPage
 */
@WebServlet("/WithPage")
public class WithPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page=1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch (Exception e) {
			// TODO: handle exception
			page = 1;
		}
		int pageSize = 5;
		try {
			 List<Emp> emps = new EmpService().getEmpByPage(page, pageSize);
			 request.setAttribute("emps", emps);
			 request.setAttribute("page", page);
			 request.setAttribute("pageSize", pageSize);
			 request.setAttribute("totalPage", new EmpService().getTotalPageCount(pageSize));
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/Menu.jsp");
	         requestDispatcher.forward(request,response);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
