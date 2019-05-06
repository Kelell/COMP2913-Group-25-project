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
    <title>Index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> <!-- Bootstrap style link  -->
    <link rel="stylesheet" href="style.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<nav class="navbar navbar-inverse"><!-- Bootstrap nav bar -->
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">B!KEWORLD</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>

<div class="content">
    <h1>Sign Up</h1>

    <form action="Register" method="post">
        <div class="form-group">
            <label for="fname">First Name : </label>
            <input name="firstname" type="text" class="form-control" id="fname" required="required">
        </div>
        <div class="form-group">
            <label for="lname">Second Name :</label>
            <input name="secondname" type="text" class="form-control" id="lname" required="required">
        </div>
        <div class="form-group">
            <label for="address">Address :</label>
            <input name="address" type="text" class="form-control" id="address" required="required">
        </div>
        <div class="form-group">
            <label for="email">Email :</label>
            <input name="email" type="text" class="form-control" id="email" required="required">
        </div>
        <div class="form-group">
            <label for="username">Username :</label>
            <input name="username" type="text" class="form-control" id="username" required="required">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input name="password" type="password" class="form-control" id="password" required="required">
        </div>
        <div class="form-group">
            <label for="conpassword">Confrim Password:</label>
            <input name="password" type="password" class="form-control" id="conpassword" required="required">
        </div>
        <button type="submit" class="btn btn-default">Register</button>
    </form>
    <p>${param.message}</p>
</div>
</body>
</html>
