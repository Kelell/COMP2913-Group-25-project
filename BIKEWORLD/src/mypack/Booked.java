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
import java.net.URLEncoder;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.Session;
import java.util.Properties;
import java.io.File;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@WebServlet(name = "Booked")
public class Booked extends HttpServlet {
    //BArcode generater
    public int generateBarcode() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(100_000_000, 1_000_000_000);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Term
        String term = request.getParameter("term");

        HttpSession session =  request.getSession(false);
        //Checks to see if session is still active by seeing if session Attribute is false
        //Session obtained through getSession
        try {

            //If session attribute is false then the session is false. Therefore you are redirected to index.jsp page
            if (session.getAttribute("uname") == null) {
                response.sendRedirect("index.jsp");
            }
        } catch (NullPointerException name) {
            response.sendRedirect("index.jsp");
        }
        PrintWriter out = response.getWriter();
        jdbc test = new jdbc();

        if (request.getParameter("expmonth").equals("January") || request.getParameter("expmonth").equals("February") || request.getParameter("expmonth").equals("March") || request.getParameter("expmonth").equals("April") || request.getParameter("expmonth").equals("May") || request.getParameter("expmonth").equals("June") || request.getParameter("expmonth").equals("July") || request.getParameter("expmonth").equals("August") || request.getParameter("expmonth").equals("September") || request.getParameter("expmonth").equals("October") || request.getParameter("expmonth").equals("November") || request.getParameter("expmonth").equals("December"))
        {
            if (request.getParameter("expyear").equals(""))
            {
                String message = "Please enter the valid year details next time";//error message
                response.sendRedirect("Views?message=" + URLEncoder.encode(message, "UTF-8"));
            }
            else if (Integer.parseInt(request.getParameter("expyear")) >= 2018 )
            {


                if (request.getParameter("card").equals("yoo")){
                    try {

                        Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
                        Statement stmt = conn.createStatement();
                        String sql;
                        //SQL string selects bike ids, location,price and status from the bike table in a mysql database
                        sql = "SELECT * FROM card";
                        //The result set is saved to rs variable
                        ResultSet rs = stmt.executeQuery(sql);
                        //Set of array lists created to store the data
                        double card = 0;
                        ArrayList<Long> bikes = new ArrayList<Long>();
                        //String loc is the location parameter sent by the Views page post method
                        String loc = request.getParameter("Location");

                        boolean x = false;
                        while(rs.next()){
                            //Retrieve by column name
                            card = rs.getDouble("CUSTOMER_CARD_NO");
                            if (Double.parseDouble(request.getParameter("cardnumber")) ==  card){
                                x = true;
                            }

                        }

                        if (x == false)
                        {
                            String sql5 = "insert into card(CUSTOMER_CARD_NO,CUSTOMER_ID,EXPM,EXPY) values(?,?,?,?)";
                            Connection conn5 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");
                            String userid = session.getAttribute("uId").toString();
                            PreparedStatement ps5 = conn5.prepareStatement(sql5);
                            String month = "";
                            if (request.getParameter("expmonth").equals("January"))
                            {
                                month ="1";
                            }
                            if (request.getParameter("expmonth").equals("February"))
                            {
                                month ="2";
                            }
                            if (request.getParameter("expmonth").equals("March"))
                            {
                                month ="3";
                            }
                            if (request.getParameter("expmonth").equals("April"))
                            {
                                month ="4";
                            }
                            if (request.getParameter("expmonth").equals("May"))
                            {
                                month ="5";
                            }
                            if (request.getParameter("expmonth").equals("June"))
                            {
                                month ="6";
                            }
                            if (request.getParameter("expmonth").equals("July"))
                            {
                                month ="7";
                            }
                            if (request.getParameter("expmonth").equals("August"))
                            {
                                month ="8";
                            }
                            if (request.getParameter("expmonth").equals("September"))
                            {
                                month ="9";
                            }
                            if (request.getParameter("expmonth").equals("October"))
                            {
                                month ="10";
                            }
                            if (request.getParameter("expmonth").equals("November"))
                            {
                                month ="11";
                            }
                            if (request.getParameter("expmonth").equals("December"))
                            {
                                month ="12";
                            }
                            //Variables added to mysql
                            ps5.setString(1, request.getParameter("cardnumber"));
                            ps5.setString(2, userid);
                            ps5.setString(3, month);
                            ps5.setString(4, request.getParameter("expyear"));
                            ps5.executeUpdate();
                            ps5.close();
                            conn5.close();
                        }

                    }
                    catch (SQLException e){
                        e.printStackTrace();
                    }catch(Exception e) {
                        System.err.println(e);
                    }


                }


                //If term is long term or 2
                if (Integer.parseInt(term) == 2)
                {


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
                        //Cycles through other barcodes in database(hires table)
                        while(rs2.next()){
                            //Retrieve by column name
                            int h  = rs2.getInt("barcode");
                            barcode.add(h);
                        }
                        rs2.close();
                        int h = 0;
                        int x = 1;
                        //While loop checks if barcode generated is equal to any in the database. If not the thhe bar code is used as x = 0
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

                        //Connection to database established
                        Connection conn2 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
                        //Variables initialized
                        String userid = session.getAttribute("uId").toString();
                        String name = session.getAttribute("uCusname").toString();
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
                        //Start and end date
                        String startdate = reqdate.get(Calendar.YEAR) + "-" + (reqdate.get(Calendar.MONTH) + 1) + "-" + reqdate.get(Calendar.DATE);
                        String enddate = reqdatedur.get(Calendar.YEAR) + "-" + (reqdatedur.get(Calendar.MONTH) + 1) + "-" + reqdatedur.get(Calendar.DATE);
                        PreparedStatement ps = conn2.prepareStatement(sql);
                        //Variables added to mysql
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




                        //Code updates values in status column in table
                        Connection conn3 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
                        PreparedStatement ps2 = conn3.prepareStatement(sql3);
                        ps2.setString(1, bike_id);
                        ps2.executeUpdate();
                        ps2.close();
                        conn3.close();



                        //Email name
                        String to = session.getAttribute("uemail").toString();

                        // Sender's email ID needs to be mentioned
                        String from_email = "bikeworld@gmail.com";

                        // Assuming you are sending email from localhost
                        String host = "localhost";

                        // Get system properties
                        Properties properties = System.getProperties();

                        // Setup mail server
                        properties.setProperty("mail.smtp.host", host);
                        Properties props = new Properties();
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", host);
                        props.put("mail.smtp.port", "25");
                        // Get the default Session object.
                        Session mailsession = Session.getDefaultInstance(properties);


                        // Create a default MimeMessage object.
                        MimeMessage message = new MimeMessage(mailsession);

                        // Set From: header field of the header.
                        message.setFrom(new InternetAddress(from_email));

                        // Set To: header field of the header.
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                        message.setSubject("Booking Confirmation!");



                        BodyPart messageBodyPart = new MimeBodyPart();

                        // Now set the actual message
                        messageBodyPart.setText("Good evening. " +
                                "" +
                                "" +
                                "Please find attatched a copy of your booking reciept.");

                        // Create a multipar message
                        Multipart multipart = new MimeMultipart();

                        // Set text message part
                        multipart.addBodyPart(messageBodyPart);


                        messageBodyPart = new MimeBodyPart();
                        File file = new File(System.getProperty("user.dir") + "/myfile.txt");
                        file.createNewFile();
                        String str = "RECIEPT\n\nName : " + name + "\n\nUser Id : " + userid + "\n\nBike id : " + bId + "\n\nLocation : " + location + "\n\nCost : " + cost + "\n\nDays : " + days + "\n\nStart date : " + startdate + "\n\nEnd date : " + enddate + "\n\nBarcode (unique payment code) : " + code +"\n\n";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(str);
                        writer.close();
                        String filename = System.getProperty("user.dir") + "/myfile.txt";
                        DataSource source = new FileDataSource(filename);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(filename);
                        multipart.addBodyPart(messageBodyPart);


                        // Send the complete message parts
                        message.setContent(multipart);

                        // Send message
                        Transport.send(message);
                        file.delete();
                        System.out.println("Sent message successfully....");




                        response.sendRedirect("index.jsp");

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
                    catch (Exception e) {
                        System.err.println(e);
                    }
                }
                else
                {

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
                        String name = session.getAttribute("uCusname").toString();
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


                        //Email name
                        String to = session.getAttribute("uemail").toString();

                        // Sender's email ID needs to be mentioned
                        String from_email = "bikeworld@gmail.com";

                        // Assuming you are sending email from localhost
                        String host = "localhost";

                        // Get system properties
                        Properties properties = System.getProperties();

                        // Setup mail server
                        properties.setProperty("mail.smtp.host", host);
                        Properties props = new Properties();
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", host);
                        props.put("mail.smtp.port", "25");
                        // Get the default Session object.
                        Session mailsession = Session.getDefaultInstance(properties);


                        // Create a default MimeMessage object.
                        MimeMessage message = new MimeMessage(mailsession);

                        // Set From: header field of the header.
                        message.setFrom(new InternetAddress(from_email));

                        // Set To: header field of the header.
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                        message.setSubject("Booking Confirmation!");



                        BodyPart messageBodyPart = new MimeBodyPart();

                        // Now set the actual message
                        messageBodyPart.setText("Good evening. " +
                                "" +
                                "" +
                                "Please find attatched a copy of your booking reciept.");

                        // Create a multipar message
                        Multipart multipart = new MimeMultipart();

                        // Set text message part
                        multipart.addBodyPart(messageBodyPart);


                        messageBodyPart = new MimeBodyPart();
                        File file = new File(System.getProperty("user.dir") + "/myfile.txt");
                        file.createNewFile();
                        String str = "RECIEPT\n\nName : " + name + "\n\nUser Id : " + userid + "\n\nBike id : " + bId + "\n\nLocation : " + location + "\n\nCost : " + cost + "\n\nHours : " + days + "\n\nStart time : " + st + "\n\nEnd time : " + et +"\n\nDAy : " + theday + "\n\nBarcode (unique payment code) : " + code +"\n\n";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(str);
                        writer.close();
                        String filename = System.getProperty("user.dir") + "/myfile.txt";
                        DataSource source = new FileDataSource(filename);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(filename);
                        multipart.addBodyPart(messageBodyPart);


                        // Send the complete message parts
                        message.setContent(multipart);

                        // Send message
                        Transport.send(message);
                        file.delete();
                        System.out.println("Sent message successfully....");


                        response.sendRedirect("index.jsp");
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
            else{
                String message = "Please enter the valid year details next time";//error message
                response.sendRedirect("Views?message=" + URLEncoder.encode(message, "UTF-8"));
            }
        }
        else{
            String message = "Please enter the valid month details next time";//error message
            response.sendRedirect("Views?message=" + URLEncoder.encode(message, "UTF-8"));
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
