<%--
  Created by IntelliJ IDEA.
  User: sc16bmt
  Date: 06/05/19
  Time: 03:36
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="javax.servlet.http.HttpSession" %>

<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> <!-- Bootstrap style link  -->
    <link rel="stylesheet" href="style.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> <!-- Drop down button script-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%

    HttpSession session1 =  request.getSession(false);
    if(session1.getAttribute("uname")==null) {
        response.sendRedirect("index.jsp");
    }%>

<nav class="navbar navbar-inverse"><!-- Bootstrap nav bar -->
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="Dashboard.jsp">B!KEWORLD</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="Dashboard.jsp">Home</a></li>
            <li><a href="Views">View bikes</a></li>
            <li><a href="AboutUs.jsp">About Us</a></li>
            <li><a href="ContactUs.jsp">Contact Us</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span><%= session1.getAttribute("uname")%>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><strong> </strong></li>
                    <li><a href="Profile.jsp">Later</a></li>
                </ul>
            </li>
            <li><a href="Logout.jsp"><span class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
        </ul>
    </div>
</nav>

<p><%=session1.getAttribute("uname")%></p>


</body>
</html>
