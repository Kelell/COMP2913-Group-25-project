import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
    @FXML ComboBox<String> customerCombo;
    @FXML Button cancelBtn;

    private double total = 0;
    private long days_p,days_p2;

    //Database connection
    private static Connection con;
    private String currentId = null;
    private static Stage primaryStage;
    private static Stage stage;

    //Initialises the BikeModel from the dashboard Bike Table
    public void setBikeDetails(BikeModel bikeDetails) {

        this.bikeDetails = bikeDetails;
        initStuff();
    }

    public void initStuff() {

        con = DbConnect.getDbConnect();

        String b_id = bikeDetails.idProperty().getValue();
        String pric = bikeDetails.priceProperty().getValue();

        //Sets the bike id and price passed from the table
        bike_id.setText(b_id);
        price.setText(pric);

        // force the field to be numeric only
        cashField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cashField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage = (Stage) cashField.getScene().getWindow();

            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                BookButtonCell.close();
                close();

                //After updating launches the dasboard with updated information
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

                    //if the total value of hire is greater than 0, it means duration and price has been set
                    if(total > 0){

                        //Calculate change
                        double change = cash - total;

                        //display the change in the text
                        changeText.setText("£ "+ String.format("%.2f", change));
                    }
                }
            }
        });

        //Retrieves the customer data from the database
        ResultSet rs1 = null;
        try {
            rs1 = con.createStatement().executeQuery("SELECT * FROM `customer`");

            customerCombo.getItems().add(" | New Customer");
            //Let first item be selected at start
            customerCombo.getSelectionModel().selectFirst();

            //Populates the data from the database to the list to display on the customer table
            while (rs1.next()) {
                customerCombo.getItems().add(rs1.getString("CUSTOMER_ID")+" | "+ rs1.getString("CUSTOMER_NAME"));
            }

            customerCombo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    String selectedItem = customerCombo.getSelectionModel().getSelectedItem();
                    String [] arr = selectedItem.split(" | ");
                    String id = arr[0];

                    if(id.trim().equals("")){
                        currentId = null;
                        System.out.println("new customer");
                        names.setText(" ");
                        address.setText(" ");
                    }else{
                        currentId = id;
                        getCustomer(id);
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
             * Now we calculate the time difference between the dates
             */

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

            Date currentDate = new Date();

            Date firstDate = null;
            try {

                LocalDate sld = start.getValue();
                Calendar c1 = Calendar.getInstance();

                c1.set(sld.getYear(), sld.getMonthValue() -1,sld.getDayOfMonth());
                firstDate = c1.getTime();

                LocalDate seld = to.getValue();
                Calendar c2 = Calendar.getInstance();
                c2.set(seld.getYear(), seld.getMonthValue() -1,seld.getDayOfMonth());

                java.util.Date secondDate = c2.getTime();

                long diff = (firstDate.getTime() - currentDate.getTime());
                long diff2 = (secondDate.getTime() - currentDate.getTime());

                 days_p = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) ) ;
                 days_p2 = (TimeUnit.DAYS.convert(diff2, TimeUnit.MILLISECONDS)) ;

                System.out.println("diff first "+days_p);
                System.out.println("diff second "+days_p2);

                if(days_p < 0 || days_p2 < 0){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Invalid Date");
                    alert.setContentText("Cannot set date in the past !");
                    alert.show();

                }

                //Get the value of the diff between dates
                long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());

                //The number of days difference
                long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                //Get the current price of the bike
                String pric = bikeDetails.priceProperty().getValue();

                //convert it to a double
                double raw_price = Double.parseDouble(pric.substring(1));

                //Calculate the total
                total = raw_price * days;

                //display the total in the Text
                totalText.setText("£ "+ String.format("%.2f",total));

            } catch (Exception e) {
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
        String SQL1 = "INSERT INTO `customer` VALUES(?,?,?)";

        try {

            //intialises the  connection
            con = DbConnect.getDbConnect();
            con.setAutoCommit(false); //

            ResultSet rs2 = con.createStatement().executeQuery("SELECT * FROM `customer` WHERE CUSTOMER_ID = '"+currentId+"'");
            if (rs2.next()) {
                if(days_p < 0 || days_p2 < 0){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Invalid Date");
                    alert.setContentText("Cannot set date in the past  !");
                    alert.show();

                }else{
                    saveHire(b_id,Integer.parseInt(currentId));
                }
                return;
            }

            //Execute the SQL
            PreparedStatement preparedStatement1 = new DbConnect().getDbConnect().prepareStatement(SQL1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setString(1, currentId);
            preparedStatement1.setString(2, name);
            preparedStatement1.setString(3, addr);
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
     * @param b_id
     * @param customer_id
     */
    public void saveHire(String b_id, int customer_id){

        String SQL1 = "INSERT INTO `hires` VALUES(?,?,?,?,?,?,?,?)";

        try {

            //Get the database object
            con = DbConnect.getDbConnect();
            con.setAutoCommit(false); //

            //Retrieves cash from cash field
            double cash = Double.parseDouble(cashField.getText());
            if(Double.parseDouble(changeText.getText().trim().replace("£ ","")) < 0){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Insufficient Funds");
                alert.setContentText("You need to add more money to proceed !");
                alert.show();

                return;
            }

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

            con.prepareStatement("UPDATE bike SET `STATUS` = 2 WHERE bike_id = "+b_id).executeUpdate();
            con.commit();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Success");
                alert.setContentText("Sucessfully Saved Ticket!");
                alert.showAndWait();
                
            BookButtonCell.close();
            close();
            System.out.println("load dashboard");

            //After updating launches the dasboard with updated information
            Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
            primaryStage = new Stage();
            primaryStage.setScene(new Scene(root, 1200, 561));
            primaryStage.centerOnScreen();
            primaryStage.show();

          //Catch any errors you get
        } catch (Exception e) {
            try {
                con.rollback(); //Rollback
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            try {
                con.setAutoCommit(true); //Set Autocommit to true
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCustomer(String id){
        //Retrieves the customer data from the database
        ResultSet rs1 = null;
        try {
            rs1 = con.createStatement().executeQuery("SELECT * FROM `customer` WHERE CUSTOMER_ID = '"+id+"'");
            //Populates the data from the database to the list to display on the customer table
            if (rs1.next()) {
                String name = rs1.getString("CUSTOMER_NAME");
                String addr = rs1.getString("CUSTOMER_ADDRESS");

                //Set customer details
                names.setText(name);
                address.setText(addr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if(primaryStage != null){
            primaryStage.close();
        }
    }
}
