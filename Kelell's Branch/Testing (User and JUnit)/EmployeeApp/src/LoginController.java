import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author Zahoor
 */
 
public class LoginController {

    private static Stage primaryStage;
    @FXML
       TextField usernameField;
     @FXML
       TextField passwordField;
    @FXML
     Label status;

    private static Connection con;

    /**
     * Login Controller handles login operations
     */
    public void loginButtonHandler() {
        Parent root = null;

        try {

            //Get database conenction
            con = new DbConnect().getDbConnect();

            String username  = usernameField.getText();

            //check if user exists
            ResultSet rs1 = con.createStatement().executeQuery("select * from admin  WHERE `username` ='"+username+"' ");

            if(rs1.next()){

                String password = rs1.getString("password");

                //If password is correct
                if(passwordField.getText().equals(password)){

                    //reset the status
                    status.setText("NOT LOGGED IN");

                    /**
                     * Proceed to dashboard
                     */
                    try {
                            root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
                            primaryStage = new Stage();
                            primaryStage.setScene(new Scene(root, 1200, 561));
                            new Main().close();
                            primaryStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                }else{
                    //If password is incorrect
                    status.setText("Invalid Username or Password");
                }
            }
			
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if(primaryStage != null)
          primaryStage.close();
    }
}
