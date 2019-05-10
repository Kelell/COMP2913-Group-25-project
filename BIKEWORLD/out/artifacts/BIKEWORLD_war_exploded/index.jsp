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

  <style>
    h5{
      -webkit-animation-name: fadeIn; /* Safari 4.0 - 8.0 */
      -webkit-animation-duration: 6s; /* Safari 4.0 - 8.0 */
      animation-name: fadeIn;
      animation-duration: 6s;
    }
    h6{
      -webkit-animation-name: fadeIn; /* Safari 4.0 - 8.0 */
      -webkit-animation-duration: 20s; /* Safari 4.0 - 8.0 */
      animation-name: fadeIn;
      animation-duration: 20s;
    }
    @-webkit-keyframes fadeIn {
      0% {opacity: 0;}
      100% {opacity: 1;}
    }

    @keyframes fadeIn {
      0% {opacity: 0;}
      100% {opacity: 1;}
    }

    .fadeIn {
      -webkit-animation-name: fadeIn;
      animation-name: fadeIn;
    }
  </style>
</head>
<%
  if(session.getAttribute("uname")==null){//displays sign up and log in options only when not in session/when not logged in

%>
<body style = "background-color: black;" >
<%
  }

%>
  <%
  if(session.getAttribute("uname")!=null){//displays sign up and log in options only when not in session/when not logged in

%>
<body  >
<%
  }

%>
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

        <%
          if(session.getAttribute("uname")!=null){//if in session show book a bike option

        %>
        <li><a href="history">History</a></li>
        <%
          }

        %>
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

<%
  if(session.getAttribute("uname")==null){//displays sign up and log in options only when not in session/when not logged in

%>

<div class="content">
  <h1 style = "color: white; text-align: center;">Welcome To B!KEWORLD</h1>


  <div class="row" >
    <div class="col" >
      <h5 style = " text-align: center; font-size: 25px; font-family: Courier New" >OUR PATH!!!</h5>
      <h5 style = " text-align: center; font-size: 25px; font-family: Courier New">"The area is mostly wooded but there are several places that offer spectacular views. Use some caution in the corners here. Out here you will find the most legal features including log rides, jumps and rock rolls in South Tahoe including the new jumps, berms, rollers and hips TAMBA and SBTS built in 2014. The trail tread is mostly packed dirt mixed with rocks. A full suspension bike with 3+ inches of travel will greatly enhance your experience!"</h5>

      <h5 style = " text-align: center; font-size: 25px; font-family: Courier New">"From there the trail stretches out and travels pretty fast into a big berm with a view out into the valley. Well built jump line with wooden lips built into each jump. Counterclockwise the descent is shorter but steeper in spots. Parking at the bottom is near mile 12 of Palmer Fishhook Road, while the top of the trailhead starts near mile 16 of Palmer Fishhook Road. Large group of trees down on northern section of trail, between buckhorn and club gap."</h5>
      <br><br>
      <h5 style = " text-align: center; font-size: 25px"; >"EXPERIENCE THE JOURNEY"</h5>
    </div >

  </div>

  <h6>
    To book a bike please first Log in or register. <br>
    TESTING ACCOUNT :<br>
    USERNAME : kelellt<br>
    PASSWORD : Password123<br>
  </h6>

</div><!--end of content -->

<%
  }

%>

<%
  if(session.getAttribute("uname")!=null){//displays drop down nav only when in session

%>

<div class="content" style="color: black; font-size: 22px ">
  <h1>Welcome ${uname}</h1>
  <br><br>


  <div class = 'row' >

    <div class="col" >
      <div id="myCarousel" class="carousel slide" data-ride="carousel" >
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
          <div class="item active">
            <img src="https://www.publicdomainpictures.net/pictures/220000/velka/mountain-biking-14915876029bb.jpg" alt="Mountain Biking" style="width:100%;">
          </div>

          <div class="item">
            <img src="https://www.publicdomainpictures.net/pictures/230000/velka/biking-15015084891IL.jpg" alt="Sunset biking" style="width:100%;">
          </div>

          <div class="item">
            <img src="https://www.publicdomainpictures.net/pictures/230000/velka/bicyclists.jpg" alt="New york" style="width:100%;">
          </div>
        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </div>

  </div>
  <br><br>

  <div class="row" style = "background-color: white">
    <h5 style = " text-align: center; font-size: 25px; font-family: Lucida Console" >How does it work</h5>
    <br><br><br><br>
    <img src="Drawing.png" alt="Explain" >








  </div>
  <div class="row" style="align-content: center;  text-align: center;">
    <br><br>
    <button><a href="Views">LETS GO!!</a></button>
    <br><br><br><br>
  </div>
  <br><br>
</div><!--end of content -->

<%
  }

%>

</body>
</html>
