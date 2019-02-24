/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.desktop.app;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;
import java.util.List ;
import java.util.ArrayList ;
/**
 *
 * @author sahil
 */
public class BikeDataAccessor {
    private Connection connection;
    
    public BikeDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException  {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);

    }
    
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public List<Bike> getBikeList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from BIKES");
        ){
            List<Bike> bikeList = new ArrayList<>();
            while (rs.next()) {
                int bikeID = rs.getInt("BIKE_ID");
                int status = rs.getInt("STATUS");
                String location = rs.getString("LOCATION");
                Bike bike = new Bike(bikeID, status, location);
                bikeList.add(bike);
            }
            return bikeList ;
        } 
    }
    
}

