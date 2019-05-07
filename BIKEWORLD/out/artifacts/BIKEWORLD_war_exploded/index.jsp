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
  <title>Home</title>

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
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="AboutUs.jsp">About Us</a></li>
        <li><a href="ContactUs.jsp">Contact Us</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>

      </ul>
    </div>

  </div>

</nav>




</body>
</html>
