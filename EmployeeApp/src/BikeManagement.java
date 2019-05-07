/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sahil
 */
public class BikeManagement implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ComboBox<String> bikeCombo;
    @FXML
    ComboBox<String> statusCombo;
    @FXML
    Button setStatusBtn;
    @FXML
    Button cancelBtn;
    @FXML
    Text statusLabel;
    
    
    private static Stage primaryStage;
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
        } catch (SQLException ex) {
            Logger.getLogger(BikeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        //set value of satus comboBox
        statusCombo.getItems().add("Hired");//0
        statusCombo.getItems().add("Free");//1
        statusCombo.getItems().add("Damaged");//2

        bikeCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int selectedBikeID;
                int status = -1;
                selectedBikeID = Integer.parseInt(bikeCombo.getSelectionModel().getSelectedItem().trim());
                System.out.print(selectedBikeID);

                //set label corresponding to current status
                ResultSet bikeStatus = null;

                try {
                    bikeStatus = con.createStatement().executeQuery("SELECT * FROM `bike` WHERE BIKE_ID=" + selectedBikeID);
                    bikeStatus.next();
                    status = Integer.parseInt(bikeStatus.getString("STATUS"));

                } catch (SQLException ex) {
                    Logger.getLogger(BikeManagement.class.getName()).log(Level.SEVERE, null, ex);
                }

                switch (status) {
                    case 0:
                        statusLabel.setText("Hired");
                        break;
                    case 1:
                        statusLabel.setText("Free");
                        break;
                    case 2:
                        statusLabel.setText("Damaged");
                        break;
                    case -1:
                        statusLabel.setText("Invalid");
                        break;

                }

            }
        });

        setStatusBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String alterSQL = "UPDATE bike SET STATUS=";
                String statusVal = statusCombo.getValue();
                int bikeID = -1;
                int status = -1;
                boolean validStatus = false;
                boolean validBike = false;

                //ensure a value is selected
                if (!bikeCombo.getSelectionModel().isEmpty()) {
                    bikeID = Integer.parseInt(bikeCombo.getValue());
                }
                //validate selections and process
                if (bikeID > 0) {
                    validBike = true;

                    if (statusVal.equals("Hired")) {
                        status = 0;
                        validStatus = true;
                    } else if (statusVal.equals("Free")) {
                        status = 1;
                        validStatus = true;
                    } else if (statusVal.equals("Damaged")) {
                        status = 2;
                        validStatus = true;
                    } else {
                        validStatus = false;
                    }
                } else {
                    validBike = false;
                }

                if (validBike && validStatus) {
                    boolean notExecuted = false;

                    alterSQL += status + " WHERE BIKE_ID='" + bikeID + "'";
                    System.out.println(alterSQL);

                    try {
                        PreparedStatement preparedStatement1 = DbConnect.getDbConnect().prepareStatement(alterSQL, Statement.RETURN_GENERATED_KEYS);
                        notExecuted = preparedStatement1.execute();

                    } catch (SQLException ex) {
                        Logger.getLogger(BikeManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (notExecuted) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("Database not updated!");
                        alert.show();
                    } else {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Success");
                        alert.setContentText("Database Updated!");
                        alert.show();
                        
                        // close manager
                        Stage stage = (Stage) setStatusBtn.getScene().getWindow();
                        stage.close();

                        //open dashboard
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        primaryStage = new Stage();
                        primaryStage.setScene(new Scene(root, 1200, 561));
                        primaryStage.centerOnScreen();
                        primaryStage.show();
                
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Invalid bike or status selected!");
                    alert.show();
                }

            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // close manager
                Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.close();
                
                //open dashboard
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage = new Stage();
                primaryStage.setScene(new Scene(root, 1200, 561));
                primaryStage.centerOnScreen();
                primaryStage.show();

            }
        });

    }

}
