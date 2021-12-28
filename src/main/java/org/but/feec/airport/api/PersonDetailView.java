package org.but.feec.airport.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonDetailView {
    private LongProperty passenger_id = new SimpleLongProperty();
    private StringProperty first_name = new SimpleStringProperty();
    private StringProperty last_name = new SimpleStringProperty();
    private StringProperty passport_number = new SimpleStringProperty();
    private StringProperty country_of_residence = new SimpleStringProperty();
    private StringProperty country_of_citizenship = new SimpleStringProperty();
    private StringProperty check_results = new SimpleStringProperty();

    public Long getPassenger_id() {
        return passenger_idProperty().get();
    }

    public void setPassenger_id(Long id) {
        this.passenger_idProperty().setValue(id);
    }

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

    public String getPassport_number(){ return passport_numberProperty().get();}
    public void setPassport_number(String passport_number) {
        this.passport_numberProperty().setValue(passport_number);
    }

    public String getCountry_of_residence() {
        return country_of_residenceProperty().get();
    }

    public void setCountry_of_residence(String country_of_residence) {
        this.country_of_residenceProperty().set(country_of_residence);
    }

    public String getCountry_of_citizenship() {
        return country_of_citizenshipProperty().get();
    }

    public void setCountry_of_citizenship(String country_of_citizenship) {
        this.country_of_citizenshipProperty().setValue(country_of_citizenship);
    }

    public String getCheck_results() {
        return check_resultsProperty().get();
    }

    public void setCheck_results(String check_results) {
        this.check_resultsProperty().setValue(check_results);
    }


    public LongProperty passenger_idProperty() {
        return passenger_id;
    }

    public StringProperty first_nameProperty() {
        return first_name;
    }

    public StringProperty last_nameProperty() {
        return last_name;
    }

    public StringProperty passport_numberProperty() {
        return passport_number;
    }

    public StringProperty country_of_residenceProperty() {
        return country_of_residence;
    }

    public StringProperty country_of_citizenshipProperty() {
        return country_of_citizenship;
    }

    public StringProperty check_resultsProperty() {
        return check_results;
    }




}