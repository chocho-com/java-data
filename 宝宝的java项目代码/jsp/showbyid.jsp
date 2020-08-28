<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>员工信息如下</h1><br/><hr/>
	员工编号：${emp.empno}<br/>
	员工名字：${emp.ename}<br/>
	员工职业：${emp.job}<br/>
	员工领导编号：${emp.mgr}<br/>
	员工入职时间：${emp.hiredate}<br/>
	员工薪水：${emp.sal}<br/>
	员工提成：${emp.comm}<br/>
	员工部门编号：${emp.deptno}<br/>
	员工部门名字：${emp.dname}<br/>
	员工部门所在地：${emp.loc}<br/><hr/>
</body>
</html>