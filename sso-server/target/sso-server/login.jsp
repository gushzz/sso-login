<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="/login" method="post">
        <label>用户名：
            <input type="text" name="username"/>
        </label>
        <input type="hidden" name="clientUrl" value="${requestScope.clientUrl}"/>
        <button type="submit">登录</button>
    </form>
</body>
</html>
