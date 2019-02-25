import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author Zahoor
 */
public class BookController {

    private BikeModel bikeDetails;

    @FXML Text bike_id;
    @FXML Text ticket_id;
    @FXML Text price;
    @FXML DatePicker start;
    @FXML DatePicker to;
    @FXML TextField names;
    @FXML TextArea address;
    @FXML Button saveButton;

    private static Connection con;


    public void setBikeDetails(BikeModel bikeDetails) {
        this.bikeDetails = bikeDetails;
        loadData();
    }

    public void loadData(){

        String b_id = bikeDetails.idProperty().getValue().toString();
        String pric = bikeDetails.priceProperty().getValue().toString();

        Random rnd = new Random();
        int n = 10000000 + rnd.nextInt(90000000);
        String t_id = "#"+n;

        ticket_id.setText(t_id);
        bike_id.setText(b_id);
        price.setText(pric);

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveCustomer(t_id,b_id);
            }
        });

    }

    public void saveCustomer(String t_id, String b_id){

        String addr = address.getText();
        String name = names.getText();

        String SQL1 = "INSERT INTO `customer` VALUES(NULL,?,?)";

        try {

            con = new DbConnect().getDbConnect();
            con.setAutoCommit(false); //

            PreparedStatement preparedStatement1 = new DbConnect().getDbConnect().prepareStatement(SQL1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setString(1, name);
            preparedStatement1.setString(2, addr);
            preparedStatement1.executeUpdate();

            ResultSet rs = preparedStatement1.getGeneratedKeys();
            if(rs.next()) {
                int customer_id = rs.getInt(1);
                con.commit(); //Commit the Changes
                saveTicket(t_id,b_id,customer_id);
            }else{
               con.rollback();
            }

        } catch (Exception e) {
            try {
                con.rollback(); //Rollback
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();

        }finally {
            try {
                con.setAutoCommit(true); //Set Autocomit to true
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void saveTicket(String t_id, String b_id, int customer_id){

        String SQL1 = "INSERT INTO `ticket` VALUES(?,?,?,?,?)";

        try {

            con = new DbConnect().getDbConnect();
            con.setAutoCommit(false); //

            PreparedStatement preparedStatement1 = new DbConnect().getDbConnect().prepareStatement(SQL1);
            preparedStatement1.setString(1, t_id);
            preparedStatement1.setString(2, b_id);
            preparedStatement1.setInt(3, customer_id);
            preparedStatement1.setString(4, start.getValue().toString());
            preparedStatement1.setString(5, to.getValue().toString());
            preparedStatement1.executeUpdate();

            con.commit(); //Commit the Changes

             JOptionPane.showMessageDialog(null, "Successfully Saved Ticket ! ","Book",JOptionPane.PLAIN_MESSAGE);
         //   new DashboardController().reload();
             BookButtonCell.close();

        } catch (Exception e) {
            try {
                con.rollback(); //Rollback
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true); //Set Autocommit to true
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
