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
import java.util.ArrayList;

@WebServlet(name = "Check")
public class Check extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession(false);
                //Checks to see if session is still active by seeing if session Attribute is false
                //Session obtained through getSession
        //If session attribute is false then the session is false. Therefore you are redirected to index.jsp page
        if (session.getAttribute("uname") == null) {
            response.sendRedirect("index.jsp");
            }
                PrintWriter out = response.getWriter();
                jdbc test = new jdbc();
                ArrayList<Integer> bikes = new ArrayList<Integer>();
                ArrayList<String> loca = new ArrayList<String>();
                ArrayList<Double> cost = new ArrayList<Double>();
                ArrayList<String> term = new ArrayList<String>();
                ArrayList<Date> SDate = new ArrayList<Date>();
                ArrayList<Date> EDate = new ArrayList<Date>();
                ArrayList<Integer> STime = new ArrayList<Integer>();
                ArrayList<Integer> ETime = new ArrayList<Integer>();
                ArrayList<Integer> status = new ArrayList<Integer>();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql = "SELECT * FROM hires WHERE CUSTOMER_ID =? ";//sql query for checking Customer table for existing username and password
                    Connection conn = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//creates connection to my sql database
                    PreparedStatement ps = conn.prepareStatement(sql);
                    String uname = session.getAttribute("uId").toString();
                    ps.setString(1, uname); // checks name field for instances of a suername
                    ResultSet rs = ps.executeQuery();
                    //Loops through the result set to find user name the assigns it to variable Username
                    while (rs.next()) {
                        int BIKE_ID = rs.getInt("BIKE_ID");
                        String loc = "";
                        String sql2 = "SELECT LOCATION FROM bike WHERE BIKE_ID =? ";
                        Connection conn2 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//creates connection to my sql database
                        PreparedStatement ps2 = conn2.prepareStatement(sql2);
                        ps2.setString(1, uname);
                        ResultSet rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            String loc2 = rs2.getString("LOCATION");

                            loc = loc2;
                        }
                        rs2.close();
                        ps2.close();
                        conn2.close();
                        double pay = rs.getDouble("cash");
                        String ter = "Long";
                        Date s = rs.getDate("START_DATE");
                        Date e = rs.getDate("END_DATE");


                        bikes.add(BIKE_ID);
                        loca.add(loc);
                        cost.add(pay);
                        term.add(ter);
                        SDate.add(s);
                        EDate.add(e);
                        STime.add(8);
                        ETime.add(20);

                    }
                    rs.close();
                    ps.close();
                    conn.close();

                    String sql3 = "SELECT * FROM Short_Hires WHERE CUSTOMER_ID =? ";
                    Connection conn3 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");
                    PreparedStatement ps3 = conn3.prepareStatement(sql3);
                    ps3.setString(1, uname);
                    ResultSet rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        int BIKE_ID = rs3.getInt("BIKE_ID");
                        String loc = "";
                        String sql4 = "SELECT LOCATION FROM bike WHERE BIKE_ID =? ";
                        Connection conn4 = DriverManager.getConnection(test.DB_URL, "EEsET82tG5", "UhgQalxiVw");//creates connection to my sql database
                        PreparedStatement ps4 = conn4.prepareStatement(sql4);
                        ps4.setString(1, uname);
                        ResultSet rs4 = ps4.executeQuery();
                        while (rs4.next()) {
                            String loc4 = rs4.getString("LOCATION");

                            loc = loc4;
                        }
                        rs4.close();
                        ps4.close();
                        conn4.close();
                        double pay = rs3.getDouble("cash");
                        String ter = "Short";
                        Date s = rs3.getDate("Date");
                        int st = rs3.getInt("Start_Time");
                        int et = rs3.getInt("End_Time");

                        bikes.add(BIKE_ID);
                        loca.add(loc);
                        cost.add(pay);
                        term.add(ter);
                        SDate.add(s);
                        EDate.add(s);
                        STime.add(st);
                        ETime.add(et);
                    }
                    rs3.close();
                    ps3.close();
                    conn3.close();

                    int listsize = bikes.size();
                    //If there are no bikes
                    if (listsize == 0)
                    {
                        String size = Integer.toString(listsize);

                        out.println("<head onload=\"openFunction()\" >" +
                                "<title id = prick >History</title>" +
                                "<meta name=viewport content=width=device-width, initial-scale=1>"+
                                "<link rel=stylesheet href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>"+ //<!-- Bootstrap style link  -->
                                "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script>"+ //<!-- Drop down button script-->
                                "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>"+  //<!-- Drop down button script-->
                                "<link rel=stylesheet href=style.css type=text/css>"+
                                "<style>\n" +
                                "table {\n" +
                                "  font-family: arial, sans-serif;\n" +
                                "  border-collapse: collapse;\n" +
                                "  width: 100%;\n" +
                                "}\n" +
                                "\n" +
                                "td, th {\n" +
                                "  border: 1px solid #dddddd;\n" +
                                "  text-align: left;\n" +
                                "  padding: 8px;\n" +
                                "}\n" +
                                "\n" +
                                "tr:nth-child(even) {\n" +
                                "  background-color: #dddddd;\n" +
                                "}\n" +
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


                        out.println(
                                "<br>" +
                                        "<h2>No Bookings yet. Get started in the book a bike tab <br></h2>"
                        );


                        out.println(
                                "<script>\n" +
                                        "function openFunction() {\n" +
                                        "document.getElementById('form2').reset();\n" +
                                        "document.getElementById('bod').reset();\n" +
                                        "location.reload();\n" +
                                        "}\n" +
                                        "\n" +
                                        "</script>"
                        );

                        out.println("</div>" + "</body>");
                    }
                    else
                    {
                        String size = Integer.toString(listsize);

                        out.println("<head onload=\"openFunction()\" >" +
                                "<title id = prick >History</title>" +
                                "<meta name=viewport content=width=device-width, initial-scale=1>"+
                                "<link rel=stylesheet href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>"+ //<!-- Bootstrap style link  -->
                                "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script>"+ //<!-- Drop down button script-->
                                "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>"+  //<!-- Drop down button script-->
                                "<link rel=stylesheet href=style.css type=text/css>"+
                                "<style>\n" +
                                "table {\n" +
                                "  font-family: arial, sans-serif;\n" +
                                "  border-collapse: collapse;\n" +
                                "  width: 100%;\n" +
                                "}\n" +
                                "\n" +
                                "td, th {\n" +
                                "  border: 1px solid #dddddd;\n" +
                                "  text-align: left;\n" +
                                "  padding: 8px;\n" +
                                "}\n" +
                                "\n" +
                                "tr:nth-child(even) {\n" +
                                "  background-color: black;\n" +
                                "}\n" +
                                "tr:nth-child(odd) {\n" +
                                "  background-color: red;\n" +
                                "}\n" +
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

                            out.println("<li  ><a href=\"Views\">Book A Bike</a></li>");


                        }

                        out.println(
                                "<li><a href=AboutUs.jsp>About Us</a></li>"+
                                        "<li><a href=ContactUs.jsp>Contact Us</a></li>"+
                                        "<li class=\"active\" ><a href=\"history\">History</a></li>"+
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

                        out.println("<h2>Your Purchase history</h2>");


                        out.println("<table style = 'font-family: arial, sans-serif;\n" +
                                "  border-collapse: collapse;\n" +
                                "  width: 100%;'>\n" +
                                "  <tr>\n" +
                                "    <th>No</th>\n" +
                                "    <th>Location</th>\n" +
                                "    <th>Bike Id</th>\n" +
                                "    <th>Start Date</th>\n" +
                                "    <th>End Date</th>\n" +
                                "    <th>Start Time</th>\n" +
                                "    <th>End Time</th>\n" +
                                "    <th>Cost</th>\n" +
                                "    <th>Term</th>\n" +
                                "  </tr>");


                        for (int i = 0; i < listsize; i++)
                        {
                            out.println(
                                    "  <tr>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\n" +
                                            "  text-align: left;\n" +
                                            "  padding: 8px;'>"+ (i + 1) +"</td>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\\n\" +\n" +
                                            "                                            \"  text-align: left;\\n\" +\n" +
                                            "                                            \"  padding: 8px;' >"+ loca.get(i) +"</td>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\\n\" +\n" +
                                            "                                            \"  text-align: left;\\n\" +\n" +
                                            "                                            \"  padding: 8px;' >"+ bikes.get(i) +"</td>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\\n\" +\n" +
                                            "                                            \"  text-align: left;\\n\" +\n" +
                                            "                                            \"  padding: 8px;' > "+ SDate.get(i) + "</td>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\\n\" +\n" +
                                            "                                            \"  text-align: left;\\n\" +\n" +
                                            "                                            \"  padding: 8px;' >"+ EDate.get(i) +"</td>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\\n\" +\n" +
                                            "                                            \"  text-align: left;\\n\" +\n" +
                                            "                                            \"  padding: 8px;' >"+ STime.get(i) +"</td>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\\n\" +\n" +
                                            "                                            \"  text-align: left;\\n\" +\n" +
                                            "                                            \"  padding: 8px;' >"+ ETime.get(i) + "</td>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\\n\" +\n" +
                                            "                                            \"  text-align: left;\\n\" +\n" +
                                            "                                            \"  padding: 8px;' >"+cost.get(i)+"</td>\n" +
                                            "    <td style = 'border: 1px solid #dddddd;\\n\" +\n" +
                                            "                                            \"  text-align: left;\\n\" +\n" +
                                            "                                            \"  padding: 8px;' >" + term.get(i) +"</td>\n" +
                                            "  </tr>\n");
                        }
                        out.println("</table>");


                        out.println(
                                "<script>\n" +
                                        "function openFunction() {\n" +
                                        "document.getElementById('form1').reset();\n" +
                                        "document.getElementById('bod').reset();\n" +
                                        "location.reload();\n" +
                                        "}\n" +
                                        "\n" +
                                        "</script>"
                        );

                        out.println("</div>" + "</body>");
                    }


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


    }
}
