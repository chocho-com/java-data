package team.f4.javaee.service;

import java.sql.SQLException;
import java.util.List;
import team.f4.javaee.dao.userDao;
import team.f4.javaee.projo.Admin;


public class AdminService {
	userDao uDao = new userDao();
	public List<Admin> GetAdminInf() throws ClassNotFoundException, SQLException{
		return uDao.GetAdmin();
	}

}
