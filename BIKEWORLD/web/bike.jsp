<%--
  Created by IntelliJ IDEA.
  User: ll16m25s
  Date: 27/02/19
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*"%>
<%@ page import="mypack.jdbc" %>
<%ResultSet ResultSet =null;%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<div class="nav"><!--Nav bar from w3schools: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_topnav (TEST ONLY)-->
    <a class="active" href="index.jsp">Home</a>
    <a href="book">Book A Bike</a>
    <a href="AboutUs.jsp">About Us</a>
    <a href="ContactUs.jsp">Contact Us</a>
    <a>Log out</a>
</div>

<%
    java.sql.Connection conn = null;
    String url = "jdbc:mysql://remotemysql.com:3306/EEsET82tG5";
    String Driver = "com.mysql.jdbc.Driver";
    String username = "EEsET82tG5";
    String password = "UhgQalxiVw";
    try{
        Class.forName(Driver);
        conn= DriverManager.getConnection(url, username,password);


        if(conn!=null){
            out.println("connected");
        }else {
            out.println("fail");
        }
    }catch (Exception e){
        e.printStackTrace();
    }
%>



</body>
</html>
