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
  <link rel="stylesheet" href="style.css" type="text/css"> <!-- link to style sheet -->
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
        <li class="active"><a href="index.jsp">Home</a></li>
        <%
          if(session.getAttribute("uname")!=null){//if in session show book a bike option

        %>
        <li><a href="Views">Book A Bike</a></li>
        <%
          }

        %>
        <li><a href="AboutUs.jsp">About Us</a></li>
        <li><a href="ContactUs.jsp">Contact Us</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">

        <%
          if(session.getAttribute("uname")==null){//displays sign up and log in options only when not in session/when not logged in

        %>
        <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <%
          }

        %>

        <%
          if(session.getAttribute("uname")!=null){//displays drop down nav only when in session

        %>

        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span>Profile
            <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><strong>User: ${uname}</strong></li>
            <li><a href="Profile.jsp">Profile</a></li>
            <li><a href="Views">Book A Bike</a></li>
          </ul>
        </li>
        <li><a href="Log"><span class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
        <%
          }

        %>
      </ul>
    </div>
  </div>
</nav><!-- end of nav -->

<div class="content">
  <h1>Welcome To B!KEWORLD</h1>
  To book a bike please first Log in or register. <br>
  TESTING ACCOUNT :<br>
  USERNAME : kelellt<br>
  PASSWORD : Password123<br>
  <div class="row">
    <div class="col-sm-5" >
      <h5>The standard Lorem Ipsum passage, used since the 1500s</h5>
      <h5>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
        dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
        ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
        fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum."</h5>

      <h5>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
        dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
        ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
        fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum."</h5>

      <h5>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
        dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
        ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
        fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum."</h5>
      <h5>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
        dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
        ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
        fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum."</h5>
    </div>
    <div class="col-5">
      <h5>Section 1.10.33 of "de Finibus Bonorum et Malorum", written by Cicero in 45 BC</h5>
      <h5>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque
        corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa
        qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita
        distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime
        placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut
        officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.
        Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis
        doloribus asperiores repellat."</h5>


    </div>
  </div>
</div><!--end of content -->


</body>
</html>
