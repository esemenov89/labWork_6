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
    <form action="${baseURL}/logout" method="post">
        <input type="button" value="Home" onclick="location.href = '${baseURL}'">
        <input type="submit" value="Logout" />
    </form>
</div>
<h1>List of books:</h1>
<table style="border-collapse: collapse; border: 2px solid white">
    <tr>
        <td style="padding: 3px;border: 1px dotted black"><b>Author</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Title</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Publising House</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>City</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Year</b></td>
        <td style="padding: 3px;border: 1px dotted black"><b>Pages count</b></td>
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
            <td><form method="post">
                <input type="submit" value="read"/>
                <input type="hidden" name="isn" value="${book.isn}"/>
            </form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>