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
                            //If requested date is before end date and after start date it is not bookable
                            else if (reqdate.before(edd) && reqdate.after(std))
                            {
                                bookable = false;
                            }
                            //If requested end date is before end date and after start date it is not bookable
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
                            //If end date is equal to requested start date it is bookable
                            if ( (reqdate.get(Calendar.MONTH ) == edd.get(Calendar.MONTH)) && (reqdate.get(Calendar.YEAR ) == edd.get(Calendar.YEAR)) && (reqdate.get(Calendar.DAY_OF_MONTH ) == edd.get(Calendar.DAY_OF_MONTH)))
                            {
                                bookable =true;
                            }
                            //If start date is equal to end date then the bike is not bookable at that date
                            if((std.get(Calendar.MONTH ) == edd.get(Calendar.MONTH)) && (std.get(Calendar.YEAR ) == edd.get(Calendar.YEAR)) && (std.get(Calendar.DAY_OF_MONTH ) == edd.get(Calendar.DAY_OF_MONTH)) && (reqdate.get(Calendar.MONTH ) == edd.get(Calendar.MONTH)) && (reqdate.get(Calendar.YEAR ) == edd.get(Calendar.YEAR)) && (reqdate.get(Calendar.DAY_OF_MONTH ) == edd.get(Calendar.DAY_OF_MONTH)))
                            {
                                bookable = false;
                            }
                        }
                    }
                    //If the bike is bookable and the location of the bike is equal to the location parameter from Available bikes add its details to the array lists
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

                //No of bikes
                int listsize = bikes.size();
                //If there are no bike details in array lists
                if (listsize == 0)
                {
                    String size = Integer.toString(listsize);

                    out.println("<head onload=\"openFunction()\" >" +
                            "<meta name=viewport content=width=device-width, initial-scale=1>"+
                            "<link rel=stylesheet href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>"+ //<!-- Bootstrap style link  -->
                            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script>"+ //<!-- Drop down button script-->
                            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>"+  //<!-- Drop down button script-->
                            "<link rel=stylesheet href=style.css type=text/css>"+
                            "<style>.btn {\n" +
                            "  background-color: #f4511e;\n" +
                            "  border: none;\n" +
                            "  color: white;\n" +
                            "  padding: 16px 32px;\n" +
                            "  text-align: center;\n" +
                            "  font-size: 16px;\n" +
                            "  margin: 4px 2px;\n" +
                            "  opacity: 1;\n" +
                            "  transition: 0.3s;\n" +
                            "}" +
                            ".btn:hover {opacity: 0.6}"+
                            "</style>"+

                            "</head>"
                    );
                    out.println("<body  id = 'bod' onload=\"openFunction()\">");
                    out.println(
                            "<nav class='navbar navbar-inverse'>"+ //<!-- Bootstrap nav bar -->
                                    "<div class='container-fluid'>"+ "<div class='navbar-header'>"+

                                    "<button type= 'button' class= 'navbar-toggle' data-toggle='collapse' data-target='#Navigation'>"+ //<!-- enables a responsive nav bar for mobiles -->
                                    "<span class='icon-bar'></span>"+
                                    "<span class='icon-bar'></span>" +
                                    "<span class='icon-bar'></span>" +
                                    "</button>" +

                                    "<a class='navbar-brand' href=index.jsp>B!KEWORLD</a>"+
                                    "</div>"+

                                    "<div class = 'collapse navbar-collapse' id='Navigation'>"+

                                    "<ul class='nav navbar-nav'>"+
                                    "<li><a href=index.jsp>Home</a></li>");

                    if(session.getAttribute("uname")!=null){//log out button for when in session

                        out.println("<li><a href=\"Views\">Book A Bike</a></li>");


                    }

                    out.println(
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
                                        "<li><strong> User: " + session.getAttribute("uname") + "</strong></li>"+
                                        "<li><a href=Profile.jsp>Profile</a></li>"+
                                        "<li><a href=Views>Book A Bike</a></li>"+
                                        "</ul>"+
                                        "</li>"+
                                        "<li><a href=Log><span class='glyphicon glyphicon-log-in'></span> LOGOUT</a></li>");

                    }


                    out.println(
                            "</ul>"+
                                    "</div>"+
                                    "</div>"+
                                    "</nav>"+
                                    "<div class='content'>"

                    );


                    //Print No bikes available
                    out.println(
                            "<br>" +
                                    "<h2>No bikes available for selected times at that location. <br></h2>"+
                                    "<h2>Please try a different time or date.</h2>"
                    );

                    out.println(

                            "<form id = 'form1' action= 'Available' method = 'post' >\n" +
                                    "<input type='text' style = 'display: none;' name='term' value = "+term+">"+
                                    "<br><br>\n" +
                                    "<input type='text' style = 'display: none;' name='bikeids'>"+
                                    "<input type='text' style = 'display: none;' name='Location' value = " + location + ">"+
                                    "<input type='text' style = 'display: none;' name='days' value = "+ duration +">"+
                                    "<input type='text' style = 'display: none;' name='cost'>"+
                                    "<input type='text' style = 'display: none;' name='startd' value = "+ date + ">"+
                                    "<input type='text' style = 'display: none;' name='endd' value = "+ endparameter + ">"+
                                    //Return to last page
                                    "<input id = 'submit' type=submit value = 'Return' >\n" +
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

                    out.println("</div>" + "</body>");
                }
                else
                {
                    String size = Integer.toString(listsize);

                    out.println("<head onload=\"openFunction()\" >" +
                            "<title id = prick >Book A Bike</title>" +
                            "<meta name=viewport content=width=device-width, initial-scale=1>"+
                            "<link rel=stylesheet href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>"+ //<!-- Bootstrap style link  -->
                            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script>"+ //<!-- Drop down button script-->
                            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>"+  //<!-- Drop down button script-->
                            "<link rel=stylesheet href=style.css type=text/css>"+

                            "</head>"
                    );
                    out.println("<body  id = 'bod' onload=\"openFunction()\">");
                    out.println(
                            "<nav class='navbar navbar-inverse'>"+ //<!-- Bootstrap nav bar -->
                                    "<div class='container-fluid'>"+ "<div class='navbar-header'>"+

                                    "<button type= 'button' class= 'navbar-toggle' data-toggle='collapse' data-target='#Navigation'>"+ //<!-- enables a responsive nav bar for mobiles -->
                                    "<span class='icon-bar'></span>"+
                                    "<span class='icon-bar'></span>" +
                                    "<span class='icon-bar'></span>" +
                                    "</button>" +

                                    "<a class='navbar-brand' href=index.jsp>B!KEWORLD</a>"+
                                    "</div>"+

                                    "<div class = 'collapse navbar-collapse' id='Navigation'>"+

                                    "<ul class='nav navbar-nav'>"+
                                    "<li><a href=index.jsp>Home</a></li>");

                    if(session.getAttribute("uname")!=null){//log out button for when in session

                        out.println("<li  class=\"active\" ><a href=\"Views\">Book A Bike</a></li>");


                    }

                    out.println(
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
                                        "<li><strong> User: " + session.getAttribute("uname") + "</strong></li>"+
                                        "<li><a href=Profile.jsp>Profile</a></li>"+
                                        "<li><a href=Views>Book A Bike</a></li>"+
                                        "</ul>"+
                                        "</li>"+
                                        "<li><a href=Log><span class='glyphicon glyphicon-log-in'></span> LOGOUT</a></li>");

                    }


                    out.println(
                            "</ul>"+
                                    "</div>"+
                                    "</div>"+
                                    "</nav>"+
                                    "<div class='content'>"

                    );

                    out.println("<h2>SELECT A BIKE</h2>");

                    //If listsize is greater than 0 all bikes are displayed
                    for (int i = 0; i < listsize; i++)
                    {

                        out.println(
                                "<div onclick = 'myfunction(this)' id = " + bikes.get(i)+ " style = 'border: 1px solid black;' class = 'bike btn'>\n" +
                                        "<img src = 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Mountainbike.jpg/1920px-Mountainbike.jpg' alt = \"bike\" width = \"390px\" height = \"300px\">\n" +
                                        "    <p id = "+ cost.get(i) +">price: "+ cost.get(i) +"</p>\n" +
                                        "    <p>id: "+ bikes.get(i)+"</p>\n" +
                                        "    <p>stat: Available </p>\n");
                        out.println(
                                "\n" +
                                        "</div>"
                        );
                    }


                    out.println(

                            "<form id = 'form1' action= 'Checkout.jsp' method = 'post' >\n" +
                                    "<input type='text' style = 'display: none;' name='term' value = "+term+">"+
                                    "<br><br>\n" +
                                    //Hidden variables to store data
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
                                    //cost calculation
                                    "var b = document.getElementsByName('cost');\n" +
                                    "var num = obj.children[1].id * "+ duration+" ; "+
                                    "b[0].value =  (obj.children[1].id * "+ duration+" * 12)  ; "+

                                    "document.getElementById('submit').style.display = 'block';"+
                                    "}\n" +
                                    "</script>"
                    );

                    out.println("</div>" + "</body>");
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





        ////Short term case
        else
        {
            String date = request.getParameter("date");
            String duration = request.getParameter("Dur");
            String location = request.getParameter("Location");
            String timelabel = request.getParameter("time0");
            String time = request.getParameter(timelabel);
            //This section deals with time as oppsed to just dates
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

                //Array lists to stor temporary database values
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
                //If statements for deciding which bikes are bookable
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
                //If there are no bikes
                if (listsize == 0)
                {
                    String size = Integer.toString(listsize);

                    out.println("<head onload=\"openFunction()\" >" +
                            "<title id = prick >Book A Bike</title>" +
                            "<meta name=viewport content=width=device-width, initial-scale=1>"+
                            "<link rel=stylesheet href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>"+ //<!-- Bootstrap style link  -->
                            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script>"+ //<!-- Drop down button script-->
                            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>"+  //<!-- Drop down button script-->
                            "<link rel=stylesheet href=style.css type=text/css>"+

                            "</head>"
                    );
                    out.println("<body  id = 'bod' onload=\"openFunction()\">");
                    out.println(
                            "<nav class='navbar navbar-inverse'>"+ //<!-- Bootstrap nav bar -->
                                    "<div class='container-fluid'>"+ "<div class='navbar-header'>"+

                                    "<button type= 'button' class= 'navbar-toggle' data-toggle='collapse' data-target='#Navigation'>"+ //<!-- enables a responsive nav bar for mobiles -->
                                    "<span class='icon-bar'></span>"+
                                    "<span class='icon-bar'></span>" +
                                    "<span class='icon-bar'></span>" +
                                    "</button>" +

                                    "<a class='navbar-brand' href=index.jsp>B!KEWORLD</a>"+
                                    "</div>"+

                                    "<div class = 'collapse navbar-collapse' id='Navigation'>"+

                                    "<ul class='nav navbar-nav'>"+
                                    "<li><a href=index.jsp>Home</a></li>");

                    if(session.getAttribute("uname")!=null){//log out button for when in session

                        out.println("<li><a href=\"Views\">Book A Bike</a></li>");


                    }

                    out.println(
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
                                        "<li><strong> User: " + session.getAttribute("uname") + "</strong></li>"+
                                        "<li><a href=Profile.jsp>Profile</a></li>"+
                                        "<li><a href=Views>Book A Bike</a></li>"+
                                        "</ul>"+
                                        "</li>"+
                                        "<li><a href=Log><span class='glyphicon glyphicon-log-in'></span> LOGOUT</a></li>");

                    }


                    out.println(
                            "</ul>"+
                                    "</div>"+
                                    "</div>"+
                                    "</nav>"+
                                    "<div class='content'>"

                    );


                    out.println(
                            "<br>" +
                                    "<h2>No bikes available for selected times at that location. <br></h2>"+
                                    "<h2>Please try a different time or date.</h2>"
                    );


                    out.println(

                            "<form id = 'form1' action= 'Available' method = 'post' >\n" +
                                    "<input type='text'  style = 'display: none;' name='term' value = "+term+">"+
                                    "<br><br>\n" +
                                    "<input type='text' style = 'display: none;' name='bikeids'>"+
                                    "<input type='text' style = 'display: none;' name='Location' value = " + location + ">"+
                                    "<input type='text' style = 'display: none;' name='hours' value = "+ duration +">"+
                                    "<input type='text' style = 'display: none;' name='cost'>"+
                                    "<input type='text' style = 'display: none;' name='startt' value = "+ starttime + ">"+
                                    "<input type='text' style = 'display: none;' name='endt' value = "+ endtime + ">"+
                                    "<input type='text' style = 'display: none;' name='theday' value = "+ date + ">"+
                                    "<input id = 'submit' type=submit value= 'Return' >\n" +
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

                    out.println("</div>" + "</body>");
                }
                else
                {
                    String size = Integer.toString(listsize);

                    out.println("<head onload=\"openFunction()\" >" +
                            "<title id = prick >Book A Bike</title>" +
                            "<meta name=viewport content=width=device-width, initial-scale=1>"+
                            "<link rel=stylesheet href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>"+ //<!-- Bootstrap style link  -->
                            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script>"+ //<!-- Drop down button script-->
                            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>"+  //<!-- Drop down button script-->
                            "<link rel=stylesheet href=style.css type=text/css>"+

                            "</head>"
                    );
                    out.println("<body  id = 'bod' onload=\"openFunction()\">");
                    out.println(
                            "<nav class='navbar navbar-inverse'>"+ //<!-- Bootstrap nav bar -->
                                    "<div class='container-fluid'>"+ "<div class='navbar-header'>"+

                                    "<button type= 'button' class= 'navbar-toggle' data-toggle='collapse' data-target='#Navigation'>"+ //<!-- enables a responsive nav bar for mobiles -->
                                    "<span class='icon-bar'></span>"+
                                    "<span class='icon-bar'></span>" +
                                    "<span class='icon-bar'></span>" +
                                    "</button>" +

                                    "<a class='navbar-brand' href=index.jsp>B!KEWORLD</a>"+
                                    "</div>"+

                                    "<div class = 'collapse navbar-collapse' id='Navigation'>"+

                                    "<ul class='nav navbar-nav'>"+
                                    "<li><a href=index.jsp>Home</a></li>");

                    if(session.getAttribute("uname")!=null){//log out button for when in session

                        out.println("<li  class=\"active\" ><a href=\"Views\">Book A Bike</a></li>");


                    }

                    out.println(
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
                                        "<li><strong> User: " + session.getAttribute("uname") + "</strong></li>"+
                                        "<li><a href=Profile.jsp>Profile</a></li>"+
                                        "<li><a href=Views>Book A Bike</a></li>"+
                                        "</ul>"+
                                        "</li>"+
                                        "<li><a href=Log><span class='glyphicon glyphicon-log-in'></span> LOGOUT</a></li>");

                    }


                    out.println(
                            "</ul>"+
                                    "</div>"+
                                    "</div>"+
                                    "</nav>"+
                                    "<div class='content'>"


                    );

                    out.println("<h2>SELECT A BIKE</h2>");

                    for (int i = 0; i < listsize; i++)
                    {
                        out.println(
                                "<div onclick = 'myfunction(this)' id = " + bikes.get(i)+ " style = 'border: 1px solid black;' class = 'bike btn'>\n" +
                                        "<img src = 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Mountainbike.jpg/1920px-Mountainbike.jpg' alt = \"bike\" width = \"390px\" height = \"300px\">\n" +
                                        "    <p id = "+ cost.get(i) +">price: "+ cost.get(i) +"</p>\n" +
                                        "    <p>id: "+ bikes.get(i)+"</p>\n" +
                                        "    <p>stat: Available </p>\n" +
                                "</div>");
                    }


                    out.println(



                            "<form id = 'form1' action= 'Checkout.jsp' method = 'post' >\n" +
                                    "<input type='text'  style = 'display: none;' name='term' value = "+term+">"+
                                    "<br><br>\n" +
                                    "<input type='text' style = 'display: none;' name='bikeids'>"+
                                    "<input type='text' style = 'display: none;' name='location' value = " + location + ">"+
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

                    out.println("</div>" + "</body>");
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

        //Redirects to index if session is expired and to Views page if this page is refreshed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        jdbc test = new jdbc();
        String driver = "com.mysql.cj.jdbc.Driver";
        String term = request.getParameter("term");
        String location = request.getParameter("Location");

        HttpSession session =  request.getSession(false);
        //Checks to see if session is still active by seeing if session Attribute is false
        //Session obtained through getSession
        out.println("<input type='text' style = 'display: none;' name='location' value = " + location + ">");
        try {

            //If session attribute is false then the session is false. Therefore you are redirected to index.jsp page
            if (session.getAttribute("uname") == null) {
                response.sendRedirect("index.jsp");
            }
        } catch (NullPointerException name) {
            response.sendRedirect("index.jsp");
        }
        response.sendRedirect("Available");

    }

}
