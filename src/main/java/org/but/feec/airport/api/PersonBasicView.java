package org.but.feec.airport.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonBasicView {
    StringProperty first_name= new SimpleStringProperty();
    StringProperty last_name = new SimpleStringProperty();
    LongProperty passport_number = new SimpleLongProperty();
    StringProperty country_of_residence = new SimpleStringProperty();
    StringProperty comments = new SimpleStringProperty();

    public String getFirst_name() {
        return first_nameProperty().get();
    }
    public void setFirst_name(String first_name) {
        this.first_nameProperty().setValue(first_name);
    }
    public String getLast_name() {
        return last_nameProperty().get();
    }
    public void setLast_name(String last_name) {
        this.last_nameProperty().setValue(last_name);
    }
    public long getPassport_number() {
        return passport_numberProperty().get();
    }
    public void setPassport_number(Long passport_number) {
        this.passport_numberProperty().setValue(passport_number);
    }
    public String getCountry_of_residence() {
        return country_of_residenceProperty().get();
    }
    public void setCountry_of_residence(String country_of_residence) {this.country_of_residenceProperty().setValue(country_of_residence);}
    public String getComments() {
        return commentsProperty().get();
    }
    public void setComments(String comments) {
        this.last_nameProperty().setValue(comments);
    }

    public StringProperty first_nameProperty() {
        return first_name;
    }
    public StringProperty last_nameProperty() {
        return last_name;
    }
    public LongProperty passport_numberProperty() {
        return passport_number;
    }
    public StringProperty country_of_residenceProperty() {
        return country_of_residence;
    }
    public StringProperty commentsProperty() {
        return comments;
    }}