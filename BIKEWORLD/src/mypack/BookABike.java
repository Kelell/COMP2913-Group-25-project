package mypack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.text.ParseException;
import javax.servlet.http.HttpSession;


@WebServlet(name = "BookABike")
public class BookABike extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        jdbc test = new jdbc();
        String driver = "com.mysql.cj.jdbc.Driver";

        String term = request.getParameter("term");
        if (Integer.parseInt(term) == 2)
        {
            String date = request.getParameter("date");
            String duration = request.getParameter("Daydur");
            String location = request.getParameter("Location");
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date ddate = format.parse(date);
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
                Statement stmt = conn.createStatement();
                String sql;
                String sql2;
                sql = "SELECT BIKE_ID, LOCATION, price FROM bike";
                sql2 = "SELECT hire_id, BIKE_ID, START_DATE, END_DATE, Start_Time, End_Time FROM hires";


                ArrayList<Integer> bikes = new ArrayList<Integer>();
                ArrayList<String> loca = new ArrayList<String>();
                ArrayList<Float> cost = new ArrayList<Float>();
                ArrayList<Integer> hires = new ArrayList<Integer>();
                ArrayList<Integer> bikes2 = new ArrayList<Integer>();
                ArrayList<Date>  startd = new ArrayList<Date>();
                ArrayList<Date>  endd = new ArrayList<Date>();
                ArrayList<Time>  startt = new ArrayList<Time>();
                ArrayList<Time>  endt = new ArrayList<Time>();

                ResultSet rs2 = stmt.executeQuery(sql2);
                while(rs2.next()){
                    //Retrieve by column name
                    int h  = rs2.getInt("hire_id");
                    int b  = rs2.getInt("BIKE_ID");
                    Date s = rs2.getDate("START_DATE");
                    Date e = rs2.getDate("END_DATE");
                    Time st = rs2.getTime("Start_Time");
                    Time et = rs2.getTime("End_Time");
                    hires.add(h);
                    bikes2.add(b);
                    startd.add(s);
                    endd.add(e);
                    startt.add(st);
                    endt.add(et);
                }
                rs2.close();

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
                    for (int i = 0; i < booking_size; i++ )
                    {
                        if (id == bikes2.get(i))
                        {
                            Calendar reqdate = Calendar.getInstance();
                            reqdate.setTime(ddate);
                            Calendar reqdatedur = Calendar.getInstance();
                            reqdatedur.setTime(ddate);
                            int reqdur = Integer.parseInt(duration);
                            reqdatedur.add(Calendar.DAY_OF_MONTH, reqdur);
                            Calendar std = Calendar.getInstance();
                            std.setTime(startd.get(i));
                            Calendar edd = Calendar.getInstance();
                            edd.setTime(endd.get(i));
                            if ( reqdate == std)
                            {
                                bookable = false;
                            }
                            else if (reqdate.before(edd) && reqdate.after(std))
                            {
                                bookable = false;
                            }
                            else if (reqdatedur.before(edd) && reqdatedur.after(std))
                            {
                                bookable = false;
                            }
                            else if (reqdate.before(std) && reqdatedur.after(edd))
                            {
                                bookable = false;
                            }
                            else if (reqdate.after(std) && reqdatedur.before(edd))
                            {
                                bookable = false;
                            }
                            if ( reqdate == edd)
                            {
                                bookable =true;
                            }
                        }
                    }
                    if (bookable == true && l.equals(location))
                    {
                        bikes.add(id);
                        loca.add(l);
                        cost.add(price);
                    }



                }
                rs.close();
                stmt.close();
                conn.close();

                int listsize = bikes.size();
                String size = Integer.toString(listsize);

                out.println("<head onload=\"openFunction()\" >" +
                        "<title id = prick >$Title$</title>" +
                        "<link rel=stylesheet href=style.css type=text/css>" +
                        "</head>"
                );
                out.println("<body  >");
                out.println("<div class = \"Title\"> B!KEWORLD </div>");
                out.println(
                        "<div class=nav>"+
                                "<a  href=index.jsp>Home</a>"+
                                "<a class=active href=book>Book A Bike</a>" +
                                "<a href=\"Views\">View bikes</a>" +
                                "<a href=AboutUs.jsp>About Us</a>"+
                                "<a href=ContactUs.jsp>Contact Us</a>"+
                                "<a>Log out</a>" +
                                "</div>"
                );

                for (int i = 0; i < listsize; i++)
                {
                    out.println(
                            "<div class = \"bike\">\n" +
                                    "\t<img src = \"https://www.cahabacycles.com/merchant/189/images/site/chc-rental-img7.jpg\" alt = \"bike\" width = \"390px\" height = \"300px\">\n" +
                                    "    <p>price: "+ cost.get(i) +"</p>\n" +
                                    "    <p>id: "+ bikes.get(i)+"</p>\n" +
                                    "\n" +
                                    "</div>"
                    );
                }

                out.println("</body>");



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
        else
        {
            String date = request.getParameter("date");
            String duration = request.getParameter("Dur");
            String time = request.getParameter("time0");
            try {
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
                Statement stmt = conn.createStatement();
                String sql;
                String sql2;
                sql = "SELECT BIKE_ID, LOCATION, price FROM bike";
                sql2 = "SELECT hire_id, START_DATE, END_DATE, Start_Time, End_Time FROM hires";


                ArrayList<Integer> bikes = new ArrayList<Integer>();
                ArrayList<String> loca = new ArrayList<String>();
                ArrayList<Float> cost = new ArrayList<Float>();
                ArrayList<Integer> hires = new ArrayList<Integer>();
                ArrayList<Date>  startd = new ArrayList<Date>();
                ArrayList<Date>  endd = new ArrayList<Date>();
                ArrayList<Time>  startt = new ArrayList<Time>();
                ArrayList<Time>  endt = new ArrayList<Time>();

                ResultSet rs2 = stmt.executeQuery(sql2);
                while(rs2.next()){
                    //Retrieve by column name
                    int h  = rs2.getInt("hires_id");
                    Date s = rs2.getDate("START_DATE");
                    Date e = rs2.getDate("END_DATE");
                    Time st = rs2.getTime("Start_Time");
                    Time et = rs2.getTime("End_Time");
                    hires.add(h);
                    startd.add(s);
                    endd.add(e);
                    startt.add(st);
                    endt.add(et);
                }
                rs2.close();

                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    //Retrieve by column name
                    int id  = rs.getInt("BIKE_ID");
                    String l = rs.getString("LOCATION");
                    float price = rs.getFloat("price");
                    bikes.add(id);
                    loca.add(l);
                    cost.add(price);
                }
                rs.close();
                stmt.close();
                conn.close();

                int listsize = bikes.size();
                String size = Integer.toString(listsize);

                out.println("<head onload=\"openFunction()\" >" +
                        "<title id = prick >$Title$</title>" +
                        "<link rel=stylesheet href=style.css type=text/css>" +
                        "</head>"
                );
                out.println("<body  >");
                out.println("<div class = \"Title\"> B!KEWORLD </div>");
                out.println(
                        "<div class=nav>"+
                                "<a  href=index.jsp>Home</a>"+
                                "<a class=active href=book>Book A Bike</a>" +
                                "<a href=\"Views\">View bikes</a>" +
                                "<a href=AboutUs.jsp>About Us</a>"+
                                "<a href=ContactUs.jsp>Contact Us</a>"+
                                "<a>Log out</a>" +
                                "</div>"
                );

                for (int i = 0; i < listsize; i++)
                {
                    out.println(
                            "<div class = \"bike\">\n" +
                                    "\t<img src = \"https://www.cahabacycles.com/merchant/189/images/site/chc-rental-img7.jpg\" alt = \"bike\" width = \"390px\" height = \"300px\">\n" +
                                    "    <p>price: "+ cost.get(i) +"</p>\n" +
                                    "    <p>id: "+ bikes.get(i)+"</p>\n" +
                                    "\n" +
                                    "</div>"
                    );
                }

                out.println("</body>");



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                out.println("Testing error 1- Failed");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Testing error 2- Failed");
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        jdbc test = new jdbc();
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5" ,"UhgQalxiVw");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT BIKE_ID, LOCATION, price FROM bike";

            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> bikes = new ArrayList<Integer>();
            ArrayList<String> loca = new ArrayList<String>();
            ArrayList<Float> cost = new ArrayList<Float>();


            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("BIKE_ID");
                String location = rs.getString("LOCATION");
                float price = rs.getFloat("price");
                bikes.add(id);
                loca.add(location);
                cost.add(price);
            }

            rs.close();
            stmt.close();
            conn.close();

            int listsize = bikes.size();
            String size = Integer.toString(listsize);


            HttpSession session = request.getSession(false);//checks for session
            if(session.getAttribute("uname")==null){
                response.sendRedirect("LogIn.jsp");
            }

            out.println("<head onload=\"openFunction()\" >" +
                    "<title id = prick >$Title$</title>" +
                    "<meta name=viewport content=width=device-width, initial-scale=1>"+
                    "<link rel=stylesheet href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>"+ //<!-- Bootstrap style link  -->
                    "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script>"+ //<!-- Drop down button script-->
                    "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>"+  //<!-- Drop down button script-->
                    "<link rel=stylesheet href=style.css type=text/css>"+
                    "</head>"
            );
            out.println("<body  >");

            out.println(
                    "<nav class='navbar navbar-inverse'>"+ //<!-- Bootstrap nav bar -->
                        "<div class='container-fluid'>"+ "<div class='navbar-header'>"+
                            "<a class='navbar-brand' href=index.jsp>B!KEWORLD</a>"+
                        "</div>"+
                            "<ul class='nav navbar-nav'>"+
                                "<li><a href=index.jsp>Home</a></li>"+
                                "<li><a href=AboutUs.jsp>About Us</a></li>"+
                                "<li><a href=ContactUs.jsp>Contact Us</a></li>"+
                            "</ul>"+
                            "<ul class='nav navbar-nav navbar-right'>");

                            if(session.getAttribute("uname")==null){//log out button for when in session


                                out.println(
                            "<li><a href=registration.jsp><span class='glyphicon glyphicon-user'></span> Sign Up</a></li>"+
                            "<li><a href=LogIn.jsp><span class='glyphicon glyphicon-log-in'></span> Login</a></li>"
                                );
                            }




                            if(session.getAttribute("uname")!=null){//log out button for when in session

                                out.println(
                            "<li class='dropdown'>"+
                                "<a class='dropdown-toggle' data-toggle='dropdown' href=#><span class='glyphicon glyphicon-user'></span>Profile <span class='caret'></span></a>"+
                                "<ul class='dropdown-menu'>"+
                                    "<li><strong>User: ${uname}</strong></li>"+
                                    "<li><a href=Profile.jsp>Profile</a></li>"+
                                    "<li><a href=Views>View bikes</a></li>"+
                                    "<li class=active><a href=book>Book a bike</a></li>"+
                                "</ul>"+
                            "</li>"+
                            "<li><a href=Log><span class='glyphicon glyphicon-log-in'></span> LOGOUT</a></li>");

                            }


            out.println(
                            "</ul>"+
                        "</div>"+
                    "</nav>"

            );
            out.println("<div class='content'>"+
                        "<h1>Book A Bike</h1>");

            out.println(

                    "<form id = 'form1' action= 'book' method = 'post' >\n" +
                            "\n" +
                            "\n" +
                            "<p name = 'Location' >Select a Location</p>\n" +
                            "<select name = 'Location' required onclick='myFunction()' >\n" +
                            "<option selected value= 'Please select'>Please select</option>\n" +
                            "<option value='Alnmouth'>Alnmouth</option>\n" +
                            "<option value='Beverly'>Beverly</option>\n" +
                            "<option value='Crystal'>Crystal</option>\n" +
                            "<option value='Hexam'>Hexam</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "<p style='display:none;' name = 'term' >Would you like the bike for short term or long term?</p>\n" +
                            "<select style='display:none;' name = 'term' required onchange='myFunction5()'>\n" +
                            "<option selected value= 0>Please select</option>\n" +
                            "<option value=2>Long term (Discount)</option>\n" +
                            "<option value=1>Short term</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "<p style='display:none;' name = 'Dur' >How long would you like the bike</p>\n" +
                            "<select style='display:none;' name = 'Dur' required onchange='myFunction2()'>\n" +
                            "<option selected value= 0>Please select</option>\n" +
                            "<option value=4>4 hours</option>\n" +
                            "<option value=3>3 hours</option>\n" +
                            "<option value=2>2 hours</option>\n" +
                            "<option value=1>1 hours</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "<p style='display:none;' name = 'Daydur' >How long would you like the bike</p>\n" +
                            "<select style='display:none;' name = 'Daydur' required onchange='myFunction6()'>\n" +
                            "<option selected value= 0>Please select</option>\n" +
                            "<option value=7>1 Week</option>\n" +
                            "<option value=4>4 days</option>\n" +
                            "<option value=3>3 days</option>\n" +
                            "<option value=2>2 days</option>\n" +
                            "<option value=1>1 day</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "<p style='display:none;' name='date' >Enter Date: </p>\n" +
                            "<input onchange='myFunction3()' required style='display:none;'  type='date' name='date'>\n" +
                            "\n" +
                            "<p style='display:none;color:red;' id ='error'> Error buddy</p>\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "<p style='display:none;' name = Time1 >Select a Time to book</p>\n" +
                            "<select onclick ='myFunction4(this)' style='display:none;' name = Time1 required >\n" +
                            "<option value= 0>Please select</option>\n" +
                            "<option value= '08-09' >8 am to 9 am</option>\n" +
                            "<option value= '09-10' >9 am to 10 am</option>\n" +
                            "<option value= '10-11' >10 am to 11 am</option>\n" +
                            "<option value= '11-12' >11 am to 12 pm</option>\n" +
                            "<option value= '12-13' >12 pm to 1 pm</option>\n" +
                            "<option value= '13-14' >1 pm to 2 pm</option>\n" +
                            "<option value= '14-15' >2 pm to 3 pm</option>\n" +
                            "<option value= '15-16' >3 pm to 4 pm</option>\n" +
                            "<option value= '16-17' >4 pm to 5 pm</option>\n" +
                            "<option value= '17-18' >5 pm to 6 pm</option>\n" +
                            "<option value= '18-19' >6 pm to 7 pm</option>\n" +
                            "<option value= '19-20' >7 pm to 8 pm</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "<p style='display:none;' name = Time2 >Select a Time to book</p>\n" +
                            "<select onclick ='myFunction4(this)' style='display:none;' name = Time2 required >\n" +
                            "<option value= 0>Please select</option>\n" +
                            "<option value= '08-10' >8 am to 10 am</option>\n" +
                            "<option value= '09-11' >9 am to 11 am</option>\n" +
                            "<option value= '10-12' >10 am to 12 pm</option>\n" +
                            "<option value= '11-13' >11 am to 1 pm</option>\n" +
                            "<option value= '12-14' >12 pm to 2 pm</option>\n" +
                            "<option value= '13-15' >1 pm to 3 pm</option>\n" +
                            "<option value= '14-16' >2 pm to 4 pm</option>\n" +
                            "<option value= '15-17' >3 pm to 5 pm</option>\n" +
                            "<option value= '16-18' >4 pm to 6 pm</option>\n" +
                            "<option value= '17-19' >5 pm to 7 pm</option>\n" +
                            "<option value= '18-20' >6 pm to 8 pm</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "<p style='display:none;' name = Time3 >Select a Time to book</p>\n" +
                            "<select onclick='myFunction4(this)' style='display:none;' name = Time3 required >\n" +
                            "<option value= 0>Please select</option>\n" +
                            "<option value= '08-11' >8 am to 11 am</option>\n" +
                            "<option value= '09-12' >9 am to 12 pm</option>\n" +
                            "<option value= '10-13' >10 am to 1 pm</option>\n" +
                            "<option value= '11-14' >11 am to 2 pm</option>\n" +
                            "<option value= '12-15' >12 pm to 3 pm</option>\n" +
                            "<option value= '13-16' >1 pm to 4 pm</option>\n" +
                            "<option value= '14-17' >2 pm to 5 pm</option>\n" +
                            "<option value= '15-18' >3 pm to 6 pm</option>\n" +
                            "<option value= '16-19' >4 pm to 7 pm</option>\n" +
                            "<option value= '17-20' >5 pm to 8 pm</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "<p style='display:none;' name = Time4 >Select a Time to book</p>\n" +
                            "<select onclick='myFunction4(this)' style='display:none;' name = Time4 required >\n" +
                            "<option value= 0>Please select</option>\n" +
                            "<option value= '08-12' >8 am to 12 pm</option>\n" +
                            "<option value= '09-13' >9 am to 1 pm</option>\n" +
                            "<option value= '10-14' >10 am to 2 pm</option>\n" +
                            "<option value= '11-15' >11 am to 3 pm</option>\n" +
                            "<option value= '12-16' >12 pm to 4 pm</option>\n" +
                            "<option value= '13-17' >1 pm to 5 pm</option>\n" +
                            "<option value= '14-18' >2 pm to 6 pm</option>\n" +
                            "<option value= '15-19' >3 pm to 7 pm</option>\n" +
                            "<option value= '16-20' >4 pm to 8 pm</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "<p style='display:none;color:red;' id ='error2'> tooo lattee</p>\n" +
                            "\n" +
                            "<p style='display:none;color:red;' id ='error3'> No more bookings today</p>\n" +
                            "\n" +
                            "<select id = tyme style='display:none;' name = time0 required >\n" +
                            "<option value= Time1>Time1</option>\n" +
                            "<option value= Time2>Time2</option>\n" +
                            "<option value= Time3>Time3</option>\n" +
                            "<option value= Time4>Time4</option>\n" +
                            "</select>\n" +
                            "\n" +
                            "\n" +
                            "<br><br>\n" +
                            "<input id = 'submit' style='display:none;' type=submit value= submit >\n" +
                            "</form>\n" +
                            "</div>"+
                            "<script>\n" +
                            "function openFunction() {\n" +
                            "document.getElementById('form1').reset();\n" +
                            "location.reload();\n" +
                            "}\n" +
                            "\n" +
                            "\n" +
                            "function myFunction() {\n" +
                            "var a = document.getElementsByName('Location');\n" +
                            "var b = document.getElementsByName('Dur');\n" +
                            "var c = document.getElementsByName('date');\n" +
                            "var d = document.getElementsByName('Time1');\n" +
                            "var e = document.getElementsByName('Time2');\n" +
                            "var f = document.getElementsByName('Time3');\n" +
                            "var g = document.getElementsByName('Time4');\n" +
                            "var h = document.getElementsByName('term');\n" +
                            "var i = document.getElementsByName('Daydur');\n" +
                            "var j = document.getElementsByName('secondate');\n" +
                            "var w = document.getElementById('error');\n" +
                            "w.style.display = 'none';\n" +
                            "var x = document.getElementById('error2');\n" +
                            "x.style.display = 'none';\n" +
                            "var y = document.getElementById('error3');\n" +
                            "y.style.display = 'none';\n" +
                            "var z = document.getElementById('submit');\n" +
                            "z.style.display = 'none';\n" +
                            "if (a[1].value !=  'Please select'){\n" +
                            "if (h[1].style.display ==  'none'){\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "h[0].style.display = 'block';\n" +
                            "h[1].style.display = 'block';\n" +
                            "i[0].style.display = 'none';\n" +
                            "i[1].style.display = 'none';\n" +
                            "j[0].style.display = 'none';\n" +
                            "j[1].style.display = 'none';\n" +
                            "}\n" +
                            "if (h[1].style.display ==  'block'){\n" +
                            "h[1].value = 0;\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "i[0].style.display = 'none';\n" +
                            "i[1].style.display = 'none';\n" +
                            "j[0].style.display = 'none';\n" +
                            "j[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "if (a[1].value ==  'Please select'){\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "h[0].style.display = 'none';\n" +
                            "h[1].style.display = 'none';\n" +
                            "i[0].style.display = 'none';\n" +
                            "i[1].style.display = 'none';\n" +
                            "j[0].style.display = 'none';\n" +
                            "j[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "\n" +
                            "\n" +
                            "function myFunctionx() {\n" +
                            "var a = document.getElementsByName('Location');\n" +
                            "var b = document.getElementsByName('Dur');\n" +
                            "var c = document.getElementsByName('date');\n" +
                            "var d = document.getElementsByName('Time1');\n" +
                            "var e = document.getElementsByName('Time2');\n" +
                            "var f = document.getElementsByName('Time3');\n" +
                            "var g = document.getElementsByName('Time4');\n" +
                            "var h = document.getElementsByName('term');\n" +
                            "var i = document.getElementsByName('Daydur');\n" +
                            "var j = document.getElementsByName('secondate');\n" +
                            "var w = document.getElementById('error');\n" +
                            "w.style.display = 'none';\n" +
                            "var x = document.getElementById('error2');\n" +
                            "x.style.display = 'none';\n" +
                            "var y = document.getElementById('error3');\n" +
                            "y.style.display = 'none';\n" +
                            "var z = document.getElementById('submit');\n" +
                            "z.style.display = 'none';\n" +
                            "if (a[1].value !=  'Please select'){\n" +
                            "if (b[1].style.display ==  'none'){\n" +
                            "b[0].style.display = 'block';\n" +
                            "b[1].style.display = 'block';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "h[0].style.display = 'none';\n" +
                            "h[1].style.display = 'none';\n" +
                            "i[0].style.display = 'none';\n" +
                            "i[1].style.display = 'none';\n" +
                            "j[0].style.display = 'none';\n" +
                            "j[1].style.display = 'none';\n" +
                            "}\n" +
                            "if (b[1].style.display ==  'block'){\n" +
                            "b[1].value = 0;\n" +
                            "h[0].style.display = 'none';\n" +
                            "h[1].style.display = 'none';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "i[0].style.display = 'none';\n" +
                            "i[1].style.display = 'none';\n" +
                            "j[0].style.display = 'none';\n" +
                            "j[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "if (a[1].value ==  'Please select'){\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "h[0].style.display = 'none';\n" +
                            "h[1].style.display = 'none';\n" +
                            "i[0].style.display = 'none';\n" +
                            "i[1].style.display = 'none';\n" +
                            "j[0].style.display = 'none';\n" +
                            "j[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "\n" +
                            "\n" +
                            "function myFunction2() {\n" +
                            "var a = document.getElementsByName('Location');\n" +
                            "var b = document.getElementsByName('Dur');\n" +
                            "var c = document.getElementsByName('date');\n" +
                            "var d = document.getElementsByName('Time1');\n" +
                            "var e = document.getElementsByName('Time2');\n" +
                            "var f = document.getElementsByName('Time3');\n" +
                            "var g = document.getElementsByName('Time4');\n" +
                            "var j = document.getElementsByName('secondate');\n" +
                            "var w = document.getElementById('error');\n" +
                            "w.style.display = 'none';\n" +
                            "var x = document.getElementById('error2');\n" +
                            "x.style.display = 'none';\n" +
                            "var y = document.getElementById('error3');\n" +
                            "y.style.display = 'none';\n" +
                            "var z = document.getElementById('submit');\n" +
                            "z.style.display = 'none';\n" +
                            "if (b[1].value !=  0){\n" +
                            "if (c[1].style.display ==  'none'){\n" +
                            "c[0].style.display = 'block';\n" +
                            "c[1].style.display = 'block';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "if (c[1].style.display ==  'block'){\n" +
                            "c[1].value = '0000-00-00';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "if (b[1].value ==  0){\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "\n" +
                            "\n" +
                            "function myFunction3() {\n" +
                            "var t = document.getElementsByName('term');\n" +
                            "if (t[1].value == 2)\n" +
                            "{\n" +
                            "var a = document.getElementsByName('Location');\n" +
                            "var b = document.getElementsByName('Dur');\n" +
                            "var c = document.getElementsByName('date');\n" +
                            "var d = document.getElementsByName('Time1');\n" +
                            "var e = document.getElementsByName('Time2');\n" +
                            "var f = document.getElementsByName('Time3');\n" +
                            "var g = document.getElementsByName('Time4');\n" +
                            "var h = document.getElementById('error');\n" +
                            "h.style.display = 'none';\n" +
                            "var x = document.getElementById('error2');\n" +
                            "x.style.display = 'none';\n" +
                            "var y = document.getElementById('error3');\n" +
                            "y.style.display = 'none';\n" +
                            "var z = document.getElementById('submit');\n" +
                            "z.style.display = 'none';\n" +
                            "h.style.display = 'none';\n" +
                            "var i = document.getElementsByName('Daydur');\n" +
                            "var today = new Date();\n" +
                            "var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' +today.getDate();\n" +
                            "var currentyear = parseInt(today.getFullYear());\n" +
                            "var currentmonth = parseInt((today.getMonth() + 1));\n" +
                            "var currentday = parseInt(today.getDate());\n" +
                            "var inputyear = parseInt(c[1].value.substring(0,4));\n" +
                            "var inputmonth = parseInt(c[1].value.substring(5,7));\n" +
                            "var inputdate = parseInt(c[1].value.substring(8,10));\n" +
                            "if ((inputyear >= currentyear) && (inputmonth >= currentmonth) && (inputdate >= currentday)){\n" +
                            "if (c[1].value !=  '0000-00-00'){\n" +
                            "z.style.display = 'block';\n" +
                            "h.style.display = 'none';\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "if (c[1].value == '0000-00-00'){\n" +
                            "z.style.display = 'none';\n" +
                            "h.style.display = 'none';\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "else{\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "c[1].value = '0000-00-00';\n" +
                            "h.style.display = 'block';\n" +
                            "}\n" +
                            "}\n" +
                            "else if (t[1].value == 1)\n" +
                            "{\n" +
                            "var a = document.getElementsByName('Location');\n" +
                            "var b = document.getElementsByName('Dur');\n" +
                            "var c = document.getElementsByName('date');\n" +
                            "var d = document.getElementsByName('Time1');\n" +
                            "var e = document.getElementsByName('Time2');\n" +
                            "var f = document.getElementsByName('Time3');\n" +
                            "var g = document.getElementsByName('Time4');\n" +
                            "var h = document.getElementById('error');\n" +
                            "h.style.display = 'none';\n" +
                            "var x = document.getElementById('error2');\n" +
                            "x.style.display = 'none';\n" +
                            "var y = document.getElementById('error3');\n" +
                            "y.style.display = 'none';\n" +
                            "var z = document.getElementById('submit');\n" +
                            "z.style.display = 'none';\n" +
                            "h.style.display = 'none';\n" +
                            "var today = new Date();\n" +
                            "var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();\n" +
                            "var currentyear = parseInt(today.getFullYear());\n" +
                            "var currentmonth = parseInt((today.getMonth() + 1));\n" +
                            "var currentday = parseInt(today.getDate());\n" +
                            "var inputyear = parseInt(c[1].value.substring(0,4));\n" +
                            "var inputmonth = parseInt(c[1].value.substring(5,7));\n" +
                            "var inputdate = parseInt(c[1].value.substring(8,10));\n" +
                            "if ((inputyear >= currentyear) && (inputmonth >= currentmonth) && (inputdate >= currentday)){\n" +
                            "if (c[1].value !=  '0000-00-00'){\n" +
                            "if (b[1].value == 4){\n" +
                            "if (g[1].style.display == 'none'){\n" +
                            "g[0].style.display = 'block';\n" +
                            "g[1].style.display = 'block';\n" +
                            "g[1].setAttribute('id','path');\n" +
                            "f[1].removeAttribute('id');\n" +
                            "e[1].removeAttribute('id');\n" +
                            "d[1].removeAttribute('id');\n" +
                            "}\n" +
                            "if (g[1].style.display == 'block'){\n" +
                            "g[1].value = 0;\n" +
                            "}\n" +
                            "}\n" +
                            "if (b[1].value == 3){\n" +
                            "if (f[1].style.display == 'none'){\n" +
                            "f[0].style.display = 'block';\n" +
                            "f[1].style.display = 'block';\n" +
                            "g[1].removeAttribute('id');\n" +
                            "f[1].setAttribute('id', 'path');\n" +
                            "e[1].removeAttribute('id');\n" +
                            "d[1].removeAttribute('id');\n" +
                            "}\n" +
                            "if (f[1].style.display == 'block'){\n" +
                            "f[1].value = 0;\n" +
                            "}\n" +
                            "}\n" +
                            "if (b[1].value == 2){\n" +
                            "if (e[1].style.display == 'none'){\n" +
                            "e[0].style.display = 'block';\n" +
                            "e[1].style.display = 'block';\n" +
                            "g[1].removeAttribute('id');\n" +
                            "f[1].removeAttribute('id');\n" +
                            "e[1].setAttribute('id', 'path');\n" +
                            "d[1].removeAttribute('id');\n" +
                            "}\n" +
                            "if (e[1].style.display == 'block'){\n" +
                            "e[1].value = 0;\n" +
                            "}\n" +
                            "}\n" +
                            "if (b[1].value == 1){\n" +
                            "if (d[1].style.display == 'none'){\n" +
                            "d[0].style.display = 'block';\n" +
                            "d[1].style.display = 'block';\n" +
                            "g[1].removeAttribute('id');\n" +
                            "f[1].removeAttribute('id');\n" +
                            "e[1].removeAttribute('id');\n" +
                            "d[1].setAttribute('id', 'path');\n" +
                            "}\n" +
                            "if (d[1].style.display == 'block'){\n" +
                            "d[1].value = 0;\n" +
                            "}\n" +
                            "}\n" +
                            "}\n" +
                            "if (c[1].value == '0000-00-00'){\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "else{\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "c[1].value = '0000-00-00';\n" +
                            "h.style.display = 'block';\n" +
                            "}\n" +
                            "}\n" +
                            "}\n" +
                            "\n" +
                            "\n" +
                            "function myFunction4(obj) {\n" +
                            "var a = document.getElementsByName('Location');\n" +
                            "var b = document.getElementsByName('Dur');\n" +
                            "var c = document.getElementsByName('date');\n" +
                            "var d = document.getElementsByName('Time1');\n" +
                            "var e = document.getElementsByName('Time2');\n" +
                            "var f = document.getElementsByName('Time3');\n" +
                            "var g = document.getElementsByName('Time4');\n" +
                            "var val = document.getElementById('tyme');\n" +
                            "var x = document.getElementById('path');\n" +
                            "var y = document.getElementById('error2');\n" +
                            "var y2 = document.getElementById('error3');\n" +
                            "var z = document.getElementById('submit');\n" +
                            "z.style.display = 'none';\n" +
                            "val.value = obj.name ;\n" +
                            "var btime = parseInt(x.value.substring(0,2));\n" +
                            "var today = new Date();\n" +
                            "var time = today.getHours();\n" +
                            "y.style.display = 'none';\n" +
                            "y2.style.display = 'none';\n" +
                            "var inputdate = parseInt(c[1].value.substring(8,10));\n" +
                            "if (x.value != 0 ){\n" +
                            "if (btime < time + 1 && time < 20 && inputdate === today.getDate()){\n" +
                            "y.style.display = 'block';\n" +
                            "z.style.display = 'none';\n" +
                            "}\n" +
                            "if (time >= 20 && inputdate === today.getDate()){\n" +
                            "y2.style.display = 'block';\n" +
                            "z.style.display = 'none';\n" +
                            "}\n" +
                            "else{\n" +
                            "z.style.display = 'block';\n" +
                            "}\n" +
                            "}\n" +
                            "}\n" +
                            "\n" +
                            "function myFunction5() {\n" +
                            "var a = document.getElementsByName('Location');\n" +
                            "var b = document.getElementsByName('Dur');\n" +
                            "var c = document.getElementsByName('date');\n" +
                            "var d = document.getElementsByName('Time1');\n" +
                            "var e = document.getElementsByName('Time2');\n" +
                            "var f = document.getElementsByName('Time3');\n" +
                            "var g = document.getElementsByName('Time4');\n" +
                            "var h = document.getElementsByName('term');\n" +
                            "var i = document.getElementsByName('Daydur');\n" +
                            "var w = document.getElementById('error');\n" +
                            "w.style.display = 'none';\n" +
                            "var x = document.getElementById('error2');\n" +
                            "x.style.display = 'none';\n" +
                            "var y = document.getElementById('error3');\n" +
                            "y.style.display = 'none';\n" +
                            "var z = document.getElementById('submit');\n" +
                            "z.style.display = 'none';\n" +
                            "if (h[1].value !=  0){\n" +
                            "if (h[1].value == 2){\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "b[1].value = 0;\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "c[1].value = '0000-00-00';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "d[1].value = 0;\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "e[1].value = 0;\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "f[1].value = 0;\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "g[1].value = 0;\n" +
                            "i[0].style.display = 'block';\n" +
                            "i[1].style.display = 'block';\n" +
                            "}\n" +
                            "if (h[1].value == 1){\n" +
                            "i[1].value = 0;\n" +
                            "b[0].style.display = 'block';\n" +
                            "b[1].style.display = 'block';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "i[0].style.display = 'none';\n" +
                            "i[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "if (h[1].value ==  0){\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "i[0].style.display = 'none';\n" +
                            "i[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "\n" +
                            "function myFunction6() {\n" +
                            "var a = document.getElementsByName('Location');\n" +
                            "var b = document.getElementsByName('Dur');\n" +
                            "var c = document.getElementsByName('date');\n" +
                            "var d = document.getElementsByName('Time1');\n" +
                            "var e = document.getElementsByName('Time2');\n" +
                            "var f = document.getElementsByName('Time3');\n" +
                            "var g = document.getElementsByName('Time4');\n" +
                            "var i = document.getElementsByName('Daydur');\n" +
                            "var w = document.getElementById('error');\n" +
                            "w.style.display = 'none';\n" +
                            "var x = document.getElementById('error2');\n" +
                            "x.style.display = 'none';\n" +
                            "var y = document.getElementById('error3');\n" +
                            "y.style.display = 'none';\n" +
                            "var z = document.getElementById('submit');\n" +
                            "z.style.display = 'none';\n" +
                            "if (i[1].value !=  0){\n" +
                            "if (c[1].style.display ==  'none'){\n" +
                            "c[0].style.display = 'block';\n" +
                            "c[1].style.display = 'block';\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "if (c[1].style.display ==  'block'){\n" +
                            "c[1].value = '0000-00-00';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "if (i[1].value ==  0){\n" +
                            "b[0].style.display = 'none';\n" +
                            "b[1].style.display = 'none';\n" +
                            "c[0].style.display = 'none';\n" +
                            "c[1].style.display = 'none';\n" +
                            "d[0].style.display = 'none';\n" +
                            "d[1].style.display = 'none';\n" +
                            "e[0].style.display = 'none';\n" +
                            "e[1].style.display = 'none';\n" +
                            "f[0].style.display = 'none';\n" +
                            "f[1].style.display = 'none';\n" +
                            "g[0].style.display = 'none';\n" +
                            "g[1].style.display = 'none';\n" +
                            "}\n" +
                            "}\n" +
                            "\n" +
                            "</script>\n"


            );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Testing error 1- Failed");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Testing error 2- Failed");
        }
        out.println("</body>");
    }
}
