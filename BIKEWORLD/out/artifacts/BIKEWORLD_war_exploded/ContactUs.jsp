<%--
  Created by IntelliJ IDEA.
  User: ll16m25s
  Date: 24/02/19
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact US</title>
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
                <%
                    if(session.getAttribute("uname")!=null){//log out button for when in session

                %>
                <li><a href="Views">Book A Bike</a></li>
                <%
                    }

                %>
                <li><a href="AboutUs.jsp">About Us</a></li>
                <li class="active"><a href="ContactUs.jsp">Contact Us</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%
                    if(session.getAttribute("uname")==null){//log out button for when in session

                %>
                <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                <%
                    }

                %>

                <%
                    if(session.getAttribute("uname")!=null){//log out button for when in session

                %>

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span>Profile
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><strong>User: ${uname}</strong></li>
                        <li><a href="Profile.jsp">Profile</a></li>
                        <li><a href="Views">Book A Bike</a></li>
                        <%--<li><a href="book">Book a bike</a></li>--%>
                    </ul>
                </li>
                <li><a href="Log"><span class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
                <%
                    }

                %>
            </ul>
        </div>
    </div>
</nav>
<div class="content">
    <h1>Contact Us</h1>
    <h5>If you have any questions why not contact us on the following email: <br> contact@bikeworld.co.uk</h5>
    <h5>Want to hear the latest news and offers? Why not follow us on one of the platforms below? <br></h5>
    <br><br>
    <a href="https://www.facebook.com/" class="fa fa-facebook"></a>
    <a href="https://twitter.com/?lang=en-gb" class="fa fa-twitter"></a>
    <a href="https://www.instagram.com/?hl=en" class="fa fa-instagram"></a>
    <a href="https://www.snapchat.com/l/en-gb/" class="fa fa-snapchat-ghost"></a>
</div>

</body>
</html>
