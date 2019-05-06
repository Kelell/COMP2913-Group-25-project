<%--
  Created by IntelliJ IDEA.
  User: ll16m25s
  Date: 17/03/19
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogIn</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> <!-- Bootstrap style link  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> <!-- Drop down button script-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>  <!-- Drop down button script-->
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<nav class="navbar navbar-inverse"><!-- Bootstrap nav bar -->
    <div class="container-fluid">
        <div class="navbar-header">
            <button type= "button" class= "navbar-toggle" data-toggle="collapse" data-target="#Navigation"><!--button enabelinig the colapse of navigation ber-->
                <span class="icon-bar"></span>                                                               <!--used when screen size is too small-->
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">B!KEWORLD</a>
        </div>
        <div class = "collapse navbar-collapse" id="Navigation">
        <ul class="nav navbar-nav">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="AboutUs.jsp">About Us</a></li>
            <li><a href="ContactUs.jsp">Contact Us</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li class="active"><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>

            <%
                if(session.getAttribute("uname")!=null){//log out button for when in session

            %>
            <li><a href="Log"><span class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
            <%
                }

            %>
        </ul>
        </div>
    </div>
</nav>



<div class="content">
    <h1>Log In</h1>
    <%--<form action="LogIn" method="post">--%>
        <%--User name : <input type="text" name="username" required="required">--%>
        <%--Password : <input type="password" name="password" required="required">--%>
        <%--<input type="submit" value="Login">--%>

    <%--</form>--%>

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
    <p>${param.message}</p>

</div>
</body>
</html>
