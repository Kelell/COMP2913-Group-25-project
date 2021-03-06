<%--
  Created by IntelliJ IDEA.
  User: sc16bmt
  Date: 05/05/19
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "javax.servlet.ServletException" %>

<html>
<head>
        <% session =  request.getSession(false);
            //Checks to see if session is still active by seeing if session Attribute is false
            //Session obtained through getSession
            try {

                //If session attribute is false then the session is false. Therefore you are redirected to index.jsp page
                if (session.getAttribute("uname") == null) {
                    response.sendRedirect("index.jsp");
                }
            } catch (NullPointerException name) {
                response.sendRedirect("index.jsp");
            }
        if (request.getParameter("term") == null){
            response.sendRedirect("Views");
        }%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> <!-- Bootstrap style link  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> <!-- Drop down button script-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>  <!-- Drop down button script-->
    <link rel="stylesheet" href="style.css" type="text/css">
    <style>
        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        /* The Close Button */
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }
        * {
            box-sizing: border-box;
        }

        .row {
            display: -ms-flexbox; /* IE10 */
            display: flex;
            -ms-flex-wrap: wrap; /* IE10 */
            flex-wrap: wrap;
            margin: 0 -16px;
        }

        .col-25 {
            -ms-flex: 25%; /* IE10 */
            flex: 25%;
        }

        .col-50 {
            -ms-flex: 50%; /* IE10 */
            flex: 50%;
        }

        .col-75 {
            -ms-flex: 75%; /* IE10 */
            flex: 75%;
        }

        .col-25,
        .col-50,
        .col-75 {
            padding: 0 16px;
        }

        .container {
            background-color: #f2f2f2;
            padding: 5px 20px 15px 20px;
            border: 1px solid lightgrey;
            border-radius: 3px;
        }

        input[type=text] {
            width: 100%;
            margin-bottom: 20px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        label {
            margin-bottom: 10px;
            display: block;
        }

        .icon-container {
            margin-bottom: 20px;
            padding: 7px 0;
            font-size: 24px;
        }

        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 12px;
            margin: 10px 0;
            border: none;
            width: 100%;
            border-radius: 3px;
            cursor: pointer;
            font-size: 17px;
        }

        .btn:hover {
            background-color: #45a049;
        }

        a {
            color: #2196F3;
        }

        hr {
            border: 1px solid lightgrey;
        }

        span.price {
            float: right;
            color: grey;
        }

        /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
        @media (max-width: 800px) {
            .row {
                flex-direction: column-reverse;
            }
            .col-25 {
                margin-bottom: 20px;
            }
        }
    </style>
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


<div class="content"><!-- start of content/ enables page styling -->

<% double a = Double.parseDouble(request.getParameter("cost"))- (0.20 * Double.parseDouble(request.getParameter("cost"))); %>

    <!-- Printing reciepts for long term -->
<%String term = request.getParameter("term"); %>
<h2>RECEIPT</h2>
<%if ((term.equals("2"))){
    out.print( "<p> Name : " + session.getAttribute("uCusname") + "</p>");
    out.print( "<p>Location : " + request.getParameter("location")+ "</p>");
    out.print( "<p>Days : " + request.getParameter("days")+ "</p>");
    out.print( "<p>Bike ID : " + request.getParameter("bikeids")+ "</p>");
    out.print( "<p>Total cost : £" + request.getParameter("cost")+ "</p>");
    out.print( "<p>Start day : " + request.getParameter("startd")+ "</p>");
    out.print( "<p>Return date : " + request.getParameter("endd")+ "</p>");
}%>
    <!-- Printing reciepts for short term -->
<%if (term.equals("1")){
    out.print( "<p> Name : " + session.getAttribute("uCusname") + "</p>");
    out.print( "<p>Location : " + request.getParameter("location")+ "</p>");
    out.print( "<p>Hours : " + request.getParameter("hours")+ "</p>");
    out.print( "<p>Bike ID : " + request.getParameter("bikeids")+ "</p>");
    out.print( "<p>Total cost : £" + request.getParameter("cost")+ "</p>");
    out.print( "<p>Start time : " + request.getParameter("startt")+ "</p>");
    out.print( "<p>Return time : " + request.getParameter("endt")+ "</p>");
    out.print( "<p>Date : " + request.getParameter("theday")+ "</p>");

}%>

<button id="myBtn" type="submit" >Pay</button>

<div id="myModal" class="modal">

    <!-- Modal page pop up for payment -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <div class="row">
            <div class="col-75">
                <div class="container">
                    <form action="complete" method = 'post'>

                        <div class="row">
                            <div class="col-50">
                                <h3>Billing Address</h3>
                                <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                                <input type="text" id="fname" name="firstname" pattern="[A-Za-z]{0-60}" value = <%=session.getAttribute("uCusname")%> >
                                <label for="email"><i class="fa fa-envelope"></i> Email</label>
                                <input type="text" id="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" value = <%=session.getAttribute("uemail")%>>
                                <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                                <input type="text" id="adr" name="address" value = <%=session.getAttribute("uAddress")%>>
                                <label for="city"><i class="fa fa-institution"></i> City</label>
                                <input type="text" id="city" name="city" value = <%=request.getParameter("location")%>>

                            </div>

                            <div class="col-50">
                                <h3>Payment</h3>
                                <label for="fname">Accepted Cards</label>
                                <div class="icon-container">
                                    <i class="fa fa-cc-visa" style="color:navy;"></i>
                                    <i class="fa fa-cc-amex" style="color:blue;"></i>
                                    <i class="fa fa-cc-mastercard" style="color:red;"></i>
                                    <i class="fa fa-cc-discover" style="color:orange;"></i>
                                </div>
                                <label for="cname">Name on Card</label>
                                <input type="text" id="cname" name="cardname" value = <%=session.getAttribute("uCusname")%>>
                                <label for="ccnum">Credit card number</label>
                                <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
                                <label for="expmonth">Exp Month</label>
                                <input type="text" id="expmonth" name="expmonth" placeholder="September">
                                <div class="row">
                                    <div class="col-50">
                                        <label for="expyear">Exp Year</label>
                                        <input type="text" id="expyear" name="expyear" placeholder="2018">
                                    </div>
                                    <div class="col-50">
                                        <label for="cvv">CVV</label>
                                        <input type="text" id="cvv" name="cvv" placeholder="352">
                                    </div>
                                </div>
                            </div>

                        </div>
                        <label>
                            <input type='text' style = 'display: none;' name='term' value = <%=request.getParameter("term")%>>
                            <br><br>
                            <input type='text' style = 'display: none;' name='bikeids' value = <%=request.getParameter("bikeids")%>>
                            <input type='text' style = 'display: none;' name='location' value = <%=request.getParameter("location")%>>
                            <input type='text' style = 'display: none;' name='days' value = <%=request.getParameter("days")%>>
                            <input type='text' style = 'display: none;' name='hours' value = <%=request.getParameter("hours")%>>
                            <%if (term.equals("2")){
                                out.print( "<input type='text' style = 'display: none;' name='cost' value = " + Double.toString(a) + ">");
                            }
                            else{
                                out.print( "<input type='text' style = 'display: none;' name='cost' value = " + Double.parseDouble(request.getParameter("cost")) + ">");
                            }%>
                            <input type='text' style = 'display: none;' name='startd' value = <%=request.getParameter("startd")%>>
                            <input type='text' style = 'display: none;' name='endd' value = <%=request.getParameter("endd")%>>
                            <input type='text' style = 'display: none;' name='startt' value = <%=request.getParameter("startt")%>>
                            <input type='text' style = 'display: none;' name='endt' value = <%=request.getParameter("endt")%>>
                            <input type='text' style = 'display: none;' name='theday' value = <%=request.getParameter("theday")%>>
                            <input type="checkbox" checked="checked" name="s1"> Shipping address same as billing
                        </label>
                        <input type="submit" value="Continue to checkout" class="btn">
                    </form>
                </div>
            </div>
            <div class="col-25">
                <div class="container">
                    <h2>RECEIPT</h2>
                    <%if (term.equals("2")){
                        out.print( "<p>Location : " + request.getParameter("location")+ "</p>");
                        out.print( "<p>Days : " + request.getParameter("days")+ "</p>");
                        out.print(" -20% (Discount)");
                        out.print( "<p>Total cost : £" + (Double.parseDouble(request.getParameter("cost"))- (0.20 * Double.parseDouble(request.getParameter("cost"))))+ "</p>");
                    }%>
                    <%if (term.equals("1")){
                        out.print( "<p>Location : " + request.getParameter("location")+ "</p>");
                        out.print( "<p>Hours : " + request.getParameter("hours")+ "</p>");
                        out.print( "<p>Total cost : £" + request.getParameter("cost")+ "</p>");

                    }%>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    // Get the modal
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>


</div><!--end of content -->
</body>
</html>
