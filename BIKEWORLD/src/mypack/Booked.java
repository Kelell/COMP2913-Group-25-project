package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "Booked")
public class Booked extends HttpServlet {
    public long generateId() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(10_000_000_000L, 100_000_000_000L);
    }
    public static double getRandomIntegerBetweenRange(double min, double max){

        double x = (int)(Math.random()*((max-min)+1))+min;

        return x;

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        jdbc test = new jdbc();
        try {
            String bike_id = request.getParameter("bikeids");
            String sql = "insert into hires(CUSTOMER_ID,BIKE_ID,days,cash,barcode,START_DATE,END_DATE) values(?,?,?,?,?,?,?)";
            String sql2 = "SELECT barcode FROM hires";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//connects to mysql database
            //create barcode
            ArrayList<Integer> barcode = new ArrayList<Integer>();
            Statement stmt = conn.createStatement();
            ResultSet rs2 = stmt.executeQuery(sql2);
            long bar = generateId();
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
                bar = generateId();
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
            String startdate = reqdate.get(Calendar.YEAR) + "-" + reqdate.get(Calendar.MONTH) + "-" + reqdate.get(Calendar.DATE);
            String enddate = reqdatedur.get(Calendar.YEAR) + "-" + reqdatedur.get(Calendar.MONTH) + "-" + reqdatedur.get(Calendar.DATE);
            PreparedStatement ps = conn2.prepareStatement(sql);
            ps.setString(1, "2");
            ps.setString(2, bId);
            ps.setString(3, days);
            ps.setString(4, cost);
            ps.setString(5, "111");
            ps.setString(6, startdate);
            ps.setString(7, enddate);
            ps.executeUpdate();
            out.println("Success Booking");
            response.sendRedirect("index.jsp");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
