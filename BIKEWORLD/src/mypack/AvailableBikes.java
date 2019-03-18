package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "AvailableBikes")
public class AvailableBikes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        jdbc test = new jdbc();
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
            Statement stmt = conn.createStatement();
            String sql2;
            sql2 = "SELECT TIME_ID, Date_, Time_, EndTime, BIKE_ID FROM time";
            ResultSet rs2 = stmt.executeQuery(sql2);
            ArrayList<Integer> time_ids = new ArrayList<Integer>();
            ArrayList<Integer> bike_ids2 = new ArrayList<Integer>();;
            ArrayList<String> date = new ArrayList<String>();
            ArrayList<String> time = new ArrayList<String>();
            ArrayList<String> timend = new ArrayList<String>();

            while(rs2.next()){
                //Retrieve by column name
                int id  = rs2.getInt("TIME_ID");
                String fe = rs2.getString("Date_");
                String ef = rs2.getString("Time_");
                String location = rs2.getString("EndTime");
                int status = rs2.getInt("BIKE_ID");
                time_ids.add(id);
                bike_ids2.add(status);
                date.add(fe);
                time.add(ef);
                timend.add(location);
            }
            rs2.close();
            stmt.close();
            conn.close();

            String d = request.getParameter("date");
            String t = request.getParameter("time");

            out.println("Test");
            out.println("<h1>" + t + "</h1>");
            out.println(d);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Testing error 1- Failed");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Testing error 2- Failed");
        }
        out.println("</body>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
