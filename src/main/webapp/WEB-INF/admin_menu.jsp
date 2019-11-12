<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD APP</title>
</head>
<body>

<h2>Все пользователи</h2><br/>
<table border="1">
    <tr>
        <th>Имя</th>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Роль</th>
        <th>Удалить</th>
        <th>Редактировать</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><c:out value="${user.getName()}"/></td>
            <td><c:out value="${user.getLogin()}"/></td>
            <td><c:out value="${user.getPassword()}"/></td>
            <td><c:out value="${user.getRole()}"/></td>
            <td>
                <form method="post" action="<c:url value='/admin/delete'/>">
                    <input type="number" hidden name="id" value="${user.getId()}"/>
                    <input type="submit" name="delete" value="Удалить"/>
                </form>
            </td>
            <td>
                <form method="get" action="<c:url value='/admin/update'/>">
                    <input type="number" hidden name="id" value="${user.getId()}"/>
                    <input type="submit" value="Редактировать"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    <form method="post" action="<c:url value='/admin/add'/>">
        <tr>
            <td>Создание нового пользователь</td>
            <td><label><input type="text" name="name"></label>Имя</td>
            <td><label><input type="text" name="login"></label>Логин</td>
            <td><label><input type="text" name="password"></label>Пароль</td>
            <td><label>
                <select name="role">
                    <option selected value="user">User</option>
                    <option value="admin">Admin</option>
                </select>
            </label>Роль
            </td>
            <td><input type="submit" value="Ok" name="Ok"></td>
        </tr>
    </form>
</table>
<br><br>
<a href="<c:url value="/logout"/>" > Logout </a>
</body>
</html>