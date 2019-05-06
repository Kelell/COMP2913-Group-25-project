<%--
  Created by IntelliJ IDEA.
  User: ll16m25s
  Date: 17/03/19
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <title>Index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> <!-- Bootstrap style link  -->
    <link rel="stylesheet" href="style.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<nav class="navbar navbar-inverse"><!-- Bootstrap nav bar -->
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">B!KEWORLD</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>

<div class="content">
    <h1>Sign Up</h1>

    <form action="Register" method="post">
        <div class="form-group">
            <label for="fname">First Name : </label>
            <input name="fname" type="text" class="form-control" id="fname" oninput="myfunction()" required="required" placeholder="First name" pattern="[A-Za-z]{0-60}">
            <p id = 'fnameError' style = 'Colour : red; Display : none;' > Not a valid name</p>
        </div>
        <div class="form-group" id="lnameg" style = 'Display : none;'>
            <label for="lname">Second Name :</label>
            <input name="lname" type="text" class="form-control" id="lname" oninput="myfunction1()" required="required" placeholder="Last name" pattern="[A-Za-z]{0-60}">
            <p id = 'lnameError' style = 'Colour : red; Display : none;' > Not a valid name</p>
        </div>
        <div class="form-group" id="addressg" style = 'Display : none;'>
            <label for="address">Address :</label>
            <input name="address" type="text" class="form-control" id="address" oninput="myfunction2()" placeholder="Address" required="required" >
            <p id = 'addressError' style = 'Colour : red; Display : none;' > Error</p>
        </div>
        <div class="form-group" id="emailg" style = 'Display : none;'>
            <label for="email">Email :</label>
            <input name="email" type="text" class="form-control" id="email" placeholder="Email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
            <p id = 'emailError' style = 'Colour : red; Display : none;' > Error</p>
        </div>
        <div class="form-group" id="usernameg" style = 'Display : none;'>
            <label for="username">Username :</label>
            <input name="username" type="text" class="form-control" id="username" placeholder="Username" required="required">
            <p id = 'usernameError' style = 'Colour : red; Display : none;' > Error</p>
        </div>
        <div class="form-group" id="passwordg" style = 'Display : none;'>
            <label for="password">Password:</label>
            <input name="password" type="password" class="form-control" id="password" required="required">
            <p id = 'passwordError' style = 'Colour : red; Display : none;' > Error</p>
        </div>
        <div class="form-group" id="conpasswordg" style = 'Display : none;'>
            <label for="conpassword">Confrim Password:</label>
            <input name="password" type="password" class="form-control" id="conpassword" required="required">
            <p id = 'confirmError' style = 'Colour : red; Display : none;' > Error</p>
        </div>
        <button id="sub" style = 'Display : none;' type="submit" class="btn btn-default">Register</button>
    </form>
    <script>
        function myfunction(){
            var fname = document.getElementById("fname");
            var fnamerror = document.getElementById("fnameError");
            var lname = document.getElementById("lnameg");
            var lnamerror = document.getElementById("lnameError");
            var addy = document.getElementById("addressg");
            var email = document.getElementById("emailg");
            var emailerror = document.getElementById("emailError");
            var username = document.getElementById("usernameg");
            var usernamerror = document.getElementById("usernameError");
            var psw = document.getElementById("passwordg");
            var pswerror = document.getElementById("passwordError");
            var cnpsw = document.getElementById("conpasswordg");
            var cnpswerror = document.getElementById("confirmError");
            var sub = document.getElementById("sub");
            var letters = /^[A-Za-z]+$/;
            if (fname.value.match(letters))
            {
                lname.style.display = 'block';
                lname.children[1].value = "";
                lnamerror.style.display = 'none';
                addy.style.display = 'none';
                addy.children[1].value = "";
                email.style.display = 'none';
                email.children[1].value = "";
                emailerror.style.display = 'none';
                username.style.display = 'none';
                username.children[1].value = "";
                psw.style.display = 'none';
                psw.children[1].value = "";
                pswerror.style.display = 'none';
                cnpsw.style.display = 'none';
                cnpsw.children[1].value = "";
                cnpswerror.style.display = 'none';
                fnamerror.style.display = 'none';
                sub.style.display = 'none';
            }
            else{
                lname.style.display = 'none';
                lname.children[1].value = "";
                lnamerror.style.display = 'none';
                addy.style.display = 'none';
                addy.children[1].value = "";
                email.style.display = 'none';
                email.children[1].value = "";
                username.style.display = 'none';
                username.children[1].value = "";
                psw.style.display = 'none';
                psw.children[1].value = "";
                cnpsw.style.display = 'none';
                cnpsw.children[1].value = "";
                fnamerror.style.display = 'block';
                sub.style.display = 'none';
            }
        }

        function myfunction1(){
            var lname = document.getElementById("lname");
            var lnamerror = document.getElementById("lnameError");
            var addy = document.getElementById("addressg");
            var email = document.getElementById("emailg");
            var emailerror = document.getElementById("emailError");
            var username = document.getElementById("usernameg");
            var usernamerror = document.getElementById("usernameError");
            var psw = document.getElementById("passwordg");
            var pswerror = document.getElementById("passwordError");
            var cnpsw = document.getElementById("conpasswordg");
            var cnpswerror = document.getElementById("confirmError");
            var sub = document.getElementById("sub");
            var letters = /^[A-Za-z]+$/;
            if (lname.value.match(letters))
            {
                addy.style.display = 'block';
                addy.children[1].value = "";
                email.style.display = 'none';
                email.children[1].value = "";
                username.style.display = 'none';
                username.children[1].value = "";
                psw.style.display = 'none';
                psw.children[1].value = "";
                cnpsw.style.display = 'none';
                cnpsw.children[1].value = "";
                lnamerror.style.display = 'none';
                sub.style.display = 'none';
            }
            else{
                addy.style.display = 'none';
                addy.children[1].value = "";
                email.style.display = 'none';
                email.children[1].value = "";
                username.style.display = 'none';
                username.children[1].value = "";
                psw.style.display = 'none';
                psw.children[1].value = "";
                cnpsw.style.display = 'none';
                cnpsw.children[1].value = "";
                lnamerror.style.display = 'block';
                sub.style.display = 'none';
            }
        }

        function myfunction2(){
            var email = document.getElementById("emailg");
            var username = document.getElementById("usernameg");
            var psw = document.getElementById("passwordg");
            var cnpsw = document.getElementById("conpasswordg");
            var sub = document.getElementById("sub");
            email.style.display = 'block';
            email.children[1].value = "";
            username.style.display = 'none';
            username.children[1].value = "";
            psw.style.display = 'none';
            psw.children[1].value = "";
            cnpsw.style.display = 'none';
            cnpsw.children[1].value = "";
            sub.style.display = 'none';
        }
        function myfunction3(){
            var addy = document.getElementById("address");
            var email = document.getElementById("emailg");
            var username = document.getElementById("usernameg");
            var psw = document.getElementById("passwordg");
            var cnpsw = document.getElementById("conpasswordg");
            var sub = document.getElementById("sub");
            email.style.display = 'block';
            email.children[1].value = "";
            username.style.display = 'none';
            username.children[1].value = "";
            psw.style.display = 'none';
            psw.children[1].value = "";
            cnpsw.style.display = 'none';
            cnpsw.children[1].value = "";
            fnamerror.style.display = 'none';
            sub.style.display = 'none';
        }
    </script>

</div>
</body>

</html>
