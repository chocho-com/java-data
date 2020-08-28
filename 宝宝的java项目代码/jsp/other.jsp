<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	body{
		background: #EEEEEE;
		text-align: center;
	}
	h1{
		text-align: center;
		font-size: 18px;
	}
</style>
<title>欢迎管理员</title>
</head>
<body>
	<% 
		out.append("欢迎"+session.getAttribute("ok")+"登陆");
		
	%>
	
	<h1>菜单选项</h1>
    <ul>
       <li><a href="http://localhost:8080/j2017051152_30_javaeeManagerSystem/jsp/insert.jsp" target="rights">
       增加员工信息</a></li>
       <li><a href="http://localhost:8080/j2017051152_30_javaeeManagerSystem/jsp/update.jsp" target="rights">
       修改员工信息</a></li>
       <li><a href="http://localhost:8080/j2017051152_30_javaeeManagerSystem/jsp/delete.jsp" target="rights">
      删除员工信息</a></li>
    </ul>
</body>
</html>