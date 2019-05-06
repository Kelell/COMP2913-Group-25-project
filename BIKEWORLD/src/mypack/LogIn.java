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
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogIn")
public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        jdbc test = new jdbc();

        HttpSession oldsession =  request.getSession(false);
        if (oldsession == null) {
            response.sendRedirect("index.jsp");
        }

        try {
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            int custid = 0;
            String address = "";
            String fullname = "";
            String Uname = "";
            String email = "";
            String Upass = "";
            String sql = "SELECT * FROM customer WHERE name =? AND password=? ";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                custid = rs.getInt("CUSTOMER_ID");
                Uname = rs.getString("name");
                Upass = rs.getString("password");
                email = rs.getString("email");
                address = rs.getString("CUSTOMER_ADDRESS");
                fullname = rs.getString("CUSTOMER_NAME");


            }
            if(name.equals(Uname)&&password.equals(Upass)){


                if (oldsession != null)
                {
                    oldsession.invalidate();
                }


                HttpSession session = request.getSession();
                session.setAttribute("uname", name);
                session.setAttribute("uemail", email);
                session.setAttribute("uId", custid);
                session.setAttribute("uAddress", address);
                session.setAttribute("uCusname", fullname);
                //setting session to expiry in 5 mins
                session.setMaxInactiveInterval(5*60);
                String message = "Welcome : " + name;
                response.sendRedirect("index.jsp?message=" + URLEncoder.encode(message, "UTF-8"));


            }else{
                //response.sendRedirect("login.jsp");
                HttpSession session = request.getSession();
                String message = "Please enter the correct details";
                session.setAttribute("error", "Invalid Username Or Password. Try again");
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
