package mypack;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import java.util.Properties;
import java.io.FileOutputStream;


@WebServlet(name = "Registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        jdbc test = new jdbc();
        try {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String fullname = fname + " " + lname;
            String address = request.getParameter("address");
            String uname = request.getParameter("username");
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
            if (Username == null)
            {
                ps.setString(1, uname);
                ps.setString(2, password);
                ps.setString(3, fullname);
                ps.setString(4, address);
                ps.setString(5, email);
                ps.executeUpdate();



                String to = request.getParameter("email").toString();

                // Sender's email ID needs to be mentioned
                String from = "bikeworld@gmail.com";

                // Assuming you are sending email from localhost
                String host = "localhost";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.setProperty("mail.smtp.host", host);
                Properties props = new Properties();
                // Get the default Session object.
                Session mailsession = Session.getDefaultInstance(properties);


                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(mailsession);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                BodyPart messageBodyPart = new MimeBodyPart();

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("Welcome!!");

                // Now set the actual message
                message.setText("This is an introductury email welcoming you to our family. As the leading bike rental website we are pleased to have you and hope your stay may be enjoyable");


                // Send message
                Transport.send(message);

                response.sendRedirect("LogIn.jsp");
            }
            else if(uname.equals(Username)){
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
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
