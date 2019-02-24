/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.desktop.app;

import javafx.beans.property.IntegerProperty ;
import javafx.beans.property.StringProperty ;
import javafx.beans.property.SimpleIntegerProperty ;
import javafx.beans.property.SimpleStringProperty ;


/**
 *
 * @author sahil
 */
public class Bike {
    private final SimpleIntegerProperty bikeID = new SimpleIntegerProperty(this, "bikeID");
    private final SimpleIntegerProperty status = new SimpleIntegerProperty(this, "status");
    private final SimpleStringProperty location = new SimpleStringProperty(this, "location");

    public IntegerProperty bikeIDProperty() {
    return bikeID;
    }
    
    public final Integer getBikeID() {
        return bikeIDProperty().get();
    }
    
    public final void setBikeID(Integer bikeID) {
        bikeIDProperty().set(bikeID);
    }
    
    
    public IntegerProperty statusProperty() {
    return status;
    }
    
    public final Integer getStatus() {
        return statusProperty().get();
    }
    
    public final void setStatus(Integer status) {
        statusProperty().set(status);
    }
    
    
    public StringProperty locationProperty() {
        return location ;
    }
    public final String getlocation() {
        return locationProperty().get();
    }
    public final void setLocation(String location) {
        locationProperty().set(location);
    }
    
    public Bike() {}

    public Bike(Integer bikeID, Integer status, String location) {
        setBikeID(bikeID);
        setStatus(status);
        setLocation(location);
    }
    
}
