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

public class LoginController {

    Stage primaryStage;
    @FXML
       TextField usernameField;
     @FXML
       TextField passwordField;
    @FXML
     Label status;

    private static Connection con;

    
    public void loginButtonHandler() {
        Parent root = null;

        try {

            con = new DbConnect().getDbConnect();

            String username  = usernameField.getText();

            ResultSet rs1 = con.createStatement().executeQuery("select * from admin  WHERE `username` ='"+username+"' ");

            if(rs1.next()){

                String encryped_password =  rs1.getString("password");
                String salt =  rs1.getString("salt");
               // String password = decryptedPassword(encryped_password,salt);
                String password = rs1.getString("password");

                if(passwordField.getText().equals(password)){
                    status.setText("NOT LOGGED IN");

                        try {
                            root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
                            primaryStage = new Stage();
                            primaryStage.setScene(new Scene(root, 993, 561));
                            new Main().close();
                            primaryStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                }else{
                    status.setText("Invalid Username or Password");
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}