package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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


        out.println("<head>" +
                        "<title>$Title$</title>" +
                        "<link rel=stylesheet href=style.css type=text/css>" +
                    "</head>"
        );
        out.println(
                "<div class=nav>"+
                "<a  href=index.jsp>Home</a>"+
                "<a href=test.jsp>TestPage</a>"+
                "<a href=LogIn.jsp>LogIn/SignUp</a>" +
                "<a class=active href=book>Book A Bike</a>" +
                "<a href=ContactUs.jsp>Contact Us</a>"+
                "<a href=SalesCon.jsp>Sales Confirmation</a>" +
                "<a href=AboutUs.jsp>About Us</a>"+
                "</div>"
        );
        out.println("<form action= servlet1 method = 'post' >" +
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
                "<input type=submit value=Submit>" +
                "</form>"
        );
    }
}
