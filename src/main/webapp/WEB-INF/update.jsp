<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UPDATE CRUD</title>
</head>
<body>
<div>Имя: <c:out value="${requestScope.user.getName()}"/></div>
<div>Логин: <c:out value="${requestScope.user.getLogin()}"/></div>
<div>Пароль: <c:out value="${requestScope.user.getPassword()}"/></div>

<br/>

<form method="post" action="<c:url value='/update'/>">
    <label>Новый логин: <input type="text" name="login" value="${requestScope.user.getLogin()}"/></label><br>
    <label>Новый пароль: <input type="text" name="password" value="${requestScope.user.getPassword()}"/></label><br>
    <input type="number" hidden name="id" value="${requestScope.user.getId()}"/>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>