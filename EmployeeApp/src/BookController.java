import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Zahoor
 */
public class BookController {

    private BikeModel bikeDetails;

    //FXML variables
    @FXML Text bike_id;
    @FXML Text price;
    @FXML DatePicker start;
    @FXML DatePicker to;
    @FXML TextField names;
    @FXML TextField cashField;
    @FXML TextArea address;
    @FXML Button saveButton;
    @FXML Text totalText;
    @FXML Text changeText;

    private double total = 0;

    //Database connection
    private static Connection con;

    //Initialises the BikeModel from the dashboard Bike Table
    public void setBikeDetails(BikeModel bikeDetails) {
        this.bikeDetails = bikeDetails;
        initStuff();
    }

    public void initStuff() {

        con = DbConnect.getDbConnect();

        String b_id = bikeDetails.idProperty().getValue().toString();
        String pric = bikeDetails.priceProperty().getValue().toString();

        //Sets the bike id and price passed from the table
        bike_id.setText(b_id);
        price.setText(pric);


        //When User clicks save button
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //check if change has been calculated and displayed on the Text before saving customer
                if(!changeText.getText().equals("")){
                    saveCustomer(b_id);
                }

            }
        });

        /*
          When typing on cash text field, get the text and automatically calculate change
         */
        cashField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                //If the new value typed is not null/empty
                if(!newValue.equals("")){

                    //cast the text from the user to double value
                    double cash = Double.parseDouble(newValue);

                    //if the total value of hire is greater than 0, means that duration and price has been set
                    if(total > 0){

                        //Calculate change
                        double change = cash - total;

                        //display the change in the text
                        changeText.setText("£ "+change);
                    }
                }


            }
        });


    }

    /**
     * This function is called from the xml when either start or end dates are clicked to
     * recalculate the total amount due and
     * initialise the total variable and display in the UI
     * to calculate change etc
     * @param event
     */

    @FXML
    void calculateTotal(ActionEvent event){

        //Both the end and start dates must be entered first to be able to calculate total the amount due
        if(to.getValue() != null && start.getValue() != null){

            /**
             * Now we calculate the time diffrence between the dates
             */

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

            Date firstDate = null;
            try {

                firstDate = sdf.parse(start.getValue().toString());
                java.util.Date secondDate = sdf.parse(to.getValue().toString());

                //Get the value of the difference between dates
                long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());

                //The number of days difference
                long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                //Get the current price of the bike
                String pric = bikeDetails.priceProperty().getValue().toString();

                //convert it to a double
                double raw_price = Double.parseDouble(pric.substring(1));

                //Calculate the total
                total = raw_price * days;

                //display the total in the Text
                totalText.setText("£ "+(total));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * This function saves customer n the database
     * @param b_id
     */
    public void saveCustomer( String b_id){

        //The address of customer
        String addr = address.getText();

        //Customer Name
        String name = names.getText();

        //Query to insert data
        String SQL1 = "INSERT INTO `customer` VALUES(NULL,?,?)";

        try {

            //intialises the  connection
            con = new DbConnect().getDbConnect();
            con.setAutoCommit(false); //

            //Execute the SQL
            PreparedStatement preparedStatement1 = new DbConnect().getDbConnect().prepareStatement(SQL1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setString(1, name);
            preparedStatement1.setString(2, addr);
            preparedStatement1.executeUpdate();

            //Get the id of the customer
            ResultSet rs = preparedStatement1.getGeneratedKeys();
            if(rs.next()) {

                //The id of the created customer
                int customer_id = rs.getInt(1);
                con.commit(); //Commit the Changes

                //Save the hire
                saveHire(b_id,customer_id);
            }else{
               con.rollback();
            }

        //handles all the error the application generates
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

    /**
     * S
     * @param b_id
     * @param customer_id
     */
    public void saveHire(String b_id, int customer_id){

        String SQL1 = "INSERT INTO `hires` VALUES(?,?,?,?,?,?,?,?)";

        try {

            //Get the database object
            con = new DbConnect().getDbConnect();
            con.setAutoCommit(false); //

            //retrieves cash from cash field
            double cash = Double.parseDouble(cashField.getText().toString());

            //Calculates the dates difference
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            java.util.Date firstDate = sdf.parse(start.getValue().toString());
            java.util.Date secondDate = sdf.parse( to.getValue().toString());
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());

            //Get the difference in days between dates
            long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            //Generate random barcode
            Random rand = new Random();
            int barcode = rand.nextInt(1000000000);

            //Save the data in the database
            PreparedStatement preparedStatement1 = con.prepareStatement(SQL1);
            preparedStatement1.setString(1, null);
            preparedStatement1.setInt(2, customer_id);
            preparedStatement1.setString(3, b_id);
            preparedStatement1.setInt(4, Math.toIntExact(days));
            preparedStatement1.setDouble(5, cash);
            preparedStatement1.setInt(6, barcode);
            preparedStatement1.setString(7, start.getValue().toString());
            preparedStatement1.setString(8, to.getValue().toString());
            preparedStatement1.executeUpdate();
            con.commit(); //Commit the Changes

            con.prepareStatement("UPDATE bike SET `status` = 2 WHERE bike_id = "+b_id).executeUpdate();
            con.commit();

             JOptionPane.showMessageDialog(null, "Successfully Saved Ticket ! ","Book",JOptionPane.PLAIN_MESSAGE);
			 //new DashboardController().reload();
             BookButtonCell.close();

            //After updating launches the dasboard with updated information
            Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root, 993, 561));
            primaryStage.centerOnScreen();
            primaryStage.show();

            //Catch any errors you get
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
