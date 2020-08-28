package team.f4.javaee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import team.f4.javaee.dbutils.DBUntils;
import team.f4.javaee.projo.Admin;
import team.f4.javaee.projo.Emp;

public class userDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Statement st = null;
	DBUntils dbUntils = new DBUntils();
	public int getTotalPageCount(int pageSize) throws ClassNotFoundException, SQLException {
		conn = DBUntils.getconn();
		String sql="select count(*) from emp";
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		rs.next();
		int total = rs.getInt(1);
		int totalPageCount = total / pageSize;
	    if (total % pageSize != 0) {
	          totalPageCount += 1;
	    }
	    return totalPageCount;
	}
	public List<Emp> getEmpByPage(int page, int pageSize) throws ClassNotFoundException, SQLException { // 分页查询
		conn = DBUntils.getconn();
		String sql="SELECT * FROM emp INNER JOIN dept ON emp.deptno=dept.deptno LIMIT ?,?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, (page-1)*pageSize);
		ps.setInt(2, pageSize);
		rs = ps.executeQuery();
		List<Emp> emps = new ArrayList<>();
		while(rs.next()) {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setMgr(rs.getInt(4));
			emp.setHiredate(rs.getString(5));
			emp.setSal(rs.getInt(6));
			emp.setComm(rs.getInt(7));
			emp.setDeptno(rs.getInt(8));
			emp.setDname(rs.getString(10));
			emp.setLoc(rs.getString(11));
			emps.add(emp);
		}
		return emps;	
	}
	public List<Emp> CheckAllEmpInf() throws ClassNotFoundException, SQLException {
		conn = DBUntils.getconn();
		String sql ="SELECT * FROM emp INNER JOIN dept ON emp.deptno=dept.deptno";
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		List<Emp> emps = new ArrayList<>();
		while(rs.next()) {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setMgr(rs.getInt(4));
			emp.setHiredate(rs.getString(5));
			emp.setSal(rs.getInt(6));
			emp.setComm(rs.getInt(7));
			emp.setDeptno(rs.getInt(8));
			emp.setDname(rs.getString(10));
			emp.setLoc(rs.getString(11));
			emps.add(emp);
		}
		return emps;
	}
	public List<Admin> GetAdmin() throws ClassNotFoundException, SQLException{
		conn = DBUntils.getconn();
		
		String sql = "select * from admin";
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		List<Admin> admins = new ArrayList<>();
		while(rs.next()) {
			Admin admin = new Admin();
			admin.setUsername(rs.getString(1));
			admin.setPassword(rs.getString(2));
			admins.add(admin);
		}
		return admins;
	}
	
	public int InsertEmp(Emp emp) throws ClassNotFoundException, SQLException{
		conn = DBUntils.getconn();
		String sql="insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; // 创建sql语句
		int count;
		try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, emp.getEmpno());
				ps.setString(2, emp.getEname());
				ps.setString(3, emp.getJob());
				ps.setInt(4, emp.getMgr());
				ps.setString(5, emp.getHiredate());
				ps.setInt(6, emp.getSal());
				ps.setInt(7, emp.getComm());
				ps.setInt(8, emp.getDeptno());
				count = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				count = 0;
			} finally {
				new DBUntils().releaseAll(conn, ps, rs);
		}
		return count;
	}
	
	public int UpdateEmp(Emp emp) throws ClassNotFoundException, SQLException{
		conn = DBUntils.getconn();
		String sql="update emp set ename=?, job=?, mgr=?, hiredate=?, sal=?, comm=?,deptno=?\r\n" + 
				"where empno=?";
		int count;
		try {
				ps=conn.prepareStatement(sql);
				ps.setInt(8, emp.getEmpno());
				ps.setString(1, emp.getEname());
				ps.setString(2, emp.getJob());
				ps.setInt(3, emp.getMgr());
				ps.setString(4, emp.getHiredate());
				ps.setInt(5, emp.getSal());
				ps.setInt(6, emp.getComm());
				ps.setInt(7, emp.getDeptno());
				count = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				count = 0;
			} finally {
				new DBUntils().releaseAll(conn, ps, rs);
		}
		return count;
	}
	public int DeleteEmp(Emp emp)throws ClassNotFoundException, SQLException{
		conn = DBUntils.getconn();
		String sql="DELETE FROM emp WHERE empno = ?";
		int count;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, emp.getEmpno());
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			count = 0;
		} finally {
			new DBUntils().releaseAll(conn, ps, rs);
	}
	return count;
	}
	public Emp GetEmpByNo(Integer empno) throws ClassNotFoundException, SQLException {
		conn = DBUntils.getconn();
		String sql="SELECT * FROM emp INNER JOIN dept ON emp.deptno=dept.deptno WHERE empno = ?";
		Emp emp =null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, empno);
			rs = ps.executeQuery();
			while(rs.next()) {
				emp = new Emp();
				emp.setEmpno(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setMgr(rs.getInt(4));
				emp.setHiredate(rs.getString(5));
				emp.setSal(rs.getInt(6));
				emp.setComm(rs.getInt(7));
				emp.setDeptno(rs.getInt(8));
				emp.setDname(rs.getString(10));
				emp.setLoc(rs.getString(11));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return emp;
	}
	
	public List<Emp> GetEmpInfByEname(String name) throws ClassNotFoundException, SQLException {
		conn = DBUntils.getconn();
		String sql ="SELECT * FROM emp INNER JOIN dept ON emp.deptno=dept.deptno"
				+ " where ename like concat('%',?,'%')";
		ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		List<Emp> emps = new ArrayList<>();
		while(rs.next()) {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setMgr(rs.getInt(4));
			emp.setHiredate(rs.getString(5));
			emp.setSal(rs.getInt(6));
			emp.setComm(rs.getInt(7));
			emp.setDeptno(rs.getInt(8));
			emp.setDname(rs.getString(10));
			emp.setLoc(rs.getString(11));
			emps.add(emp);
			//System.out.println(emp);
		}
		return emps;
	}
}
