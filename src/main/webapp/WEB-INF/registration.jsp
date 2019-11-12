<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>

</head>
<body>
<div class="form">
    <h1>Registration</h1><br>

    <form method="post" action="/registration">
        <input type="text" required placeholder="login" name="login"><br>
        <input type="text" required placeholder="name" name="name"><br>
        <input type="password" required placeholder="password" name="password"><br><br>
        <label>Role:
            <select required name="role">
                <option value="user">User</option>
                <option value="admin">Admin</option>
            </select></label><br><br><br>
        <input class="button" type="submit" value="Registration">
    </form>
</div>
</body>
</html>