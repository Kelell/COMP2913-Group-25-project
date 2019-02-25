
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Key;
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

    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue =
            new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S',
                    'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    private static final int ITERATIONS = 2;


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

    public static String decryptedPassword(String value, String salt)
            throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);

        String dValue = null;
        String valueToDecrypt = value;
        for (int i = 0; i < ITERATIONS; i++) {
            byte[] decordedValue
                    = new BASE64Decoder().decodeBuffer(valueToDecrypt);
            byte[] decValue = c.doFinal(decordedValue);
            dValue = new String(decValue).substring(salt.length());
            valueToDecrypt = dValue;
        }
        return dValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        // SecretKeyFactory keyFactory
        //                  = SecretKeyFactory.getInstance(ALGORITHM);
        // key = keyFactory.generateSecret(new DESKeySpec(keyValue));
        return key;
    }



}
