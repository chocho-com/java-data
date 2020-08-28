<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工信息</title>
<style>
        table {
            border: solid 1px #999;
        }

        table th, td {
            padding: 5px;
            border: solid 1px #999;
        }
    </style>
</head>
<body>
<h1>员工信息如下</h1><br/><hr/>
<table cellspacing="0">

    <tr>
        <th>员工编号</th>
        <th>员工名字</th>
        <th>员工职业</th>
        <th>员工领导编号</th>
        <th>员工入职时间</th>
        <th>员工薪水</th>
        <th>员工提成</th>
        <th>员工部门编号</th>
        <th>员工部门名字</th>
        <th>员工部门所在地</th>
    </tr>

    <c:forEach var="emps" items="${requestScope.emps}">
        <tr>
            <td>${emps.empno}</td>
            <td>${emps.ename} </td>
            <td>${emps.job} </td>
            <td>${emps.mgr} </td>
            <td>${emps.hiredate} </td>
            <td>${emps.sal} </td>
            <td>${emps.comm} </td>
            <td>${emps.deptno} </td>
            <td>${emps.dname} </td>
            <td>${emps.loc} </td>
        </tr>
    </c:forEach>
</table>
<span> <% int nowPage = Integer.parseInt(request.getAttribute("page").toString()); 
			if(nowPage != 1){
				out.print("<a href=\"http://localhost:8080/j2017051152_30_javaeeManagerSystem/WithPage?page=" + (nowPage - 1) + "\" target=\"rights\">\n" +
		                "上一页</a>");
			}
%></span>
<span>当前第${requestScope.page}页, 总共${requestScope.totalPage}页</span>
<span><%
    if (nowPage != (int) request.getAttribute("totalPage")) {
        out.print("<a href=\"http://localhost:8080/j2017051152_30_javaeeManagerSystem/WithPage?page=" + (nowPage + 1) + "\" target=\"rights\">\n" +
                "下一页</a>");
    }
%></span>
</body>
</html>