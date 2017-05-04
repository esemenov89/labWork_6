<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
-->
<html>
<head>
    <title>Read book</title>
</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<div style="position: fixed; top: 0%; right: 0;">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="button" value="Home" onclick="location.href = '${baseURL}'">
        <input type="button" value="List" onclick="location.href = '${baseURL}/${admin==null ? '/listEntitiesForUsers' : '/listEntitiesForAdmins'}'">
        <input type="submit" value="Logout" />
    </form>
</div>
<h1>${title}</h1>
<table>
    <tr>
        <td>
            <c:set var="newline" value="<%= \"\n\" %>" />
            ${fn:replace(text, newline, "<br />")}
        </td>
    </tr>
</table>
</body>
</html>
