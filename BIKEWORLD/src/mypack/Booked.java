package mypack;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.Session;
import java.util.Properties;

@WebServlet(name = "Booked")
public class Booked extends HttpServlet {
    public int generateBarcode() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(100_000_000, 1_000_000_000);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String term = request.getParameter("term");

        HttpSession session =  request.getSession(false);
        if (session.getAttribute("uname") == null) {
            response.sendRedirect("index.jsp");
        }

        if (Integer.parseInt(term) == 2)
        {
            PrintWriter out = response.getWriter();
            jdbc test = new jdbc();
            try {
                String bike_id = request.getParameter("bikeids");
                String sql = "insert into hires(CUSTOMER_ID,BIKE_ID,days,cash,barcode,START_DATE,END_DATE) values(?,?,?,?,?,?,?)";
                String sql2 = "SELECT barcode FROM hires";
                String sql3 = "UPDATE `bike` SET `STATUS` = '0' WHERE `bike`.`BIKE_ID` = ? ";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
                //create barcode
                ArrayList<Integer> barcode = new ArrayList<Integer>();
                Statement stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(sql2);
                long bar = generateBarcode();
                while(rs2.next()){
                    //Retrieve by column name
                    int h  = rs2.getInt("barcode");
                    barcode.add(h);
                }
                rs2.close();
                int h = 0;
                int x = 1;
                while(x == 1)
                {
                    bar = generateBarcode();
                    for (h = 0; h < barcode.size(); h++)
                    {
                        if(bar == barcode.get(h))
                        {
                            break;
                        }
                        else if(h == barcode.size() - 1 && bar != barcode.get(h))
                        {
                            x = 0;
                        }
                    }
                }
                stmt.close();
                conn.close();


                Connection conn2 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
                String userid = session.getAttribute("uId").toString();
                String bId = request.getParameter("bikeids");
                String location = request.getParameter("location");
                String days = request.getParameter("days");
                String cost = request.getParameter("cost");
                String code = Long.toString(bar);
                //Date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date ddate = format.parse(request.getParameter("startd"));
                Calendar reqdate = Calendar.getInstance();
                reqdate.setTime(ddate);
                Calendar reqdatedur = Calendar.getInstance();
                reqdatedur.setTime(ddate);
                int reqdur = Integer.parseInt(days);
                reqdatedur.add(Calendar.DAY_OF_MONTH, reqdur);
                String startdate = reqdate.get(Calendar.YEAR) + "-" + (reqdate.get(Calendar.MONTH) + 1) + "-" + reqdate.get(Calendar.DATE);
                String enddate = reqdatedur.get(Calendar.YEAR) + "-" + (reqdatedur.get(Calendar.MONTH) + 1) + "-" + reqdatedur.get(Calendar.DATE);
                PreparedStatement ps = conn2.prepareStatement(sql);
                ps.setString(1, userid);
                ps.setString(2, bId);
                ps.setString(3, days);
                ps.setString(4, cost);
                ps.setString(5, code);
                ps.setString(6, startdate);
                ps.setString(7, enddate);
                ps.executeUpdate();
                ps.close();
                conn2.close();





                Connection conn3 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
                PreparedStatement ps2 = conn3.prepareStatement(sql3);
                ps2.setString(1, bike_id);
                ps2.executeUpdate();
                ps2.close();
                conn3.close();




                String to = session.getAttribute("uemail").toString();

                // Sender's email ID needs to be mentioned
                String from = "web@gmail.com";

                // Assuming you are sending email from localhost
                String host = "localhost";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.setProperty("mail.smtp.host", host);

                // Get the default Session object.
                Session mailsession = Session.getDefaultInstance(properties);


                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(mailsession);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("This is the Subject Line!");

                // Now set the actual message
                message.setText("This is actual message");

                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");




                response.sendRedirect("Dashboard.jsp");

            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (SQLException e){
                e.printStackTrace();
            }catch (ParseException e){
                e.printStackTrace();
            }catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
        else
        {
            PrintWriter out = response.getWriter();
            jdbc test = new jdbc();
            try {
                String userid = session.getAttribute("uId").toString();
                String bike_id = request.getParameter("bikeids");
                String sql = "insert into Short_Hires(Customer_Id,Bike_Id,Hours,Cash,Barcode,Start_Time,End_Time, Date) values(?,?,?,?,?,?,?,?)";
                String sql2 = "SELECT barcode FROM hires";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
                //create barcode
                ArrayList<Integer> barcode = new ArrayList<Integer>();
                Statement stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(sql2);
                long bar = generateBarcode();
                while(rs2.next()){
                    //Retrieve by column name
                    int h  = rs2.getInt("barcode");
                    barcode.add(h);
                }
                rs2.close();
                int h = 0;
                int x = 1;
                while(x == 1)
                {
                    bar = generateBarcode();
                    for (h = 0; h < barcode.size(); h++)
                    {
                        if(bar == barcode.get(h))
                        {
                            break;
                        }
                        else if(h == barcode.size() - 1 && bar != barcode.get(h))
                        {
                            x = 0;
                        }
                    }
                }
                stmt.close();
                conn.close();


                Connection conn2 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
                String bId = request.getParameter("bikeids");
                String location = request.getParameter("location");
                String days = request.getParameter("hours");
                String cost = request.getParameter("cost");
                String code = Long.toString(bar);
                //Time
                String st = request.getParameter("startt");
                String et = request.getParameter("endt");
                String theday = request.getParameter("theday");
                PreparedStatement ps = conn2.prepareStatement(sql);
                ps.setString(1, userid);
                ps.setString(2, bId);
                ps.setString(3, days);
                ps.setString(4, cost);
                ps.setString(5, code);
                ps.setString(6, st);
                ps.setString(7, et);
                ps.setString(8, theday);
                ps.executeUpdate();
                out.println("Success Booking");


                // Recipient's email ID needs to be mentioned.
                String to = session.getAttribute("uemail").toString();

                // Sender's email ID needs to be mentioned
                String from = "web@gmail.com";

                // Assuming you are sending email from localhost
                String host = "localhost";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.setProperty("mail.smtp.host", host);

                // Get the default Session object.
                Session mailsession = Session.getDefaultInstance(properties);


                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(mailsession);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("This is the Subject Line!");

                // Now set the actual message
                message.setText("This is actual message");

                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");


                response.sendRedirect("Dashboard.jsp");
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (SQLException e){
                e.printStackTrace();
            }catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
