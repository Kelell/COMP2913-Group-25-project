<%--
  Created by IntelliJ IDEA.
  User: sc16bmt
  Date: 06/05/19
  Time: 03:36
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="javax.servlet.http.HttpSession" %>
<%

    HttpSession ion =  request.getSession(false);
    if (ion == null) {
        response.sendRedirect("index.jsp");
    }%>
<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="style.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class = "Title">
    B!KEWORLD
</div>

<div class="nav"><!--Nav bar from w3schools: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_topnav (TEST ONLY)-->
    <a class="active" href="Dashboard.jsp">Home</a>
    <a href="Views">View bikes</a>
    <a href="AboutUs.jsp">About Us</a>
    <a href="ContactUs.jsp">Contact Us</a>
    <a href="Logout.jsp">Log out</a>
</div>

<% HttpSession current = request.getSession(false);%>
<%= out.println(current.getAttribute("uName"));%>




</body>
</html>
