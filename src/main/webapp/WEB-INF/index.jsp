<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>CRUD APP</title>
</head>
<body>

<h2>Все пользователи</h2><br />

<c:forEach var="user" items="${requestScope.users}">
  <ul>
    <li>Имя: <c:out value="${user.getName()}"/></li>
    <li>Логин: <c:out value="${user.getLogin()}"/></li>
    <li>Пароль: <c:out value="${user.getPassword()}"/></li>


      <form method="post" action="<c:url value='/delete'/>">
          <input type="number" hidden name="id" value="${user.getId()}" />
          <input type="submit" name="delete" value="Удалить"/>
      </form>

      <form method="get" action="<c:url value='/update'/>">
          <input type="number" hidden name="id" value="${user.getId()}" />
          <input type="submit" value="Редактировать"/>
      </form>
  </ul>
  <hr />

</c:forEach>

<h2>Создание нового пользователя</h2><br />

<form method="post" action="<c:url value='/add'/>">

  <label><input type="text" name="name"></label>Имя<br>
  <label><input type="text" name="login"></label>Логин<br>
  <label><input type="text" name="password"></label>Пароль<br>

  <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>