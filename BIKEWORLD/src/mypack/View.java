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

@WebServlet(name = "View")
public class View extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        jdbc test = new jdbc();
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT BIKE_ID, LOCATION, price FROM bike";
            ResultSet rs = stmt.executeQuery(sql);
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
            rs.close();
            stmt.close();
            conn.close();

            int listsize = bike_ids.size();
            String size = Integer.toString(listsize);

            out.println("<head onload=\"openFunction()\" >" +
                    "<title id = prick >$Title$</title>" +
                    "<link rel=stylesheet href=style.css type=text/css>" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
                    "<style>\n" +
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
                    "<div class = \"Title\">\n" +
                            "    B!KEWORLD\n" +
                            "  </div>"+
                    "<div class=nav>"+
                            "<a  href=index.jsp>Home</a>"+
                            "<a class=active href=\"Views\">View bikes</a>" +
                            "<a href=\"book\">Book a bike</a>"+
                            "<a href=AboutUs.jsp>About Us</a>"+
                            "<a href=ContactUs.jsp>Contact Us</a>"+
                            "<a>Log out</a>" +
                            "</div>"
            );
            out.println(
                    "<form id = 'form2' action= Available method = 'post' >" +

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


                            "<h1 name = Location >Select a Location</h1>" + "\n" +
                            "<div class=\"row\"  style = \"\">\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \" https://i.pinimg.com/474x/de/2e/9d/de2e9d4d049c7032056149762f313f88--wales-england.jpg\" alt=\"Alnmouth\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Alnmouth</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"http://blackwoodcontracts.co.uk/public/images/images/1476785212.9959.jpg\" alt=\"Barnsley Interchange\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Barnsley</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://odis.homeaway.com/odis/listing/6e43f6f7-ca87-4df4-b68d-06e1d0199fb7.c6.jpg\" alt=\"Beverly\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Beverly</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://odis.homeaway.com/odis/listing/837a55f6-e395-4443-9abd-3b486af23e90.c6.jpg\" alt=\"Bournemouth\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Bournemouth</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://imgs.nestimg.com/2_bedroom_flat_110858279168491148.jpg\" alt=\"Bradford Interchange\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Bradford</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://imgc.allpostersimages.com/img/print/u-g-P1H1SK0.jpg?w=400&h=300\" alt=\"Bristol\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Bristol</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img  src= \"https://t1.ftcdn.net/jpg/01/84/33/50/400_F_184335035_sqCKtkB9su1fGseg1NhRIsFmBlC5b1mJ.jpg\" alt=\"Buckingham\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Buckingham</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://res.cloudinary.com/travelodgeuk/image/upload/w_400/hotels/GB0727_Croydon_Central_EXTERIOR_2208.jpg\" alt=\"Crystal\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Crystal</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"http://www.visitoruk.com/images/franchises/Halifax/1290959401_1homeslider_halifax2.jpg\" alt=\"Halifax\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Halifax</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"http://www.visitoruk.com/images/franchises/Harrogate/1290959182_1homeslider_harrogate2.jpg\" alt=\"Harrogate\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Harrogate</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://www.britainexpress.com/images/accommodation/cottages/SY/SY930177_19.jpg\" alt=\"Hebden\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Hebden</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://images.snaptrip.com/uploads/image/file/4809608/hero_304527.jpg\" alt=\"Hexam\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Hexham</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"http://www.europeanrailguide.com/images/itour/182.jpg\" alt=\"Leeds\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Leeds</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\"onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://i.pinimg.com/originals/be/e8/ec/bee8ec97008567e59dab790d58ab6836.jpg\" alt=\"Manchester\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Manchester</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://3.bp.blogspot.com/-8yGySJG5cas/WIdzaDKG1CI/AAAAAAAAT-Y/MicdItwRdDkkzRT7xEgY2J0ECSTeCe_FwCLcB/s400/rothbiz+rotherham+interchange.jpg\" alt=\"Rotherham\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Rotherham</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "  <div class=\"column\">\n" +
                            "    <div class=\"container\" onclick=\"myFunction(this)\" >"+
                            "       <img src= \"https://imgs.nestimg.com/1_bedroom_flat_for_sale_110499546788616634.jpg\" alt=\"Shipley\" width=\"390\" height=\"300\">\n" +
                            "       <div id=\"imgtext\">Shipley</div>" +
                            "    </div>"+
                            "  </div>\n" +
                            "</div>" +
                            "</form>" + "\n" +
                            "<script>"  + "\n" +
                            "function openFunction() {"   + "\n" +
                            "document.getElementById(\"form1\").reset();" +
                            "location.reload();" +
                            "}" +
                            "function myFunction(cont) {\n" +
                            "  var tag = cont.lastElementChild; " +
                            "  var val = document.getElementById(\"Loca\");" +
                            "  var i;\n" +
                            "  for (i = 1; i < val.options.length; i++) {\n" +
                            "    if (val.options[i].innerHTML === tag.innerHTML){" +
                            "       val.value = tag.innerHTML;"+
                            "    }"+
                            "  }"+
                            "  var x = document.getElementById('form2').submit();" +
                            "}"+

                            "</script>"

            );

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
