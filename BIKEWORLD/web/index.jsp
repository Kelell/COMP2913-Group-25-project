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
        <a class="navbar-brand" href="index.jsp">B!KEWORLD</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="AboutUs.jsp">About Us</a></li>
        <li><a href="ContactUs.jsp">Contact Us</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">

        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Profile
            <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="Views">View bikes</a></li>
            <li><a href="book">Book a bike</a></li>
            <li><a href="bookbikes">Book</a></li>           <!-- <<<<  NEEDS REMOVING, required by Max for testing-->
          </ul>
        </li>
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
        <li><a href="Log"><span class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
        <%
          }

        %>
      </ul>

    </div>
  </nav>





  </body>
</html>
