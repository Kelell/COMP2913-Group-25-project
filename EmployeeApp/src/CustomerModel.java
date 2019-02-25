import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Zahoor
 */
public  class CustomerModel {

    private StringProperty id;
    private StringProperty name;
    private StringProperty address;

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
