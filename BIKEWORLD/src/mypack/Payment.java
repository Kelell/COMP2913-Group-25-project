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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet(name = "Payment")
public class Payment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    jdbc test = new jdbc();
    try {

       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        String CardNoString = request.getParameter("CardNo");
        int CardNo = Integer.parseInt(CardNoString);
        String CSCString = request.getParameter("CSC");
        int CSC = Integer.parseInt(CSCString);
      //  String ExpDateString = request.getParameter("ExpDate");
        //Date ExpDate = dateFormatter.parse(ExpDateString);

        int CustID = 37;
        int Location = 3;
        int Duration = 2;                                                     //Hard coded values, assumed real values will be passed by book a bike
        java.sql.Date StartDate = Date("2019-06-27");
        //String StartDateString = StartDate.format(dateFormatter);
        double Price = Duration*5;





        String sql = "insert into Receipts(Customer_ID,Location,Start_Date,Duration,Price) values(?,?,?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, CustID);
        ps.setInt(2, Location);
        ps.setInt(3, Duration);
        ps.setDate(4, StartDate);
        ps.setDouble(5, Price);
        ps.executeUpdate();
        out.println("Payment Completed");
    }
    catch (ClassNotFoundException e){
        e.printStackTrace();
    } catch (SQLException e){
        e.printStackTrace();
    }
}



}
