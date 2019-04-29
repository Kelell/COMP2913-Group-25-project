import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Zahoor
 */

/**
 * Ticket Model holds the structure of a ticket object to display in the ticket table, constructor initialises the object and getters to retrieve the data
 */
public  class TicketModel {

    private StringProperty id;
    private StringProperty customer_id;
    private StringProperty bike_id;
    private StringProperty customerName;
    private StringProperty startDate;
    private StringProperty endDate;
    private StringProperty price;
    private StringProperty total;

    /**
     * Constructor initialises the ticket object
     * @param id
     * @param customer_id
     * @param bike_id
     * @param customerName
     * @param startDate
     * @param endDate
     * @param price
     * @param total
     */
    public TicketModel(String id, String customer_id, String bike_id, String customerName, String startDate, String endDate, String price, String total) {
        this.id = new SimpleStringProperty(id);
        this.customer_id = new SimpleStringProperty(customer_id);
        this.bike_id = new SimpleStringProperty(bike_id);
        this.customerName = new SimpleStringProperty(customerName);
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
        this.price = new SimpleStringProperty("£"+price);
        this.total = new SimpleStringProperty("£"+total);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty customer_idProperty() {
        return customer_id;
    }

    public StringProperty bike_idProperty() {
        return bike_id;
    }

    public StringProperty totalProperty() {
        return total;
    }
    public StringProperty customerNameProperty() {
        return customerName;
    }
    public StringProperty startDateProperty() {
        return startDate;
    }
    public StringProperty endDateProperty() {
        return endDate;
    }
    public StringProperty priceProperty() {
        return price;
    }
}
