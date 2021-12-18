package org.but.feec.airport.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonBasicView {
    StringProperty name= new SimpleStringProperty();
    StringProperty surname= new SimpleStringProperty();
    StringProperty date_of_birth= new SimpleStringProperty();
    LongProperty passportNumber= new SimpleLongProperty();
    StringProperty residence= new SimpleStringProperty();

    public String getName() {
        return nameProperty().get();
    }
    public void setName(String name) {
        this.nameProperty().setValue(name);
    }
    public String getSurname() {
        return surnameProperty().get();
    }
    public void setSurname(String surname) {
        this.surnameProperty().setValue(surname);
    }
    public String getDate_of_birth() {
        return date_of_birthProperty().get();
    }
    public void setDate_of_birth(String date_of_birth) {
        this.surnameProperty().setValue(date_of_birth);
    }
    public Long getPassportNumber() {
        return passportNumberProperty().get();
    }
    public void setPassportNumber(Long passportNumber) {
        this.passportNumberProperty().setValue(passportNumber);
    }
    public String getResidence() {
        return residenceProperty().get();
    }
    public void setResidence(String residence) {
        this.residenceProperty().setValue(residence);
    }

    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty surnameProperty() {
        return surname;
    }
    public StringProperty date_of_birthProperty() {
        return date_of_birth;
    }
    public LongProperty passportNumberProperty() {
        return passportNumber;
    }
    public StringProperty residenceProperty() {
        return residence;
    }}