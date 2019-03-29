package mypack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LogIn")
public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        jdbc test = new jdbc();
        try {
            String name = request.getParameter("user");
            String password = request.getParameter("password");
            String Uname = null;
            String Upass = null;
            String sql = "SELECT * FROM users WHERE name =? AND password=? ";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Uname = rs.getString("name");
                Upass = rs.getString("password");

            }
            if(name.equals(Uname)&&password.equals(Upass)){
                out.println("Successful LogIn");
                
            }else{
                //response.sendRedirect("login.jsp");
                RequestDispatcher rd = request.getRequestDispatcher("LogIn.jsp");
                rd.include(request, response);
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
