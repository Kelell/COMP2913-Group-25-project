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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            String sql;
            sql = "SELECT BIKE_ID, LOCATION, price, STATUS FROM bike";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> bikes = new ArrayList<Integer>();
            ArrayList<String> loca = new ArrayList<String>();
            ArrayList<Float> cost = new ArrayList<Float>();
            ArrayList<Integer> status = new ArrayList<Integer>();
            String loc = request.getParameter("Location");


            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("BIKE_ID");
                String location = rs.getString("LOCATION");
                float price = rs.getFloat("price");
                int x = rs.getInt("STATUS");
                if (location.equals(loc) ){
                    bikes.add(id);
                    loca.add(location);
                    cost.add(price);
                    status.add(x);
                }

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
                    ".holder {\n" +
                    "  float: right;\n" +
                    "  display: block\n" +
                    "}" +
                    "</style>" +
                    "</head>"
            );

            out.println("<body id = 'bod2' onload=\"openFunction()\"  >");
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
                                "<li><strong> User: " + session.getAttribute("uname") + "</strong></li>"+
                                "<li><a href=Profile.jsp>Profile</a></li>"+
                                "<li><a href=Views>BookABike</a></li>"+
                                "</ul>"+
                                "</li>"+
                                "<li><a href=Log><span class='glyphicon glyphicon-log-in'></span> LOGOUT</a></li>");

            }


            out.println(
                    "</ul>"+
                            "</div>"+
                            "</nav>"+
                            "<div class='content'>"

            );



            out.println(
                    "<p>There are(is) "+ listsize +" bike(s) in this location.</p>" );


            if(loc.equals("Alnmouth"))
            {
                out.println("<iframe src='https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d36279.987310143624!2d-1.6253423327709322!3d55.366744091928254!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487e0472db35c6d3%3A0x2a39153ad77fb156!2sAlnmouth%2C+Alnwick!5e0!3m2!1sen!2suk!4v1557101499273!5m2!1sen!2suk' width='600' height='450' frameborder='0' style='border:0' allowfullscreen></iframe>");
            }
            else if(loc.equals("Barnsley")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75828.47829640322!2d-1.5184422816798653!3d53.564194575225066!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487962b7c1b768c3%3A0xc2476c02bf53d08a!2sBarnsley!5e0!3m2!1sen!2suk!4v1557102298285!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Beverly")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d37665.6294351413!2d-0.46681248311360063!3d53.84105255182187!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4878b82fd883c559%3A0xa7ecf2db4d15fc7b!2sBeverley!5e0!3m2!1sen!2suk!4v1557102404709!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Bournemouth")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d80773.26454549863!2d-1.9297784048150328!3d50.7539830758078!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487398a0b1a067fd%3A0x3b2ee0156ba92c94!2sBournemouth!5e0!3m2!1sen!2suk!4v1557102446821!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Bradford")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75410.53514953193!2d-1.8243998841439404!3d53.79697624683072!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48795f7ae9c21919%3A0x8fe0edd83227194f!2sBradford!5e0!3m2!1sen!2suk!4v1557102466695!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Bristol")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d79534.02653582788!2d-2.6607570769134634!3d51.46846808883825!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4871836681b3d861%3A0x8ee4b22e4b9ad71f!2sBristol!5e0!3m2!1sen!2suk!4v1557102488221!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Buckingham")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d19652.048218070522!2d-0.9877888182930727!3d51.99765650198046!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4876e1098aea9191%3A0xee092e76e65ad2b!2sBuckingham!5e0!3m2!1sen!2suk!4v1557102519357!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe");
            }
            else if(loc.equals("Crystal")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d9953.150285288582!2d-0.09139270756538645!3d51.416143873922245!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4876015d4bc394bd%3A0x6f51309b2fa08ffe!2sCrystal+Palace%2C+London!5e0!3m2!1sen!2suk!4v1557102575453!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Halifax")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75513.18119590492!2d-1.9444972364519215!3d53.73986983397286!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487bc201a120ef39%3A0x6c3f29996afa5893!2sHalifax!5e0!3m2!1sen!2suk!4v1557102603964!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Harrogate")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d37527.89968060756!2d-1.5639340815819953!3d53.99401141057279!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48794e237175bd01%3A0x3c084fbaadea4ff!2sHarrogate!5e0!3m2!1sen!2suk!4v1557102776212!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Hebden")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15753.06615053711!2d-1.9772564924384983!3d54.061199239474654!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487c08344de97f31%3A0xdda31bad3a66c662!2sHebden%2C+Skipton+BD23+5DT!5e0!3m2!1sen!2suk!4v1557102836436!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Hexham")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d36646.20391480342!2d-2.138369721778697!3d54.96629911957963!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487d90620d319815%3A0xa83a2b78210dc004!2sHexham!5e0!3m2!1sen!2suk!4v1557102851915!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Leeds")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d150788.90157971418!2d-1.675814485279139!3d53.80592089162559!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48793e4ada64bd99%3A0x51adbafd0213dca9!2sLeeds!5e0!3m2!1sen!2suk!4v1557102866772!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Manchester")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d75993.25869269183!2d-2.293502347247276!3d53.47222497479299!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487a4d4c5226f5db%3A0xd9be143804fe6baa!2sManchester!5e0!3m2!1sen!2suk!4v1557102886859!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else if(loc.equals("Rotherham")){
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d38031.31077189646!2d-1.4022718871804016!3d53.433478479214486!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48790a41d8154027%3A0x5d4fabde0673d601!2sRotherham!5e0!3m2!1sen!2suk!4v1557102903339!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }
            else{
                out.println("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d18837.49851814451!2d-1.7988262137881466!3d53.830638325322845!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487be25520ab12b9%3A0x9af31619017257e0!2sShipley!5e0!3m2!1sen!2suk!4v1557102922643!5m2!1sen!2suk\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
            }

            for (int i = 0; i < listsize; i++)
            {

                out.println(
                        "<div id = " + bikes.get(i)+ " class = \"bike\">\n" +
                                "<img src = \"https://www.cahabacycles.com/merchant/189/images/site/chc-rental-img7.jpg\" alt = \"bike\" width = \"390px\" height = \"300px\">\n" +
                                "    <p id = "+ cost.get(i) +">price: "+ cost.get(i) +"</p>\n" +
                                "    <p>id: "+ bikes.get(i)+"</p>\n" +
                                "    <p class = "+ status.get(i)+ ">stat: Available </p>\n" +
                                " <button style=\"display:none;\" type=\"button\" onclick=\"alert('Simulation of booking')\">Book</button>"+
                                "</div>");
            }
            out.println(
                    "<form id = 'form1' action= 'book' method = 'post' >\n" +
                            "<div class = 'holder' style = 'display : block; width: 100%;' >"+
                            "<br><br>"+
                            "<br><br>"+"<br><br>"+
                            "<p style = 'display: none;' name = 'Location' >Select a Location</p>\n" +
                            "<select style = 'display: none;' name = 'Location' >\n" +
                            "<option value=Alnmouth>Alnmouth</option>" +
                            "<option value=Barnsley>Barnsley</option>"+
                            "<option value=Beverly>Beverly</option>"+
                            "<option value=Bournemouth>Bournemouth</option>"+
                            "<option value=Bradford>Bradford</option>"+
                            "<option value=Bristol>Bristol</option>"+
                            "<option value=Buckingham>Buckingham</option>"+
                            "<option value=Crystal>Crystal</option>"+
                            "<option value=Halifax>Halifax</option>"+
                            "<option value=Harrogate>Harrogate</option>"+
                            "<option value=Hebden>Hebden</option>"+
                            "<option value=Hexham>Hexham</option>"+
                            "<option value=Leeds>Leeds</option>"+
                            "<option value=Manchester>Manchester</option>"+
                            "<option value=Rotherham>Rotherham</option>"+
                            "<option value=Shipley>Shipley</option>"+
                            "</select>\n" +

                            "<button type=\"button\" onclick=\"myFunction0()\">Availabillity by time</button>"+
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
                            "<p style='display:none;' name = 'Time1' >Select a Time to book</p>\n" +
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
                            "<p style='display:none;' name = 'Time2' >Select a Time to book</p>\n" +
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
                            "<p style='display:none;' name = 'Time3' >Select a Time to book</p>\n" +
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
                            "<p style='display:none;' name = 'Time4' >Select a Time to book</p>\n" +
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
                            "<select id = tyme style='display:none;' name = 'time0' required >\n" +
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


                            "<script>"  + "\n" +


                            "function openFunction() {"   + "\n" +

                            "document.getElementById('form1').reset();\n" +
                            "document.getElementById('bod2').reset();\n" +
                            "location.reload();\n" +
                            "document.getElementsByName('Location')[1].value = " + request.getParameter("Location") +";"+
                            "}" + "\n" +

                            "function myFunction0() {"   + "\n" +
                            "document.getElementsByName('term')[0].style.display = 'block';" +
                            "document.getElementsByName('term')[1].style.display = 'block';" +
                            "}" + "\n" +

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
        out.println("</div>" + "</body>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }
}
