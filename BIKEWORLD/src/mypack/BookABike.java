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


@WebServlet(name = "BookABike")
public class BookABike extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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

            out.println("<head>" +
                    "<title id = prick >$Title$</title>" +
                    "<link rel=stylesheet href=style.css type=text/css>" +
                    "</head>"
            );
            out.println("<body>");
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
                    "<form action= book method = 'post' >" +
                    "<p>Select a Location</p>" + "\n" +
                    "<select name = Location required = 'required'>" +
                    "<option value=Alnmouth>Alnmouth</option>" +
                    "<option value=Beverly>Beverly</option>"+
                    "<option value=Crystal>Crystal</option>"+
                    "<option value=Hexam>Hexam</option>"+
                    "</select>"+ "\n" +
                    "<p>How long would you like the bike</p> "   + "\n" +
                    "<select id = 'myDur' name = Dur required = 'required' onchange=\"myFunction()\"> " +
                    "<option value=4>4 hours</option>"  +
                    "<option value=3>3 hours</option>"  +
                    "<option value=2>2 hours</option>"  +
                    "<option value=1>1 hours</option>" +
                    "<option value=0.5>30 mins</option>" +
                    "</select>" + "\n" +
                    "<input type=submit value=Submit>" + "\n" +
                    "</form>" + "\n" +
                    "<script>"  + "\n" +
                    "function myFunction() {"   + "\n" +
                        "var x = document.getElementById('myDur').value;"   + "\n" +
                        "if (x == 3){"    + "\n" +
                            "document.getElementById('myDur').style.display = 'none';" + "\n" +
                        "}" + "\n" +
                    "} " + "\n" +
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
