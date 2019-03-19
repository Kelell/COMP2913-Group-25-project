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


@WebServlet(name = "BookABike")
public class BookABike extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

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
            sql = "SELECT BIKE_ID, STATUS, LOCATION, price FROM bike";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> bike_ids = new ArrayList<Integer>();
            ArrayList<Integer> stats = new ArrayList<Integer>();;
            ArrayList<String> loca = new ArrayList<String>();
            ArrayList<Float> cost = new ArrayList<Float>();


            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("BIKE_ID");
                int status = rs.getInt("STATUS");
                String location = rs.getString("LOCATION");
                float price = rs.getFloat("price");
                bike_ids.add(id);
                stats.add(status);
                loca.add(location);
                cost.add(price);
            }
            rs.close();
            stmt.close();
            conn.close();

            int listsize = bike_ids.size();
            String size = Integer.toString(listsize);

            out.println("<head onload=\"openFunction()\" >" +
                    "<title id = prick >$Title$</title>" +
                    "<link rel=stylesheet href=style.css type=text/css>" +
                    "</head>"
            );
            out.println("<body  >");
            out.println(
                    "<div class=nav>"+
                            "<a  href=index.jsp>Home</a>"+
                            "<a class=active href=book>Book A Bike</a>" +
                            "<a href=AboutUs.jsp>About Us</a>"+
                            "<a href=ContactUs.jsp>Contact Us</a>"+
                            "<a>Log out</a>" +
                            "</div>"
            );
            out.println(
                    "<form id = 'form1' action= book method = 'post' >" +


                            "<p name = Location >Select a Location</p>" + "\n" +
                            "<select name = Location required = 'required' onclick=\"myFunction()\" >" +
                            "<option selected value= 'Please select'>Please select</option>" +
                            "<option value=Alnmouth>Alnmouth</option>" +
                            "<option value=Beverly>Beverly</option>"+
                            "<option value=Crystal>Crystal</option>"+
                            "<option value=Hexam>Hexam</option>"+
                            "</select>"+ "\n" +

                            "<p style=\"display:none;\" name = Dur >How long would you like the bike</p> "   + "\n" +
                            "<select style=\"display:none;\" name = Dur required = 'required' onchange=\"myFunction2()\"> " +
                            "<option selected value= 0>Please select</option>" +
                            "<option value=4>4 hours</option>"  +
                            "<option value=3>3 hours</option>"  +
                            "<option value=2>2 hours</option>"  +
                            "<option value=1>1 hours</option>" +
                            "</select>" + "\n" +

                            "<p style=\"display:none;\" name=\"date\" >Enter Date: </p> " + "\n" +
                            "<input onchange=\"myFunction3()\" required = 'required' style=\"display:none;\"  type=\"date\" name=\"date\" required=\"required\">" + "\n" +

                            "<p style=\"display:none;color:red;\" id =\"error\"> Error buddy</p>" +



                            "<p style=\"display:none;\" name = Time1 >Select a Time to book</p>" + "\n" +
                            "<select onclick =\"myFunction4()\" style=\"display:none;\" name = Time1 required = \"required\" >" + "\n" +
                            "<option value= 0>Please select</option>" + "\n" +
                            "<option value= \"8-9\" >8 am to 9 am</option>" + "\n" +
                            "<option value= \"9-10\" >9 am to 10 am</option>" + "\n" +
                            "<option value= \"10-11\" >10 am to 11 am</option>" + "\n" +
                            "<option value= \"11-12\" >11 am to 12 pm</option>" + "\n" +
                            "<option value= \"12-13\" >12 pm to 1 pm</option>" + "\n" +
                            "<option value= \"13-14\" >1 pm to 2 pm</option>" + "\n" +
                            "<option value= \"14-15\" >2 pm to 3 pm</option>" + "\n" +
                            "<option value= \"15-16\" >3 pm to 4 pm</option>" + "\n" +
                            "<option value= \"16-17\" >4 pm to 5 pm</option>" + "\n" +
                            "<option value= \"17-18\" >5 pm to 6 pm</option>" + "\n" +
                            "<option value= \"18-19\" >6 pm to 7 pm</option>" + "\n" +
                            "<option value= \"19-20\" >7 pm to 8 pm</option>" + "\n" +
                            "</select>" + "\n" +

                            "<p style=\"display:none;\" name = Time2 >Select a Time to book</p>" + "\n" +
                            "<select onclick =\"myFunction4()\" style=\"display:none;\" name = Time2 required = \"required\" >" + "\n" +
                            "<option value= 0>Please select</option>" + "\n" +
                            "<option value= \"8-10\" >8 am to 10 am</option>" + "\n" +
                            "<option value= \"9-11\" >9 am to 11 am</option>" + "\n" +
                            "<option value= \"10-12\" >10 am to 12 pm</option>" + "\n" +
                            "<option value= \"11-13\" >11 am to 1 pm</option>" + "\n" +
                            "<option value= \"12-14\" >12 pm to 2 pm</option>" + "\n" +
                            "<option value= \"13-15\" >1 pm to 3 pm</option>" + "\n" +
                            "<option value= \"14-16\" >2 pm to 4 pm</option>" + "\n" +
                            "<option value= \"15-17\" >3 pm to 5 pm</option>" + "\n" +
                            "<option value= \"16-18\" >4 pm to 6 pm</option>" + "\n" +
                            "<option value= \"17-19\" >5 pm to 7 pm</option>" + "\n" +
                            "<option value= \"18-20\" >6 pm to 8 pm</option>" + "\n" +
                            "</select>" + "\n" +

                            "<p style=\"display:none;\" name = Time3 >Select a Time to book</p>" + "\n" +
                            "<select onclick=\"myFunction4()\" style=\"display:none;\" name = Time3 required = \"required\" >" + "\n" +
                            "<option value= 0>Please select</option>" + "\n" +
                            "<option value= \"8-11\" >8 am to 11 am</option>" + "\n" +
                            "<option value= \"9-12\" >9 am to 12 pm</option>" + "\n" +
                            "<option value= \"10-13\" >10 am to 1 pm</option>" + "\n" +
                            "<option value= \"11-14\" >11 am to 2 pm</option>" + "\n" +
                            "<option value= \"12-15\" >12 pm to 3 pm</option>" + "\n" +
                            "<option value= \"13-16\" >1 pm to 4 pm</option>" + "\n" +
                            "<option value= \"14-17\" >2 pm to 5 pm</option>" + "\n" +
                            "<option value= \"15-18\" >3 pm to 6 pm</option>" + "\n" +
                            "<option value= \"16-19\" >4 pm to 7 pm</option>" + "\n" +
                            "<option value= \"17-20\" >5 pm to 8 pm</option>" + "\n" +
                            "</select>" + "\n" +

                            "<p style=\"display:none;\" name = Time4 >Select a Time to book</p>" + "\n" +
                            "<select onclick=\"myFunction4()\" style=\"display:none;\" name = Time4 required = \"required\" >" + "\n" +
                            "<option value= 0>Please select</option>" + "\n" +
                            "<option value= \"8-12\" >8 am to 12 pm</option>" + "\n" +
                            "<option value= \"9-13\" >9 am to 1 pm</option>" + "\n" +
                            "<option value= \"10-14\" >10 am to 2 pm</option>" + "\n" +
                            "<option value= \"11-15\" >11 am to 3 pm</option>" + "\n" +
                            "<option value= \"12-16\" >12 pm to 4 pm</option>" + "\n" +
                            "<option value= \"13-17\" >1 pm to 5 pm</option>" + "\n" +
                            "<option value= \"14-18\" >2 pm to 6 pm</option>" + "\n" +
                            "<option value= \"15-19\" >3 pm to 7 pm</option>" + "\n" +
                            "<option value= \"16-20\" >4 pm to 8 pm</option>" + "\n" +
                            "</select>" + "\n" +



                            "<p style=\"display:none;color:red;\" id =\"error2\"> tooo lattee</p>" +



                            "<br><br>" +
                            "<input id = \"submit\" style=\"display:none;\" type=submit value=Submit>" + "\n" +
                            "</form>" + "\n" +
                            "<script>"  + "\n" +


                            "function openFunction() {"   + "\n" +
                            "document.getElementById(\"form1\").reset();" +
                            "location.reload();" +
                            "}" + "\n" +


                            "function myFunction() {"   + "\n" +
                            "var a = document.getElementsByName('Location')"   + "\n" +
                            "var b = document.getElementsByName('Dur')"   + "\n" +
                            "var c = document.getElementsByName('date')"   + "\n" +
                            "var d = document.getElementsByName('Time1')"   + "\n" +
                            "var e = document.getElementsByName('Time2')"   + "\n" +
                            "var f = document.getElementsByName('Time3')"   + "\n" +
                            "var g = document.getElementsByName('Time4')"   + "\n" +
                            "if (a[1].value !=  'Please select'){" + "\n" +
                            "if (b[1].style.display ==  'none'){" + "\n" +
                            "b[0].style.display = 'block';" + "\n" +
                            "b[1].style.display = 'block';" + "\n" +
                            "c[0].style.display = 'none';" + "\n" +
                            "c[1].style.display = 'none';" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "d[1].style.display = 'none';" + "\n" +
                            "e[0].style.display = 'none';" + "\n" +
                            "e[1].style.display = 'none';" + "\n" +
                            "f[0].style.display = 'none';" + "\n" +
                            "f[1].style.display = 'none';" + "\n" +
                            "g[0].style.display = 'none';" + "\n" +
                            "g[1].style.display = 'none';" + "\n" +
                            "}" +
                            "if (b[1].style.display ==  'block'){" + "\n" +
                            "b[1].value = 0;" + "\n" +
                            "c[0].style.display = 'none';" + "\n" +
                            "c[1].style.display = 'none';" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "d[1].style.display = 'none';" + "\n" +
                            "e[0].style.display = 'none';" + "\n" +
                            "e[1].style.display = 'none';" + "\n" +
                            "f[0].style.display = 'none';" + "\n" +
                            "f[1].style.display = 'none';" + "\n" +
                            "g[0].style.display = 'none';" + "\n" +
                            "g[1].style.display = 'none';" + "\n" +
                            "}" +
                            "}" + "\n" +
                            "if (a[1].value ==  'Please select'){" + "\n" +
                            "b[0].style.display = 'none';" + "\n" +
                            "b[1].style.display = 'none';" + "\n" +
                            "c[0].style.display = 'none';" + "\n" +
                            "c[1].style.display = 'none';" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "d[1].style.display = 'none';" + "\n" +
                            "e[0].style.display = 'none';" + "\n" +
                            "e[1].style.display = 'none';" + "\n" +
                            "f[0].style.display = 'none';" + "\n" +
                            "f[1].style.display = 'none';" + "\n" +
                            "g[0].style.display = 'none';" + "\n" +
                            "g[1].style.display = 'none';" + "\n" +
                            "} " + "\n" +
                            "} " + "\n" +


                            "function myFunction2() {"   + "\n" +
                            "var a = document.getElementsByName('Location')"   + "\n" +
                            "var b = document.getElementsByName('Dur')"   + "\n" +
                            "var c = document.getElementsByName('date')"   + "\n" +
                            "var d = document.getElementsByName('Time1')"   + "\n" +
                            "var e = document.getElementsByName('Time2')"   + "\n" +
                            "var f = document.getElementsByName('Time3')"   + "\n" +
                            "var g = document.getElementsByName('Time4')"   + "\n" +
                            "if (b[1].value !=  0){" + "\n" +
                            "if (c[1].style.display ==  'none'){" + "\n" +
                            "c[0].style.display = 'block';" + "\n" +
                            "c[1].style.display = 'block';" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "d[1].style.display = 'none';" + "\n" +
                            "e[0].style.display = 'none';" + "\n" +
                            "e[1].style.display = 'none';" + "\n" +
                            "f[0].style.display = 'none';" + "\n" +
                            "f[1].style.display = 'none';" + "\n" +
                            "g[0].style.display = 'none';" + "\n" +
                            "g[1].style.display = 'none';" + "\n" +
                            "}" +
                            "if (c[1].style.display ==  'block'){" + "\n" +
                            "c[1].value = 0000-00-00;" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "d[1].style.display = 'none';" + "\n" +
                            "e[0].style.display = 'none';" + "\n" +
                            "e[1].style.display = 'none';" + "\n" +
                            "f[0].style.display = 'none';" + "\n" +
                            "f[1].style.display = 'none';" + "\n" +
                            "g[0].style.display = 'none';" + "\n" +
                            "g[1].style.display = 'none';" + "\n" +
                            "}" +
                            "}" + "\n" +
                            "if (b[1].value ==  0){" + "\n" +
                            "c[0].style.display = 'none';" + "\n" +
                            "c[1].style.display = 'none';" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "d[1].style.display = 'none';" + "\n" +
                            "e[0].style.display = 'none';" + "\n" +
                            "e[1].style.display = 'none';" + "\n" +
                            "f[0].style.display = 'none';" + "\n" +
                            "f[1].style.display = 'none';" + "\n" +
                            "g[0].style.display = 'none';" + "\n" +
                            "g[1].style.display = 'none';" + "\n" +
                            "}" + "\n" +
                            "} " + "\n" +






                            "function myFunction3() {"   + "\n" +
                            "var a = document.getElementsByName('Location')"   + "\n" +
                            "var b = document.getElementsByName('Dur')"   + "\n" +
                            "var c = document.getElementsByName('date')"   + "\n" +
                            "var d = document.getElementsByName('Time1')"   + "\n" +
                            "var e = document.getElementsByName('Time2')"   + "\n" +
                            "var f = document.getElementsByName('Time3')"   + "\n" +
                            "var g = document.getElementsByName('Time4')"   + "\n" +
                            "var h = document.getElementById('error')"   + "\n" +
                            "h.style.display = 'none';"   + "\n" +
                            "var today = new Date();" +
                            "var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();" +
                            "var currentyear = parseInt(today.getFullYear());"+
                            "var currentmonth = parseInt((today.getMonth()+1));"+
                            "var currentday = parseInt(today.getDate());"+
                            "var inputyear = parseInt(c[1].value.substring(0,4));"+
                            "var inputmonth = parseInt(c[1].value.substring(5,7));"+
                            "var inputdate = parseInt(c[1].value.substring(8,10));"+
                            "if ((inputyear >= currentyear) && (inputmonth >= currentmonth) && (inputdate >= currentday)){" + "\n" +
                            "if (c[1].value !=  '0000-00-00'){" + "\n" +
                            "if (b[1].value == 4){" + "\n" +
                            "if (g[1].style.display == 'none'){" + "\n" +
                            "g[0].style.display = 'block';" + "\n" +
                            "g[1].style.display = 'block';" + "\n" +
                            "g[1].setAttribute(\"id\", \"path\");" +
                            "f[1].removeAttribute('id');" +
                            "e[1].removeAttribute('id');" +
                            "d[1].removeAttribute('id');" +
                            "}" + "\n" +
                            "if (g[1].style.display == 'block'){" + "\n" +
                            "g[1].value = 0;" + "\n" +
                            "}" + "\n" +
                            "}" + "\n" +
                            "if (b[1].value == 3){" + "\n" +
                            "if (f[1].style.display == 'none'){" + "\n" +
                            "f[0].style.display = 'block';" + "\n" +
                            "f[1].style.display = 'block';" + "\n" +
                            "g[1].removeAttribute('id');" +
                            "f[1].setAttribute(\"id\", \"path\");" +
                            "e[1].removeAttribute('id');" +
                            "d[1].removeAttribute('id');" +
                            "}" + "\n" +
                            "if (f[1].style.display == 'block'){" + "\n" +
                            "f[1].value = 0;" + "\n" +
                            "}" + "\n" +
                            "}" + "\n" +
                            "if (b[1].value == 2){" + "\n" +
                            "if (e[1].style.display == 'none'){" + "\n" +
                            "e[0].style.display = 'block';" + "\n" +
                            "e[1].style.display = 'block';" + "\n" +
                            "g[1].removeAttribute('id');" +
                            "f[1].removeAttribute('id');" +
                            "e[1].setAttribute(\"id\", \"path\");" +
                            "d[1].removeAttribute('id');" +
                            "}" + "\n" +
                            "if (e[1].style.display == 'block'){" + "\n" +
                            "e[1].value = 0;" + "\n" +
                            "}" + "\n" +
                            "}" + "\n" +
                            "if (b[1].value == 1){" + "\n" +
                            "if (d[1].style.display == 'none'){" + "\n" +
                            "d[0].style.display = 'block';" + "\n" +
                            "d[1].style.display = 'block';" + "\n" +
                            "g[1].removeAttribute('id');" +
                            "f[1].removeAttribute('id');" +
                            "e[1].removeAttribute('id');" +
                            "d[1].setAttribute(\"id\", \"path\");" +
                            "}" + "\n" +
                            "if (d[1].style.display == 'block'){" + "\n" +
                            "d[1].value = 0;" + "\n" +
                            "}" + "\n" +
                            "}" + "\n" +
                            "}" + "\n" +
                            "if (c[1].value == '0000-00-00'){" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "d[1].style.display = 'none';" + "\n" +
                            "e[0].style.display = 'none';" + "\n" +
                            "e[1].style.display = 'none';" + "\n" +
                            "f[0].style.display = 'none';" + "\n" +
                            "f[1].style.display = 'none';" + "\n" +
                            "g[0].style.display = 'none';" + "\n" +
                            "g[1].style.display = 'none';" + "\n" +
                            "}" + "\n" +
                            "} " + "\n" +
                            "else{" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "d[1].style.display = 'none';" + "\n" +
                            "e[0].style.display = 'none';" + "\n" +
                            "e[1].style.display = 'none';" + "\n" +
                            "f[0].style.display = 'none';" + "\n" +
                            "f[1].style.display = 'none';" + "\n" +
                            "g[0].style.display = 'none';" + "\n" +
                            "g[1].style.display = 'none';" + "\n" +
                            "c[1].value = '0000-00-00'" + "\n" +
                            "h.style.display = 'block';"   + "\n" +

                            "} " + "\n" +
                            "} " + "\n" +


                            "function myFunction4() {"   + "\n" +
                            "var a = document.getElementsByName('Location')"   + "\n" +
                            "var b = document.getElementsByName('Dur')"   + "\n" +
                            "var c = document.getElementsByName('date')"   + "\n" +
                            "var d = document.getElementsByName('Time1')"   + "\n" +
                            "var e = document.getElementsByName('Time2')"   + "\n" +
                            "var f = document.getElementsByName('Time3')"   + "\n" +
                            "var g = document.getElementsByName('Time4')"   + "\n" +
                            "var x = document.getElementById('path')"   + "\n" +
                            "var z = document.getElementById('submit')"   + "\n" +
                            "var btime = x.value.substring(0,1)"   + "\n" +
                            "if (x.value != 0){" + "\n" +
                            "z.style.display = 'block' " +
                            "} " + "\n" +
                            "} " + "\n" +


                            "</script>"

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
