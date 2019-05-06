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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.*;

@WebServlet(name = "Registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        jdbc test = new jdbc();
        try {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String fullname = String.format("%s %s", fname, lname);
            String address = request.getParameter("address");
            String uname = request.getParameter("user");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String sql = "insert into customer(name,password,CUSTOMER_NAME,CUSTOMER_ADDRESS,email) values(?,?,?,?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
            PreparedStatement ps = conn.prepareStatement(sql);
            String sql2 = "SELECT * FROM customer WHERE name =? ";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            String Username = null;
            ps2.setString(1, uname);
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                Username = rs.getString("name");

            }
            if(uname.equals(Username)){
                String message = "Username already in use.";
                response.sendRedirect("registration.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

                //response.sendRedirect("registration.jsp");

            }
            else{
                ps.setString(1, uname);
                ps.setString(2, password);
                ps.setString(3, fullname);
                ps.setString(4, address);
                ps.setString(5, email);
                ps.executeUpdate();
                out.println("Success Registration");
                response.sendRedirect("LogIn.jsp");
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
