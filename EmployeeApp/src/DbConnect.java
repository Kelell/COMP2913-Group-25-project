/**
 * @author Zahoor
 */

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {

    //Connection method (dbconnect())
    public static Connection getDbConnect() {
        String url = "jdbc:mysql://localhost:3306/";

        String user = "root";
        String password = "";

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);

            conn.setAutoCommit(false);

            Statement stt=conn.createStatement();
            stt.executeUpdate("USE combined");

            conn.commit(); //Commit the changes if everything is OK

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback(); //Roll back the changes
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,e);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.setAutoCommit(true); //Set Autocomit to true
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn;
    }
}
