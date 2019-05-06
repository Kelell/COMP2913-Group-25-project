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
    <title>SignUp</title>
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
            <li class="active"><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
        </div>
    </div>
</nav>



<div class="content">
    <h1>Sign Up</h1>
    <%--<form action="Register" method="post">--%>
        <%--First Name : <input type="text" name="firstname" required="required">--%>
        <%--Second Name : <input type="text" name="secondname" required="required">--%>
        <%--Address : <input type="text" name="address" required="required">--%>
        <%--Email : <input type="text" name="email" required="required">--%>
        <%--User name : <input type="text" name="username" required="required">--%>
        <%--Password : <input type="password" name="password" required="required">--%>
        <%--Confirm Password : <input type="password2" name="password2" required="required"  >--%>
        <%--<input type="submit" value="Register">--%>
    <%--</form>--%>


    <form action="Register" method="post">
        <div class="form-group">
            <label for="firstname">First Name : </label>
            <input name="firstname" type="text" class="form-control" id="firstname" required="required">
        </div>
        <div class="form-group">
            <label for="secondname">Second Name :</label>
            <input name="secondname" type="text" class="form-control" id="secondname" required="required">
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
        <button type="submit" class="btn btn-default">Register</button>
    </form>
    <p>${param.message}</p>
</div>

</body>
</html>
