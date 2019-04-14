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
            ArrayList<Integer> bike_ids = new ArrayList<Integer>();
            ArrayList<String> loca = new ArrayList<String>();
            ArrayList<Float> cost = new ArrayList<Float>();
            String loc = request.getParameter("Location");


            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("BIKE_ID");
                String location = rs.getString("LOCATION");
                float price = rs.getFloat("price");
                if (location.equals(loc) ){
                    bike_ids.add(id);
                    loca.add(location);
                    cost.add(price);
                }

            }
            rs.close();
            stmt.close();
            conn.close();

            int listsize = bike_ids.size();
            String size = Integer.toString(listsize);

            out.println("<head onload=\"openFunction()\" >" +
                    "<title id = prick >$Title$</title>" +
                    "<link rel=stylesheet href=style.css type=text/css>" +
                    "<style>" +
                    ".section {\n" +
                    "  list-style-type: none;\n" +
                    "  margin: 0;\n" +
                    "  padding: 0;\n" +
                    "  overflow: hidden;\n" +
                    "  background-color: white;\n" +
                    "  height: 300px;\n" +
                    "}\n" +
                    "\n" +
                    ".bike {\n" +
                    "  float: left;\n" +
                    "  display: block\n" +
                    "border-right: 1px solid black;"+
                    "}\n" +
                    "\n" +
                    ".info {\n" +
                    "  float: left;\n" +
                    "  display: block\n" +
                    "  height: 300px;\n" +

                    "}" +
                    ".grid-container {\n" +
                    "  display: grid;\n" +
                    "  grid-template-columns: auto auto auto auto auto auto ;\n" +
                    "  grid-gap: 10px;\n" +
                    "  background-color: #2196F3;\n" +
                    "  padding: 10px;\n" +
                    "}"+
                    ".grid-container > div {\n" +
                    "  text-align: center;\n" +
                    "  padding: 20px 0;\n" +
                    "  font-size: 30px;\n" +
                    "}" +
                    "</style>" +
                    "</head>"
            );

            out.println(
                    "<div class = \"Title\">\n" +
                            "    B!KEWORLD\n" +
                            "  </div>"+
                    "<div class=nav>"+
                            "<a  href=index.jsp>Home</a>"+
                            "<a class=active href=\"Views\">View bikes</a>" +
                            "<a href=\"book\">Book a bike</a>"+
                            "<a href=AboutUs.jsp>About Us</a>"+
                            "<a href=ContactUs.jsp>Contact Us</a>"+
                            "<a>Log out</a>" +
                            "</div>"
            );
            out.println(
                    "<p>There are(is) "+ listsize +" bike(s) in this location.</p>" );
            for (int i = 0; i < listsize; i++)
            {
                out.println(
                        "<div class = \"section\">\n" +
                        "<div  class = \"bike\">\n" +
                                "\t<img src = \"https://www.cahabacycles.com/merchant/189/images/site/chc-rental-img7.jpg\" alt = \"bike\" width = \"390px\" height = \"300px\">\n" +

                                "\n" +
                                "</div>" +
                                "<div class = \"info\">\n" +
                                "    <p>price per hour : "+ cost.get(i) +"</p>\n" +
                                "    <p>id: "+ bike_ids.get(i)+"</p>\n" +
                                " <button style=\"display:none;\" type=\"button\" onclick=\"alert('Simulation of booking')\">Book</button>"+
                                "</div>" +
                                "</div>"+
                                "<br>"+
                                "<br>"
                );
            }
            out.println(

                    "<button type=\"button\" onclick=\"myFunction0()\">Availabillity by time</button>"+
                    "<p style=\"display:none;\" name=\"date\" >Enter Date: </p> " + "\n" +
                    "<input onchange=\"myFunction1()\" required = 'required' style=\"display:none;\"  type=\"date\" name=\"date\" required=\"required\">" + "\n" +
                    "<p style=\"display:none;color:red;\" id =\"error\"> Error buddy</p>" +
                            "<dive class = \"grid-container\" style=\"display:none;\"  name = \"Time\" >" +
                            "<div>8</div>\n" +
                            "  <div>9</div>\n" +
                            "  <div>10</div>  \n" +
                            "  <div>11</div>\n" +
                            "  <div>12</div>\n" +
                            "  <div>13</div>  \n" +
                            "  <div>14</div>\n" +
                            "  <div>15</div>\n" +
                            "  <div>16</div>\n" +
                            "  <div>17</div>  \n" +
                            "  <div>18</div>\n" +
                            "  <div>19</div>" +
                            "</div>"+

                    "<form id = 'form1' action= book method = 'post' >" +

                            "</form>" + "\n" +
                            "<script>"  + "\n" +


                            "function openFunction() {"   + "\n" +
                            "document.getElementById(\"form1\").reset();" +
                            "location.reload();" +
                            "}" + "\n" +

                            "function myFunction0() {"   + "\n" +
                            "document.getElementsByName('date')[0].style.display = 'block';" +
                            "document.getElementsByName('date')[1].style.display = 'block';" +
                            "}" + "\n" +

                            "function myFunction1() {"   + "\n" +
                            "var c = document.getElementsByName('date');"   + "\n" +
                            "var d = document.getElementsByName('Time');"   + "\n" +
                            "var h = document.getElementById('error');"   + "\n" +
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
                            "d[0].style.display = 'grid';" + "\n" +
                            "}" +
                            "if (c[1].value == '0000-00-00'){" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "}" + "\n" +
                            "} " + "\n" +
                            "else{" + "\n" +
                            "d[0].style.display = 'none';" + "\n" +
                            "c[1].value = '0000-00-00'" + "\n" +
                            "h.style.display = 'block';"   + "\n" +

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
