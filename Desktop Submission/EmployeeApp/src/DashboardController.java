
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 * @author Zahoor
 */
public class DashboardController implements Initializable {

    private static Connection con;

    @FXML
    VBox vBox;

    /*
      Customer Tab
     */
    private ObservableList<CustomerModel> customerData;
    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn printColumn;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchCombo;

    /*
      Bike Tab
     */
    private ObservableList<BikeModel> bikeData;
    private ObservableList<BikeModel> bikeBookedData;
    @FXML
    private TableView bikeTable;
    @FXML
    private TableColumn bike_idColumn;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn locationColumn;
    @FXML
    private TableColumn bookColumn;
    @FXML
    private TextField searchField_b;
    @FXML
    private ComboBox<String> searchCombo_b;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button manageBikesBtn;

    /*
      Ticket Tab
     */
    private ObservableList<TicketModel> ticketData;
    @FXML
    private TableView ticketTable;
    @FXML
    private TableColumn ticket_idColumn;
    @FXML
    private TableColumn customerID;
    @FXML
    private TableColumn customerName;
    @FXML
    private TableColumn t_bike_idColumn;
    @FXML
    private TableColumn t_priceColumn;
    @FXML
    private TableColumn startDateColumn;
    @FXML
    private TableColumn endDateColumn;
    @FXML
    private TableColumn totalColumn;
    @FXML
    private TextField searchField_t;
    @FXML
    private ComboBox<String> searchCombo_t;

    protected static Stage primaryStage;

    private SortedList<BikeModel> sortedData;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        customerPaneBuild();
        bikePaneBuild();
        ticketPaneBuild();
    }

    /*
      Handles All Customer Data and displays in a table
     */
    private void customerPaneBuild() {

        //Initialise the combo box with items
        searchCombo.getItems().setAll("BY NAME", "BY ADDRESS", "BY ID");

        //Let first item be selected at start
        searchCombo.getSelectionModel().selectFirst();

        try {

            con = new DbConnect().getDbConnect();

            //Initialises a list to hold table data
            customerData = FXCollections.observableArrayList();

            //Retrieves the customer data from the database
            ResultSet rs1 = con.createStatement().executeQuery("SELECT * FROM `customer`");

            //Populates the data from the database to the list to display on the customer table
            while (rs1.next()) {
                customerData.add(new CustomerModel(rs1.getString("CUSTOMER_ID"), rs1.getString("CUSTOMER_NAME"), rs1.getString("CUSTOMER_ADDRESS")));
            }

            //initialises the customer table columns
            idColumn.setCellValueFactory(new PropertyValueFactory("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
            addressColumn.setCellValueFactory(new PropertyValueFactory("address"));

            // 1. Wrap the ObservableList in a FilteredList (initially display all customerData).
            FilteredList<CustomerModel> filteredData = new FilteredList<>(customerData, p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(customer -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every customer with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    switch (searchCombo.getSelectionModel().getSelectedItem()) {
                        case "BY NAME":
                            if (customer.nameProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Name.
                            }
                            break;
                        case "BY ADDRESS":
                            if (customer.addressProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Address.
                            }
                            break;
                        case "BY ID":
                            if (customer.idProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches ID
                            }
                            break;
                        default:
                            if (customer.nameProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Name
                            }
                            break;

                    }
                    return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<CustomerModel> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(customerTable.comparatorProperty());

            // 5. Add sorted (and filtered) customerData to the table.
            customerTable.setItems(sortedData);

            customerTable.setRowFactory(tv -> {
                TableRow<CustomerModel> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        CustomerModel customer = row.getItem();

                        String id = customer.idProperty().getValue();
                        String names = customer.nameProperty().getValue();
                        String address = customer.addressProperty().getValue();

                        //close the dashboard
                        BookButtonCell.close();
                        close();
                        new LoginController().close();
                        new UpdateCustomerController().close();

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/updateDetails.fxml"));

                        Parent root2 = null;
                        try {
                            root2 = (Parent) fxmlLoader.load();
                            UpdateCustomerController controller = fxmlLoader.<UpdateCustomerController>getController();
                            controller.setData(id, names, address);
                            Scene scene = new Scene(root2);
                            primaryStage = new Stage();
                            primaryStage.setScene(scene);
                            //Displays App Name 
                            primaryStage.setTitle("Bike World");
                            // Displays App Icon
                            primaryStage.getIcons().add(new Image("/CSS/icon2.png"));
                            primaryStage.show();
                            
                            // close dashboard
                            Stage stage = (Stage) customerTable.getScene().getWindow();
                            stage.close();

                      
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    /*
      Handles All Bikes Data and displays in a table
     */
    private void bikePaneBuild() {

        //Initialise the combo box with list items
        searchCombo_b.getItems().setAll("BY BIKE ID", "BY LOCATION", "BY PRICE");
        searchCombo_b.getSelectionModel().selectFirst();
        statusCombo.getItems().setAll("ALL", "FREE", "HIRED", "DAMAGED");
        statusCombo.getSelectionModel().selectFirst();

        manageBikesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                BookButtonCell.close();
                close();

                //Launches bike manager
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/bikeManager.fxml"));

                        Parent root = null;
                        try {
                            root = (Parent) fxmlLoader.load();
                            Scene scene = new Scene(root);
                            primaryStage = new Stage();
                            primaryStage.setScene(scene);
                            //Displays App Name 
                            primaryStage.setTitle("Bike World");
                            // Displays App Icon
                            primaryStage.getIcons().add(new Image("/CSS/icon2.png"));
                            primaryStage.show();
                            // close dashboard
                            Stage stage = (Stage) customerTable.getScene().getWindow();
                            stage.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                
                
                try {
                    refresh();
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        try {

             refresh();
            //initialise the bike table columns
            bike_idColumn.setCellValueFactory(new PropertyValueFactory("id"));
            statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
            locationColumn.setCellValueFactory(new PropertyValueFactory("location"));
            priceColumn.setCellValueFactory(new PropertyValueFactory("price"));

            // Table cell coloring
            statusColumn.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
                @Override
                public TableCell<String, String> call(TableColumn<String, String> param) {
                    return new TableCell<String, String>() {

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {

                                // Get fancy and change color based on data
                                if (item.equals("Hired")) {
                                    this.setTextFill(Color.RED);
                                } else {
                                    if (item.equals("Free")) {
                                        this.setTextFill(Color.GREEN);
                                    } else {
                                        if (item.equals("Damaged")) {
                                            this.setTextFill(Color.ORANGE);
                                        }
                                    }
                                }
                                setText(item);

                            } else {
                                setText("");
                            }
                        }
                    };
                }
            });

            //Book column needs a custom class BookButtonCell to be able to display a button on the column
            bookColumn.setCellFactory(BookButtonCell.<BikeModel>forTableColumn());

            bookColumn.setText("");

            // 1. Wrap the ObservableList in a FilteredList (initially display all bikeData).
            FilteredList<BikeModel> filteredData = new FilteredList<>(bikeData, p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            searchField_b.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(bike -> {
                    // If filter text is empty, display all bikes.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare value of customer with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    switch (searchCombo_b.getSelectionModel().getSelectedItem()) {
                        case "BY BIKE ID":
                            if (bike.idProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Bike ID
                            }
                            break;
                        case "BY STATUS":
                            if (bike.statusProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Status
                            }
                            break;
                        case "BY LOCATION":
                            if (bike.locationProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Location.
                            }
                        case "BY PRICE":
                            if (bike.priceProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Price.
                            }
                            break;
                        default:
                            if (bike.idProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Bike ID.
                            }
                            break;

                    }

                    return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(bikeTable.comparatorProperty());

            // 5. Add sorted (and filtered) bikeData to the table.
            bikeTable.setItems(sortedData);

            //Date Constraints
            statusCombo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    String currentStatus = statusCombo.getSelectionModel().getSelectedItem();
                    FilteredList<BikeModel> filteredData = new FilteredList<>(sortedData, p -> true);
                    filteredData.setPredicate(bike -> {

                        // If filter text is empty, display all bikes
                        if (bike.idProperty().getValue().trim().equals("")) {
                            return false;
                        } else {
                            if (currentStatus.equals("ALL")) {
                                return true;
                            } else if (currentStatus.toLowerCase().equals(bike.statusProperty().getValue().toLowerCase())) {
                                return true;
                            }
                        }

                        return false;
                    });

                    // 3. Wrap the FilteredList in a SortedList.
                    SortedList<BikeModel> sortedData = new SortedList<>(filteredData);
                    // 4. Bind the SortedList comparator to the TableView comparator.
                    // 	  Otherwise, sorting the TableView would have no effect.
                    sortedData.comparatorProperty().bind(bikeTable.comparatorProperty());
                    // 5. Add sorted (and filtered) bikeData to the table.
                    bikeTable.setItems(sortedData);

                }

            });

            endDate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ResultSet bikesBooked;
                    bikeBookedData = FXCollections.observableArrayList();

                    //query which gets bikes which are occupied within a given time frame
                    String dateConstrainedQuery = "SELECT bike.BIKE_ID, bike.STATUS, bike.LOCATION, bike.PRICE FROM bike INNER JOIN hires ON hires.BIKE_ID = bike.BIKE_ID WHERE (START_DATE <= ('" + startDate.getValue() + "') AND END_DATE >= ('" + startDate.getValue() + "')) OR (START_DATE <= ('" + endDate.getValue() + "') AND END_DATE >= ('" + endDate.getValue() + "'))";
                    System.err.println("Selected date: " + dateConstrainedQuery);

                    try {
                        bikesBooked = con.createStatement().executeQuery(dateConstrainedQuery);

                        //populate list with sql data
                        while (bikesBooked.next()) {
                            bikeBookedData.add(new BikeModel(bikesBooked.getString("BIKE_ID"), bikesBooked.getString("STATUS"), bikesBooked.getString("LOCATION"), String.format("%.2f", bikesBooked.getDouble("PRICE"))));
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //if the booked bike is in the current (active) data set then filter it out
                    filteredData.setPredicate(bike -> {
                        for (BikeModel bikes : bikeBookedData) {
                            if (bike.idProperty().getValue().equals(bikes.idProperty().getValue())) {
                                return false;
                            }
                        }
                        return true;

                    });

                    // 3. Wrap the FilteredList in a SortedList.
                    SortedList<BikeModel> sortedData = new SortedList<>(filteredData);
                    // 4. Bind the SortedList comparator to the TableView comparator.
                    // 	  Otherwise, sorting the TableView would have no effect.
                    sortedData.comparatorProperty().bind(bikeTable.comparatorProperty());
                    // 5. Add sorted (and filtered) bikeData to the table.
                    bikeTable.setItems(sortedData);

                }

            });

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    //refresh the Bikes table
    private void refresh() throws SQLException{
        
          //get DB Object
            con = new DbConnect().getDbConnect();

            //Create a list to store bike data
            bikeData = FXCollections.observableArrayList();
            bikeData.clear();
            //Fetches data from the bike database
            ResultSet rs1 = con.createStatement().executeQuery("SELECT * FROM `bike`");

            //Populates the data from the database to the list to display on the bike table
            while (rs1.next()) {
                bikeData.add(new BikeModel(rs1.getString("BIKE_ID"), rs1.getString("STATUS"), rs1.getString("LOCATION"), String.format("%.2f", rs1.getDouble("PRICE"))));
            }
        bikeTable.setItems(bikeData);
    }

    /*
      Handles All Ticket Data and displays in a table
     */
    private void ticketPaneBuild() {

        //initiliase the combo box on the ticket panel
        searchCombo_t.getItems().setAll("BY TICKET ID", "BY CUSTOMER NAME", "BY BIKE ID", "BY CUSTOMER ID");
        searchCombo_t.getSelectionModel().selectFirst();

        try {

            con = new DbConnect().getDbConnect();

            // A list to store ticket data fromm DB and display on the table
            ticketData = FXCollections.observableArrayList();

            //Retreives data from the ticket table
            ResultSet rs1 = con.createStatement().executeQuery("SELECT *,TRUNCATE((days * price),2)  as total, DATE_FORMAT(start_date, '%d/%m/%Y') as start, DATE_FORMAT(end_date, '%d/%m/%Y') as end from hires LEFT JOIN bike ON hires.bike_id = bike.BIKE_ID LEFT JOIN customer ON customer.CUSTOMER_ID = hires.customer_id");

            //Populates the data from the database to the list to display on the ticket table
            while (rs1.next()) {
                ticketData.add(new TicketModel(rs1.getString("hire_id"), rs1.getString("customer_id"), rs1.getString("bike_id"),
                        rs1.getString("CUSTOMER_NAME"), rs1.getString("start"), rs1.getString("end"),
                        String.format("%.2f", rs1.getDouble("price")), String.format("%.2f", rs1.getDouble("total"))));
            }

            //Initialise the columns on the ticket table
            ticket_idColumn.setCellValueFactory(new PropertyValueFactory("id"));
            customerID.setCellValueFactory(new PropertyValueFactory("customer_id"));
            t_bike_idColumn.setCellValueFactory(new PropertyValueFactory("bike_id"));
            customerName.setCellValueFactory(new PropertyValueFactory("customerName"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory("endDate"));
            t_priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
            totalColumn.setCellValueFactory(new PropertyValueFactory("total"));

            //Print Column has a button, needs a custom class to allow that
            printColumn.setCellFactory(PrintButtonCell.<TicketModel>forTableColumn());

            // 1. Wrap the ObservableList in a FilteredList (initially display all ticket Data).
            FilteredList<TicketModel> filteredData = new FilteredList<>(ticketData, p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            searchField_t.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(ticket -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare value of every ticket  with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    switch (searchCombo_t.getSelectionModel().getSelectedItem()) {
                        case "BY TICKET ID":
                            if (ticket.idProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter Ticket ID
                            }
                            break;
                        case "BY CUSTOMER NAME":
                            if (ticket.customerNameProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Address.
                            }
                            break;
                        case "BY BIKE ID":
                            if (ticket.bike_idProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches ID
                            }
                            break;
                        case "BY CUSTOMER ID":
                            if (ticket.customer_idProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches ID
                            }
                            break;
                        default:
                            if (ticket.idProperty().toString().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches Ticket ID
                            }
                            break;
                    }

                    return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<TicketModel> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no affect.
            sortedData.comparatorProperty().bind(ticketTable.comparatorProperty());

            // 5. Add sorted (and filtered) customerData to the table.
            ticketTable.setItems(sortedData);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void close() {
        if (primaryStage != null) {
            primaryStage.close();
        }
    }
}
