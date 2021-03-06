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
    <title>SingUp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> <!-- Bootstrap style link  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> <!-- Drop down button script-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>  <!-- Drop down button script-->
    <link rel="stylesheet" href="style.css" type="text/css">
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
                <li><a href="index.jsp">Home</a></li>
                <li><a href="AboutUs.jsp">About Us</a></li>
                <li><a href="ContactUs.jsp">Contact Us</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="LogIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </div>
</nav><!-- end of nav -->



<div class="content"><!-- start of content/ enables page styling -->
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
        </div>
        <div class="form-group" id="emailg" style = 'Display : none;'>
            <label for="email">Email :</label>
            <input name="email" type="text" class="form-control" id="email" oninput="myfunction3()" placeholder="Email i.e someone@yahoo.com" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
            <p id = 'emailError' style = 'Colour : red; Display : none;' > Invalid Email. Remember to add @</p>
        </div>
        <div class="form-group" id="usernameg" style = 'Display : none;'>
            <label for="username">Username :</label>
            <input name="username" type="text" class="form-control" id="username" oninput="myfunction4()" placeholder="Username" required="required">
            <p id = 'usernameError' style = 'Colour : red; Display : none;' > Error</p>
        </div>
        <div class="form-group" id="passwordg" style = 'Display : none;'>
            <label for="password">Password:</label>
            <input name="password" type="password" class="form-control" id="password"  required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" >
            <div id="message">
                <h4>Password must contain the following:</h4>
                <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
                <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
                <p id="number" class="invalid">A <b>number</b></p>
                <p id="length" class="invalid">Minimum <b>8 characters</b></p>
            </div>
        </div>
        <button id="sub" style = 'Display : none;' type="submit" class="btn btn-default">Register</button>
    </form>
    <p>${param.message}</p><!-- print error message when required-->
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
            var sub = document.getElementById("sub");
            var letters = /^[A-Za-z]+$/;
            if (fname.value.match(letters) )
            {
                if ( lname.style.display == 'none'){
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
                    fnamerror.style.display = 'none';
                    sub.style.display = 'none';
                }

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
            var sub = document.getElementById("sub");
            var letters = /^[A-Za-z]+$/;
            if (lname.value.match(letters))
            {
                if (addy.style.display == 'none'){

                    addy.style.display = 'block';
                    addy.children[1].value = "";
                    email.style.display = 'none';
                    email.children[1].value = "";
                    username.style.display = 'none';
                    username.children[1].value = "";
                    psw.style.display = 'none';
                    psw.children[1].value = "";
                    lnamerror.style.display = 'none';
                    sub.style.display = 'none';

                }

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
                lnamerror.style.display = 'block';
                sub.style.display = 'none';
            }
        }


        function myfunction2(){
            var email = document.getElementById("emailg");
            var username = document.getElementById("usernameg");
            var psw = document.getElementById("passwordg");
            var sub = document.getElementById("sub");
            if (email.style.display == 'none'){
                email.style.display = 'block';
                email.children[1].value = "";
                username.style.display = 'none';
                username.children[1].value = "";
                psw.style.display = 'none';
                psw.children[1].value = "";
                sub.style.display = 'none';
            }

        }


        function myfunction3(){
            var email = document.getElementById("email");
            var emailerror = document.getElementById("emailError");
            var username = document.getElementById("usernameg");
            var usernamerror = document.getElementById("usernameError");
            var psw = document.getElementById("passwordg");
            var pswerror = document.getElementById("passwordError");
            var sub = document.getElementById("sub");
            var letters = /^[[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
            if (email.value.match(letters)){
                if(username.style.display == 'none'){
                    username.style.display = 'block';
                    username.children[1].value = "";
                    psw.style.display = 'none';
                    psw.children[1].value = "";
                    emailerror.style.display = 'none';
                    sub.style.display = 'none';
                }
            }
            else{
                username.style.display = 'none';
                username.children[1].value = "";
                psw.style.display = 'none';
                psw.children[1].value = "";
                emailerror.style.display = 'block';
                sub.style.display = 'none';
            }

        }

        function myfunction4() {
            var username = document.getElementById("username");
            var usernamerror = document.getElementById("usernameError");
            var psw = document.getElementById("passwordg");
            var pswerror = document.getElementById("passwordError");
            var sub = document.getElementById("sub");
            var letters = /^[[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
            if (psw.style.display == 'none') {
                psw.style.display = 'block';
                psw.children[1].value = "";
                usernamerror.style.display = 'none';
                sub.style.display = 'none';
            }
        }
                var psw = document.getElementById("password");
                var letter = document.getElementById("letter");
                var capital = document.getElementById("capital");
                var number = document.getElementById("number");
                var length = document.getElementById("length");
        var pswerror = document.getElementById("passwordError");
                psw.onfocus = function() {
                    document.getElementById("message").style.display = "block";
                }

                psw.onblur = function() {
                    document.getElementById("message").style.display = "none";
                }

// When the user starts to type something inside the password field
                psw.onkeyup = function() {
                    // Validate lowercase letters
                    var lowerCaseLetters = /[a-z]/g;
                    if(psw.value.match(lowerCaseLetters)) {
                        letter.classList.remove("invalid");
                        letter.classList.add("valid");
                    } else {
                        letter.classList.remove("valid");
                        letter.classList.add("invalid");
                    }

                    // Validate capital letters
                    var upperCaseLetters = /[A-Z]/g;
                    if(psw.value.match(upperCaseLetters)) {
                        capital.classList.remove("invalid");
                        capital.classList.add("valid");
                    } else {
                        capital.classList.remove("valid");
                        capital.classList.add("invalid");
                    }

                    // Validate numbers
                    var numbers = /[0-9]/g;
                    if(psw.value.match(numbers)) {
                        number.classList.remove("invalid");
                        number.classList.add("valid");
                    } else {
                        number.classList.remove("valid");
                        number.classList.add("invalid");
                    }

                    // Validate length
                    if(psw.value.length >= 8) {
                        length.classList.remove("invalid");
                        length.classList.add("valid");
                    } else {
                        length.classList.remove("valid");
                        length.classList.add("invalid");
                    }

                    if(letter.classList == 'valid' && number.classList == 'valid' && length.classList == 'valid' && capital.classList == 'valid')
                    {
                        sub.style.display = 'block';
                    }
                    else{
                        sub.style.display = 'none';
                    }

                }



    </script>

</div><!--end of content -->
</body>

</html>
