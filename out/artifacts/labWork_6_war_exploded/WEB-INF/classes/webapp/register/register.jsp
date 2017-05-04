<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
-->
<html>
<head>
    <title>Register in Internet Library</title>
    <h1>Register</h1>
</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<div style="position: fixed; top: 0%; right: 0;">
    <input type="button" value="Home" onclick="location.href = '${baseURL}'">
</div>
<form method="post">
    <p>Login:</p>
    <p style="color: red;">${registerLogin}</p>
    <input type="text" name="login"/>
    <p>Password (password most be contain latin symbols and digits, minimum 8 symbols):</p>
    <p style="color: red;">${registerPassword}</p>
    <input type="text" name="password"/>
    <p>Email:</p>
    <p style="color: red;">${registerMail}</p>
    <input type="text" name="email"/>
    <p></p>
    <input type="submit" value="ok"/>
</form>
</body>
</html>