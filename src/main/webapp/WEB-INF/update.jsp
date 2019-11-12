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
<div>Роль: <c:out value="${requestScope.user.getRole()}"/></div>
<br/>
<form method="post" action="<c:url value='/admin/update'/>">
    <label>Новый логин: <input type="text" name="login" value="${requestScope.user.getLogin()}"/></label><br>
    <label>Новый пароль: <input type="text" name="password" value="${requestScope.user.getPassword()}"/></label><br>
    <label>Новая роль:
        <select name="role">
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select></label><br>
    <input type="number" hidden name="id" value="${requestScope.user.getId()}"/>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
<br><br>
<a href="<c:url value="/logout"/>" > Logout </a>
</body>
</html>