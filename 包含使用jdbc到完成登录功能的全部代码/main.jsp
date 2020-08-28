<%--
  Created by IntelliJ IDEA.
  User: 山鬼名夏
  Date: 2019/10/1
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理系统主界面</title>
    <%
        if(session.getAttribute("currentUser") == null){
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <script type="text/javascript">
        $(function () {
           //数据
            var treeData=[{
                text:"根",
                children:[{
                    text:"班级信息管理",
                    attributes:{
                        url:""
                    }
                },{
                    text:"学生信息管理",
                    attributes:{
                        url:""
                    }
                }]
            }];
            //实例化树菜单
            $("#tree").tree({
                data:treeData,
                lines:true
            });
        });
    </script>
</head>
<body class="easyui-layout">
    <div region="north" style="height: 80px;" align="center" background-color="green">学生信息管理系统
        <tr>当前用户:${currentUser.getName}</tr>
    </div>
    <div region="center">
        <div class="easyui-tabs" fit="true" border="false" id="tabs">
            <div title="首页" align="center">
                欢迎使用!
            </div>
        </div>
    </div>
    <div region="west" style="width: 150px;">
        <ul id="tree"></ul>
    </div>
    <div region="south" style="height: 25px;" align="center">版权所有<a href="http://www.chocho.com">www.chocho.com</a></div>
</body>
</html>
