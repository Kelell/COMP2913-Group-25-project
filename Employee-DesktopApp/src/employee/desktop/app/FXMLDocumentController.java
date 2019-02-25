/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.desktop.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.TableView ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.cell.PropertyValueFactory ;
import javafx.scene.layout.BorderPane ;
import javafx.scene.Scene ;

import java.sql.SQLException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author sahil
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    
    @FXML
    private TableView bikeTable;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
    }
        
    private BikeDataAccessor dataAccessor ;

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            dataAccessor = new BikeDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/testImport","root","ib1133729304"); // provide driverName, dbURL, user, password...
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.print(e.toString());
        }

        TableColumn<Bike, Integer> bikeIDCol = new TableColumn<>("Bike ID");
        bikeIDCol.setCellValueFactory(new PropertyValueFactory<>("bikeID"));
        TableColumn<Bike, Integer> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<Bike, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        bikeTable.getColumns().addAll(bikeIDCol, statusCol, locationCol);

        try{
             bikeTable.getItems().addAll(dataAccessor.getFreeBikeList());

        }
        catch(SQLException e){
            
        }
    }    
    
}
