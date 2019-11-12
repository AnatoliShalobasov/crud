<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple page</title>
</head>
<body>
<h1>ТЫ ВИДИШЬ ЭТО ТОЛЬКО ЕСЛИ ТЫ USER</h1>
<br><br>
<a href="<c:url value="/logout"/>" > Logout </a>
</body>
</html>
