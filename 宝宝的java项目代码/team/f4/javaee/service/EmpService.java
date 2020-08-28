package team.f4.javaee.service;

import java.sql.SQLException;
import java.util.List;
import team.f4.javaee.dao.userDao;
import team.f4.javaee.projo.Emp;

public class EmpService {
	userDao uDao = new userDao();
	public int getTotalPageCount(int pageSize)throws SQLException, ClassNotFoundException {
	        return uDao.getTotalPageCount(pageSize);
	}
	public List<Emp> getEmpByPage(int page, int pageSize) throws ClassNotFoundException, SQLException { 
		if(page<1) {
			page = 1;
		}
		int totalPage = uDao.getTotalPageCount(pageSize);
		if(page > totalPage) {
			page = totalPage;
		}
		return uDao.getEmpByPage(page, pageSize);
	}
	public List<Emp>  GetAllEmpInf() throws ClassNotFoundException, SQLException {
		return uDao.CheckAllEmpInf();	
	}
	public int InsertEmpInf(Emp emp) throws ClassNotFoundException, SQLException {
		return uDao.InsertEmp(emp);
	}
	public int UpdateEmpInf(Emp emp) throws ClassNotFoundException, SQLException {
		return uDao.UpdateEmp(emp);
	}
	public int DeleteEmpInf(Emp emp) throws ClassNotFoundException, SQLException {
		return uDao.DeleteEmp(emp);
	}
	public Emp GetEmpInfByNo(Integer empno) throws ClassNotFoundException, SQLException {
		return uDao.GetEmpByNo(empno);
	}
	public List<Emp>  GetAllEmpInfByName(String name) throws ClassNotFoundException, SQLException {
		return uDao.GetEmpInfByEname(name);
	}
}
