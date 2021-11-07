<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>System A</title>
</head>
<body>
<h1>Hello! Here is System A!</h1>
<%
    String username = "尚未登录";
    Cookie[] cookies = request.getCookies();
    if (null!=cookies && cookies.length != 0){
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")){
                username = cookie.getValue();
                break;
            }
        }
    }
%>
<br>
<span style="font-size: 25px;font-weight: bold;">当前用户：<%=username %></span>
<br>
<br>
<a href="/app2">去System B</a>
<br>
<a href="/">回到主页</a>
<br>
<br>
<a href="/logout">退出登录</a>
</body>
</html>
