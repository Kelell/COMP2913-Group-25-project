<%--
  Created by IntelliJ IDEA.
  User: ll16c2pc
  Date: 25/03/19
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<div class="nav"><!--Nav bar from w3schools: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_topnav (TEST ONLY)-->
    <a href="index.jsp">Home</a>
    <a href="book">Book A Bike</a>
    <a href="AboutUs.jsp">About Us</a>
    <a href="ContactUs.jsp">Contact Us</a>
    <a href="LogIn.jsp">LogIn</a>
    <a href="registration.jsp">Register</a>
    <a href="AvailableBikes.jsp">See bikes</a>
    <a class="active" href="Payment.jsp">Payment</a> <!--Temp button so payment page can be accessed for testing before the book a bike is complete-->
    <a>Log out</a>
</div>


<form action="Payment" method="post">
    <p>Card Number: <input type="number" name="CardNo" required="required"></p>
    <p>Csc: <input type="number" name="CSC" required="required"></p>
    <p>Expiry Date: <input type="month" name="ExpDate" required="required"  ></p>
    <input type="submit" value="Pay Now">
</form>


</body>
</html>
