/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.desktop.app;

import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EmployeeDesktopApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        initUI(stage);
    }
    
    private BikeDataAccessor dataAccessor ;


    private void initUI(Stage stage) throws Exception {

        StackPane root = new StackPane();

        TabPane tabPane = new TabPane();
        
        
        
        try {
            dataAccessor = new BikeDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/testImport","root","ib1133729304"); // provide driverName, dbURL, user, password...

        }
        catch(ClassNotFoundException | SQLException e){
            System.out.print(e.toString());
        }
        
        //_bikeID.setCellValueFactory(new PropertyValueFactory<>("bikeID"));
        //_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        //_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        //bikeTable.getColumns().addAll(_bikeID, _status, _location);

        




        TableView<Bike> bikeTable = new TableView<>();
        TableColumn<Bike, Integer> bikeIDCol = new TableColumn<>("Bike ID");
        bikeIDCol.setCellValueFactory(new PropertyValueFactory<>("bikeID"));
        TableColumn<Bike, Integer> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<Bike, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        bikeTable.getColumns().addAll(bikeIDCol, statusCol, locationCol);

        try{
             bikeTable.getItems().addAll(dataAccessor.getBikeList());

        }
        catch(SQLException e){
            
        }
        
        BorderPane table = new BorderPane();
        table.setCenter(bikeTable);
        //Scene scene = new Scene(root, 600, 400);

        
        
        
        //content comes from fxml file
        Parent clickme = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Tab tab1 = new Tab();
        tab1.setText("Click Here!");
        tab1.setContent(bikeTable); //content of tab set
        
        Tab tab2 = new Tab();
        tab2.setText("Line");
        tab2.setContent(new Line(0, 0, 100, 100));  
        
        Tab tab3 = new Tab();
        tab3.setText("Circle");
        tab3.setContent(new Circle(0, 0, 50));         
        
        tabPane.getSelectionModel().select(1);
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        
        root.getChildren().add(tabPane);

        Scene scene = new Scene(root, 300, 250);

        stage.setTitle("TabPane");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
