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
    <title>LogIn</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> <!-- Bootstrap style link  -->
    <link rel="stylesheet" href="style.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<nav class="navbar navbar-inverse"><!-- Bootstrap nav bar -->
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="Home.jsp">B!KEWORLD</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li class="active"><a href="index.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>

            <%
                if(session.getAttribute("uname")!=null){//log out button for when in session

            %>
            <li><a href="Log"><span class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
            <%
                }

            %>
        </ul>
    </div>
</nav>


<div class="content">
    <h1>Log In</h1>

    <form action="LogIn" method="post">
        <div class="form-group">
            <label for="username">User name :</label>
            <input name="username" type="text" class="form-control" id="username" required="required">
        </div>
        <div class="form-group">
            <label for="password">Password :</label>
            <input name="password" type="password" class="form-control" id="password" required="required">
        </div>
        <button type="submit" class="btn btn-default">Login</button>
    </form>

</div>


</body>
</html>
