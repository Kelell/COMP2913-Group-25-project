<%--
  Created by IntelliJ IDEA.
  User: sc16bmt
  Date: 18/03/19
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> <!-- Bootstrap style link  -->
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">B!KEWORLD</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="index.jsp">Home</a></li>
            <li><a href="Views">View bikes</a></li>
            <li><a href="book">Book a bike</a></li>
            <li><a href="bookbikes">Book</a></li>
            <li><a href="AboutUs.jsp">About Us</a></li>
            <li><a href="ContactUs.jsp">Contact Us</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>

<form action="Available" method="post">
    Enter Date:
    <input type="date" name="date" required="required">
    Enter Time :
    <select name = "time" required = "required" >
        <option value= Please select>Please select</option>
        <option value= "8-9" >8 am to 9 am</option>
        <option value= "9-10" >9 am to 10 am</option>
        <option value= "10-11" >10 am to 11 am</option>
        <option value= "11-12" >11 am to 12 pm</option>
        <option value= "12-13" >12 pm to 1 pm</option>
        <option value= "13-14" >1 pm to 2 pm</option>
        <option value= "14-15" >2 pm to 3 pm</option>
        <option value= "15-16" >3 pm to 4 pm</option>
        <option value= "16-17" >4 pm to 5 pm</option>
        <option value= "17-18" >5 pm to 6 pm</option>
        <option value= "18-19" >6 pm to 7 pm</option>
        <option value= "19-20" >7 pm to 8 pm</option>
    </select>
    <input type="submit" value="Register">
</form>
</body>
</html>
