package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.text.ParseException;

@WebServlet(name = "Booking")
public class Booking extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        jdbc test = new jdbc();
        String driver = "com.mysql.cj.jdbc.Driver";


        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT BIKE_ID, LOCATION, price FROM bike";

            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> bikes = new ArrayList<Integer>();
            ArrayList<String> loca = new ArrayList<String>();
            ArrayList<Float> cost = new ArrayList<Float>();


            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("BIKE_ID");
                String location = rs.getString("LOCATION");
                float price = rs.getFloat("price");
                bikes.add(id);
                loca.add(location);
                cost.add(price);
            }

            rs.close();
            stmt.close();
            conn.close();

            int listsize = bikes.size();
            String size = Integer.toString(listsize);

            out.println("<head onload=\"openFunction()\" >" +
                    "<title id = prick >$Title$</title>" +
                    "<link rel=stylesheet href=style.css type=text/css>" +
                    "</head>"
            );
            out.println("<body  >");

            out.println(
                    "<p name = 'Location' >Select a Location</p>\n" +
                            "<select name = 'Location' required onclick='myFunction()' >\n" +
                            "<option selected value= 'Please select'>Please select</option>\n" +
                            "<option value='Alnmouth'>Alnmouth</option>\n" +
                            "<option value='Beverly'>Beverly</option>\n" +
                            "<option value='Crystal'>Crystal</option>\n" +
                            "<option value='Hexam'>Hexam</option>\n" +
                            "</select>\n"
            );

            for (int i = 0; i < listsize; i++)
            {
                out.println(
                        "<div name = "+ loca.get(i) +" class = 'section' >\n" +
                                "<div  class = \"bike\">\n" +
                                "\t<img src = \"https://www.cahabacycles.com/merchant/189/images/site/chc-rental-img7.jpg\" alt = \"bike\" width = \"390px\" height = \"300px\">\n" +

                                "\n" +
                                "</div>" +
                                "<div class = \"info\">\n" +
                                "    <p>price per hour : "+ cost.get(i) +"</p>\n" +
                                "    <p>id: "+ bikes.get(i)+"</p>\n" +
                                " <button style=\"display:none;\" type=\"button\" onclick=\"alert('Simulation of booking')\">Book</button>"+
                                "</div>" +
                                "</div>"+
                                "<br>"+
                                "<br>"
                );
            }

            out.println(

                            "<script>\n" +
                            "function openFunction() {\n" +
                            "document.getElementById('form1').reset();\n" +
                            "location.reload();\n" +
                            "}\n" +
                            "\n" +
                                    
                            "function myFunction(){\n" +
                                    "  var bikes = document.getElementsByClassName('section');\n" +
                                    "  var location = document.getElementsByName('Location');\n" +
                                    "  var i ;\n" +
                                    "  for (i = 0; i < bikes.length; i ++){\n" +
                                    "    if (location[1].value  == bikes[i].getAttribute(\"name\")){ \n" +
                                    "      bikes[i].style.display = 'block';\n" +
                                    "    }\n" +
                                    "    else{\n" +
                                    "      bikes[i].style.display = \"none\";\n" +
                                    "    }\n" +
                                    "  }\n" +
                                    "}"+

                    "</script>\n"
            );

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Testing error 1- Failed");
        }
        catch (SQLException e) {
            e.printStackTrace();
            out.println("Testing error 2- Failed");
        }

        out.println("</body >");
    }
}
