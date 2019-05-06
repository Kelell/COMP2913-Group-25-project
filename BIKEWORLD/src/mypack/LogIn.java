package mypack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.*;

@WebServlet(name = "LogIn")
public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        jdbc test = new jdbc();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String Uname = null;
            String Upass = null;
            String sql = "SELECT * FROM customer WHERE name =? AND password=? ";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Uname = rs.getString("name");
                Upass = rs.getString("password");

            }
            if(username.equals(Uname)&&password.equals(Upass)){
                HttpSession session = request.getSession();
                session.setAttribute("uname",username);

                //out.println("Successful LogIn");
                String message = "Welcome : " + username;
                response.sendRedirect("index.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
                //response.sendRedirect("Profile.jsp");

            }else{
                //response.sendRedirect("login.jsp");
                String message = "Please enter the correct details";
                response.sendRedirect("LogIn.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
                //RequestDispatcher rd = request.getRequestDispatcher("LogIn.jsp");
                //rd.include(request, response);
            }

        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
