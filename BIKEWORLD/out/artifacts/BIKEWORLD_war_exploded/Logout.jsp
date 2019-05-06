<%--
  Created by IntelliJ IDEA.
  User: sc16bmt
  Date: 06/05/19
  Time: 09:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page import ="javax.servlet.http.HttpSession" %>

<head>
    <title>Title</title>
</head>
<body>

<%HttpSession oldSession = request.getSession(false);
    if (oldSession.getAttribute("uName") != null) {
        oldSession.invalidate();
        response.sendRedirect("index.jsp");
    }
    else{
    response.sendRedirect("index.jsp");
    }%>

</body>
</html>
