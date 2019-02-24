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
                "<input type=submit value=Submit>" +
                "</form>"
        );
    }
}
