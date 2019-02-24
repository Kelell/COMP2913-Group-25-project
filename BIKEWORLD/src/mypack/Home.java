package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Home")
public class Home extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<form action= servlet1 method = 'post' >");
        out.println("First name:<br>");
        out.println("<input type=text name= firstname>");
        out.println("<br>");
        out.println("Last name:<br>");
        out.println("<input type=text name='lastname' >");
        out.println("<br><br>");
        out.println("<input type=submit value=Submit>");
        out.println("</form>");



    }
}
