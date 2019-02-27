package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "BookABike")
public class BookABike extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<form action= servlet1 method = 'post' >" +
                        "<p>Select a Location</p>" + "\n" +
                        "<select name = Location >" +
                            "<option value=0>0</option>" +
                            "<option value=1>1</option>"+
                            "<option value=2>2</option>"+
                            "<option value=3>3</option>"+
                            "<option value=4>4</option>"+
                        "</select>"+
                    "</form>"
        );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        jdbc test = new jdbc();


        String driver = "com.mysql.cj.jdbc.Driver";

        try {
           Class.forName(driver);
            Connection conn = DriverManager.getConnection(test.DB_URL);

            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT BIKE_ID, STATUS, LOCATION FROM BIKES";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("BIKE_ID");
                int status = rs.getInt("STATUS");
                String location = rs.getString("LOCATION");

                //Display values
                out.println("ID: " + id + "<br>");
                out.println(", Status: " + status + "<br>");
                out.println(", Location: " + location + "<br>");
            }
            rs.close();
            stmt.close();
            conn.close();

            out.println("<head>" +
                    "<title>$Title$</title>" +
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

            out.println("<form action= submit method = 'post' >" +
                    "<p>Select a Location</p>" + "\n" +
                    "<select name = Location >" +
                    "<option value=0>0</option>" +
                    "<option value=1>1</option>"+
                    "<option value=2>2</option>"+
                    "<option value=3>3</option>"+
                    "<option value=4>4</option>"+
                    "</select>"+ "\n" +
                    "<p>Select a Bike model</p>" + "\n" +
                    "<select name = Model >" +
                    "<option value=0>0</option>" +
                    "<option value=1>1</option>"+
                    "<option value=2>2</option>"+
                    "<option value=3>3</option>"+
                    "<option value=4>4</option>"+
                    "</select>"+ "\n" +
                    "<p>Select number of bikes</p>" + "\n" +
                    "<select name = quantity >" +
                    "<option value=1>1</option>"+
                    "<option value=2>2</option>"+
                    "<option value=3>3</option>"+
                    "<option value=4>4</option>"+
                    "</select>"+ "\n" +
                    "<p>Select your duration</p>" + "\n" +
                    "<select name = Duration >" +
                    "<option value=1>24 hours</option>" +
                    "<option value=2>48 hours</option>"+
                    "<option value=3>72 hours</option>"+
                    "</select>"+ "\n" +
                    "<input type=submit value=Submit>" + "\n" +
                    "</form>"
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
