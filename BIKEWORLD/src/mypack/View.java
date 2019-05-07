package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@WebServlet(name = "View")
public class View extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Checks to see if session is still active by seeing if session Attribute is false
        //Session obtained through getSession
        HttpSession session =  request.getSession(false);
        //If session attribute is false then the session is false. Therefore you are redirected to index.jsp page
        if (session.getAttribute("uname") == null) {
            response.sendRedirect("index.jsp");
        }

        //Print writter variable out is set and used to write to response.
        PrintWriter out = response.getWriter();
        //Response is set to a HTML page
        response.setContentType("text/html");
        //JDBC object created from class
        jdbc test = new jdbc();
        //String driver created for mysql database
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            //Class created for driver
            Class.forName(driver);
            //Connection to mysql database created with password and username created in order toaccess it
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
            Statement stmt = conn.createStatement();
            String sql;
            //sql statement selects all BIKE_ID , LOCATION, price from database
            sql = "SELECT BIKE_ID, LOCATION, price FROM bike";
            ResultSet rs = stmt.executeQuery(sql);
            //Array lists created to hold values
            ArrayList<Integer> bike_ids = new ArrayList<Integer>();
            ArrayList<String> loca = new ArrayList<String>();
            ArrayList<Float> cost = new ArrayList<Float>();


            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("BIKE_ID");
                String location = rs.getString("LOCATION");
                float price = rs.getFloat("price");
                bike_ids.add(id);
                loca.add(location);
                cost.add(price);
            }
            //close database attributes
            rs.close();
            stmt.close();
            conn.close();

            //Creates list of six
            int listsize = bike_ids.size();
            String size = Integer.toString(listsize);
            //Head
            out.println("<head onload=\"openFunction()\" >");
            //Out.println prints html code
            out.println(
                    "<title id = prick >Book A Bike</title>" +
                            "<meta name=viewport content=width=device-width, initial-scale=1>"+
                            "<link rel=stylesheet href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>"+ //<!-- Bootstrap style link  -->
                            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script>"+ //<!-- Drop down button script-->
                            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>"+  //<!-- Drop down button script-->
                            "<link rel=stylesheet href=style.css type=text/css>"+
                    "<style>\n" +//Styl
                    "\n" +
                    "/* The grid: Four equal columns that floats next to each other */\n" +
                    ".column {\n" +
                    "  float: left;\n" +
                    "  width: 25%;\n" +
                    "  padding: 10px;\n" +
                    "}\n" +
                    "\n" +
                    "/* Style the images inside the grid */\n" +
                    ".column img {\n" +
                    "  opacity: 0.8; \n" +
                    "  cursor: pointer; \n" +
                    "}\n" +
                    "\n" +
                    ".column img:hover {\n" +
                    "  opacity: 1;\n" +
                    "}\n" +
                    "\n" +
                    "/* Clear floats after the columns */\n" +
                    ".row:after {\n" +
                    "  content: \"\";\n" +
                    "  display: table;\n" +
                    "  clear: both;\n" +
                    "}\n" +
                    "\n" +
                    "#imgtext {\n" +
                    "  position: absolute;\n" +
                    "  top: 50%;\n" +
                    "  left: 50%;\n" +
                    "  transform: translate(-50%, -50%);\n" +
                    "  -ms-transform: translate(-50%, -50%);\n" +
                    "  background-color: #555;\n" +
                    "  color: white;\n" +
                    "  font-size: 16px;\n" +
                    "  padding: 12px 24px;\n" +
                    "  border: none;\n" +
                    "  cursor: pointer;\n" +
                    "  border-radius: 5px;\n" +
                    "  text-align: center;" +
                    "  font-size: 20px;\n" +
                    "}\n" +
                    "\n" +
                    ".container {\n" +
                    "  position: relative;\n" +
                    "  width: 100%;\n" +
                    "  max-width: 400px;\n" +
                    "}"+
                    "/* Closable button inside the expanded image */\n" +
                    ".closebtn {\n" +
                    "  position: absolute;\n" +
                    "  top: 10px;\n" +
                    "  right: 15px;\n" +
                    "  color: white;\n" +
                    "  font-size: 35px;\n" +
                    "  cursor: pointer;\n" +
                    "}\n" +
                    "img {\n" +
                    "  border-radius: 10%;\n" +
                    "}"+
                    "</style>"+
                    "</head>"
            );
            out.println("<body  >");

            out.println(
                    "<nav class='navbar navbar-inverse'>"+ //Navbar
                            "<div class='container-fluid'>"+ "<div class='navbar-header'>"+

                            "<button type= 'button' class= 'navbar-toggle' data-toggle='collapse' data-target='#Navigation'>"+ //<!-- enables a responsive nav bar for mobiles -->
                            "<span class='icon-bar'></span>"+
                            "<span class='icon-bar'></span>" +
                            "<span class='icon-bar'></span>" +
                            "</button>" +

                            "<a class='navbar-brand' href=index.jsp>B!KEWORLD</a>"+
                            "</div>"+

                            "<div class = 'collapse navbar-collapse' id='Navigation'>"+

                            "<ul class='nav navbar-nav'>"+
                            "<li><a href=index.jsp>Home</a></li>");

            if(session.getAttribute("uname")!=null){//If session is not null Then Book a bike tab is active

                out.println("<li  class=\"active\" ><a href=\"Views\">Book A Bike</a></li>");


            }

            out.println(
                    "<li><a href=AboutUs.jsp>About Us</a></li>"+
                            "<li><a href=ContactUs.jsp>Contact Us</a></li>"+
                            "</ul>"+
                            "<ul class='nav navbar-nav navbar-right'>");

            if(session.getAttribute("uname")==null){//If session is null then Sign up and Login are added to nav bar


                out.println(
                        "<li><a href=registration.jsp><span class='glyphicon glyphicon-user'></span> Sign Up</a></li>"+
                                "<li><a href=LogIn.jsp><span class='glyphicon glyphicon-log-in'></span> Login</a></li>"
                );
            }




            if(session.getAttribute("uname")!=null){///If session is not null Then profile tab is added to Nav bar

                out.println(
                        "<li class='dropdown'>"+
                                "<a class='dropdown-toggle' data-toggle='dropdown' href=#><span class='glyphicon glyphicon-user'></span>Profile <span class='caret'></span></a>"+
                                "<ul class='dropdown-menu'>"+
                                "<li><strong> User: " + session.getAttribute("uname") + "</strong></li>"+
                                "<li><a href=Profile.jsp>Profile</a></li>"+
                                "<li><a href=Views>Book A Bike</a></li>"+
                                "</ul>"+
                                "</li>"+
                                "<li><a href=Log><span class='glyphicon glyphicon-log-in'></span> LOGOUT</a></li>");

            }


            out.println(
                    "</ul>"+
                            "</div>"+
                            "</div>"+
                            "</nav>"


            );

            //Displays Different locations
            out.println(
                    "<form id = 'form2' action= 'Available' method = 'post' >" +
                            //Hidden location variable to store location choice
                            "<p style = \"display: none\" >Select a Location</p>" + "\n" +
                            "<select  id = \"Loca\" style = \"display: none\" name = Location >" +
                            "<option selected value= 'Please select'>Please select</option>" +
                            "<option value=Alnmouth>Alnmouth</option>" +
                            "<option value=Barnsley>Barnsley</option>"+
                            "<option value=Beverly>Beverly</option>"+
                            "<option value=Bournemouth>Bournemouth</option>"+
                            "<option value=Bradford>Bradford</option>"+
                            "<option value=Bristol>Bristol</option>"+
                            "<option value=Buckingham>Buckingham</option>"+
                            "<option value=Crystal>Crystal</option>"+
                            "<option value=Halifax>Halifax</option>"+
                            "<option value=Harrogate>Harrogate</option>"+
                            "<option value=Hebden>Hebden</option>"+
                            "<option value=Hexham>Hexham</option>"+
                            "<option value=Leeds>Leeds</option>"+
                            "<option value=Manchester>Manchester</option>"+
                            "<option value=Rotherham>Rotherham</option>"+
                            "<option value=Shipley>Shipley</option>"+
                            "</select>"+ "\n" +

                            //Display of locations and images.(All images cleared and labeled for reuse)
                            "<h1 name = Location >Select a Location</h1>" + "\n" +
                            "<div class=\"row\"  style = \"\">\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://upload.wikimedia.org/wikipedia/commons/0/0d/Alnmouth%2C_Northumberland_Street_-_geograph.org.uk_-_1716284.jpg\" alt=\"Alnmouth\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Alnmouth</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://s1.geograph.org.uk/geophotos/04/07/74/4077465_8a0260e7_1024x1024.jpg\" alt=\"Barnsley Interchange\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Barnsley</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://upload.wikimedia.org/wikipedia/commons/b/be/Beverley_Minster_-_geograph.org.uk_-_1733055.jpg\" alt=\"Beverly\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Beverly</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Bournemouth_pier.jpg/1280px-Bournemouth_pier.jpg\" alt=\"Bournemouth\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Bournemouth</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Bradford_City_Hall_020.jpg/800px-Bradford_City_Hall_020.jpg\" alt=\"Bradford Interchange\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Bradford</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://cdn.pixabay.com/photo/2015/09/19/15/46/bristol-947391_960_720.jpg\" alt=\"Bristol\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Bristol</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img  src= \"https://s0.geograph.org.uk/geophotos/04/47/26/4472608_b7b60ad1_1024x1024.jpg\" alt=\"Buckingham\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Buckingham</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://cdn.pixabay.com/photo/2016/07/02/14/45/madrid-1493002_960_720.jpg\" alt=\"Crystal\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Crystal</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://cdn.pixabay.com/photo/2017/01/11/17/04/halifax-1972314_960_720.jpg\" alt=\"Halifax\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Halifax</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"http://res.freestockphotos.biz/pictures/5/5699-traffic-lights-at-night-in-a-city-pv.jpg\" alt=\"Harrogate\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Harrogate</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://s0.geograph.org.uk/geophotos/02/58/33/2583373_daf45c15.jpg\" alt=\"Hebden\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Hebden</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://s0.geograph.org.uk/geophotos/05/61/61/5616173_ea0be852_original.jpg\" alt=\"Hexam\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Hexham</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://s2.geograph.org.uk/geophotos/04/97/93/4979322_06774607_1024x1024.jpg\" alt=\"Leeds\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Leeds</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\"onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Urbis%2C_Manchester_city_centre_in_2012.jpg/800px-Urbis%2C_Manchester_city_centre_in_2012.jpg\" alt=\"Manchester\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Manchester</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://upload.wikimedia.org/wikipedia/commons/5/51/ECT_waalhaven_bij_nacht.jpg\" alt=\"Rotherham\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Rotherham</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://s0.geograph.org.uk/geophotos/05/48/81/5488166_2b917b16_original.jpg\" alt=\"Shipley\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Shipley</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "</div>" +
                            "</form>" + "\n" +

                            //Script for choosing location
                            "<script>"  + "\n" +
                            //Open function refreshes page on every load
                            "function openFunction() {"   + "\n" +
                            "document.getElementById(\"form1\").reset();" +
                            "location.reload();" +
                            "}" +
                            //If location object cosen is equal to one ofthe values  in hidden variable, then the value is chosen as the location
                            "function myFunction(cont) {\n" +
                            "  var tag = cont.lastElementChild; " +
                            "  var val = document.getElementById(\"Loca\");" +
                            "  var i;\n" +
                            "  for (i = 1; i < val.options.length; i++) {\n" +
                            "    if (val.options[i].innerHTML === tag.innerHTML){" +
                            "       val.value = tag.innerHTML;"+
                            "    }"+
                            "  }"+
                            //Submits
                            "  var x = document.getElementById('form2').submit();" +
                            "}"+

                            "</script>"

            );
        //Error exception catching
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Testing error 1- Failed");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Testing error 2- Failed");
        }
        out.println("</body>");

    }
}
