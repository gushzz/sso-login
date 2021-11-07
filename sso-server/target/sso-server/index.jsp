<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>单点登录系统</title>
</head>

<body >
    <h1 text-algign:center>单点登录系统</h1>
    <a href="/system-a">系统A</a>
    <a href="/system-b">系统B</a>
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
</body>
</html>
