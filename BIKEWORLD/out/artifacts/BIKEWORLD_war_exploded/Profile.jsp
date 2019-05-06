<%--
  Created by IntelliJ IDEA.
  User: ll16m25s
  Date: 05/05/19
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
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
                        <li class=active><a href="Profile.jsp">Profile</a></li>
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



<%//tests if user is logged in before accessing about us page (testing only)
    if(session.getAttribute("uname")==null){
        response.sendRedirect("LogIn.jsp");
    }
%>
<div class="content">
    <h1>Profile</h1>

    <%--<p>${param.message}</p>--%>
    <div class="row">
        <div class="col-sm-3" >
            <div class="img" >
                <img src = "https://www.sgm-inc.com/wp-content/uploads/2014/06/no-profile-male-img.gif" alt = "bike" width = "220px" height = "290px">
            </div>

            <ul class="nav flex-column">
                <li><a href="Views">View bikes</a></li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="AboutUs.jsp">About Us</a></li>
                <li><a href="ContactUs.jsp">Contact Us</a></li>
            </ul>
        </div>
        <div class="col">
            <h4>Username : ${uname}</h4>
        </div>
    </div>


</div>


</body>
</html>
