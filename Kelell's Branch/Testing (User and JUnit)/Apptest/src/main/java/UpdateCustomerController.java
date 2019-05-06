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
                        JOptionPane.showMessageDialog(null, "Record Updated Successfully", "Success!", JOptionPane.PLAIN_MESSAGE);

                        //Close the update window
                        new DashboardController().close();

                        //After updating launches the dashboard with updated information
                        Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
                        primaryStage = new Stage();
                        primaryStage.setScene(new Scene(root, 1200, 561));
                        primaryStage.centerOnScreen();
                        primaryStage.show();

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
