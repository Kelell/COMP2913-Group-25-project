package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Submit")
public class Submit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String loc = request.getParameter("Location");

        String mod = request.getParameter("Model");

        String qu = request.getParameter("quantity");

        String dur = request.getParameter("Duration");

        out.println("<h1>Location is " + loc + "</h1>");

        out.println("<h1>Model is " + mod + "</h1>");

        out.println("<h1>" + qu + " bikes</h1>");

        out.println("<h1>For " + dur + " days long</h1>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
