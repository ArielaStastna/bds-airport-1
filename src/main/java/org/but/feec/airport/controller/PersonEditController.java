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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.but.feec.airport.api.PersonBasicView;
import org.but.feec.airport.api.PersonEditView;
import org.but.feec.airport.data.PersonRepository;
import org.but.feec.airport.service.PersonService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class PersonEditController {
    private static final Logger logger = LoggerFactory.getLogger(PersonEditController.class);

    @FXML
    public Button editButton;
    @FXML
    public TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField passportTextField;
    @FXML
    private TextField residenceTextField;
    @FXML
    private TextField idTextField;

    private PersonService personService;
    private PersonRepository personRepository;
    private ValidationSupport validation;
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        personRepository = new PersonRepository();
        personService = new PersonService(personRepository);

        validation = new ValidationSupport();
        validation.registerValidator(nameTextField, Validator.createEmptyValidator("Cell name must not be empty."));
        validation.registerValidator(surnameTextField, Validator.createEmptyValidator("Cell surname must not be empty."));
        validation.registerValidator(passportTextField, Validator.createEmptyValidator("Cell passport number must not be empty."));
        validation.registerValidator(residenceTextField, Validator.createEmptyValidator("Cell country of residence must not be empty."));
        validation.registerValidator(idTextField, Validator.createEmptyValidator("Cell id must not be empty."));
        idTextField.setEditable(false);

        editButton.disableProperty().bind(validation.invalidProperty());

        loadPersonsData();

        logger.info("PersonsEditController initialized");
    }

    /**
     * Load passed data from Persons controller. Check this tutorial explaining how to pass the data between controllers: https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8
     */
    private void loadPersonsData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof PersonBasicView) {
            PersonBasicView personBasicView = (PersonBasicView) stage.getUserData();
            passportTextField.setText(personBasicView.getPassport_number());
            idTextField.setText(String.valueOf(personBasicView.getId()));
            surnameTextField.setText(personBasicView.getLast_name());
            nameTextField.setText(personBasicView.getFirst_name());
            residenceTextField.setText(personBasicView.getCountry_of_residence());

        }
    }

    @FXML
    public void handleEditButton(ActionEvent event) {
        String passport_number = passportTextField.getText();
        String last_name = surnameTextField.getText();
        String first_name = nameTextField.getText();
        String country_of_residence = residenceTextField.getText();
        Long id = Long.valueOf(idTextField.getText());

        PersonEditView personEditView = new PersonEditView();
        personEditView.setId(id);
        personEditView.setPassport_number(passport_number);
        personEditView.setCountry_of_residence(country_of_residence);
        personEditView.setFirst_name(first_name);
        personEditView.setLast_name(last_name);


        personService.editPerson(personEditView);

        personEditedConfirmationDialog();
    }

    private void personEditedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Person Edited Confirmation");
        alert.setHeaderText("Your person was successfully edited.");

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
