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
            sql = "SELECT BIKE_ID, STATUS, LOCATION, price FROM bike";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> bike_ids = new ArrayList<Integer>();
            ArrayList<Integer> stats = new ArrayList<Integer>();;
            ArrayList<String> loca = new ArrayList<String>();
            ArrayList<Float> cost = new ArrayList<Float>();


            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("BIKE_ID");
                int status = rs.getInt("STATUS");
                String location = rs.getString("LOCATION");
                float price = rs.getFloat("price");
                bike_ids.add(id);
                stats.add(status);
                loca.add(location);
                cost.add(price);
            }
            rs.close();
            stmt.close();
            conn.close();

            int listsize = bike_ids.size();
            String size = Integer.toString(listsize);

            File file = new File("/home/csunix/sc16bmt/thingsTHISYEAR/SEPROJECT/comp2913-group-25-project/BIKEWORLD/web/loca/alnmouth.jpg");
            URL url = file.toURI().toURL();
            URI uri = file.toURI();

            out.println("<head onload=\"openFunction()\" >" +
                    "<title id = prick >$Title$</title>" +
                    "<link rel=stylesheet href=style.css type=text/css>" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
                    "</head>"
            );
            out.println("<body  >");
            out.println(
                    "<div class=nav>"+
                            "<a  href=index.jsp>Home</a>"+
                            "<a class=active href=book>Book A Bike</a>" +
                            "<a href=AboutUs.jsp>About Us</a>"+
                            "<a href=ContactUs.jsp>Contact Us</a>"+
                            "<a>Log out</a>" +
                            "</div>"
            );
            out.println(
                    "<form id = 'form1' action= book method = 'post' >" +


                            "<p name = Location >Select a Location</p>" + "\n" +
                            "<div class=\"row\">\n" +
                            "  <div class=\"column\">\n" +
                            "    <img src= \""+ url + "\" alt=\"alnmouth\" width=\"400\" height=\"300\">\n" +
                            "    <div id=\"alnmousth\"></div>" +
                            "  </div>\n" +
                            "</div>" +

                            "<input id = \"submit\" style=\"display:none;\" type=submit value=Submit>" + "\n" +
                            "</form>" + "\n" +
                            "<script>"  + "\n" +
                            "function openFunction() {"   + "\n" +
                            "document.getElementById(\"form1\").reset();" +
                            "location.reload();" +
                            "}" +

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
