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
        try {
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            String Uname = "";
            String Upass = "";
            int customerId = 0;
            String sql = "SELECT * FROM customer WHERE name =? AND password=? ";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                customerId = rs.getInt("CUSTOMER_ID");
                Uname = rs.getString("name");
                Upass = rs.getString("password");


            }
            if(name.equals(Uname)&&password.equals(Upass)){

                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }

                HttpSession session = request.getSession(true);
                session.setAttribute("uName", "ChaitanyaSingh");
                session.setAttribute("uemailId", "myemailid@gmail.com");
                session.setAttribute("uAge", "30");

                //setting session to expiry in 5 mins
                session.setMaxInactiveInterval(5*60);

                Cookie message = new Cookie("message", "Welcome");
                response.addCookie(message);

                response.sendRedirect("Dashboard");
            }else{
                //response.sendRedirect("login.jsp");
                out.print("Sorry, username or password error!");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
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
