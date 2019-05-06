<%--
  Created by IntelliJ IDEA.
  User: sc16bmt
  Date: 23/02/19
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Index</title>
    <link rel="stylesheet" href="style.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  </head>
  <body>

  <div class = "Title">
    B!KEWORLD
  </div>

  <div class="nav"><!--Nav bar from w3schools: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_topnav (TEST ONLY)-->
    <a class="active" href="index.jsp">Home</a>
    <a href="registration.jsp">Sign up</a>
  </div>

  <form action="LogIn" method="post">

    Username: <br><input type="text" name="username" required>
    <br>
    Password: <br><input type="password" name="password" required>
    <br><br>
    <input type="submit" value="Login">

  </form>



  </body>
</html>
