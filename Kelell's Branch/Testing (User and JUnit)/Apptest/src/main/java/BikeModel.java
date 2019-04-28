
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Zahoor
 */

/**
 * Bike Model holds the structure of a bike object to display in the bike table, constructor initialises the object and getters to retrieve the data
 */
public  class BikeModel {

    private StringProperty bike_id;
    private StringProperty status;
    private StringProperty location;
    private StringProperty price;

    /***
     * Bike Model holds the properties of a bike, initialises using constructor and getters to retrieve the values
     * @param id
     * @param status
     * @param location
     * @param price
     */
    public BikeModel(String id, String status, String location, String price) {

        this.bike_id = new SimpleStringProperty(id);

        if(status.equals("1")){
            this.status = new SimpleStringProperty("Free");
        }else{
            this.status = new SimpleStringProperty("Hired");
        }

        this.location = new SimpleStringProperty(location);
        this.price = new SimpleStringProperty("£"+price);

    }

    public StringProperty idProperty() {
        return bike_id;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty priceProperty() {
        return price;
    }
}
