package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        jdbc test = new jdbc();
        try {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String fullname = request.getParameter("fname") + " " + request.getParameter("lname");
            String address = request.getParameter("address");
            String uname = request.getParameter("user");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String sql = "insert into customer(CUSTOMER_NAME,CUSTOMER_ADDRESS,name,password,email) values(?,?,?,?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, address);
            ps.setString(3, uname);
            ps.setString(4, password);
            ps.setString(5, email);
            ps.executeUpdate();
            out.println("Success Registration");
            response.sendRedirect("LogIn.jsp");
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
