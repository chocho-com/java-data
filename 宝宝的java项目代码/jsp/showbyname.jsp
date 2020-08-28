<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工信息</title>
</head>
<body>
<h1>员工信息如下</h1><hr/>
<c:forEach var="emps" items="${requestScope.emps}">
       员工编号：${emps.empno}<br/>
	员工名字：${emps.ename}<br/>
	员工职业：${emps.job}<br/>
	员工领导编号：${emps.mgr}<br/>
	员工入职时间：${emps.hiredate}<br/>
	员工薪水：${emps.sal}<br/>
	员工提成：${emps.comm}<br/>
	员工部门编号：${emps.deptno}<br/>
	员工部门名字：${emps.dname}<br/>
	员工部门所在地：${emps.loc}<br/><hr/>
    </c:forEach>
</body>
</html>