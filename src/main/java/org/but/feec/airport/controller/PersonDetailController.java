package org.but.feec.airport.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.but.feec.airport.api.PersonDetailView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonDetailController {
    private static final Logger logger = LoggerFactory.getLogger(PersonDetailController.class);

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField passportTextField;

    @FXML
    private TextField residenceTextField;

    @FXML
    private TextField citizenshipTextField;

    @FXML
    private TextField certificateTextField;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        idTextField.setEditable(false);
        nameTextField.setEditable(false);
        surnameTextField.setEditable(false);
        passportTextField.setEditable(false);
        residenceTextField.setEditable(false);
        citizenshipTextField.setEditable(false);
        certificateTextField.setEditable(false);


        loadPersonsData();

        logger.info("PersonsDetailController initialized");
    }

    private void loadPersonsData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof PersonDetailView) {
            PersonDetailView personBasicView = (PersonDetailView) stage.getUserData();
            idTextField.setText(String.valueOf(personBasicView.getId()));
            nameTextField.setText(personBasicView.getFirst_name());
            surnameTextField.setText(personBasicView.getLast_name());
            passportTextField.setText(personBasicView.getPassport_number());
            residenceTextField.setText(personBasicView.getCountry_of_residence());
            citizenshipTextField.setText(personBasicView.getCountry_of_citizenship());
            certificateTextField.setText(personBasicView.getValid());
        }
    }
}
