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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> <!-- Drop down button script-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>  <!-- Drop down button script-->
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<nav class="navbar navbar-inverse"><!-- Bootstrap nav bar -->
    <div class="container-fluid">
        <div class="navbar-header">
            <button type= "button" class= "navbar-toggle" data-toggle="collapse" data-target="#Navigation"><!-- enables a responsive nav bar for mobiles -->
                <span class="icon-bar"></span>
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
</nav><!-- end of nav -->



<div class="content"><!-- start of content/ enables page styling -->
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
        <% if (session.getAttribute("error") == null){
            out.println("<p style = 'Display: none;' id = 'usernameError' style = 'Colour : red;'> "+ session.getAttribute("error") + "</p>");
                }
            else{
            out.println("<p id = 'usernameError' style = 'Colour : red;'> "+ session.getAttribute("error") + "</p>");
        }%>

        <button type="submit" class="btn btn-default">Login</button>
    </form>

</div><!--end of content -->

</body>
</html>
