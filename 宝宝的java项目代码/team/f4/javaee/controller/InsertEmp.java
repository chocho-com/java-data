package team.f4.javaee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.f4.javaee.projo.Emp;
import team.f4.javaee.service.EmpService;

/**
 * Servlet implementation class InsertEmp
 */
@WebServlet("/InsertEmp")
public class InsertEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEmp() {
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
		 int empno = Integer.parseInt(request.getParameter("empno"));
		 String ename = request.getParameter("ename");
		 String job = request.getParameter("job");
		 int mgr =  Integer.parseInt(request.getParameter("mgr"));   //直接领导编号
		 String hiredate = request.getParameter("hiredate"); // 入职时间
		 int sal = Integer.parseInt(request.getParameter("sal"));
		 int comm = Integer.parseInt(request.getParameter("comm"));//- 提成
		 int deptno = Integer.parseInt(request.getParameter("deptno")); //-- 部门编号
		// System.out.println(deptno);
		 Emp emp = new Emp(); 
		 emp.setEmpno(empno);
		 emp.setEname(ename);
		 emp.setJob(job);
		 emp.setMgr(mgr);
		 emp.setHiredate(hiredate);
		 emp.setSal(sal);
		 emp.setComm(comm);
		 emp.setDeptno(deptno);
		 try {
			int count = new EmpService().InsertEmpInf(emp);
			 if(count==0) {
				 response.getWriter().print("<script type='text/javascript'>alert('插入失败！');"
							+ "window.history.back();</script>");
			 }
			 else {
				 response.getWriter().print("<script type='text/javascript'>alert('插入成功！');"
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
