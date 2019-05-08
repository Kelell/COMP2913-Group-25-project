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
            String fname = request.getParameter("fname");//converts name input from registration from to string
            String lname = request.getParameter("lname");//converts surname input from registration from to string
            String fullname = fname + " " + lname;//creates fullname string from firstname and surname
            String address = request.getParameter("address");//converts address input from registration from to string
            String uname = request.getParameter("username");//converts username input from registration from to string
            String password = request.getParameter("password");//converts password input from registration from to string
            String email = request.getParameter("email");//converts email input from registration from to string
            String sql = "insert into customer(name,password,CUSTOMER_NAME,CUSTOMER_ADDRESS,email) values(?,?,?,?,?)"; //sql query for accessing Customers table
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//creates connection to my sql database
            PreparedStatement ps = conn.prepareStatement(sql);
            String sql2 = "SELECT * FROM customer WHERE name =? ";//sql query for checking Customer table for existing username and password
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            String Username = null;//ensures username is null before assigning new name
            ps2.setString(1, uname); // checks name field for instances of a suername
            ResultSet rs = ps2.executeQuery();
            //Loops through the result set to find user name the assigns it to variable Username
            while(rs.next()){
                Username = rs.getString("name");

            }
            //Username is null meaning the variable did not find a record with username == Username
            if (Username == null)
            {
                //Assigns these values to prepared statement
                ps.setString(1, uname);
                ps.setString(2, password);
                ps.setString(3, fullname);
                ps.setString(4, address);
                ps.setString(5, email);
                ps.executeUpdate();



                //Welcome to Bike world email to  let user know he has joined
                String recieve_email = request.getParameter("email");
                // Sender's email ID needs to be mentioned
                String from_email = "bikeworld@gmail.com";
                String host = "localhost";
                Properties prop = System.getProperties();
                // Setting up mail server
                prop.setProperty("mail.smtp.host", host);
                // Got mail session.
                Session mailsession = Session.getDefaultInstance(prop);
                MimeMessage message = new MimeMessage(mailsession);
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from_email));
                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recieve_email));
                // Set Subject: header field
                message.setSubject("Welcome!!");
                // Email body
                message.setText("This is an introductury email welcoming you to our family. As the leading bike rental website we are pleased to have you and hope your stay may be enjoyable");
                // Email sent
                Transport.send(message);

                response.sendRedirect("LogIn.jsp");
            }
            else if(uname.equals(Username)){//if username entered already exists redirects user to log in register page
                String message = "Username already in use.";//error message
                response.sendRedirect("registration.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

            }
            else{//if username not taken assigns input to database collumns
                ps.setString(1, uname);
                ps.setString(2, password);
                ps.setString(3, fullname);
                ps.setString(4, address);
                ps.setString(5, email);
                ps.executeUpdate();//updates changes to database
                response.sendRedirect("LogIn.jsp");//redirects user to login
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//not used
    }
}
