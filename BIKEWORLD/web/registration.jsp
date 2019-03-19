<%--
  Created by IntelliJ IDEA.
  User: ll16m25s
  Date: 17/03/19
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="Register" method="post">
    User name : <input type="text" name="user" required="required">
    Password : <input type="password" name="password" required="required">
    Confirm Password : <input type="password2" name="password2" required="required"  >
    <input type="submit" value="Register">
</form>
</body>
</html>
