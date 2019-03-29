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
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<div class="nav"><!--Nav bar from w3schools: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_topnav (TEST ONLY)-->
    <a class="active" href="index.jsp">Home</a>
    <a href="AboutUs.jsp">About Us</a>
    <a href="ContactUs.jsp">Contact Us</a>
    <a href="LogIn.jsp">LogIn</a>
    <a href="registration.jsp">Register</a>
    <a>Log out</a>
</div>

<form action="Register" method="post">
    User name : <input type="text" name="user" required="required">
    Password : <input type="password" name="password" required="required">
    Confirm Password : <input type="password2" name="password2" required="required"  >
    <input type="submit" value="Register">
</form>
</body>
</html>
