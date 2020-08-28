<%--
  Created by IntelliJ IDEA.
  User: 山鬼名夏
  Date: 2019/9/29
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理系统登录</title>
    <!--重置账号密码-->
    <script type="text/javascript">
        function resetValue() {
            document.getElementById("_name").value="";
            document.getElementById("_password").value="";
        }
    </script>
</head>
<body>
<div align="center" style="padding-top: 50px;">
    <form method="post" action="login">
        <table width="740" height="500" border="0" background="WEB-INF/image/login.jpg">
            <tr height="10">
                <td width="40%"></td>
                <td width="10%" align="center">用户名</td>
                <td><input type="text" value="${_name}" name="_name" id="_name"></td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%" align="center">密码</td>
                <td><input type="password" value="${_password}" name="_password" id="_password"></td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%" align="center"><input type="submit" value="登录"></td>
                <td align="center"><input type="button" value="重置" onclick="resetValue()"></td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td colspan="3">
                    <font color="red">${error}</font>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
