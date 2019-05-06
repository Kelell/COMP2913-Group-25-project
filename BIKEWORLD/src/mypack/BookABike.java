package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.text.ParseException;


@WebServlet(name = "BookABike")
public class BookABike extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession session =  request.getSession(false);
        if (session.getAttribute("uName") == null) {
            response.sendRedirect("index.jsp");
        }


        //Settinng up print writter to write to hml page and sql connection
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        jdbc test = new jdbc();
        String driver = "com.mysql.cj.jdbc.Driver";
        String term = request.getParameter("term");
        //If long term is chosen
        String endparameter = "";
        if (Integer.parseInt(term) == 2)
        {
            //Get selected date duration and location
            String date = request.getParameter("date");
            String duration = request.getParameter("Daydur");
            String location = request.getParameter("Location");
            try {
                //Format date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date ddate = format.parse(date);
                //SQL connection and statments made
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
                Statement stmt = conn.createStatement();
                String sql;
                String sql2;
                String sql3;
                sql = "SELECT BIKE_ID, LOCATION, price, STATUS FROM bike";
                sql2 = "SELECT hire_id, BIKE_ID, START_DATE, END_DATE FROM hires";
                sql3 = "SELECT Hire_Id, Bike_Id, Start_Time, End_Time, Date FROM Short_Hires";

                //Array lists created
                ArrayList<Integer> bikes = new ArrayList<Integer>();
                ArrayList<String> loca = new ArrayList<String>();
                ArrayList<Float> cost = new ArrayList<Float>();
                ArrayList<Integer> status = new ArrayList<Integer>();
                ArrayList<Integer> hires = new ArrayList<Integer>();
                ArrayList<Integer> bikes2 = new ArrayList<Integer>();
                ArrayList<Date>  startd = new ArrayList<Date>();
                ArrayList<Date>  endd = new ArrayList<Date>();
                //SQL statement used to add all the bikes from hire table into array lists
                ResultSet rs2 = stmt.executeQuery(sql2);
                while(rs2.next()){
                    //Retrieve by column name
                    int h  = rs2.getInt("hire_id");
                    int b  = rs2.getInt("BIKE_ID");
                    Date s = rs2.getDate("START_DATE");
                    Date e = rs2.getDate("END_DATE");
                    hires.add(h);
                    bikes2.add(b);
                    startd.add(s);
                    endd.add(e);
                }
                rs2.close();

                ResultSet rs3 = stmt.executeQuery(sql3);
                while(rs3.next()){
                    //Retrieve by column name
                    int h  = rs3.getInt("hire_id");
                    int b  = rs3.getInt("Bike_Id");
                    Date s = rs3.getDate("Date");
                    Date e = rs3.getDate("Date");
                    hires.add(h);
                    bikes2.add(b);
                    startd.add(s);
                    endd.add(e);
                }
                rs3.close();


                //If statement to check if bikes are in hires table
                int booking_size =  bikes2.size();
                ResultSet rs = stmt.executeQuery(sql);
                boolean bookable = true;
                List<Integer> count = new ArrayList<Integer>();
                //While there is another resultant set line
                while(rs.next()){
                    //Whether this row/record is bookable is true
                    bookable = true;
                    //Retrieve record data by column name
                    int id  = rs.getInt("BIKE_ID");
                    String l = rs.getString("LOCATION");
                    float price = rs.getFloat("price");
                    int stat = rs.getInt("STATUS");
                    //For each element in the hires table
                    for (int i = 0; i < booking_size; i++ )
                    {
                        //If id of resultant set record is equal to an id in hires table. I.e If its already been booked before
                        if (id == bikes2.get(i))
                        {
                            //Set the requested date (Date user wants) to reqdate
                            Calendar reqdate = Calendar.getInstance();
                            reqdate.setTime(ddate);
                            //Set the requested end date. (Date user wants + duration) to reqdate
                            Calendar reqend = Calendar.getInstance();
                            reqend.setTime(ddate);
                            int dur = Integer.parseInt(duration);
                            reqend.add(Calendar.DAY_OF_MONTH, dur);
                            //Set start date and end date of bike in hires table
                            Calendar std = Calendar.getInstance();
                            std.setTime(startd.get(i));
                            Calendar edd = Calendar.getInstance();
                            edd.setTime(endd.get(i));
                            endparameter = reqend.get(Calendar.YEAR) + "-" +  (reqend.get(Calendar.MONTH) +1) + "-" + reqend.get(Calendar.DAY_OF_MONTH);
                            //If requested date is equal to start date of already existing hire on same bike
                            if ( (reqdate.get(Calendar.MONTH ) == std.get(Calendar.MONTH)) && (reqdate.get(Calendar.YEAR ) == std.get(Calendar.YEAR)) && (reqdate.get(Calendar.DAY_OF_MONTH ) == std.get(Calendar.DAY_OF_MONTH)))
                            {
                                //Cannot be booked
                                bookable = false;
                            }
                            else if (reqdate.before(edd) && reqdate.after(std))
                            {
                                bookable = false;
                            }
                            else if (reqend.before(edd) && reqend.after(std))
                            {
                                bookable = false;
                            }
                            else if (reqdate.before(std) && reqend.after(edd))
                            {
                                bookable = false;
                            }
                            else if (reqdate.after(std) && reqend.before(edd))
                            {
                                bookable = false;
                            }
                            if ( (reqdate.get(Calendar.MONTH ) == edd.get(Calendar.MONTH)) && (reqdate.get(Calendar.YEAR ) == edd.get(Calendar.YEAR)) && (reqdate.get(Calendar.DAY_OF_MONTH ) == edd.get(Calendar.DAY_OF_MONTH)))
                            {
                                bookable =true;
                            }
                            if((std.get(Calendar.MONTH ) == edd.get(Calendar.MONTH)) && (std.get(Calendar.YEAR ) == edd.get(Calendar.YEAR)) && (std.get(Calendar.DAY_OF_MONTH ) == edd.get(Calendar.DAY_OF_MONTH)) && (reqdate.get(Calendar.MONTH ) == edd.get(Calendar.MONTH)) && (reqdate.get(Calendar.YEAR ) == edd.get(Calendar.YEAR)) && (reqdate.get(Calendar.DAY_OF_MONTH ) == edd.get(Calendar.DAY_OF_MONTH)))
                            {
                                bookable = false;
                            }
                        }
                    }

                    if (bookable == true && l.equals(location))
                    {
                        bikes.add(id);
                        loca.add(l);
                        cost.add(price);
                        status.add(stat);

                    }



                }
                rs.close();
                stmt.close();
                conn.close();

                int listsize = bikes.size();
                if (listsize == 0)
                {
                    String size = Integer.toString(listsize);

                    out.println("<head onload=\"openFunction()\" >" +
                            "<title id = prick >$Title$</title>" +
                            "<link rel=stylesheet href=style.css type=text/css>" +
                            "</head>"
                    );
                    out.println("<body  id = 'bod' onload=\"openFunction()\">");
                    out.println("<div class = \"Title\"> B!KEWORLD </div>");
                    out.println(
                            "<div class=nav>"+
                                    "<a href=\"Dashboard\">Home</a>"+
                                    "<a class=active href=\"Views\">View bikes</a>" +
                                    "<a href=AboutUs.jsp>About Us</a>"+
                                    "<a href=ContactUs.jsp>Contact Us</a>"+
                                    "<a href=\"Logout.jsp\">Log out</a>"+
                                    "</div>"
                    );


                    out.println(
                            "<h1>NO BIKES AVAILABLE</h1>"
                    );

                    out.println(

                            "<form id = 'form1' action= 'Checkout.jsp' method = 'post' >\n" +
                                    "<input type='text' style = 'display: none;' name='term' value = "+term+">"+
                                    "<br><br>\n" +
                                    "<input type='text' style = 'display: none;' name='bikeids'>"+
                                    "<input type='text' style = 'display: none;' name='location' value = " + location + ">"+
                                    "<input type='text' style = 'display: none;' name='days' value = "+ duration +">"+
                                    "<input type='text' style = 'display: none;' name='cost'>"+
                                    "<input type='text' style = 'display: none;' name='startd' value = "+ date + ">"+
                                    "<input type='text' style = 'display: none;' name='endd' value = "+ endparameter + ">"+
                                    "<input id = 'submit' style = 'display: none;' type=submit value= 'Book' >\n" +
                                    "</form>\n" +
                                    "<script>\n" +
                                    "function openFunction() {\n" +
                                    "document.getElementById('form2').reset();\n" +
                                    "document.getElementById('bod').reset();\n" +
                                    "location.reload();\n" +
                                    "}\n" +
                                    "\n" +
                                    "function myfunction(obj) {\n" +
                                    //bike id
                                    "var a = document.getElementsByName('bikeids');\n" +
                                    "a[0].value = obj.id;"+
                                    //cost
                                    "var b = document.getElementsByName('cost');\n" +
                                    "var num = obj.children[1].id * "+ duration+" ; "+
                                    "b[0].value =  (obj.children[1].id * "+ duration+" * 12)  ; "+

                                    "document.getElementById('submit').style.display = 'block';"+
                                    "}\n" +
                                    "</script>"
                    );

                    out.println("</body>");
                }
                else
                {
                    String size = Integer.toString(listsize);

                    out.println("<head onload=\"openFunction()\" >" +
                            "<title id = prick >$Title$</title>" +
                            "<link rel=stylesheet href=style.css type=text/css>" +
                            "</head>"
                    );
                    out.println("<body  id = 'bod' onload=\"openFunction()\">");
                    out.println("<div class = \"Title\"> B!KEWORLD </div>");
                    out.println(
                            "<div class=nav>"+
                                    "<a href=\"Dashboard\">Home</a>"+
                                    "<a class=active href=\"Views\">View bikes</a>" +
                                    "<a href=AboutUs.jsp>About Us</a>"+
                                    "<a href=ContactUs.jsp>Contact Us</a>"+
                                    "<a href=\"Logout.jsp\">Log out</a>"+
                                    "</div>"
                    );

                    for (int i = 0; i < listsize; i++)
                    {

                        out.println(
                                "<div onclick = 'myfunction(this)' id = " + bikes.get(i)+ " class = \"bike\">\n" +
                                        "<img src = \"https://www.cahabacycles.com/merchant/189/images/site/chc-rental-img7.jpg\" alt = \"bike\" width = \"390px\" height = \"300px\">\n" +
                                        "    <p id = "+ cost.get(i) +">price: "+ cost.get(i) +"</p>\n" +
                                        "    <p>id: "+ bikes.get(i)+"</p>\n" +
                                        "    <p class = "+ status.get(i)+ ">stat: Available </p>\n");
                        out.println(
                                "\n" +
                                        "</div>"
                        );
                    }


                    out.println(

                            "<form id = 'form1' action= 'Checkout.jsp' method = 'post' >\n" +
                                    "<input type='text' style = 'display: none;' name='term' value = "+term+">"+
                                    "<br><br>\n" +
                                    "<input type='text' style = 'display: none;' name='bikeids'>"+
                                    "<input type='text' style = 'display: none;' name='location' value = " + location + ">"+
                                    "<input type='text' style = 'display: none;' name='days' value = "+ duration +">"+
                                    "<input type='text' style = 'display: none;' name='cost'>"+
                                    "<input type='text' style = 'display: none;' name='startd' value = "+ date + ">"+
                                    "<input type='text' style = 'display: none;' name='endd' value = "+ endparameter + ">"+
                                    "<input id = 'submit' style = 'display: none;' type=submit value= 'Book' >\n" +
                                    "</form>\n" +
                                    "<script>\n" +
                                    "function openFunction() {\n" +
                                    "document.getElementById('form2').reset();\n" +
                                    "document.getElementById('bod').reset();\n" +
                                    "location.reload();\n" +
                                    "}\n" +
                                    "\n" +
                                    "function myfunction(obj) {\n" +
                                    //bike id
                                    "var a = document.getElementsByName('bikeids');\n" +
                                    "a[0].value = obj.id;"+
                                    //cost
                                    "var b = document.getElementsByName('cost');\n" +
                                    "var num = obj.children[1].id * "+ duration+" ; "+
                                    "b[0].value =  (obj.children[1].id * "+ duration+" * 12)  ; "+

                                    "document.getElementById('submit').style.display = 'block';"+
                                    "}\n" +
                                    "</script>"
                    );

                    out.println("</body>");
                }




            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                out.println("Testing error 1- Failed");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Testing error 2- Failed");
            }  catch (ParseException e){
                e.printStackTrace();
            }
        }





        ////Short termmmm
        else
        {
            String date = request.getParameter("date");
            String duration = request.getParameter("Dur");
            String location = request.getParameter("Location");
            String timelabel = request.getParameter("time0");
            String time = request.getParameter(timelabel);
            int starttime = Integer.parseInt(time.substring(0,2));
            int endtime = Integer.parseInt(time.substring(3,5));

            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date ddate = format.parse(date);
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
                Statement stmt = conn.createStatement();
                String sql;
                String sql2;
                String sql3;
                sql = "SELECT BIKE_ID, LOCATION, price, STATUS FROM bike";
                sql2 = "SELECT Hire_Id, Bike_Id, Start_Time, End_Time, Date FROM Short_Hires";
                sql3 = "SELECT hire_id, BIKE_ID, START_DATE, END_DATE FROM hires";


                ArrayList<Integer> bikes = new ArrayList<Integer>();
                ArrayList<String> loca = new ArrayList<String>();
                ArrayList<Float> cost = new ArrayList<Float>();
                ArrayList<Integer> status = new ArrayList<Integer>();
                ArrayList<Integer> hires = new ArrayList<Integer>();
                ArrayList<Integer> bikes2 = new ArrayList<Integer>();
                ArrayList<Integer>  startt = new ArrayList<Integer>();
                ArrayList<Integer>  endt = new ArrayList<Integer>();
                ArrayList<Date>  startd = new ArrayList<Date>();
                ArrayList<Date>  endd = new ArrayList<Date>();

                ResultSet rs2 = stmt.executeQuery(sql2);
                while(rs2.next()){
                    //Retrieve by column name
                    int h  = rs2.getInt("Hire_Id");
                    int b  = rs2.getInt("Bike_Id");
                    int s = rs2.getInt("Start_Time");
                    int e = rs2.getInt("End_Time");
                    Date d = rs2.getDate("Date");
                    hires.add(h);
                    bikes2.add(b);
                    startt.add(s);
                    endt.add(e);
                    startd.add(d);
                    endd.add(d);
                }
                rs2.close();

                int timelength = bikes2.size();

                ResultSet rs3 = stmt.executeQuery(sql3);
                while(rs3.next()){
                    //Retrieve by column name
                    int h  = rs3.getInt("hire_id");
                    int b  = rs3.getInt("BIKE_ID");
                    Date s = rs3.getDate("START_DATE");
                    Date e = rs3.getDate("END_DATE");
                    hires.add(h);
                    bikes2.add(b);
                    startd.add(s);
                    endd.add(e);
                }
                rs3.close();

                int booking_size =  bikes2.size();
                ResultSet rs = stmt.executeQuery(sql);
                boolean bookable = true;
                List<Integer> count = new ArrayList<Integer>();
                while(rs.next()){
                    bookable = true;
                    //Retrieve by column name
                    int id  = rs.getInt("BIKE_ID");
                    String l = rs.getString("LOCATION");
                    float price = rs.getFloat("price");
                    int stat = rs.getInt("STATUS");
                    for (int i = 0; i < booking_size; i++ )
                    {
                        Calendar std = Calendar.getInstance();
                        std.setTime(startd.get(i));
                        Calendar edd = Calendar.getInstance();
                        edd.setTime(endd.get(i));
                        Calendar reqdate = Calendar.getInstance();
                        reqdate.setTime(ddate);
                        if (id == bikes2.get(i))
                        {
                            if (i < timelength)
                            {
                                int reqtime = starttime;
                                int reqtimedur = endtime;
                                int stt = startt.get(i);
                                int edt = endt.get(i);
                                if (ddate == startd.get(i))
                                {
                                    if ( reqtime == stt)
                                    {
                                        bookable = false;
                                    }
                                    else if (reqtime < edt && reqtime > stt)
                                    {
                                        bookable = false;
                                    }
                                    else if (reqtimedur < edt && reqtimedur > stt)
                                    {
                                        bookable = false;
                                    }
                                    else if (reqtime < stt && reqtimedur > edt )
                                    {
                                        bookable = false;
                                    }
                                    else if (reqtime > stt && reqtimedur < edt)
                                    {
                                        bookable = false;
                                    }
                                    if ( reqtime == edt )
                                    {
                                        bookable =true;
                                    }
                                }
                            }
                            else {

                                if (reqdate.before(edd) && reqdate.after(std))
                                {
                                    bookable = false;
                                }
                                if ((reqdate.get(Calendar.MONTH ) == std.get(Calendar.MONTH)) && (reqdate.get(Calendar.YEAR ) == std.get(Calendar.YEAR)) && (reqdate.get(Calendar.DAY_OF_MONTH ) == std.get(Calendar.DAY_OF_MONTH)))
                                {

                                    bookable = false;
                                }
                                if ((reqdate.get(Calendar.MONTH ) == edd.get(Calendar.MONTH)) && (reqdate.get(Calendar.YEAR ) == edd.get(Calendar.YEAR)) && (reqdate.get(Calendar.DAY_OF_MONTH ) == edd.get(Calendar.DAY_OF_MONTH)))
                                {
                                    bookable = true;
                                }
                            }


                        }
                    }
                    if (bookable == true && l.equals(location))
                    {
                        bikes.add(id);
                        loca.add(l);
                        cost.add(price);
                        status.add(stat);

                    }



                }
                rs.close();
                stmt.close();
                conn.close();

                int listsize = bikes.size();
                if (listsize == 0)
                {
                    String size = Integer.toString(listsize);

                    out.println("<head onload=\"openFunction()\" >" +
                            "<title id = prick >$Title$</title>" +
                            "<link rel=stylesheet href=style.css type=text/css>" +
                            "</head>"
                    );
                    out.println("<body  id = 'bod' onload=\"openFunction()\">");
                    out.println("<div class = \"Title\"> B!KEWORLD </div>");
                    out.println(
                            "<div class=nav>"+
                                    "<a href=\"Dashboard\">Home</a>"+
                                    "<a class=active href=\"Views\">View bikes</a>" +
                                    "<a href=AboutUs.jsp>About Us</a>"+
                                    "<a href=ContactUs.jsp>Contact Us</a>"+
                                    "<a href=\"Logout.jsp\">Log out</a>"+
                                    "</div>"
                    );

                    out.println( "<h1>NO BIKES AVAILABLE</h1>");


                    out.println(

                            "<form id = 'form1' action= 'Checkout.jsp' method = 'post' >\n" +
                                    "<input type='text'  style = 'display: none;' name='term' value = "+term+">"+
                                    "<br><br>\n" +
                                    "<input type='text' style = 'display: none;' name='bikeids'>"+
                                    "<input type='text' style = 'display: none;' name='location' value = " + loca.get(0) + ">"+
                                    "<input type='text' style = 'display: none;' name='hours' value = "+ duration +">"+
                                    "<input type='text' style = 'display: none;' name='cost'>"+
                                    "<input type='text' style = 'display: none;' name='startt' value = "+ starttime + ">"+
                                    "<input type='text' style = 'display: none;' name='endt' value = "+ endtime + ">"+
                                    "<input type='text' style = 'display: none;' name='theday' value = "+ date + ">"+
                                    "<input id = 'submit' style = 'display: none;' type=submit value= 'Book' >\n" +
                                    "</form>\n" +
                                    "<script>\n" +
                                    "function openFunction() {\n" +
                                    "document.getElementById('form2').reset();\n" +
                                    "document.getElementById('bod').reset();\n" +
                                    "location.reload();\n" +
                                    "}\n" +
                                    "\n" +
                                    "function myfunction(obj) {\n" +
                                    //bike id
                                    "var a = document.getElementsByName('bikeids');\n" +
                                    "a[0].value = obj.id;"+
                                    //cost
                                    "var b = document.getElementsByName('cost');\n" +
                                    "var num = obj.children[1].id * "+ duration+" ; "+
                                    "b[0].value =  (obj.children[1].id * "+ duration+");"+

                                    "document.getElementById('submit').style.display = 'block';"+
                                    "}\n" +
                                    "</script>"
                    );

                    out.println("</body>");
                }
                else
                {
                    String size = Integer.toString(listsize);

                    out.println("<head onload=\"openFunction()\" >" +
                            "<title id = prick >$Title$</title>" +
                            "<link rel=stylesheet href=style.css type=text/css>" +
                            "</head>"
                    );
                    out.println("<body  id = 'bod' onload=\"openFunction()\">");
                    out.println("<div class = \"Title\"> B!KEWORLD </div>");
                    out.println(
                            "<div class=nav>"+
                                    "<a  href=index.jsp>Home</a>"+
                                    "<a  class=active href=\"Views\">View bikes</a>" +
                                    "<a href=AboutUs.jsp>About Us</a>"+
                                    "<a href=ContactUs.jsp>Contact Us</a>"+
                                    "<a href=\"Logout.jsp\">Log out</a>"+
                                    "</div>"
                    );

                    for (int i = 0; i < listsize; i++)
                    {
                        out.println(
                                "<div onclick = 'myfunction(this)' id = " + bikes.get(i)+ " class = \"bike\">\n" +
                                        "<img src = \"https://www.cahabacycles.com/merchant/189/images/site/chc-rental-img7.jpg\" alt = \"bike\" width = \"390px\" height = \"300px\">\n" +
                                        "    <p id = "+ cost.get(i) +">price: "+ cost.get(i) +"</p>\n" +
                                        "    <p>id: "+ bikes.get(i)+"</p>\n" +
                                        "    <p class = "+ status.get(i)+ ">stat: Available </p>\n" +
                                "</div>");
                    }


                    out.println(

                            "<form id = 'form1' action= 'complete' method = 'post' >\n" +
                                    "<input type='text'  style = 'display: none;' name='term' value = "+term+">"+
                                    "<br><br>\n" +
                                    "<input type='text' style = 'display: none;' name='bikeids'>"+
                                    "<input type='text' style = 'display: none;' name='location' value = " + loca.get(0) + ">"+
                                    "<input type='text' style = 'display: none;' name='hours' value = "+ duration +">"+
                                    "<input type='text' style = 'display: none;' name='cost'>"+
                                    "<input type='text' style = 'display: none;' name='startt' value = "+ starttime + ">"+
                                    "<input type='text' style = 'display: none;' name='endt' value = "+ endtime + ">"+
                                    "<input type='text' style = 'display: none;' name='theday' value = "+ date + ">"+
                                    "<input id = 'submit' style = 'display: none;' type=submit value= 'Book' >\n" +
                                    "</form>\n" +
                                    "<script>\n" +
                                    "function openFunction() {\n" +
                                    "document.getElementById('form1').reset();\n" +
                                    "document.getElementById('bod').reset();\n" +
                                    "location.reload();\n" +
                                    "}\n" +
                                    "\n" +
                                    "function myfunction(obj) {\n" +
                                    //bike id
                                    "var a = document.getElementsByName('bikeids');\n" +
                                    "a[0].value = obj.id;"+
                                    //cost
                                    "var b = document.getElementsByName('cost');\n" +
                                    "var num = obj.children[1].id * "+ duration+" ; "+
                                    "b[0].value =  (obj.children[1].id * "+ duration+");"+

                                    "document.getElementById('submit').style.display = 'block';"+
                                    "}\n" +
                                    "</script>"
                    );

                    out.println("</body>");
                }





            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                out.println("Testing error 1- Failed");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Testing error 2- Failed");
            }  catch (ParseException e){
                e.printStackTrace();
            }
        }


    }




    /////////////////////////GET

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
