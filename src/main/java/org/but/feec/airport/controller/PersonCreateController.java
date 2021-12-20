package org.but.feec.airport.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.but.feec.airport.api.PersonCreateView;
import org.but.feec.airport.data.PersonRepository;
import org.but.feec.airport.service.PersonService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PersonCreateController {
    private static final Logger logger = LoggerFactory.getLogger(PersonCreateController.class);

    @FXML
    public Button createButton;
    @FXML
    private TextField newName;

    @FXML
    private TextField newSurname;


    @FXML
    private TextField newCertificate;


    private PersonService personService;
    private PersonRepository personRepository;
    private ValidationSupport validation;

    @FXML
    public void initialize() {
        personRepository = new PersonRepository();
        personService = new PersonService(personRepository);

        validation = new ValidationSupport();
        validation.registerValidator(newName, Validator.createEmptyValidator("The email must not be empty."));
        validation.registerValidator(newSurname, Validator.createEmptyValidator("The first name must not be empty."));
        validation.registerValidator(newCertificate, Validator.createEmptyValidator("The nickname must not be empty."));


        createButton.disableProperty().bind(validation.invalidProperty());

        logger.info("PersonCreateController initialized");
    }

    @FXML
    void handleCreateNewPerson(ActionEvent event) {
        String first_name = newName.getText();
        String last_name = newSurname.getText();
        String valid = newCertificate.getText();


        PersonCreateView personCreateView = new PersonCreateView();
        personCreateView.setFirst_name(first_name);
        personCreateView.setLast_name(last_name);
        personCreateView.setValid(valid);


        personService.createPerson(personCreateView);

        personCreatedConfirmationDialog();
    }

    private void personCreatedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Person Created Confirmation");
        alert.setHeaderText("Your person was successfully created.");

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();
        Optional<ButtonType> result = alert.showAndWait();
    }

}

