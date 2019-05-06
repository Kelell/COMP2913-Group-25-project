package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Dashboard")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession current = request.getSession(false);
        out.println("<html>\n");
        if (current.getAttribute("uName") == null) {
            response.sendRedirect("index.jsp");
        }

        out.println("<html>\n" +
                "<head>\n" +
                "    <title>Index</title>\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class = \"Title\">\n" +
                "    B!KEWORLD\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"nav\"><!--Nav bar from w3schools: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_topnav (TEST ONLY)-->\n" +
                "    <a class=\"active\" href=\"Dashboard\">Home</a>\n" +
                "    <a href=\"Views\">View bikes</a>\n" +
                "    <a href=\"AboutUs.jsp\">About Us</a>\n" +
                "    <a href=\"ContactUs.jsp\">Contact Us</a>\n" +
                "    <a href=\"Logout.jsp\">Log out</a>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession current = request.getSession(false);

        out.println("<html>\n");
        if (current.getAttribute("uName") == null) {
            response.sendRedirect("index.jsp");
        }
        out.println(
                "<head>\n" +
                "    <title>Index</title>\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class = \"Title\">\n" +
                "    B!KEWORLD\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"nav\"><!--Nav bar from w3schools: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_topnav (TEST ONLY)-->\n" +
                "    <a class=\"active\" href=\"Dashboard\">Home</a>\n" +
                "    <a href=\"Views\">View bikes</a>\n" +
                "    <a href=\"AboutUs.jsp\">About Us</a>\n" +
                "    <a href=\"ContactUs.jsp\">Contact Us</a>\n" +
                "    <a href=\"Logout.jsp\">Log out</a>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }
}
