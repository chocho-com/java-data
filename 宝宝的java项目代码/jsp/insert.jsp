<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>请输入插入信息</h1>
	<form action="/j2017051152_30_javaeeManagerSystem/InsertEmp" method="post">
	请输入员工编号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" size=20 name="empno"><br/>
	请输入员工名字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" size=20 name="ename"><br/>
	请输入员工职业&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" size=20 name="job"><br/>
	请输入员工领导编号：<input type="text" size=20 name="mgr"><br/>
	请输入员工入职时间：<input type="text" size=20 placeholder="year-month-day" name="hiredate"><br/>
	请输入员工薪水&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" size=20 name="sal"><br/>
	请输入员工提成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" size=20 name="comm"><br/>
	请输入员工部门编号：<input type="text" size=20 name="deptno"><br/>
	<input type="submit" name="提交">
	</form>
</body>
</html>