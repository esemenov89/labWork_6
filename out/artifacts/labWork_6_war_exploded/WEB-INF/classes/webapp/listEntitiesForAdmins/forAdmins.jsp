<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
-->
<html>
<head>
    <title>List of books</title>
</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<div style="position: fixed; top: 0%; right: 0;">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="button" value="Home" onclick="location.href = '${baseURL}'">
        <input type="submit" value="Logout" />
    </form>
</div>
<h1>List of books:</h1>
<table style="border-collapse: collapse; border: 2px solid white">
    <td><input type="button" value="add" onclick="location.href = '${pageContext.request.contextPath}${currentURL}/addStorageUnit'"></td>

    <tr>
        <td style="padding: 3px;border: 1px dotted black"><b>Author</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Title</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Publising House</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>City</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Year</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Pages count</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Isn</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Action</b></td>
    </tr>

    <c:forEach items="${requestScope.books}" var="book">
        <tr>
            <td><c:out value="${book.author}"></c:out></td>
            <td><c:out value="${book.title}"></c:out></td>
            <td><c:out value="${book.publishingHouse}"></c:out></td>
            <td><c:out value="${book.city}"></c:out></td>
            <td><c:out value="${book.year}"></c:out></td>
            <td><c:out value="${book.pagesCount}"></c:out></td>
            <td><c:out value="${book.isn}"></c:out></td>
            <td>
                <input type="button" value="read" onclick="location.href = '${pageContext.request.contextPath}${currentURL}/readStorageUnit?isn=<c:out value="${book.isn}" />'">
                <input type="button" value="change" onclick="location.href = '${pageContext.request.contextPath}${currentURL}/changeStorageUnit?isn=<c:out value="${book.isn}" />'">
                <input type="button" value="del" onclick="location.href = '${pageContext.request.contextPath}${currentURL}/deleteStorageUnit?isn=<c:out value="${book.isn}" />'">
            </td>
        </tr>
    </c:forEach>
</table>
<p></p>
<h1>List of users:</h1>
<table style="border-collapse: collapse; border: 2px solid white">

    <tr>
        <td style="padding: 3px;border: 1px dotted black"><b>Login</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Account role</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Locked</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Action</b></td>
    </tr>

    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.role=='ROLE_USER' ? 'User' : 'Admin'}"></c:out></td>
            <c:choose>
                <c:when test="${user.enabled=='1'}">
                    <td><p>Not locked</p></td>
                    <c:if test="${user.role=='ROLE_USER'}">
                        <td>
                            <input type="button" value="lock" onclick="location.href = '${pageContext.request.contextPath}${currentURL}/lockOrUnlock?nick=<c:out value="${user.login}"/>&lock=<c:out value="${user.enabled}"/>'">
                        </td>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <td><p>Locked</p></td>
                    <c:if test="${user.role=='ROLE_USER'}">
                        <td>
                            <input type="button" value="unlock" onclick="location.href = '${pageContext.request.contextPath}${currentURL}/lockOrUnlock?nick=<c:out value="${user.login}"/>&lock=<c:out value="${user.enabled}"/>'">
                        </td>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>