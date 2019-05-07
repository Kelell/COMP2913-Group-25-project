import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;


/**
 * @author Zahoor
 */
 
public class UpdateCustomerController {

    private static Connection con;

    @FXML private TextField nameField;

    @FXML private TextField addressField;

    @FXML private Button updateButton;

    private static Stage primaryStage;
    private static  String id;

     //Called when update button is clicked
    @FXML
    void buttonClicked(ActionEvent event) throws SQLException {

        //Identify which button was clicked
        Object source = event.getSource();

        if (source instanceof Button) {

            //Get that button
            Button clickedBtn = (Button) source;

            //Check which button is clicked
            switch (clickedBtn.getId()) {

                //If it's the update button
                case "updateButton":

                    con = new DbConnect().getDbConnect();
                    con.setAutoCommit(false);

                    //Get text from textfields
                    String address = addressField.getText();
                    String names = nameField.getText();

                    String sq11="UPDATE customer SET `CUSTOMER_NAME`='"+names+"',`CUSTOMER_ADDRESS`='"+address+"' WHERE `CUSTOMER_ID` ='"+id+"'";

                    try {
                        PreparedStatement pst1=con.prepareStatement(sq11);
                        pst1.executeUpdate();
                        con.commit();//Commit the changes                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Success");
                        alert.setContentText("Record Updated Successfully");
                        alert.showAndWait();
                        
                        closeAndBack();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        con.rollback(); //Rollback any changes made
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
    private void closeAndBack() throws IOException{
        
                        //Close the update window
                        new DashboardController().close();

                        //After updating launches the dashboard with updated information
                        Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
                        primaryStage = new Stage();
                        //Displays App Name 
                        primaryStage.setTitle("Bike World");
                        // Displays App Icon
                        primaryStage.getIcons().add(
                        new Image("/CSS/icon2.png"));
                        primaryStage.setScene(new Scene(root, 1200, 561));
                        primaryStage.centerOnScreen();
                        primaryStage.show();

    }
     @FXML
    void deleteCustomer(ActionEvent event) throws SQLException, IOException {
            Connection con=DbConnect.getDbConnect();
            String sql="delete from customer where CUSTOMER_ID="+id;
            Alert a=new Alert(AlertType.CONFIRMATION);
            a.setContentText("Are you sure you want to delete this customer? "+nameField.getText());
            a.setHeaderText(null);
            a.setTitle("Warning");
            a.showAndWait();
            if(a.getResult()==ButtonType.OK);
            {PreparedStatement pst1=con.prepareStatement(sql);
               pst1.executeUpdate();
               con.close();
               closeAndBack();
            }
    }
    //Initialise the text fields with data from the dashboard
    public void setData(String id,String names, String address){
         this.id  = id;
         addressField.setText(address);
         nameField.setText(names);
    }

   public void close(){
       if(primaryStage != null)
          primaryStage.close();
   }
}
