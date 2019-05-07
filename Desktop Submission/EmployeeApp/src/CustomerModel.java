import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Zahoor
 */

/**
 * Customer Model holds the structure of a customer object to display in the customer table, constructor initialises the object and getters to retrieve the data
 */
public  class CustomerModel {

    private StringProperty id;
    private StringProperty name;
    private StringProperty address;

    /**
     * Constructor initialises the Customer Model
     * @param id
     * @param name - name of customer
     * @param address
     */
    public CustomerModel(String id, String name, String address) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty addressProperty() {
        return address;
    }

}
