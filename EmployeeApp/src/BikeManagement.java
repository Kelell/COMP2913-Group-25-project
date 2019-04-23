/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author sahil
 */


public class BikeManagement implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML ComboBox<String> bikeCombo;

    
    //Database connection
    private static Connection con;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        con = DbConnect.getDbConnect();
        
        ResultSet rs1 = null;
        try {
            rs1 = con.createStatement().executeQuery("SELECT * FROM `bike`");


            //Populates the combo box with bike IDs from the database
            while (rs1.next()) {
                bikeCombo.getItems().add(rs1.getString("BIKE_ID"));
            }
            
            bikeCombo.getSelectionModel().selectFirst();


            bikeCombo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    int selectedBikeID;
                    selectedBikeID = Integer.parseInt(bikeCombo.getSelectionModel().getSelectedItem().trim());
                    System.out.print(selectedBikeID);

                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
}
