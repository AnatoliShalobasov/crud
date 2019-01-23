<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD APP</title>
</head>
<body>

<h2>Все пользователи</h2><br/>
    <table border="1">
        <tr><th>Имя </th><th>Логин</th><th>Пароль</th><th>Удалить</th><th>Редактировать</th></tr>
        <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><c:out value="${user.getName()}"/></td>
            <td><c:out value="${user.getLogin()}"/></td>
            <td><c:out value="${user.getPassword()}"/></td>
            <td>
            <form method="post" action="<c:url value='/delete'/>">
                <input type="number" hidden name="id" value="${user.getId()}"/>
                <input type="submit" name="delete" value="Удалить"/>
            </form>
            </td>
            <td>
                <form method="get" action="<c:url value='/update'/>">
                    <input type="number" hidden name="id" value="${user.getId()}"/>
                    <input type="submit" value="Редактировать"/>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>


<h3>Создание нового пользователя</h3><br/>
<form method="post" action="<c:url value='/add'/>">

    <label><input type="text" name="name"></label>Имя<br>
    <label><input type="text" name="login"></label>Логин<br>
    <label><input type="text" name="password"></label>Пароль<br>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>