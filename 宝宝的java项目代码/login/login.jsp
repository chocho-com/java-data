<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="main.css" />
<title>欢迎登陆</title>
</head>
<body>
<div class="main">
    <div class="title">
        <span>密码登录</span>
    </div>
 
    <div class="title-msg">
        <span>请输入登录账户和密码</span>
    </div>
 
    <form action="/j2017051152_30_javaeeManagerSystem/CheckAdmin" method="post">
        <!--输入框-->
        <div class="input-content">
            <!--autoFocus-->
            <div>
                <input type="text" autocomplete="off"
                       placeholder="用户名" name="userName" required/>
            </div>
 
            <div style="margin-top: 16px">
                <input type="password"
                       autocomplete="off" placeholder="登录密码" name="password" required maxlength="32"/>
            </div>
        </div>
 
        <!--登入按钮-->
        <div style="text-align: center">
            <button type="submit" class="enter-btn" >登录</button>
        </div>
 
        <div class="foor">
            <div class="left"><span>忘记密码 ?</span></div>
 
            <div class="right"><span>注册账户</span></div>
        </div>
    </form>
 
</div>
</body>
</html>